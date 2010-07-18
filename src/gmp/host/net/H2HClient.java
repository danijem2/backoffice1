package gmp.host.net;


import gmp.sax.*;

import java.net.HttpURLConnection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import gmp.terminal.local.gmpplatform;

/** This class implements Terminal-to-Host GMP client. This client sends requests
 for authentifications, authorizastions, reconciliation etc. */
public class H2HClient implements Client
{

    private CommDevice commdevice ;
    private Server server;
    private Protocol protocol;
    private Architecture architecture;

    //private boolean quiet = false;
    private String method = null;
    private InputStream body = null;
    private URL url = null;
    private BufferedImage image = null;

    public URL getURL(){return this.url;}

    public CommDevice getCommDevice(){ return this.commdevice;}
    public Server getServer(){ return this.server;}
    public Protocol getProtocol(){ return this.protocol;}
    public Architecture getArchitecture(){ return this.architecture;}
    public H2HClient(CommDevice n, Server h, HttpProtocol p)
    {
       super();
       gmpplatform.Log("H2HClient(CommDevice, Server,HttpProtocol):Start instantiating H2HCLient .........");
       this.server = new Server(h);
       this.protocol = new HttpProtocol(p);
       this.architecture = new RESTArchitecture();
       this.commdevice = new CommDevice();
       this.method = "POST";

       try { this.url = new URL(p.getTag()+"://"+h.geturl());}
       catch (MalformedURLException ex) {
            gmpplatform.Log("H2HClient:Bad url"+ex.getMessage());
          }
       gmpplatform.Log("H2HClient END instantiate H2H CLient ......");
     }
     public H2HClient(H2HClient rc)
    {
       super();
       gmpplatform.Log("H2HClient(H2HCLient) instantiate .........");
       this.server = new Server(rc.getServer());
       this.protocol = new HttpProtocol((HttpProtocol)rc.getProtocol());
       this.commdevice = new CommDevice(rc.getCommDevice());
       this.method = "GET";
       try { this.url = new URL(((HttpProtocol)this.protocol).getTag()+"://"+this.getServer().geturl()) ; }
       catch (MalformedURLException ex) {
           gmpplatform.Log("Bad url H2HClient(H2HClient) :" + this.getServer().geturl()+ex.getMessage()); }
     }


    public ResponseSwing Send( Request req )     throws IOException
    {
        gmpplatform.Log("Sending over H2H req |"+req.write()+"|");

        URL urllocal = new URL(
                this.url.getProtocol() + "://" +
                this.url.getAuthority() + "/" +
                req.getServiceScript() + "?" +
                req.write() )
                ;

        HttpURLConnection connection = (HttpURLConnection)urllocal.openConnection();
        connection.setRequestMethod(method);

        byte buffer[] = new byte[8192];
        int read = 0;
        if (body != null)
        {
            connection.setDoOutput(true);

            OutputStream output = connection.getOutputStream();
            while ((read = body.read(buffer)) != -1)
            {
                output.write(buffer, 0, read);
            }
        }

        // do request
        long time = System.currentTimeMillis();
        connection.connect();

        InputStream responseBodyStream = connection.getInputStream();
        StringBuffer responseBody = new StringBuffer();
        while ((read = responseBodyStream.read(buffer)) != -1)
        {
            responseBody.append(new String(buffer, 0, read));
        }
        connection.disconnect();
        time = System.currentTimeMillis() - time;


      // gmpplatform.Log("H2HClient[read " + responseBody.length() + " chars in " + time + "ms]\n");

        // look at headers
        // the 0th header has a null key, and the value is the response line ("HTTP/1.1 200 OK" or whatever)
      /*
            String header = null;
            String headerValue = null;
            int index = 0;
            while ((headerValue = connection.getHeaderField(index)) != null)
            {
                header = connection.getHeaderFieldKey(index);
                if (header == null)
                     System.out.println(headerValue) ;
                else
                     System.out.println(header + ": " + headerValue) ;
                index++;
            }
        } */

        // dump body
        gmpplatform.Log("H2HClient Unwrapping response"+  responseBody.toString()) ;
        String tmp = this.protocol.Unwrap(responseBody.toString());
        gmpplatform.Log("H2HClient AFTER unwrapping response"+  tmp +" going to unwrapp architecture ...") ;
        GMPSAXParser parserDemo = new GMPSAXParser();
        parserDemo.performParsing(tmp,1);
        gmpplatform.Log("H2HClient AFTER ARCHITECTURE unwrapp gmprow :\n" +
                "*"+ parserDemo.getGmpRow().write()+"*");

        return new ResponseSwing(parserDemo.getGmpRow()); //??? PERHAPS WE SHOULD RETURN architectural level ???
    }

}