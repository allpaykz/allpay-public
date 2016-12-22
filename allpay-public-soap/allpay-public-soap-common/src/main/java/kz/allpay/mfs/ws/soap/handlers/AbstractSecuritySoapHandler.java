package kz.allpay.mfs.ws.soap.handlers;

import kz.allpay.mfs.webshop.signature.SignatureService;
import kz.allpay.mfs.webshop.signature.SignatureServiceSoapImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Set;

import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.soapToString;

public abstract class AbstractSecuritySoapHandler implements SOAPHandler<SOAPMessageContext> {

    private static final String WSSE_NS_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final QName QNAME_WSSE_USERNAMETOKEN = new QName(WSSE_NS_URI, "UsernameToken");
    private static final QName QNAME_WSSE_USERNAME = new QName(WSSE_NS_URI, "Username");
    private static final QName QNAME_WSSE_PASSWORD = new QName(WSSE_NS_URI, "Password");

    protected final String NAMESPACE_URI = "https://allpay.kz";
    protected final String CERTIFICATE_TAG = "certificateNumber";
    protected final String REQUEST_DSIG_TAG = "requestDsig";

    private static final Log logger = LogFactory.getLog(AbstractSecuritySoapHandler.class.getName());

    private SignatureService signatureService = new SignatureServiceSoapImpl();


    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
            String messageAsString = soapToString(context.getMessage());
            logger.info("got message as string = " + messageAsString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handleMessageImpl(context);
    }

    public abstract boolean handleMessageImpl(SOAPMessageContext context);

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    protected final String getByXPath(String messageAsString, String expression) throws XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        xpath.setNamespaceContext(new NamespaceContext() {
            public String getNamespaceURI(String prefix) {
                if (prefix == null) throw new NullPointerException("Null prefix");
                else if ("SOAP-ENV".equals(prefix)) return "http://schemas.xmlsoap.org/soap/envelope/";
                else if ("soap".    equals(prefix)) return "http://schemas.xmlsoap.org/soap/envelope/";
                else if ("dsig".    equals(prefix)) return "http://www.w3.org/2000/09/xmldsig#";
                else if ("AP".      equals(prefix)) return NAMESPACE_URI;
                else if ("xml".equals(prefix)) return XMLConstants.XML_NS_URI;
                return XMLConstants.NULL_NS_URI;
            }
            // This method isn't necessary for XPath processing.
            public String getPrefix(String uri) {
                throw new UnsupportedOperationException();
            }
            // This method isn't necessary for XPath processing either.
            public Iterator getPrefixes(String uri) {
                throw new UnsupportedOperationException();
            }
        });

        InputSource source1 = new InputSource(new StringReader(messageAsString));
        String msg = xpath.evaluate(expression, source1);
        return msg;
    }

    protected final String getDsig(String messageAsString) throws XPathExpressionException {
        return getByXPath(messageAsString, "//soap:Header/dsig:Signature/dsig:SignedInfo/dsig:Reference/dsig:DigestValue");
    }
}