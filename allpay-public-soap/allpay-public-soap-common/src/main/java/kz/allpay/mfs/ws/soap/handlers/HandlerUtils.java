package kz.allpay.mfs.ws.soap.handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
 * @author magzhan.karasayev
 * @since 10/7/16 5:56 PM
 */
public class HandlerUtils {

    private static final Log logger = LogFactory.getLog(HandlerUtils.class);

    public static String getMessageAsString(SOAPMessageContext context) throws SOAPException, IOException {
//        SOAPMessage msg = context.getMessage();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        msg.writeTo(out);
//        return new String(out.toByteArray());
        final SOAPMessage message = context.getMessage();
        final StringWriter sw = new StringWriter();

        try {
            TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(message.getSOAPPart()),
                    new StreamResult(sw));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return sw.toString();
    }

    public static org.w3c.dom.Node generateDomTree(SOAPMessage soapMessage) throws SOAPException, ParserConfigurationException, SAXException, IOException, TransformerException {
        logger.info("Generating the DOM tree...");
        // Get input source
        Source source = soapMessage.getSOAPPart().getContent();
        Node root = null;

        if (source instanceof DOMSource) {
            root = ((DOMSource)source).getNode();

        } else if (source instanceof SAXSource) {
            InputSource inSource = ((SAXSource)source).getInputSource();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = null;

            synchronized (dbf) {
                db = dbf.newDocumentBuilder();
            }
            Document doc = db.parse(inSource);
            root = doc.getDocumentElement();

        } else {
            Document res = loadXMLFrom(soapToString(soapMessage));
            return res;
//            throw new SOAPException("error: cannot convert SOAP message (" +
//                    source.getClass().getName() + ") into a W3C DOM tree");
        }

        return root;
    }

    public static org.w3c.dom.Document loadXMLFrom(String xml)
            throws org.xml.sax.SAXException, java.io.IOException {
        return loadXMLFrom(new java.io.ByteArrayInputStream(xml.getBytes()));
    }

    public static org.w3c.dom.Document loadXMLFrom(java.io.InputStream is)
            throws org.xml.sax.SAXException, java.io.IOException {
        javax.xml.parsers.DocumentBuilderFactory factory =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        javax.xml.parsers.DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        }
        catch (javax.xml.parsers.ParserConfigurationException ex) {
        }
        org.w3c.dom.Document doc = builder.parse(is);
        is.close();
        return doc;
    }

    public static String soapToString(SOAPMessage soapMessage) throws SOAPException, IOException {
        Source source = soapMessage.getSOAPPart().getContent();
        Node root = null;
        // to string
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMessage.writeTo(out);
        String strMsg = new String(out.toByteArray());
        logger.debug("strMsg = '" + strMsg + "'");
        return strMsg;
    }

    /*
     * Outputs DOM representation to the standard output stream.
     *
     * @param root The DOM representation to be outputted
     */
    public static String nodeToString(Node root)
            throws TransformerException, TransformerConfigurationException {
        // Create a new transformer object
        Transformer transformer =
                TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // Dump the DOM representation to standard output
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(root), new StreamResult(writer));
        String output = writer.getBuffer().toString();//.replaceAll("\n|\r", "");
        logger.info("converted to String length = " + output.length());
        return output;
    }

    public static void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            if (soapBody.getFault() == null) {
                SOAPFault soapFault = soapBody.addFault();
                soapFault.setFaultString(reason);
            }
            throw new SOAPFaultException(soapBody.getFault());
        } catch (SOAPException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void increaseLogging() {
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINEST);
        Log l = LogFactory.getLog("org.jcp.xml.dsig.internal.dom");
    }

    public static SOAPMessage createSoapMessageFromString(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
        return message;
    }
}
