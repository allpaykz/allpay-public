package kz.allpay.mfs.ws.soap.handlers;

import kz.allpay.mfs.webshop.signature.SignatureService;
import kz.allpay.mfs.webshop.signature.SignatureServiceSoapImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.io.StringReader;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.generateSOAPErrMessage;
import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.soapToString;

/**
 * @author magzhan.karasayev
 * @since 10/7/16 5:53 PM
 */
public class SecuritySoapHandlerServer extends AbstractSecuritySoapHandler {

    private static final Log logger = LogFactory.getLog(SecuritySoapHandlerServer.class);

    private static SignatureService signatureService = new SignatureServiceSoapImpl();

    @Override
    public boolean handleMessageImpl(SOAPMessageContext context) {
        Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isResponse) {
            return true;
        }
        try {
            String messageAsString = soapToString(context.getMessage());
            logger.info("got message as string = " + messageAsString);

            Integer certificateNumber = getCertificateNumber(context.getMessage());
            logger.info("certificate number = " + certificateNumber);

            boolean isValid = signatureService.verifySignatureInXML(
                    new StringReader(messageAsString), getPublicKey(certificateNumber));
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

    private Integer getCertificateNumber(SOAPMessage message) throws SOAPException {
        NodeList elementsByTagNameNS = message.getSOAPHeader().getElementsByTagNameNS(NAMESPACE_URI, CERTIFICATE_TAG);
        if (elementsByTagNameNS.getLength() != 1 ||
                elementsByTagNameNS.item(0).getChildNodes().getLength() != 1) {
            throw new SOAPException("Header " + NAMESPACE_URI + " " + CERTIFICATE_TAG + " not provided");
        }
        String nodeValue = elementsByTagNameNS.item(0).getChildNodes().item(0).getNodeValue();
        return Integer.parseInt(nodeValue);
    }

    protected PublicKey getPublicKey(Integer message) throws IOException, InvalidKeySpecException {
        throw new RuntimeException("not yet implemented");
    }
}
