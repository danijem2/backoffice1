package gmp.host.net;



import java.io.*;
/** Interface describes how GMP Client call to GMP Host shall be implemented */
public interface Client {
    /** Returns the Protocol used to transmit data to service
     * @return Protocol
     */
  public Protocol getProtocol();
      /** Returns the Architecture used to convert local GMP requests and responses
       * from gmprow-based Java objects to the Strings that remote service application
       * can understand
       * @return Architecture
       */
  public Architecture getArchitecture();
   /** Returns the Server where the call will be sent
    * @return Server
    */
  public Server getServer();
  /** Returns the NetAccessDevice where this Client is connected to through
   * @return CommDevice
   */
  public CommDevice getCommDevice();
  /** Sends request to Server. Returns the ResponseSwing object from the Server
   * @param req Request
   * @return ResponseSwing
   * @throws IOException 
   */
  public Response Send(Request req) throws IOException;
}


