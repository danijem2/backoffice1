package gmp.host.net;



import gmp.terminal.local.*;
import gmp.ui.*;

import java.io.File;

/** Interafce represents the response gmp service sent to gmp client
 Because some parts of the resposne are GUI componenets the response
 implementaion depends on particular GUI */
public interface Response {

public String getRawData();

public ResponseCode [] getResponseCodes();

public gmprow getGmprow();
public gmpImage getPhoto();
public File getCommercial();
public gmpImage getCaptcha();
public gmpfile getAcqscript();
public gmpfile getIssscript();
public gmpfile getBroadcast();
public File getAuthfile();
public boolean setPhoto(String photopath);

public boolean setCaptcha(String captchapath);

public boolean setCommercial(String commercialpath);

public boolean setAuthfile(String authfilepath);

public boolean setAcqscript(String acqscriptpath);
public boolean setIssscript(String issscriptpath);
public boolean setBroadcast(String broadcastpath);


}