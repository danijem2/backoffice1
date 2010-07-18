package gmp.host.net;



/** Interface Protocol is used to describe the conversions to/from
 specific netwoek layer protocols */
public interface Protocol {

        // Protocols
    /** Bad protocol, unable to do anything usefull*/
    static final int INVALID      = -1;
    /** Manual protocol, used for Voice referals. Not implemented yet*/
    static final int MANUAL       = 0;
    /** Bluetooth protocol for proximity */
    static final int BTTPS        = 1000; // Bluetooth serial protocol
    /** USB protocol for proximity, not implemented yet */
    static final int USB          = 2000;
        /** WiFi protocol for terminal -> localrouter, not implemented yet */
    static final int WIFI         = 3000;
        /** Socket protocol for terminal -> localrouter, not implemented yet */
    static final int SOCKET       = 4000;
    /** HTTP protocol */
    static final int HTTP         = 5000;
    /** HTTPS protocol */
    static final int HTTPS        = 6000;
      /** NFC protocol for proximity, not implemented yet */
    static final int NFC          = 7000; // RFU

    /** Forms the Strig that client knows how to send over the network.
     * Client may additionally use the information from the type of request
     * (like HostScrpt to use for ex.) but all wraping based on network is
     * done by Protocol.
     * @param s String
     * @return String
     */
    public String Wrap(String s);
      /** Unwraps the response from network so that the client may forward it to
       * architecture level for further unwrapping.
       * @param s String
       * @return String
       */
    public String Unwrap(String s);
    /** Gets the numeric id of the Protocol
     * @return int
     */
    public int getProtocolId();

    } // End of class 'Protocol'
