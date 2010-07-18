package gmp.host.net;


/** Describes REST architecture. This simple architecture is actually the one
 used for Terminal requests to Host Web services */
public class RESTArchitecture implements Architecture{
/** Returns constant number code for REST architecture */
    public int getArchitectureId(){ return Architecture.REST;}
    /** NOT FINISHED!!! Returns ResponseSwing as constructed by XML response.
     Requires usage of SAX */
    public ResponseSwing Unwrap(String s){ return new ResponseSwing(s); }
    /** Returns REST String to send to Service. Since gmprow is formatted exactly
     as a list of RESTfull Web Service parameters it just means that gmprow needs
     to be written as a String :-)  */
    public String Wrap(Request req){
    return (req.write());
    }

}
