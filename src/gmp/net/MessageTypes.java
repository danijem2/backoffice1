package gmp.net;

/** Object of this class holds a request types from gmp client to gmp service*/
public interface MessageTypes {
    /** Invalid request*/
    public static final int INVALID      = -1;
    /** Host requests logon,logoff, connect/disconnect, init, test, keepalive*/
    public static final int NETWORK      = 1000; // Logon, logoff, connect, disconect,
                                         //test, keepalive, initialze

    public static final int CONNECT     = 1000;
    public static final int INIT        = 1001;
    public static final int TEST        = 1002;
    public static final int LOGON       = 1003;
    public static final int LOGOFF      = 1004;
    public static final int DISCONNECT  = 1005;

    public static final int HANDSHAKE                    = 0;
    public static final int HANDSHAKE_RESPONSE          = 1;

       public static final int CHALLANGE = 3;
          public static final int CHALANGE_RESPONSE = 4;

    public static final int BROADCAST_REQUEST           = 10;
    public static final int BROADCAST_REQUEST_REPEAT    = 11;
    public static final int BROADCAST_MANUAL_REQUEST    = 12;

    public static final int BROADCAST_RESPONSE          = 20;
    public static final int BROADCAST_RESPONSE_REPEAT   = 21;
    public static final int BROADCAST_MANUAL_RESPONSE   = 22;

    public static final int AUTHENTIFICATION_REQUEST    = 30; // Silent authentification
    public static final int AUTHENTIFICATION_RESPONSE   = 40; // Silent authentification


    public static final int AUTHORIZATION_REQUEST       = 50; // Usual transactions
    public static final int AUTHORIZATION_RESPONSE      = 60; 

    public static final int CONFIRMATION_REQUEST        = 70;
    public static final int CONFIRMATION_RESPONSE       = 80;

    public static final int ADVICE_REQUEST              = 90;
    public static final int ADVICE_RESPONSE             = 100;

    public static final int NEW_FACEPAY_ID              = 200;
    public static final int NEW_FACEPAY_ID_RESPONSE     = 210;
    public static final int NEW_ACCOUNT                 = 230;
    public static final int NEW_ACCOUNT_RESPONSE        = 240;

}
