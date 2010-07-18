package gmp.host.net;



import gmp.terminal.local.*;
import java.util.Calendar;

/**  Class describes remote or proximity servers that have to be
 * called to retrieve some services. Mostly it applies to GMP Server, but it 
 * also applies to Wallet in proximity, to Printers etc.*/
public class Server extends gmprow{
    // constant values at the level of one Server
    private int id;
    private String url;

    // dynamic values
   private int total_requests = 0;
   private int total_responses = 0;
   private int total_request_time = 0;
   private Calendar cal = null;
    
   /** Constructs empty server*/
    public Server ( ){
    this.id = 0;
    this.url = new String();

    this.total_requests = 0;
    this.total_responses = 0;
    this.total_request_time = 0;
    this.cal = null;
    }

    /** Constructs Server from local file record
     * @param row
     */
    public Server (gmprow row){
      super(row);
      boolean ok = true;
      if(ok == true) { id = 0;  id = row.getid();
        if( id == 0 )
           ok = false; }
      if(ok == true) { url = null;  url = row.getValue("url")  ;
        if( url == null )
            ok = false; }
      if(ok == false){
        this.id = 0;
        this.url = new String();
      }
      this.total_requests = 0;
      this.total_responses = 0;
      this.total_request_time = 0;
      this.cal = null;
    }
/** Increments the number of requests sent to this Server */
   public void add_request(){ this.total_requests++;}
   /** Increments the number of responses received from this Server */
   public void add_response(){ this.total_responses++;}
   /** Increments the total processing time of this Server
    * @param period int
    */
   public void add_time(int period){this.total_request_time+=period;}

   /** Returns url of the Server
    * @return String
    */
    public String geturl(){ return this.url;}

    /** Sets the id of this Server
     * @param i int
     */
    public void setid(int i){
       this.id = i;
    }
    /** Sets the url of this Server
     * @param u Atring
     */
    public void seturl(String u){
       this.url = u;
    }
    /** Returns the Calendar object attached to this Server
     * @return Calendar
     */
    public Calendar getCalendar(){ return this.cal;}
     /** Sets the new Calendar instance for this Server
     */
    public void setCalendar(){ this.cal = Calendar.getInstance();}
}
