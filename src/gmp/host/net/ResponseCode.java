package gmp.host.net;



import gmp.terminal.local.*;
/** Response code is the numeric code indicating the reason why some
 * client request was not accepted. Only value 0 means acceptance.*/
public class ResponseCode extends gmprow {
   public String Explain(int rc){
      return this.getValue("Explain");
   }

   public ResponseCode(gmprow r){
     super();
   }
}
