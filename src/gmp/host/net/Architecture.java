package gmp.host.net;


/** Interface that describes how to form request and interpret responses 
 * according to remote service formatting.*/
public interface Architecture {

        // Architecture
    /** Inicates invaid architecture*/
    static final int INVALID       = -1;
    /** Indicates Wallet data was entered by hand. Used in proximity exchange*/
    static final int MANUAL       = 0;
    /** RESTfull architecture, method and parameters ae whithin the url*/
    static final int REST         = 1000;
    /** SOAP, not implemented. Too complex for the current needs */
    static final int SOAP         = 2000;
/**  Takes the request and converts it to the String that service architecture
 * will understand and will be able to respond
 * @param req Request
 * @return String
 */
    public String Wrap(Request req);
/** Takes the String response received from architecture of the service side
 * and converts it to response as Java object which inherits gmprow
 * @param s String
 * @return ResponseSwing
 */
    public ResponseSwing Unwrap(String s);
    /** Returns the numeric id of this architecture
     * @return int
     */
    public int getArchitectureId();

    } // End of class 'Architecture'