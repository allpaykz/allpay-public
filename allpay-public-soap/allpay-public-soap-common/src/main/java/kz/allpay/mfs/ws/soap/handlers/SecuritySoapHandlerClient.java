package kz.allpay.mfs.ws.soap.handlers;

import kz.allpay.mfs.webshop.signature.SignatureService;
import kz.allpay.mfs.webshop.signature.SignatureServiceSoapImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.generateSOAPErrMessage;
import static kz.allpay.mfs.ws.soap.handlers.HandlerUtils.soapToString;

/**
 * @author magzhan.karasayev
 * @since 10/7/16 5:53 PM
 */
public class SecuritySoapHandlerClient extends AbstractSecuritySoapHandler {

    private static final Log logger = LogFactory.getLog(SecuritySoapHandlerClient.class);
    private static SignatureService signatureService = new SignatureServiceSoapImpl();
    private Integer certificateNumber;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public SecuritySoapHandlerClient(Integer certificateNumber, PrivateKey privateKey, PublicKey publicKey) {
        this.certificateNumber = certificateNumber;
        this.privateKey = privateKey; // new StaticTestKeyProvider().getPrivateKey("FIXME");
        this.publicKey = publicKey; // new StaticTestKeyProvider().getPrivateKey("FIXME");
    }

    @Override
    public boolean handleMessageImpl(SOAPMessageContext context) {
        Boolean isResponse = !((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY));
        return isResponse ? validateResponse(context) : signRequest(context);
    }

    private boolean validateResponse(SOAPMessageContext context) {
        try {
            String messageAsString = soapToString(context.getMessage());
            logger.info("got message as string = " + messageAsString);

            boolean isValid = signatureService.verifySignatureInXML(new StringReader(messageAsString), publicKey);
            if (!isValid) {
                generateSOAPErrMessage(context.getMessage(), "Not valid sign.");
            } else {
                logger.info("securitySoapHandler: sign validated");
            }
            return isValid;
        } catch (Exception e) {
            logger.warn("response was not validated", e);
            return false;
        }
    }

    private boolean signRequest(SOAPMessageContext context) {
        try {
            if (context.getMessage().getSOAPHeader() == null) {
                context.getMessage().getSOAPPart().getEnvelope().addHeader();
            }

            addCertificateNumber(context);

            String messageAsString = HandlerUtils.soapToString(context.getMessage());
            ByteArrayInputStream is = new ByteArrayInputStream(messageAsString.getBytes(StandardCharsets.UTF_8));
            String signed = signatureService.signXML(privateKey, is);
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

    private void addCertificateNumber(SOAPMessageContext context) throws SOAPException {
        QName qNamePassword = new QName(NAMESPACE_URI, CERTIFICATE_TAG);
        context.getMessage().getSOAPHeader().addChildElement(qNamePassword).addTextNode(certificateNumber.toString());
    }

    protected String postProcessMessage(String signed) {
        return signed;
    }
}

