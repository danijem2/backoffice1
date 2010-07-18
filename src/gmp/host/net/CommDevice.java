package gmp.host.net;



import gmp.terminal.local.*;
import java.util.Calendar;
/** Objects of this class describe one physical access device to
 access GMP Host via Internet connection */
public class CommDevice extends gmprow{
    // constant values at the level of one CommDevice
    private int id;
    private String transport = null;
    private String stack = null;
    private String name = null;
    private boolean primary = false;
    private boolean auto = false;
    private int timeout = 0;
    private int attempts = 0;

    // dynamic values
   private int total_requests = 0;
   private int total_responses = 0;
   private int total_request_time = 0;
   private Calendar cal = null;
 /** Constructs empty CommDevice object     */
    public CommDevice ( ){
    this.id = 0;
    this.name = new String();
    this.transport = new String();
    this.stack = new String();
    this.primary = false;
    this.auto = false;
    this.timeout  = 0;
    this.attempts = 0;

    this.total_requests = 0;
    this.total_responses = 0;
    this.total_request_time = 0;
    this.cal = null;
    }

    /** Constructs CommDevice object based on local file row
     * @param row gmprow
     */
    public CommDevice (gmprow row){
      super(row);
      boolean ok = true;
      if(ok == true) { id = 0;  id = row.getid();
        if( id == 0 )
           ok = false; }
      if(ok == true) { name = null;  name = row.getValue("name")  ;
        if( name == null )
            ok = false; }
      if(ok == true) { transport = null;  transport = row.getValue("transport")  ;
        if( transport == null )
            ok = false; }
      if(ok == true) { stack = null;  stack = row.getValue("stack")  ;
        if( stack == null )
            ok = false; }
      if(ok == true) {
        primary = false;
        primary = gmpconverter.StringToBoolean(row.getValue("primary"));}
      if(ok == true) {
          auto = false;
          auto = gmpconverter.StringToBoolean(row.getValue("auto")) ;}
      if(ok == true) {
          timeout = 0;
          //System.out.println("Timeout"+row.getValue("timeout"));
          //System.out.flush();
        timeout = gmpconverter.StringToInt(row.getValue("timeout"));
          }
      if(ok == true) {
          attempts = 0;
          attempts = gmpconverter.StringToInt(row.getValue("attempts"));}
      if(ok == false){
        this.id = 0;
        this.name = new String();
        this.transport = new String();
        this.stack = new String();
        this.primary = false;
        this.auto = false;
        this.timeout  = 0;
        this.attempts = 0;

        this.total_requests = 0;
        this.total_responses = 0;
        this.total_request_time = 0;
        this.cal = null;
      }

    }

    /** Returns the net device name
     * @return String
     */
    public String getname(){ return this.name;}
    /**Returns the net device transport-level protocol name
     * @return String
     */
    public String gettransport(){ return this.transport;}
    /**Returns the net device stack name
     * @return String
     */
    public String getstack(){ return this.stack;}
    /** Returns true if this is the primary CommDevice device
     * @return boolean
     */
    public boolean getprimary(){ return this.primary;}
    /** Returns true if device shall be started automatically
     * @return boolean
     */
    public boolean getauto(){ return this.auto;}
    /** Returns the timeout for getting the response from this net device whean calling Send()
     * @return int
     */
    public int gettimeout(){ return this.timeout;}
    /** Returns the number of attempts that Send() shall be called
     * @return int
     */
    public int getattempts(){ return this.attempts;}

    /** Sets the id of this NetAcces device
     * @param i int
     */
    public void setid(int i){
       this.id = i;
    }
    /** Sets the name of this NetAcces device
     * @param n String
     */
    public void setname(String n){
       this.name = n;
    }
    /** Sets the transport of this NetAcces device
     * @param t String
     */

    public void settransport(String t){
       this.transport = t;
    }
      /** Sets the stack for this NetAcces device
     * @param s String
     */
       public void setstack(String s){
       this.stack = s;
    }
    /** Sets this NetAcces device as primary or not primary
     * @param b boolean
     */
    public void setprimary(boolean b){
       this.primary = b;
    }
    /** Sets this NetAcces device to start automatically or not
     * @param b boolean
     */
    public void setauto(boolean b){
       this.auto = b;
    }
    /** Sets the timeout of this NetAcces device
     * @param t int
     */
    public void settimeout(int t){
      this.timeout = t;
    }
    /** Sets the number of attempts for this NetAcces device
     * @param a int
     */
     public void setattempts(int a){
      this.attempts = a;
    }
     
     /** Increments the number of requests sent to this device */
   public void add_request(){ this.total_requests++;}
      /** Increments the number of responses received from this device */
   public void add_response(){ this.total_responses++;}
   /** Increments the number of requests sent to this device
    * @param period int
    */
   public void add_time(int period){this.total_request_time+=period;}

    /** Returns the Calendar object attached to this Server
     * @return Calendar
     */
    public Calendar getCalendar(){ return this.cal;}
     /** Sets the new Calendar instance for this Server
     */
    public void setCalendar(){ this.cal = Calendar.getInstance();}
}
