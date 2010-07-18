package gmp.terminal.local;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/** This class is used to make conversion during the interpretation of
 * gmptlv.value. It only has static methods.
 */
public class gmpconverter {

    public static String PreferedDateFormat = "yDHms"; //y year Julian date D,
                                                       // H hour, m minute, s second

  /** Get the current datetime*/
  public static String now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(PreferedDateFormat);
    return sdf.format(cal.getTime());

  }
 /** Convert gmptlv.Value to integer
  * @param s String
  * @return int
  */
    public static int StringToInt(String s){
    return  Integer.parseInt(s.trim());
    }
  /** Convert gmptlv.Value to boolean
  * @param s String
  * @return boolean
  */
  public static boolean StringToBoolean(String s){
   return Boolean.parseBoolean(s);
  }
   /** Convert gmptlv.Value to float
  * @param s String
  * @return float
  */
  public static float StringToFloat(String s){
  return Float.parseFloat(s.trim());
  }

  /**
   * 
   * @param s
   * @param dfs
   * @return
   */
  public static Date StringToDate(String s, String dfs){
    Date ret = null;
    DateFormat  df =  new SimpleDateFormat(dfs);
    try{
    if(df == null)
       ret = df.parse(gmpconverter.PreferedDateFormat);
    else
       ret = df.parse(s);
    }
    catch (ParseException e)
        {
          //gmpconverter.log("Bad date parsing :") ;
          ret = null;
          ///e.printStackTrace();
        }

   return ret;
  }

}
