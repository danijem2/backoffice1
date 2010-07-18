/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gmp.terminal.local;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/** This object represents the whole file where each line in it is a gmpcommand
 */
public class gmpscript extends gmpfile{

    public static final String PENDINGSCRIPTS = ".\\gmpscripts\\pendingscript.gmp";
    public static final String RESULTSCRIPTS = ".\\gmpscripts\\resultscript.gmp";
    public static final String ERRORSCRIPTS  = ".\\gmpscripts\\errorscript.gmp";
    public static final String DONESCRIPTS  = ".\\gmpscripts\\donescript.gmp";

    //private String resultfilename = null;
   // public String getResultFileName(){ return this.resultfilename;}

private gmpfile gmpresultscript = null;
gmpfile getgmpresultscript(){ return this.gmpresultscript;}
private gmpfile gmperrorscript = null;
gmpfile getgmperrorscript(){ return this.gmperrorscript;}
private gmpfile gmpdonescript = null;
gmpfile getgmpdonescript(){ return this.gmpdonescript;}
private gmpfile gmppendingscript = null;
gmpfile getgmppendingscript(){ return this.gmppendingscript;}

public gmpscript(gmpfile gmpf){
 super(gmpf.getFile());
 this.gmpresultscript = new gmpfile(gmpscript.RESULTSCRIPTS);
 this.gmpdonescript = new gmpfile(gmpscript.DONESCRIPTS);
 this.gmperrorscript = new gmpfile(gmpscript.ERRORSCRIPTS);
 this.gmppendingscript = new gmpfile(gmpscript.PENDINGSCRIPTS);
}

 /** Execute the whole gmpfile ( all its lines are treated as gmprow objects)
      * Returns the result, and prepares scriptresult gmpfile
       * from array of gmprow objects
  * @return boolean
      */
public boolean  execute(){
    boolean retn  = true ;
    gmprow ret    = null ;
    gmprow result = null ;
    gmpcommand comm = null;

    // First save the whole dump of gmp script in the PENDINGSCRIPTS file
    this.AppendToFile(this.gmppendingscript);

    BufferedReader in = null;
    String line = null;

    try {
        in = new BufferedReader(new FileReader(this.getFile().getPath()));
        int i = 0;
        while ( ((line = in.readLine()) != null) && i< gmpfile.MAX_GMPROWS_IN_FILE ) {
             ret = new gmprow(line);
             comm = new gmpcommand(ret);
             result  = new gmprow(comm.execute());
             gmpplatform.Log("Executed script |"+comm.write()+"|");
             this.gmpresultscript.add(result);
             this.gmppendingscript.delete(ret.getid()); 
             if( ( (boolean)
                     Boolean.valueOf(result.getValue(gmpcommand.GMPCOMMANDEXECUTIONRESULT)) )
                     == false )
                { // FAILED gmpcommand
                  this.gmperrorscript.add(result);
                  retn = false; // when one failes the script is failed
                }
             else{
                  this.gmpdonescript.add(result);
                 }
             i++;
             }
        }
    catch (FileNotFoundException ex) {
        gmpplatform.Log("gmpscript.execute() File not found exception "+ ex.getMessage());
        }
    catch (IOException ex) {
        gmpplatform.Log("gmpscript.dump() IO exception "+ ex.getMessage());
        }

    return retn;
    }



}
