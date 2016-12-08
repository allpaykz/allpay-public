package kz.allpay.mfs.ws.soap.handlers;

import kz.allpay.mfs.signature.keyproviders.KeyProvider;
import kz.allpay.mfs.signature.keyproviders.StaticTestKeyProvider;
import kz.allpay.mfs.webshop.signature.SignatureService;
import kz.allpay.mfs.webshop.signature.SignatureServiceSoapImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.StringReader;

import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.generateSOAPErrMessage;
import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.soapToString;

/**
 * @author magzhan.karasayev
 * @since 10/7/16 5:53 PM
 */
public class SecuritySoapHandlerServer extends AbstractSecuritySoapHandler {

    private static final Log logger = LogFactory.getLog(SecuritySoapHandlerServer.class);

    private static SignatureService signatureService = new SignatureServiceSoapImpl();

    private static KeyProvider keyProvider = new StaticTestKeyProvider();

    @Override
    public boolean handleMessageImpl(SOAPMessageContext context) {
        Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isResponse) {
            return true;
        }
        try {
            String messageAsString = soapToString(context.getMessage());
            logger.info("got message as string = " + messageAsString);

            boolean isValid = signatureService.verifySignatureInXML(
                    new StringReader(messageAsString), keyProvider.getPublicKey("FIXME-merchantId"));
            if (!isValid) {
                generateSOAPErrMessage(context.getMessage(), "Not valid sign.");
            } else {
                logger.info("securitySoapHandler: sign validated");
            }
            return isValid;
        } catch (SignatureService.SignatureException e) {
            e.printStackTrace();
            generateSOAPErrMessage(context.getMessage(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            generateSOAPErrMessage(context.getMessage(), "another exception");
        }

        return true;
    }
}
