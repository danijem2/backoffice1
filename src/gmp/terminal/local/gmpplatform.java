package gmp.terminal.local;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

 /** Class holds the platform-specific and local device system properties.
  * It shall be the first class to cosntruct whan starting-up.
  */
public class gmpplatform  {

    /** local log file  */
    public static final String statisticfile = ".\\log\\statistic.txt";
    /** local gmpdb file for platform - THESE ONE MUST BE FIXED ON ANY INSTALATION
     since it is the first file to be opened when starting-up */
    public static final String localhostfilename = ".\\gmpdb\\Platform.gmp";

    private static boolean initialized = false;

    private File file = null;

    /** Returns the Platform local file
     * @return File
     */
    public File getFile(){ return this.file;}

    private String hardware = null;
    private String manufacturer = null;
    private String serialnumber = null;
    private String homedir = null;

        /** Inner class for CPU processing  */
    public class Cpu {
        private String name = null;
        private float ghz = 0;
        private Cpu (){
         this.name = null;
         this.ghz = 0;
         } //
        private Cpu (String name, float ghz){
         this.name = name;
         this.ghz = ghz;
         } //
        /** Return CPU name
         * @return String
         */
        public String getName (){
        return this.name;
         } //
        /** Return CPU frequency in GHz
         * @return float
         */
        public float getGHz (){
        return this.ghz;
         } //

    } ; // End nested class cpuclass

      /** Return home directory for this application
         * @return String
         */
        public String getHomedir (){
        return this.homedir;
         } //

    private Cpu cpu = new Cpu();
    /** Inner class to describe device RAM
         */
    public class Ram {
        private String name = null;
        private float mhz;
        private float gbytes;

        private Ram (){
         this.name = null;
         this.mhz = 0;
         this.gbytes = 0;
         } //
        private Ram (String name, float mhz, float gbytes){
         this.name = name;
         this.mhz = mhz;
         this.gbytes = gbytes;
         }//
        /** Return RAM name
         * @return String
         */
        public String getName (){
        return this.name;
         } //
         /** Return RAM operating frequency
         * @return float
         */
        public float getMHz (){
        return this.mhz;
         } //
         /** Return RAM size in GBytes
         * @return float
         */
        public float getGbytes (){
        return this.gbytes;
         } //

    };// End of nested class ramclass
    private Ram ram = new Ram();
    private String os = null; // operating system
    private String name = null;
    private String group = null;

     /** Object to write local logs   */
    public static PrintWriter logfile = null;
    /** Return Hardware desription
     * @return String
     */
    public String getHardware (){
        return hardware;
    } //
     /** Return manufacturer name
     * @return String
     */
    public String getManufacturer (){
        return manufacturer;
    } //
     /** Return serial number of the device (not for mobile phone serial numbers!)
     * @return String
     */
    public String getSerialNumber (){
        return serialnumber;
    } //
     /** Return local Cpu
     * @return Cpu
     */
    public Cpu getCpu (){
        return this.cpu;
    } //
       /** Return local RAM
     * @return Ram
     */
    public Ram getRam (){
        return this.ram;
    } // 
    public String getOS (){
        return os;
    } //
       /** Return local device name
     * @return String
     */
    public String getName (){
        return name;
    } //

     /** Return LAN group name
     * @return String
     */
    public String getGroup (){
        return group;
    } //
    /** Return local log file name (without full path)
     * @return Cpu
     */
    public String getStatisticFile (){
        return "statistic.txt";
    } //

 /** The only constructor of local system properties, Reads and interprests all
  * the properties from the first line of a file \\gmpdb\\Platform.gmp
  */
public gmpplatform() {
    BufferedReader localhostfile = null;
    String line = null;
    String tag = null;
    String value = null;
    int position = -1;


            logfile = new PrintWriter(System.out);
        
        
    Log("Starting ");

    try {
       if(initialized){
            Log("localhost is aleady initialized");
            throw new Exception("localhost is aleady initialized");
            }
        }
    catch (Exception ex)
        {
           Log("localhost is aleady initialized "+gmpplatform.class.getName()+ex.getMessage());
        } 

    try { this.file = new File(localhostfilename);
          localhostfile =   new BufferedReader( new FileReader(localhostfilename));
        } catch (FileNotFoundException ex) {
            Log("Platform.gmp not found "+gmpplatform.class.getName()+ex.getMessage());
        } catch (IOException ex) {
            Log("Platform.gmp IO excpetion "+gmpplatform.class.getName()+ex.getMessage());
        }
    try {

         while ((line = localhostfile.readLine()) != null) {
         gmprow gr = new gmprow(line);
         this.homedir = gr.getValue("homedir");
         this.hardware = gr.getValue("hardware");
         this.manufacturer = gr.getValue("manufacturer");
         this.serialnumber = gr.getValue("serialnumber");
         this.cpu.name = gr.getValue("cpu.name");
         this.cpu.ghz =  (float)Float.valueOf(gr.getValue("cpu.ghz")) ; 
         this.ram.mhz = (float)Float.valueOf(gr.getValue("ram.mhz"));
         this.os = gr.getValue("os");
         this.name = gr.getValue("name");
         this.group = gr.getValue("group");

         line = null;
        } // End while that reads file
         initialized = true;
     }
    catch (IOException ex)
    {
      Log(gmpplatform.class.getName()+ex.getMessage());
    }
    

        Log("hardware:" + this.hardware);
        Log("manufacturer:"+ this.manufacturer);
        Log("serialnumber:"+ this.serialnumber);
        Log("cpu.name:"+ this.cpu.getName());
        Log("cpu.ghz:"+ this.cpu.getGHz());
        Log("ram.name:"+ this.ram.getName());
        Log("ram.mhz:"+ this.ram.getMHz());
        Log("ram.gbytes:"+ this.ram.getGbytes());
        Log("os:"+ this.os);
        Log("name:"+ this.name);
        Log("group:"+ this.group);
       


}; // constructs LocalHost object from  file

/** Writes into the local log
 * @param msg String
 */
public static void Log(String msg){
  System.out.println(msg);
};//

}

