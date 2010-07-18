package gmp.terminal.local;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/** Class that performs operations with gmpfiles on a local file system */
public class gmpdirectory {

    /**  Method removes all invalid gmprows from the file.
     * Returns true if successfull
     * @param filename String
     * @return boolean
     */
    public boolean compact(String filename){
    boolean ret = true;

    BufferedReader in = null;
    PrintWriter out = null;
    String line= null;
    File fout = null;
    File fin = null;
    gmprow g = null;

    try {
            fout = File.createTempFile("comp", ".tmp");
        } catch (IOException ex) {
            log("gmpdirectory.compact, createTempFile error" + ex.getMessage());
            ret = false;
        }
    fout.deleteOnExit();

    try {
         in = new BufferedReader(new FileReader(filename));
         out = new PrintWriter(fout);

        }
    catch (FileNotFoundException ex) {
         log("gmpdirectory.compact, FileNotFound error" + ex.getMessage());
         ret = false;
        }
     catch (IOException ex) {
         log("gmpdirectory.compact, IOException" + ex.getMessage());
         ret = false;
        }

    try {
           while ((line = in.readLine()) != null) {
               g = new gmprow(line);
               if(g.isValid() && line.length() > 0 ){
                 out.println(line);
               }
           }
           out.flush();
           out.close();
           in.close();
        }
    catch (IOException ex) {
            ret = false;
        }

    if(backup(filename)) {
        fin = new File(filename);
        fin.delete();
        String foutname = fout.getPath();
        if(!copy(foutname,filename)){
            restore(filename);
            ret = false;
            }
       }
    
    return ret;
    }//

    /** Method creates one more copy of the file. The new copy has the same name
     * with extra '.bak' added. It is used as a temporary file.
     * Returns true if copy is created.
     * @param fname
     * @return boolean
     */
    public boolean backup(String fname){
    boolean ret = false;
     if(copy(fname,fname+".bak")) ret = true;
    return ret;
    }

    /** Method restores the file form its backup copy. Backup copy is assumed
     * to have the same name with extra '.bak' added.
     * Returns true if file is restored.
     * @param fname
     * @return boolean
     */
    public boolean restore(String fname){
    boolean ret = false;
     if(copy(fname+".bak",fname)) ret = true;
    return ret;
    }

    /** Method creates one more copy of the file. The new copy is created
     * with a requested name in 'to' parameter
     * Returns true if copy is created.
     * @param from String
     * @param to String
     * @return boolean
     */
    public boolean copy (String from, String to){
    boolean ret = true;
    String line = null;
    BufferedReader in = null;
    PrintWriter out = null;
    gmprow g = null;

    try {
         in = new BufferedReader(new FileReader(from));
         out = new PrintWriter( new FileWriter(to));

         while ((line = in.readLine()) != null) {
            g = new gmprow(line);
            out.println(g.write());
            }
         out.flush();
         out.close();
         in.close();
       }

    catch (FileNotFoundException ex) { 
        log("gmpdirectory.copy(), FileNotFoundException" + ex.getMessage());
        ret = false;
        }
    catch (IOException ex) { 
        log("gmpdirectory.copy(), FileNotFoundException" + ex.getMessage());
        ret = false;
        }
    
    return ret;
    }//


      private int DEBUG = 0;
  private String ClassName = "gmpdirectory";
  private void log(String s){
   if(DEBUG > 200){ System.out.println(this.ClassName+" "+s);} // Screen log
   if(DEBUG > 100){ Util.log(this.ClassName+" "+s);}  // Package log
   if(DEBUG > 0){ gmpplatform.Log(this.ClassName+" "+s);}  // GMP log
  }
  public void setDEBUG(int dbg){ this.DEBUG = dbg;}
  
}
