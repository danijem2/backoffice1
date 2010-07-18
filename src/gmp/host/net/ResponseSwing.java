package gmp.host.net;



import gmp.net.*;
import gmp.terminal.local.*;
import gmp.ui.*;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;

/** Class represents the response gmp service sent to gmp client*/
public class ResponseSwing implements Response {
    private String rawdata = null;
    private gmprow row;
    public static int MAX_RSP_CODES = 20;
    private ResponseCode [] ResponseCodes;

    private gmpImage Photo     = null; // consumer's photography for authentification
    private gmpfile Acqscript       = null; // acquirer script to be executed on Terminal
    private gmpfile Issscript       = null; // issuer script to be executed on Wallet
    private gmpImage Captcha   = null; //captcha picture for human recognition, mostly for unattended terminals
    private gmpfile Broadcast       = null; // broadcast message from gmp system
    private File Authfile = null; // RFU : alternative authentification file (fingerprint etc ...)
    private File Commercial     = null; // comercial to be distributed (at the end of session)
    
/** Constructs an empty ResponseSwing object */
public ResponseSwing(){
  this.rawdata = new String();
  this.row = new gmprow();
  this.ResponseCodes = new ResponseCode[ResponseSwing.MAX_RSP_CODES];
  }
/** Constructs a ResponseSwing from String. String is treated as rawdata sent
 * from service and it is parsed as gmprow
 * @param l String
 */
public ResponseSwing (String l){
  this.rawdata = l;
  this.row = new gmprow(l);
  this.ResponseCodes = new ResponseCode[ResponseSwing.MAX_RSP_CODES];
  }

/** Copy constructor for Responses
 * @param resp ResponseSwing
 */
public ResponseSwing (ResponseSwing resp){
  this.rawdata = resp.getRawData();
  this.row = new gmprow(resp.getGmprow());
  this.ResponseCodes = resp.getResponseCodes();
  }

public ResponseSwing (gmprow grow){
  this.rawdata = grow.write();
  this.row = new gmprow(grow);
  this.ResponseCodes = new ResponseCode[ResponseSwing.MAX_RSP_CODES];
  }


/** Returns the rawdata response received from service as String
 * @return String
 */


public String getRawData(){ return this.rawdata;}
/** Returns the array of response codes received from service
 * @return ResponseSwing Code []
 */
public ResponseCode [] getResponseCodes(){ return this.ResponseCodes;}
/** Returns the gmprow value of this ResponseSwing object
 * @return gmprow
 */
public gmprow getGmprow(){ return this.row;}
/** Returns the picture of the consumer. Ued in authentification response from
 * GMP Host to attended Terminal
 * @return BufferedImage
 */
public gmpImage getPhoto(){ return this.Photo;}
/** Returns the Commercial that Host sent to Wallet for publishing
 * at the very end of session
 * @return File
 */
public File getCommercial(){ return this.Commercial;}
/** Returns Captcha received in response. Used for unattended Terminals
 * @return BufferedImage
 */
public gmpImage getCaptcha(){ return this.Captcha;}
/** Returns the acquirer script that GMP Host has prepared to be executed
 * on Terminal local system at the end of session. Host sends this in responses to
 * Reconcile and Network requests. This is used only in T2H sessions.
 * @return gmpfile
 */
public gmpfile getAcqscript(){ return this.Acqscript;}
/** Returns the issuer script that GMP Host has prepared to be executed
 * on Wallet local system at the end of session. Host sends this in responses to
 * any message that involves Wallet. This is used in T2H sessions and in requests that
 * involve only Wallet and Host.
 * @return gmpfile
 */
public gmpfile getIssscript(){ return this.Issscript;}
/** Returns the braodcast script that GMP Host has prepared to be executed
 * on Terminals and Walets. Used for urgent massive messages only.
 * @return gmpfile
 */
public gmpfile getBroadcast(){ return this.Broadcast;}
/** Returns the file that GMP Host has prepared for alternative authorization on Terminal
  Host sends this in responses to special merchant requests or for Terminals capable an using alternative
 * authentifications (fingerprint)
 * @return File
 */
public File getAuthfile(){ return this.Authfile;}

/** Sets the Photo of the consumer from String that represents
 * photo's URL. Returns true if successfull.
 * @param photopath String
 * @return boolean
 */
public boolean setPhoto(String photopath){
    boolean ret = false;
    URL urllocal  = null;
    
    this.Photo = null;

    try {
         urllocal = new URL(photopath);
         try{
             BufferedImage bufi = ImageIO.read(urllocal);
             this.Photo = new swImage(null,bufi);
             ret = true;
	     }
	 catch(IOException e) {
		gmpplatform.Log(" setPhoto() Response Error occured : " + e.getMessage());
		}
        }
    catch (MalformedURLException ex) {
            gmpplatform.Log("setPhoto() Error occured : " + ex.getMessage());
        }
    gmpplatform.Log("PHOTO SET");
    return ret;
}
/** Sets the Captcha  from String that represents
 * Captcha's URL. Returns true if successfull.
 * @param captchapath String
 * @return boolean
 */
public boolean setCaptcha(String captchapath){
    boolean ret = false;
    URL urllocal  = null;

    this.Captcha = null;

    try {
         urllocal = new URL(captchapath);
         try{
             BufferedImage bufi = ImageIO.read(urllocal);
             this.Captcha = new swImage(null,bufi);
             ret = true;
	     }
	 catch(IOException e) {
		gmpplatform.Log(" setCaptcha() Error occured : " + e.getMessage());
		}
        }
    catch (MalformedURLException ex) {
            gmpplatform.Log(" setCaptcha()  Error occured : " + ex.getMessage());
        }

    return ret;
}
/** Sets the Commercial from String that represents
 * its URL. Returns true if successfull.
 * @param commercialpath String
 * @return boolean
 */
public boolean setCommercial(String commercialpath){
    boolean ret = false;
    InputStream is;
    FileOutputStream fos;
    this.Commercial = null;
    int oneChar ;

    try {
         is = new URL(commercialpath).openStream();   //urllocal = new URL(commercialpath);
         File f = File.createTempFile("commercial", ".tmp");
         fos = new FileOutputStream(f);
         while ((oneChar=is.read()) != -1)
          {
             fos.write(oneChar);
          }
             fos.close();
             is.close();
             this.Commercial = f;
             ret = true;
	     }
    catch(IOException e) {
		gmpplatform.Log(" setCommercial() Error occured : " + e.getMessage());
		} 
    return ret;
}
/** Sets the Authorisation file from String that represents
 * its URL. Returns true if successfull.
 * @param authfilepath String
 * @return boolean
 */
public boolean setAuthfile(String authfilepath){
    boolean ret = false;
    InputStream is;
    FileOutputStream fos;
    this.Authfile = null;
    int oneChar ;

    try {
         is = new URL(authfilepath).openStream();   //urllocal = new URL(commercialpath);
         File f = File.createTempFile("authfile", ".tmp");
         fos = new FileOutputStream(f);
         while ((oneChar=is.read()) != -1)
          {
             fos.write(oneChar);
          }
             fos.close();
             is.close();
             this.Authfile = f;
             ret = true;
	     }
    catch(IOException e) {
		gmpplatform.Log(" setAuthfile() Error occured : " + e.getMessage());
		}
    return ret;
}
/** Sets the acquirer script from String that represents
 * its URL. Returns true if successfull.
 * @param acqscriptpath String
 * @return boolean
 */
public boolean setAcqscript(String acqscriptpath){
    boolean ret = false;
    URL urllocal  = null;
    InputStream is;
    FileOutputStream fos;
    this.Acqscript = null;
    int oneChar ;

    try {
         is = new URL(acqscriptpath).openStream();   //urllocal = new URL(commercialpath);
         File f = File.createTempFile("acqscript", ".gmp");
         fos = new FileOutputStream(f);
         while ((oneChar=is.read()) != -1)
          {
             fos.write(oneChar);
          }
             fos.close();
             is.close();
             this.Acqscript = new gmpfile(f);
             ret = true;
	     }
    catch(IOException e) {
		gmpplatform.Log(" setAcqscript Error occured : " + e.getMessage());
		}
    return ret;
}

/** Sets the issuer script from String that represents
 * its URL. Returns true if successfull.
 * @param issscriptpath String
 * @return boolean
 */
public boolean setIssscript(String issscriptpath){
    boolean ret = false;
    URL urllocal  = null;
    InputStream is;
    FileOutputStream fos;
    this.Issscript = null;
    int oneChar ;

    try {
         is = new URL(issscriptpath).openStream();   //urllocal = new URL(commercialpath);
         File f = File.createTempFile("issscript", ".gmp");
         fos = new FileOutputStream(f);
         while ((oneChar=is.read()) != -1)
          {
             fos.write(oneChar);
          }
             fos.close();
             is.close();
             this.Issscript = new gmpfile(f);
             ret = true;
	     }
    catch(IOException e) {
		gmpplatform.Log(" setIssscript Error occured : " + e.getMessage());
		}
    return ret;
}
/** Sets the Broadcast from String that represents
 * its URL. Returns true if successfull.
 * @param broadcastpath String
 * @return boolean
 */
public boolean setBroadcast(String broadcastpath){
    boolean ret = false;
    URL urllocal  = null;
    InputStream is;
    FileOutputStream fos;
    this.Broadcast = null;
    int oneChar ;

    try {
         is = new URL(broadcastpath).openStream();   //urllocal = new URL(commercialpath);
         File f = File.createTempFile("issscript", ".gmp");
         fos = new FileOutputStream(f);
         while ((oneChar=is.read()) != -1)
          {
             fos.write(oneChar);
          }
             fos.close();
             is.close();
             this.Broadcast = new gmpfile(f);
             ret = true;
	     }
    catch(IOException e) {
		gmpplatform.Log(" setBraodcast() Error occured : " + e.getMessage());
		}
    return ret;
}

}