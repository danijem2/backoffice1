package gmp.terminal.local;
/** Class that describes one GMP local data  r e c o r d.
 It has an array of properties and their values as gmptlv objects.
 gmprow itself does not know how to interpret the data in this array.
 But gmprow can be used as an input for object constructors.
 Interpretation of Tags and Vaues from gmptlv objects occurs within these constructors.
 All objects created in a local gmp database will inherit from gmprow class.
 */
public class gmprow {
    /** Maximum number of properties in a GMP record*/
  public static final int MAX_TLVS = 2047;
  /**
   * String whose presence at the begining of the line represents the deleted row
   */
  public static final String deletedmarker = "#";
  private gmptlv[] tlv = new gmptlv[MAX_TLVS];
  private int id=0;
  private boolean IsNull = true;
  private int count = 0;
  /** Returns id for the row
   * @return int
   */
  public int getid(){return this.id;}
  /** Return the number of properties
   * @return int
   */
  public int getCount(){ return this.count; }

  /** Returns the String expression for the Value of property tagged with 'tag' from the record
   * @param tag String 
   * @return String
   */
  public String getValue(String tag){
  String ret = null;
  gmptlv ltlv = null;

    // log("Tlvlength"+this.tlv.length);
  for(int i=0;i<this.tlv.length;i++){
      //log(tlv[i].write());
     ltlv = new gmptlv(this.tlv[i]);
     if( ltlv == null) break;
     if(ltlv.getTag().equals(tag)){
       ret = ltlv.getValue();
       break;
     }
   }
  return ret;
  }

  public String [] getAllValues(String tag){
  
  gmptlv ltlv = null;
  String [] ret = new String [gmprow.MAX_TLVS];

  gmpplatform.Log("getAllValues() was called for tag" + tag);
    // log("Tlvlength"+this.tlv.length);
  int j = 0;
  for(int i=0;i<this.tlv.length;i++){
      //log(tlv[i].write());
     ltlv = new gmptlv(this.tlv[i]);
     if( ltlv == null) break;
     if(ltlv.getTag().equals(tag)){
       ret[j] = ltlv.getValue();
       j++; // NO BREAK HERE !!!
       gmpplatform.Log("Found a row number "+ j+"for tag "+tag+"on index"+i);
     }
   }
  return ret;
  }


  /** Constructs uninitialized gmprow object*/
   public gmprow(){ tlv = new gmptlv[MAX_TLVS];
      this.id = 0; this.tlv = null; this.IsNull = true; this.count = 0;}
   /** Copy constructor that constructs one gmprow object from another one
    * @param g gmprow
    */
   public gmprow(gmprow g) {
       tlv = new gmptlv[MAX_TLVS];
       this.id = g.getid();
       this.IsNull = g.IsNull;
       this.count = g.count;

       for (int i=0;i<g.count;i++){
          this.tlv[i] = new gmptlv(g.tlv[i]);
       }
   }
/** Construcs the gmprow object from the String of the form of one line in GMP
 * local data. It looks for (leftmost) equality sign and treats the String left from it
 * as Tag, and the String right from it till the delimiter as Value.
 * The next Tag is taken from one delimiter to another
 * If it cannot parse the input correctly it constructs uninitialized gmprow
 * object
 * @param line String
 */
   public gmprow(String line){
    boolean ret = true;

    String tlvstring = null;
    int position = -1;
    int lastposition = 0 ;

    tlv = new gmptlv[MAX_TLVS];
    this.IsNull = true;
    position = -1;
    int lcount = 0;
    gmptlv ltlv = null;
    id = 0;

    for(;;){

       position = line.indexOf(gmptlv.delimiter, lastposition) ;
       if(position < 0) break;
       tlvstring = line.substring(lastposition, position);
       ltlv = new gmptlv(tlvstring);
       if(ltlv != null ){
        this.tlv[lcount] = new gmptlv(ltlv) ;}
       else { ret = false; break;}

       if (this.tlv[lcount].isNull()) {
           ret = false; 
           break;
           }
       else {
           lcount++;
           lastposition = position+1;  
        if( ltlv.getTag().equals("id" )){
           this.id = new Integer(ltlv.getValue()).intValue();
           }
         }
       }

    if(lcount == 0 ) ret = false;
    if(ret==false || lcount > MAX_TLVS )
      {
        this.IsNull = true; this.count = 0; this.id = 0;
      }
    else 
       {
        this.IsNull = false; this.count = lcount;
       }
     }    

   /** Returns true if gmprow is uninitialized
    * @return boolean
    */
   public boolean isNull(){ 
       return this.IsNull; }

   /*public boolean isEmpty(){
     String s = this.write();
     if(s.length()==0 )
         return true;
       else
         return false;
   }*/

/** Writes down the String that this gmprow object shall be represented at
 * The output may be used to construct new gmprow object.
 * @return String
 */
public String write(){
   String ret = "";

    for(int i=0;i<this.count;i++){
      ret =  ret + this.tlv[i].write() ;
   }

   return ret;
}

public gmprow appendTlv(gmptlv gtlv, boolean newline){

    String ret = "";

    for(int i=0;i<this.count;i++){
        ret =  ret + this.tlv[i].write() ;
    }
    ret = ret + gtlv.write();
    if ( newline )  ret = ret + '\n';
    
    return new gmprow(ret);
}

public gmprow removeTag(String removetag){

    String ret = "";

    for(int i=0;i<this.count;i++){
      if( ! this.tlv[i].getTag().equals(removetag) ) // skip removetag tlv equations :-)
        ret =  ret + this.tlv[i].write() ;

    }
    return new gmprow(ret);  
}
/** Returns true if the row is actually deleted from local file, and is just
 * waiting for file to be cleaned. These rows are recogni
 * @return boolean
 */
 public boolean isDeleted(){
    boolean ret = false;

    if((this.write()).startsWith(gmprow.deletedmarker)) ret = true;

    return ret;
    }

 /**
  * Returns true if record is either uninitilaized, deleted or it has no id
  * @return boolean 
  */
 public boolean isValid(){
    boolean ret = true;

       if( this.isNull() == true || this.isDeleted() == true
        || this.id == 0 ) return false;
       else return true;
    }

 private int DEBUG = 0;
  private String ClassName = "gmprow";
  private void log(String s){
   if(DEBUG > 200){ System.out.println(this.ClassName+" "+s);} // Screen log
   if(DEBUG > 100){ Util.log(this.ClassName+" "+s);}  // Package log
   if(DEBUG > 0){ gmpplatform.Log(this.ClassName+" "+s);}  // GMP log
  }
public void setDEBUG(int dbg){ this.DEBUG = dbg;}

}


