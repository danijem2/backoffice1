package gmp.terminal.local;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/** The class represents one local gmp file. It contains one gmprow per line.
 It can be used to constructs an array of gmp objects*/
public  class gmpfile {
    
    private File file;
/** Maximal number of rows in local gmpfile */
    public static final int MAX_GMPROWS_IN_FILE = 20000;

    /** Should check if gmprow references some other row. Not implemented. Returns true, always
     * @param g gmprow
     * @return boolean
     */
    public boolean CheckReference(gmprow g){return true;} ;
    /** Should check if gmprow is referenced by some other row. Not implemented. Returns true, always
     * @param id int
     * @return boolean
     */
    public boolean CheckReferenced(int id) { return true;};

    /** Constructs the brand new gmpfile with a requested name
     * It is constructed on homedir directory
     * @param filename String
     */
    public  gmpfile(String filename){ 
        this.file = new File(filename);
    }
    /** Constructs the brand new uninitialized gmpfile
     * It will be set to NULL
     */
     public  gmpfile(){
        this.file = null;
    }
    /** Constructs the brand new gmpfile from File
     * It is constructed on homedir directory
     * @param f File
     */
    public  gmpfile(File f){
        this.file = f;
    }
    /** Returns the file from gmpfile object
     * @return File
     */
    public File getFile() { return this.file; };


    /** Changes one row, its value gets transformed to
     * new one.Row to be changed is identified by id form this gmpfile
     * Returns true if row is successfully changed
     * @param id int
     * @param row gmprow
     * @return boolean
     */
    public boolean change(int id, gmprow row){
    boolean ret = true;

    BufferedReader in = null;
    PrintWriter out = null;
    String line= null;
    File fout = null;
    File fin = null;
    gmprow g = null;

    gmpdirectory gdir = new gmpdirectory();

    String filename = this.getFile().getPath();

    try {
            fout = File.createTempFile("chan", ".tmp");
        } catch (IOException ex) {
            ret = false;
        }
    fout.deleteOnExit();

    try {
         in = new BufferedReader(new FileReader(filename));
         out = new PrintWriter(new FileWriter(fout));

        }
    catch (FileNotFoundException ex) {
         ret = false;
        }
     catch (IOException ex) {
         ret = false;
        }

    try {
           while ((line = in.readLine()) != null) {
               g = new gmprow(line);
               if(g.getid() != id ){
                 out.println(line);
               }
               else{
               out.println(row.write());
               }
           }
           out.flush();
           out.close();
           in.close();
        }
    catch (IOException ex) {
            ret = false;
        }

    if(gdir.backup(filename)) {
        fin = new File(filename);
        fin.delete();
        String foutname = fout.getPath();
        if(!gdir.compact(foutname)) {
           gdir.restore(filename);
           ret = false;
        }
        if( !ret || !gdir.copy(foutname,filename)){
            gdir.restore(filename);
            ret = false;
            }
       }

    return ret;
    }//

