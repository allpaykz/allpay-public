package kz.allpay.mfs.ws.soap.handlers;

import kz.allpay.mfs.signature.keyproviders.KeyProvider;
import kz.allpay.mfs.signature.keyproviders.StaticTestKeyProvider;
import kz.allpay.mfs.webshop.signature.SignatureService;
import kz.allpay.mfs.webshop.signature.SignatureServiceSoapImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author magzhan.karasayev
 * @since 10/7/16 5:53 PM
 */
public class SecuritySoapHandlerClient extends AbstractSecuritySoapHandler {

    private static final Log logger = LogFactory.getLog(SecuritySoapHandlerClient.class);
    private static SignatureService signatureService = new SignatureServiceSoapImpl();
    private static KeyProvider keyProvider = new StaticTestKeyProvider();

    @Override
    public boolean handleMessageImpl(SOAPMessageContext context) {
        Boolean isResponse = !((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY));
        if (isResponse) {
            return true;
        }

        try {
            if (context.getMessage().getSOAPHeader() == null) {
                context.getMessage().getSOAPPart().getEnvelope().addHeader();
            }
            String messageAsString = HandlerUtils.soapToString(context.getMessage());
            ByteArrayInputStream is = new ByteArrayInputStream(messageAsString.getBytes(StandardCharsets.UTF_8));
            String signed = signatureService.signXML(keyProvider.getPrivateKey("FIXME"), is);
            SOAPMessage signedMessage = HandlerUtils.createSoapMessageFromString(postProcessMessage(signed));
            if (logger.isDebugEnabled()) {
                logger.debug("signedMessage = " + HandlerUtils.soapToString(signedMessage));
            }
            context.setMessage(signedMessage);
            return true;
        } catch (Exception e) {
            logger.error("could not sign soap message", e);
            return false;
        }
    }

    protected String postProcessMessage(String signed) {
        return signed;
    }
}

