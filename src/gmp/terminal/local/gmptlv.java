package gmp.terminal.local;

/** Class that holds the expression to join o n e  property with  o n e  value.
 * Expression is a String of the form Tag=Value.
 * "Tag" is a mnemonic or variable name for property.
 * "Value" is a value we wish to join to the property .
 * Both the Tag and the Value have meaningful interpretations o n l y  within
 a wider contents of the file or class. 
 * Property and the formatting of the value shall be used in a class constructor.
 */

public class gmptlv {
    /** character that ends the String expression  */
    public static final String delimiter = "&" ;
    /** character that separates Tag from Vaue in a Tag=Value expression  */
    public static final String eqsign = "=";

    private String Tag = new String() ;
    private String Value = new String();
    private boolean IsNull = true;
    /** Returns true if object gmptlv is not successfully initialized
     * @return boolean
     */
public boolean isNull(){ return this.IsNull;}
/** Copy constructor to make new gmptlv object from existing one
 * @param t gmptlv
 */
public gmptlv(gmptlv t){
  this.Tag = t.Tag; this.Value = t.Value; this.IsNull = t.IsNull;}
/** Constructs gmptlv object from tag and vaue strings
 * @param tag String 
 * @param value String
 */
public gmptlv(String tag, String value){
  this.Tag = tag; this.Value = value; this.IsNull = false;
}
/** Empty constructor, will create new, but uninitialized gmptlv object*/
public gmptlv(){
  this.Tag = null; this.Value = null; this.IsNull = true;
}
/** Construcs the gmptlv object from the String of the form Tag=Value
 * It looks for (leftmost) equality sign and treats the String left from it
 * as Tag, and the String right from it till the delimiter as Value.
 * If it cannot parse the input correctly it constructs uninitialized gmptlv
 * object
 * @param tlvstring String
 */
public gmptlv(String tlvstring){
   boolean ret = true;

       String tag = null;
       String value = null;
       int eqposition = -1;

       eqposition = tlvstring.indexOf(eqsign);
       if( eqposition <= 0 ) {
            ret=false;
       }
       else{
        tag = tlvstring.substring(0, eqposition);
         value = tlvstring.substring(eqposition+1);
         if(tag == null || value == null) { ret=false;}
         else{
           if( tag.indexOf(eqsign) >= 0 ) ret = false;
           if( tag.indexOf(delimiter) >= 0 ) ret = false;
           if( value.indexOf(eqsign) >= 0 ) ret = false;
           if( value.indexOf(delimiter) >= 0 ) ret = false;
          }
       }
        

    if(ret==true){ this.setTag(tag); this.setValue(value); this.IsNull = false;}
    else { this.setTag(null); this.setValue(null); this.IsNull = true;}
   } //
/** Writes down the String that this gmptlv object shall be represented at
 * The output may be used to construct new gmptlv object.
 * @return String
 */
public String write(){ return this.Tag +eqsign+this.Value + delimiter;}
/** Returns Tag string from gmptlv
 * @return String
 */
public String getTag(){ return Tag;}
/** Returns Value string from gmptlv
 * @return String
 */
public String getValue(){ return Value;}

private void setTag(String t){ this.Tag = t;}
private void setValue(String v){ this.Value = v;}


private int DEBUG = 0;
  private String ClassName = "gmptlv";
  private void log(String s){
   if(DEBUG > 200){ System.out.println(this.ClassName+" "+s);} // Screen log
   if(DEBUG > 100){ Util.log(this.ClassName+" "+s);}  // Package log
   if(DEBUG > 0){ gmpplatform.Log(this.ClassName+" "+s);}  // GMP log
  }
public void setDEBUG(int dbg){ this.DEBUG = dbg;}
}
