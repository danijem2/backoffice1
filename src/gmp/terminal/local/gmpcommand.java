package gmp.terminal.local;

/**
 * Objects of this class represent commands from Host that execute changes on
 * a Terminal local configuration. This is used to enable centralized Terminal
 * Management System (TMS). Format of these 'acquirer script commands' is inherited
 * from gmprow object
 */
public class gmpcommand extends gmprow{
    public static final int INVALID = -1;
    public static final int READ = 0;
    public static final int ADD = 1;
    public static final int CHANGE = 2;
    public static final int DELETE = 3;

    public static final String GMPCOMMANDACTIONTAG = "gmpcommandaction";
    public static final String GMPCOMMANDIDTAG     = "gmpcommandid";
    public static final String GMPCOMMANDFILETAG   = "gmpcommandfile";
    public static final String GMPCOMMANDEXECUTIONRESULT = "gmpcommandexecutionresult" ;
    public static final String GMPCOMMANDRESULTMESSAGE = "gmpcommandresultmessage" ;
    private int getAction(int s){
       int ret = gmpcommand.INVALID;
        if( s != gmpcommand.READ && s !=  gmpcommand.ADD &&
            s != gmpcommand.CHANGE && s !=  gmpcommand.DELETE )
           {
            ret =  gmpcommand.INVALID;
           }
        else ret = s;
      return ret;
    }

    private gmpfile gmpf = null;

    private int commandid ;
    
    /** Command id is generated on Host. This value is later used in acquirer script response
     and sent back to Host with a result of execution. Host is then able to track success or failure
     of each command.
     */
    public int getCommandId(){ return this.commandid;}

    private int gmpcommandaction ;
    /** Returns either READ,ADD,CHANGE or DELETE depending on what is to be
     * executed with this command */
    public int getGmpCommandAction(){ return this.gmpcommandaction;}

    private boolean gmpcommandexecutionresult = false;
    private String gmpcommandresultmessage = null;

    private String strcom = null ;
    private  String strdidcom = null;

    /** Constructs an empty command*/
    public gmpcommand(){ super();}
    /** Constructs a command based on a gmprow. The gmprow must have tags
     * </br>"gmpcommandid" : the unique id of the command generated on Host
     * </br>"gmpcommandaction" : ADD, CHANGE, DELETE or READ indicator
     * </br>"gmpcommandfile" : name of the local terminal file where action is required
     * </br>gmprow will also need to have all tags required by the nature of records
     * of the file on which it is executed.
     */
    public gmpcommand(gmprow row){
      super(row);
      try{
        this.strcom     = row.getValue(gmpcommand.GMPCOMMANDACTIONTAG);
        this.strdidcom  = row.getValue(gmpcommand.GMPCOMMANDIDTAG);
        this.gmpf    = new gmpfile(row.getValue(gmpcommand.GMPCOMMANDFILETAG));
        log("Construct gmpcommand strcom|"+this.strcom +"|");
        log("Construct gmpcommand stridcom|"+this.strdidcom +"|");
        //log("Construct gmpcommand gmpf|"+this.gmpf.cat() +"|");
      }
      catch (NullPointerException ex){
        this.gmpcommandexecutionresult =   false;
        this.gmpcommandresultmessage = "Constructor gmpcommand(gmprow) NullPointerExceptio for gmpcommand" +
              this.commandid +" exception: "+ex.getMessage();
      log(this.gmpcommandresultmessage);
    }
    this.commandid = gmpconverter.StringToInt(strdidcom);
    this.gmpcommandaction = getAction(gmpconverter.StringToInt(strcom));
    }

    /** Constructs the command from String by first constructing gmprow from String.
     Then gmprow is used to construct command itself*/
    public gmpcommand(String line){
     super(line);
     try{
      gmprow row =  new gmpcommand(new gmprow(line));
      this.strcom     = row.getValue(gmpcommand.GMPCOMMANDACTIONTAG);
      this.strdidcom  = row.getValue(gmpcommand.GMPCOMMANDIDTAG);
      this.gmpf       = new gmpfile(row.getValue(gmpcommand.GMPCOMMANDFILETAG));

    }
    catch (NullPointerException ex){
      this.gmpcommandexecutionresult =   false;
      this.gmpcommandresultmessage = "Constructor gmpcommand(gmprow) NullPointerExceptio for gmpcommand" +
              this.commandid +" exception: "+ex.getMessage();
      log(this.gmpcommandresultmessage);
    }
    this.commandid = gmpconverter.StringToInt(strdidcom);
    this.gmpcommandaction = getAction(gmpconverter.StringToInt(strcom));
     }

