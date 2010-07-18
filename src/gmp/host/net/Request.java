package gmp.host.net;



import gmp.net.MessageTypes;
import gmp.terminal.local.*;

/** Objects of this class present one request from client to service*/
public class Request extends gmprow{

private int messagetype ;
//private ResponseSwing Response;
private Client client;
private String ServiceScript;

/** Constructs the empty request*/
public Request(){ // default constructor
    super();
    this.messagetype = MessageTypes.INVALID;
    //this.Response = new ResponseSwing();
    this.ServiceScript = new String();
}
/** constructs the request from gmprow and request type
 * @param messagetype
 * @param r gmprow
 */
public Request(int messagetype, gmprow r){// from request type and gmp db row
    super(r);
    this.messagetype = messagetype;
    //this.Response = new ResponseSwing();
    this.ServiceScript = new String();
}

/** copy constructs for requests
 * @param req Request
 */
public Request(Request req){// from existing request
    super(req.write());
    this.messagetype = req.getMessageType();
    //this.Response = new ResponseSwing(req.getResponse());
    this.ServiceScript = new String();
}

/** Returns the name of the Service script that will be caled on
 * the server
 * @return String
 */
public String getServiceScript(){ return this.ServiceScript;}
/** Returns the type of the request beeing sent
 * @return messagetype
 */
public int getMessageType(){ return this.messagetype;}
/** Returns the ResponseSwing received from the service
 * @return ResponseSwing
 */

public Client getClient(){ return this.client;}

/** Sets the request type. Returns true if successfull
 * @param t messagetype
 * @return boolean
 */
public boolean setMessageType(int t ){
  boolean ret = false;
  this.messagetype = t;
  return true;
  }

/** Sets the client to be used for this request. Returns true if successfull.
 * @param c Client
 * @return boolean
 */
public boolean setClient(Client c){
  boolean ret = false;
  this.client = c;
  return true;
  }
/** Sets the script to be called for service, Returns true if successfull.
 * @param s String
 * @return boolean
 */
public boolean setServiceScript(String s){
  boolean ret = false;
  this.ServiceScript = s;
  return true;
  }

}