    /** Deletes one row, identified by id form this gmpfile
     * Returns true if row is successfully deleted
     * @param id int
     * @return boolean
     */
    public boolean delete(int id){
    boolean ret = true;

    BufferedReader in = null;
    PrintWriter out = null;
    String line= null;
    File fout = null;
    File fin = null;
    gmprow g = null;

    gmpdirectory gdir = new gmpdirectory();

    String filename = this.getFile().getPath();

    try {
            fout = File.createTempFile("dele", ".tmp");
        } catch (IOException ex) {
            ret = false;
        }
    fout.deleteOnExit();

    try {
         in = new BufferedReader(new FileReader(filename));
         out = new PrintWriter(new FileWriter(fout));

        }
    catch (FileNotFoundException ex) {
         ret = false;
        }
     catch (IOException ex) {
         ret = false;
        }

    try {
           while ((line = in.readLine()) != null) {
               g = new gmprow(line);
               if(g.getid() != id ){
                 out.println(line);
               }
               else{
               out.println(gmprow.deletedmarker+line);
               }
           }
           out.flush();
           out.close();
           in.close();
        }
    catch (IOException ex) {
            ret = false;
        }

    if(gdir.backup(filename)) {
        fin = new File(filename);
        fin.delete();
        String foutname = fout.getPath();
        if(!gdir.compact(foutname)) {
           gdir.restore(filename);
           ret = false;
        }
        if( !ret || !gdir.copy(foutname,filename)){
            gdir.restore(filename);
            ret = false;
            }
       }

    return ret;
    }//
   /** Adds one row, equeal to input parameter to this gmpfile
     * Returns true if row is successfully added
     * @param g gmprow
     * @return boolean
     */
    public boolean add(gmprow g){
    boolean ret = true;
    gmprow lrow = this.read(g.getid());
    if(lrow != null) {
        return false; } // already exists this id

    if( this.CheckReference(g) == false) {
        return false; } // references non-existing entities

        try {
            BufferedWriter fw = new BufferedWriter (new FileWriter(this.getFile().getPath(),true) );
            fw.write(g.write());
            fw.newLine();
            fw.flush();
            fw.close();
        } 
        catch (IOException ex) {
          log("add() gmprow IOexception" + ex.getMessage());
        }

    return ret;
    }
 /** Reads one row, identified by its id form this gmpfile
     * Returns the gmprow it found, or null if not found
     * @param id int
     * @return gmprow
     */
    public gmprow read(int id){
    gmprow ret = null;
    gmprow g = null;
    BufferedReader in = null;
    String line = null;

    try {
        in = new BufferedReader(new FileReader(this.getFile().getPath()));

        while ((line = in.readLine()) != null) {
             g = new gmprow(line);
             if(g.getid()==id){ ret = new gmprow(g); break;}
            }
        }
    catch (FileNotFoundException ex) {
        log("File not found exception "+ ex.getMessage());
        }
    catch (IOException ex) {
        log("IO exception "+ ex.getMessage());
        }

    return ret;
    }

     /** Dumps the whole gmpfile ( all its lines are treated as gmprow objects)
      * Returns the array of gmprow objects
      * @return gmprow []
      */
public gmprow [] dump(){

    gmprow ret [] = new gmprow[gmpfile.MAX_GMPROWS_IN_FILE];
    gmprow g = null;
    BufferedReader in = null;
    String line = null;

    try {
        in = new BufferedReader(new FileReader(this.getFile().getPath()));
        int i = 0;
        while ( ((line = in.readLine()) != null) && i< gmpfile.MAX_GMPROWS_IN_FILE ) {
             ret[i] = new gmprow(line);
             i++;
             }
        }
    catch (FileNotFoundException ex) {
        log("gmpfile.dump() File not found exception "+ ex.getMessage());
        }
    catch (IOException ex) {
        log("gmpfile.dump() IO exception "+ ex.getMessage());
        }

    return ret;
    }

public String cat(){

    String ret = "";
    BufferedReader in = null;
    String line = null;

    try {
        in = new BufferedReader(new FileReader(this.getFile().getPath()));
        int i = 0;
        while ( ((line = in.readLine()) != null)  ) {
             ret = ret + line +"\n";
             i++;
             }
        }
    catch (FileNotFoundException ex) {
        log("gmpfile.cat() File not found exception "+ ex.getMessage());
        }
    catch (IOException ex) {
        log("gmpfile.cat() IO exception "+ ex.getMessage());
        }

    return ret;
    }

public boolean AppendToFile(gmpfile gimpf){
    boolean retn = false;
    gmprow ret [] = new gmprow[gmpfile.MAX_GMPROWS_IN_FILE];
    gmprow g = null;
    BufferedReader in = null;
    String line = null;

    try {
        in = new BufferedReader(new FileReader(this.getFile().getPath()));
        int i = 0;
        while ( ((line = in.readLine()) != null) && i< gmpfile.MAX_GMPROWS_IN_FILE ) {
             ret[i] = new gmprow(line);
             gimpf.add(ret[i]); // ADDING TO THE ARGUMENT FILE
             i++;
             }
        }
    catch (FileNotFoundException ex) {
        log("gmpfile.AppendToFile() File not found exception "+ ex.getMessage());
        }
    catch (IOException ex) {
        log("gmpfile.AppendToFile() IO exception "+ ex.getMessage());
        }

    return retn;
    }

  private int DEBUG = 0;
  private String ClassName = "gmpfile";
  private void log(String s){
   if(DEBUG > 200){ System.out.println(this.ClassName+" "+s);} // Screen log
   if(DEBUG > 100){ Util.log(this.ClassName+" "+s);}  // Package log
   if(DEBUG > 0){ gmpplatform.Log(this.ClassName+" "+s);}  // GMP log
  }
  public void setDEBUG(int dbg){ this.DEBUG = dbg;}
  
}