    /** Copy constructor that will create new gmpcommand from previous one*/
public gmpcommand(gmpcommand comm){
  super(comm);
  try{
      String line = comm.write();
      gmprow row =  new gmpcommand(new gmprow(line));
      this.strcom    = row.getValue("gmpcommandaction");
      log("this.strcom" + this.strcom);
      this.strdidcom  = row.getValue("gmpcommandid");
      this.gmpf    = new gmpfile(row.getValue("gmpcommandfile"));
    }
    catch (NullPointerException ex){
      this.gmpcommandexecutionresult =   false;
      this.gmpcommandresultmessage = "Constructor gmpcommand(gmprow) NullPointerExceptio for gmpcommand" +
              this.commandid +" exception: "+ex.getMessage();
      log(this.gmpcommandresultmessage);
    }
    this.commandid = gmpconverter.StringToInt(strdidcom);
    this.gmpcommandaction = getAction(gmpconverter.StringToInt(strcom));
     }
/**
 * Returns the result of one single gmpcommand as
 * gmprow for resultscript result file
 * @return gmprow
 */
public gmprow execute(){

   gmprow exeresult = null;
   gmprow commandrow = null;

   try{
     exeresult = new gmprow(this);
     commandrow = new gmprow(this);
     commandrow = commandrow.removeTag("gmpcommandaction");
        //log("commandrow trimmed 1"+ commandrow.write());
     commandrow = commandrow.removeTag("gmpcommandid");
        //log("commandrow trimmed 2"+ commandrow.write());
     commandrow = commandrow.removeTag("gmpcommandfile");
        //log("commandrow trimmed 3"+ commandrow.write());
   }
   catch (NullPointerException ex){
      this.gmpcommandexecutionresult =   false;
      this.gmpcommandresultmessage = "Execute gmpcommand(gmprow) NullPointerExceptio for gmpcommand" +
              this.commandid +" exception: "+ex.getMessage();
      log(this.gmpcommandresultmessage);
    }
   
  try{
      log("gmpcommandaction" + this.gmpcommandaction);
   switch(this.gmpcommandaction){
       case 1:
           gmpf.add(commandrow);
           this.gmpcommandexecutionresult = true;
           this.gmpcommandresultmessage =  "ADD OK";
           break;
       case 3:
           gmpf.delete(commandrow.getid());
           this.gmpcommandexecutionresult = true;
           this.gmpcommandresultmessage =  "DELETE OK";
           break;
       case 2:
           gmpf.change(commandrow.getid(), commandrow);
           this.gmpcommandexecutionresult = true;
           this.gmpcommandresultmessage =  "CHANGE OK";
           break;
       case 0:
           gmpf.read(commandrow.getid());
           this.gmpcommandexecutionresult = true;
           this.gmpcommandresultmessage =  "READ OK";
           break;
       default:
              this.gmpcommandexecutionresult = false;
           this.gmpcommandresultmessage =  "UNKNOWN gmpcommand:" + this.gmpcommandaction;
           break;
      }
  }
 catch (NullPointerException ex){
      this.gmpcommandexecutionresult =   false;
      this.gmpcommandresultmessage = "Execute gmpcommand action ERROR for gmpcommand" +
              this.commandid +" exception: "+ex.getMessage();
      log(this.gmpcommandresultmessage);
    }

    exeresult = exeresult.appendTlv(new gmptlv(
               "gmpcommandexecutionresult",
                Boolean.toString(this.gmpcommandexecutionresult)
                ),
                false);
    exeresult = exeresult.appendTlv(new gmptlv(
               "gmpcommandresultmessage",
                this.gmpcommandresultmessage
                ),
                true);
    log("exeresult :" + exeresult.write());
    return exeresult;
  }

  private int DEBUG = 0;
  private String ClassName = "gmpcommand";
  private void log(String s){
   if(DEBUG > 200){ System.out.println(this.ClassName+" "+s);} // Screen log
   if(DEBUG > 100){ Util.log(this.ClassName+" "+s);}  // Package log
   if(DEBUG > 0){ gmpplatform.Log(this.ClassName+" "+s);}  // GMP log
  }
    @Override
  public void setDEBUG(int dbg){ this.DEBUG = dbg;}



}
