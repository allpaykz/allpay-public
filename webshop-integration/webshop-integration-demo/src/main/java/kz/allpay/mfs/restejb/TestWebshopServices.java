package kz.allpay.mfs.restejb;

import kz.allpay.mfs.webshop.WebShopRequestTransformer;
import kz.allpay.mfs.webshop.WebShopResponseTransformer;
import kz.allpay.mfs.webshop.generated.request.MerchantType;
import kz.allpay.mfs.webshop.generated.request.WebShopRequestType;
import kz.allpay.mfs.webshop.generated.response.WebShopResponseType;
import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.keys.PublicKeyReader;
import kz.allpay.mfs.webshop.signature.SignatureUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static kz.allpay.mfs.restejb.TestPageServlet.mockTransactionStatusDataBase;
import static kz.allpay.mfs.webshop.Constants.*;

/**
 * User: Sanzhar Aubakirov
 * Date: 5/3/16
 */
@Stateless
@Path("test")
public class TestWebshopServices {
    private static final String TARGET_IP = TestPropertiesUtils.getServicesIp();
    private static final String TEST_SUCCES_LINK = "http://" + TARGET_IP + "/webshop-integration-rest/transactions.jsp";
    private static final String TEST_FAILURE_LINK = "http://" + TARGET_IP + "/webshop-integration-rest/failure.jsp";
    private static final String TEST_RESPONSE_URL = "http://" + TARGET_IP + "/webshop-integration-rest/webresources/test/webshopResponse/";
    private final static Map<String, String> mockInvoiceDatabase = new HashMap<>();
    private Logger logger = Logger.getLogger(TestWebshopServices.class.toString());

    @GET
    @Path("/getSignedXML")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getSignedXML(
            @QueryParam("amount") String amount,
            @QueryParam("shopName") String shopName,
            @QueryParam("terminalId") String terminalId,
            @QueryParam("invoiceNumber") String invoiceNumber,
            @QueryParam("deleteRequiredFields") Boolean deleteRequiredFields,
            @QueryParam("wrongKeyFile") Boolean wrongKeyFile,
            @QueryParam("transactionTimeOutInSeconds") int transactionTimeOutInSeconds) {

        System.out.println("amount = " + amount);
        System.out.println("shopName = " + shopName);
        System.out.println("terminalId = " + terminalId);
        System.out.println("invoiceNumber = " + invoiceNumber);
        System.out.println("deleteRequiredFields = " + deleteRequiredFields);
        System.out.println("wrongKeyFile = " + wrongKeyFile);
        System.out.println("transactionTimeOutInSeconds = " + transactionTimeOutInSeconds);

        try {
            final String signedXML = addInvoiceToDatabase(amount, terminalId, invoiceNumber, shopName, wrongKeyFile,
                                                          deleteRequiredFields,transactionTimeOutInSeconds);
            final BASE64Encoder encoder = new BASE64Encoder();
            final String base64XML = encoder.encode(signedXML.getBytes());
            final Map<String, String> result = new HashMap<>();
            result.put("webshopRequest", base64XML);

            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String addInvoiceToDatabase(String amount, String terminalId,
                                        String invoiceNumber, String shopName, Boolean wrongKeyFile,
                                        Boolean deleteRequiredFields,int transactionTimeOutInSeconds) throws UnsupportedEncodingException {

        try {
            final WebShopRequestType request = new WebShopRequestType();
            request.setShopName(shopName);

            if (!deleteRequiredFields) {
                request.setInvoiceNumber(invoiceNumber);
            }
            request.setTimeoutInSeconds(transactionTimeOutInSeconds);
            request.setTotalCost(BigDecimal.valueOf(Double.valueOf(amount)));
            request.setSuccessLink(TEST_SUCCES_LINK);
            request.setFailureLink(TEST_FAILURE_LINK);
            request.setResponseURL(TEST_RESPONSE_URL);

            MerchantType merchantType = new MerchantType();

            if (!deleteRequiredFields) {
                merchantType.setMerchantID(terminalId);
                merchantType.setWalletID(terminalId);
            }
            request.setMerchant(merchantType);

            WebShopRequestTransformer transformer = new WebShopRequestTransformer();

            InputStream privateKeyStream = TestWebshopServices.class.getResourceAsStream(
                    wrongKeyFile ? PRIVATE_WRONG_KEY_FILE : PRIVATE_KEY_FILE);
            PrivateKey privateKey = PrivateKeyReader.loadPrivateKeyFromFile(privateKeyStream);

            InputStream myInputStream = new ByteArrayInputStream(transformer.marshallRequestToByteArr(request));
            String signedRequestXml = SignatureUtils.signXML(privateKey, myInputStream);
            mockInvoiceDatabase.put(invoiceNumber, signedRequestXml);
            return signedRequestXml;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/webshopResponse")
    @Consumes(MediaType.APPLICATION_XML)
    public String receiveResponse(String xml) {
        final WebShopResponseTransformer transformer = new WebShopResponseTransformer();
        try {
            logger.info("\n" + xml);
            final BASE64Decoder decoder = new BASE64Decoder();
            final byte[] decodedBytes = decoder.decodeBuffer(xml);
            final String decodedString = new String(decodedBytes, "UTF-8");
            logger.info("\n" + decodedString);

            final InputStream keyStream = TestWebshopServices.class.getResourceAsStream(PUBLIC_KEY_FILE);
            final PublicKey publicKey = PublicKeyReader.loadPublicKeyFromFile(keyStream);
            final boolean verified = SignatureUtils.verifySignatureInXML(decodedString, publicKey);
            logger.info("\n\tXML received is verified: " + verified);

            final InputStream stream = new ByteArrayInputStream(decodedBytes);
            final WebShopResponseType response = transformer.unmarshallResponse(stream);

            logger.info(response.getTransaction().getStatus());
            mockTransactionStatusDataBase.put(response.getTransaction().getTransactionId(),
                                              response.getTransaction().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @GET
    @Path("/getTransactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getTransactions() {
        return mockTransactionStatusDataBase;
    }
}
