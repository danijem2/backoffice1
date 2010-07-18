package gmp.terminal.local;

import java.io.File;


    /** This class is only used to list the content of the local directory.
     */

public class gmplist {
    /** Get all the file names from the directory.
     * Returns the array of String file names
     * @param path String
     * @return String []
     */
public String [] getList(String path){
     String[] filesList = new String[1024];
       try
       {

             File dir = new File(path);
             filesList= dir.list();
         }
         catch(Exception e){}

   return filesList;
  }

public String [] getList(String path, String regex){
     String[] filesList = new String[1024];
     String[] retList = new String[1024];
       try
       {

             File dir = new File(path);
             filesList= dir.list();
             int j=0;
             for (int i=0;i< filesList.length;i++)
             {
                 if(filesList[i].endsWith(regex))
                 {
                     retList[j] = filesList[i];
                     j++;
                     //System.out.println(filesList[i]);
                 }
             }
         }
         catch(Exception e){}

   return retList;
  }

  private int DEBUG = 0;
  private String ClassName = "gmplist";
  private void log(String s){
   if(DEBUG > 200){ System.out.println(this.ClassName+" "+s);} // Screen log
   if(DEBUG > 100){ Util.log(this.ClassName+" "+s);}  // Package log
   if(DEBUG > 0){ gmpplatform.Log(this.ClassName+" "+s);}  // GMP log
  }
  public void setDEBUG(int dbg){ this.DEBUG = dbg;}




}
