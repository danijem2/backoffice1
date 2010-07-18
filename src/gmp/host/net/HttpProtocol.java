package gmp.host.net;



  /** Implements HTTP and HTTPS protocols to call Web Services*/
public class HttpProtocol implements Protocol{
      /** Specific GET method for WebService */
    public static final String GET = "GET";
     /** Specific PUT method for WebService */
    public static final String PUT = "PUT";
     /** Specific DELETE method for WebService */
    public static final String DELETE = "DELETE";
     /** Specific POST method for WebService */
    public static final String POST = "POST";

    private String method;
    private String Tag ;
    private String Html = "html";
    private String Body ="body";
    private String XMLPI="?xml";

    /** Constructs the Protocol from TSring as either http or https
     * @param tag String
     */
    public HttpProtocol(String tag){
     this.Tag = tag;
    }
    /** Returns 'http' or 'https'
     * @return String
     */
    public String getTag (){ return this.Tag;}
    /** Copy constructor
     * @param p HttpProtocol
     */
    public HttpProtocol(HttpProtocol p){
      this.Tag = p.getTag();
    }

     /** Gets the numeric Id of Protocol based on weather it is htpp or https */
    public int getProtocolId(){
        if(this.Tag.equals("http"))
        return Protocol.HTTP;
        if(this.Tag.equals("https"))
        return Protocol.HTTPS;
        return Protocol.INVALID;
    }
     /** Wrap concatenates method and tag before the gmprow parameters   */
    public String Wrap(String s){
        return (this.method + " " + this.Tag+"://"+s);
    }
    /** NOT FINISHED !!! Http Unwrap() shall use SAX  */
    public String Unwrap(String s){
    /*int start =  s.indexOf("<"+Body+">");
    if(start <0)
        return null;
    else start = start + 2 + Body.length();
    int end = s.indexOf("</"+Body+">", start);
    if (end <0)
        return null;
    //else end = end + 3 + Body.length();

    return s.substring(start,end).trim();*/
        return s;
    }

}
