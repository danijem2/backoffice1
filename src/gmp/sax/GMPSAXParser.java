package gmp.sax;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import gmp.terminal.local.gmptlv;
import gmp.terminal.local.gmprow;
import gmp.terminal.local.gmpplatform;

// Import your vendor's XMLReader implementation here

import org.apache.xerces.parsers.SAXParser;

/** The object is used when parsing the response from Host. Since Host
 * response is via Web service it will be an XML-formatted String */
public class GMPSAXParser {


    private String tag = null;
    public void settag(String t){ this.tag = t;}
    public String gettag(){ return this.tag;}
 private String value = null;
  public void setvalue(String v){ this.value = v;}
  public String getvalue(){ return this.value;}
   public String gmpline = null;
   public String getGmpline(){ return this.gmpline;}
  public void appendgmpline(String l){ this.gmpline = this.gmpline+l;}


    /**
* <b><code>MyContentHandler</code></b> implements the SAX
* <code>ContentHandler</code> interface and defines callback
* behavior for the SAX callbacks associated with an XML
* document's content.
*/
class MyContentHandler implements ContentHandler {




/** Hold onto the locator for location information */
private Locator locator;
 private GMPSAXParser parent = null;
public GMPSAXParser getparent(){ return this.parent;}
public MyContentHandler(GMPSAXParser s){
    parent = s;
 
}
/**
* <p>
* Provide reference to <code>Locator</code> which provides
* information about where in a document callbacks occur.
* </p>
*
* @param locator <code>Locator</code> object tied to callback
* process
*/
public void setDocumentLocator(Locator locator) {
    log(" SAX setDocumentLocator() called");
    // We save this for later use if desired.
    this.locator = locator;
}
/**
* <p>
* This indicates the start of a Document parse—this precedes
* all callbacks in all SAX Handlers with the sole exception
* of <code>{@link #setDocumentLocator}</code>.
* </p>
*
* @throws <code>SAXException</code> when things go wrong
*/
public void startDocument() throws SAXException {
    log(" SAX Parsing begins...");
}

/**
* <p>
* This indicates the end of a Document parse—this occurs after
* all callbacks in all SAX Handlers.</code>.
* </p>
*
* @throws <code>SAXException</code> when things go wrong
*/
public void endDocument() throws SAXException {
    log(" SAX...Parsing ends.");
}
/**
* <p>
* This indicates that a processing instruction (other than
* the XML declaration) has been encountered.
* </p>
*
* @param target <code>String</code> target of PI
* @param data <code>String</code containing all data sent to the PI.
* This typically looks like one or more attribute value
* pairs.
* @throws <code>SAXException</code> when things go wrong
*/
public void processingInstruction(String target, String data)
throws SAXException {
    log(" SAX PI: Target:" + target + " and Data:" + data);
}
/**
* <p>
* This indicates the beginning of an XML Namespace prefix
* mapping. Although this typically occurs within the root element
* of an XML document, it can occur at any point within the
* document. Note that a prefix mapping on an element triggers
* this callback <i>before</i> the callback for the actual element
* itself (<code>{@link #startElement}</code>) occurs.
* </p>
*
* @param prefix <code>String</code> prefix used for the namespace
* being reported
* @param uri <code>String</code> URI for the namespace
* being reported
* @throws <code>SAXException</code> when things go wrong
*/
public void startPrefixMapping(String prefix, String uri) {
    log(" SAX Mapping starts for prefix " + prefix + " mapped to URI " + uri);

}
/**
* <p>
* This indicates the end of a prefix mapping, when the namespace
CONTENT HANDLERS 57
Book Title, eMatter Edition
Copyright © 2000 O’Reilly & Associates, Inc. All rights reserved.
* reported in a <code>{@link #startPrefixMapping}</code> callback
* is no longer available.
* </p>
*
* @param prefix <code>String</code> of namespace being reported
* @throws <code>SAXException</code> when things go wrong
*/
public void endPrefixMapping(String prefix) {
    log(" SAX Mapping ends for prefix " + prefix);
}
/**
* <p>
* This reports the occurrence of an actual element. It includes
* the element's attributes, with the exception of XML vocabulary
* specific attributes, such as
* <code>xmlns:[namespace prefix]</code> and
* <code>xsi:schemaLocation</code>.
* </p>
*
* @param namespaceURI <code>String</code> namespace URI this element
* is associated with, or an empty
* <code>String</code>
* @param localName <code>String</code> name of element (with no
* namespace prefix, if one is present)
* @param rawName <code>String</code> XML 1.0 version of element name:
* [namespace prefix]:[localName]
* @param atts <code>Attributes</code> list for this element
* @throws <code>SAXException</code> when things go wrong
*/
public void startElement(String namespaceURI, String localName,
String rawName, Attributes atts)
throws SAXException {
    this.getparent().settag(localName);
    this.getparent().setvalue(null);
    log(" SAX startElement: " + localName);
    if (!namespaceURI.equals("")) {
        log(" SAX in namespace " + namespaceURI +" (" + rawName + ")");
        }
    else {
        log(" SAX has no associated namespace");
        }
    for (int i=0; i<atts.getLength(); i++)
        log(" SAX Attribute: " + atts.getLocalName(i) + "=" + atts.getValue(i));
   }
/**
* <p>
* Indicates the end of an element
* (<code>&lt;/[element name]&gt;</code>) is reached. Note that
* the parser does not distinguish between empty
* elements and non-empty elements, so this occurs uniformly.
* </p>
*
* @param namespaceURI <code>String</code> URI of namespace this
* element is associated with
* @param localName <code>String</code> name of element without prefix
* @param rawName <code>String</code> name of element in XML 1.0 form
* @throws <code>SAXException</code> when things go wrong
*/
public void endElement(String namespaceURI, String localName,String rawName)
throws SAXException {
    if( localName.equals(this.getparent().gettag())){
    this.getparent().appendgmpline(
            this.getparent().gettag()+ gmptlv.eqsign +
            this.getparent().getvalue()+gmptlv.delimiter);
    }
    log(" SAX GMPLINE: *" + this.getparent().getGmpline()+ "*\n");
    log(" SAX endElement: " + localName + "\n");
}
/**
* <p>
* This reports character data (within an element).
* </p>
*
* @param ch <code>char[]</code> character array with character data
* @param start <code>int</code> index in array where data starts.
* @param end <code>int</code> index in array where data ends.
* @throws <code>SAXException</code> when things go wrong
*/
public void characters(char[] ch, int start, int end)
throws SAXException {
    String s = new String(ch, start, end);
    this.getparent().setvalue(s);
   log(" SAX characters: " + s);
}
/**
* <p>
* This reports whitespace that can be ignored in the
* originating document. This is typically invoked only when
* validation is ocurring in the parsing process.
* </p>
*
* @param ch <code>char[]</code> character array with character data
* @param start <code>int</code> index in array where data starts.
* @param end <code>int</code> index in array where data ends.
* @throws <code>SAXException</code> when things go wrong
*/
public void ignorableWhitespace(char[] ch, int start, int end)
throws SAXException {
    String s = new String(ch, start, end);
    log(" SAX ignorableWhitespace: [" + s + "]");
}
/**
* <p>
* This reports an entity that is skipped by the parser. This
* should only occur for non-validating parsers, and then is still
* implementation-dependent behavior.
* </p>
*
* @param name <code>String</code> name of entity being skipped
* @throws <code>SAXException</code> when things go wrong
*/
public void skippedEntity(String name) throws SAXException {
   log(" SAX Skipping entity " + name);
}
    }
public static XMLReader createXMLReader (String className)
throws SAXException {
// Implementation
    return new SAXParser();
}


/**
* <b><code>MyErrorHandler</code></b> implements the SAX
* <code>ErrorHandler</code> interface and defines callback
* behavior for the SAX callbacks associated with an XML
* document's errors.
*/
class MyErrorHandler implements ErrorHandler {
/**
* <p>
* This will report a warning that has occurred; this indicates
 * * that while no XML rules were broken, something appears
* to be incorrect or missing.
* </p>
*
* @param exception <code>SAXParseException</code> that occurred.
* @throws <code>SAXException</code> when things go wrong
*/
public void warning(SAXParseException exception)
throws SAXException {
   log(" SAX**Parsing Warning**\n" + " Line: " + exception.getLineNumber() + "\n" +
        " URI: " + exception.getSystemId() + "\n" +" Message: " +exception.getMessage());
        throw new SAXException("Warning encountered");
}
/**
* <p>
* This will report an error that has occurred; this indicates
* that a rule was broken, typically in validation, but that
* parsing can reasonably continue.
* </p>
*
* @param exception <code>SAXParseException</code> that occurred.
* @throws <code>SAXException</code> when things go wrong
*/
public void error(SAXParseException exception)
throws SAXException {
    log(" SAX**Parsing Error**\n" + " Line: " + exception.getLineNumber() + "\n" +
       " URI: " + exception.getSystemId() + "\n" + " Message: " + exception.getMessage());
throw new SAXException("Error encountered");
}
/**
* <p>
* This will report a fatal error that has occurred; this indicates
* that a rule has been broken that makes continued parsing either
* impossible or an almost certain waste of time.
* </p>
*
* @param exception <code>SAXParseException</code> that occurred.
* @throws <code>SAXException</code> when things go wrong
*/
public void fatalError(SAXParseException exception)
throws SAXException {
    log(" SAX**Parsing Fatal Error**\n" + " Line: " + exception.getLineNumber() + "\n" +
    " URI: " + exception.getSystemId() + "\n" + " Message: " + exception.getMessage());
throw new SAXException("Fatal Error encountered");
}

}
/**
* <p>
* This provides a command-line entry point for this demo.
* </p>
*/

/**
* <p>
* This parses the file or a message, using registered SAX handlers, and outputs
* the events in the parsing process cycle.
* </p>
 * * @param uri <code>String</code> URI of file to parse.
*/
public void performParsing(String uri, int mode) {


    if( mode != 1)
        log(" SAX parsing XML File: " + uri + "\n\n");
    else
        log(" SAX Parsing XML STRING: " + uri + "\n\n");

     this.tag = new String();
     this.value = new String();
     // Get instances of our handlers
    ContentHandler contentHandler = new MyContentHandler(this);
    ErrorHandler errorHandler = new MyErrorHandler();

    try {
        // Instantiate a parser
XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        // Instantiate a parser !!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //XMLReader parser = new SAXParser();
      // ORRR Instantiate a parser
     /* XMLReader parser =
        XMLReaderFactory.createXMLReader(PropertiesReader().getInstance().getProperty("parserClass")); */

        // Register the content handler
        parser.setContentHandler(contentHandler);
        // Register the error handler
        parser.setErrorHandler(errorHandler);

//        gmpplatform.Log("URI for java.io.StringReader(uri) is |"+uri.toString());
        // Parse the document
        if( mode != 1) parser.parse(uri);
        else parser.parse( new org.xml.sax.InputSource(new java.io.StringReader(uri)));
        log(" SAX RESP gmprow :+"+ this.getGmpRow().write()+"+");
        }
    catch (IOException e) {
        log(" SAX Error reading URI: " + e.getMessage());
        }
    catch (SAXException e) {
        log(" SAX Error in parsing: " + e.getMessage());
        }

}

public gmprow getGmpRow(){
    return new gmprow(this.gmpline);}

public GMPSAXParser(){

   this.tag = new String();
   this.value = new String();
   this.gmpline = new String(); 
  }

  private int DEBUG = 0;
  private String ClassName = "GMPSAXParser";
  private void log(String s){
   if(DEBUG > 200){ System.out.println(this.ClassName+" "+s);} // Screen log
  // if(DEBUG > 100){ Util.log(this.ClassName+" "+s);}  // Package log
   if(DEBUG > 0){ gmpplatform.Log(this.ClassName+" "+s);}  // GMP log
  }
  public void setDEBUG(int dbg){ this.DEBUG = dbg;}



}
