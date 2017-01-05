package kz.allpay.mfs.ws.soap.handlers;

import kz.allpay.mfs.webshop.signature.SignatureService;
import kz.allpay.mfs.webshop.signature.SignatureServiceSoapImpl;
import kz.allpay.mfs.ws.soap.util.SoapUtils;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.security.c14n.Canonicalizer;

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

    static {
        org.apache.xml.security.Init.init();
    }

    private static final ThreadLocal<RequestModel> requestModel = new ThreadLocal<>();

    protected static class RequestModel {
        private final String requestDsig;

        public RequestModel(String requestDsig) {
            this.requestDsig = requestDsig;
        }
    }

    protected RequestModel popRequestModel() {
        RequestModel requestModel = this.requestModel.get();
        this.requestModel.set(null);
        return requestModel;
    }

    private void setRequestModel(RequestModel value) {
        requestModel.set(value);
    }


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

            String actualRequestDsig = getByXPath(messageAsString, "//soap:Header/AP:requestDsig");
            if (actualRequestDsig == null || !actualRequestDsig.equals(popRequestModel().requestDsig)) {
                generateSOAPErrMessage(context.getMessage(), "Not valid sign. - requester dsig");
            }
            final Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS);
            final byte canonXmlBytes[] = canon.canonicalize(messageAsString.getBytes());

            boolean isValid = signatureService.verifySignatureInXML(new StringReader( new String(canonXmlBytes)), publicKey);
            if (!isValid) {
                generateSOAPErrMessage(context.getMessage(), "Not valid sign.");
            } else {
                logger.info("securitySoapHandler: sign validated");
            }
            return isValid;
        } catch (Exception e) {
            logger.info(e);
            throw SoapUtils.createSoapFaultException(TransactionManagementV1_0.TARGET_NAMESPACE, e);
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
            setRequestModel(new RequestModel(getDsig(signed)));
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

