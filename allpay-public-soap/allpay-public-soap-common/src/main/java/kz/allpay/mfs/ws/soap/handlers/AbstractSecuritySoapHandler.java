package kz.allpay.mfs.ws.soap.handlers;

import kz.allpay.mfs.webshop.signature.SignatureService;
import kz.allpay.mfs.webshop.signature.SignatureServiceSoapImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.soapToString;

public abstract class AbstractSecuritySoapHandler implements SOAPHandler<SOAPMessageContext> {

    private static final String WSSE_NS_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final QName QNAME_WSSE_USERNAMETOKEN = new QName(WSSE_NS_URI, "UsernameToken");
    private static final QName QNAME_WSSE_USERNAME = new QName(WSSE_NS_URI, "Username");
    private static final QName QNAME_WSSE_PASSWORD = new QName(WSSE_NS_URI, "Password");

    protected final String NAMESPACE_URI = "https://allpay.kz";
    protected final String CERTIFICATE_TAG = "certificateNumber";

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
}