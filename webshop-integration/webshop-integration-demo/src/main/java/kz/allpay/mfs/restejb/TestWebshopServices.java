package kz.allpay.mfs.restejb;

import java.util.List;
import kz.allpay.mfs.webshop.WebShopRequestTransformer;
import kz.allpay.mfs.webshop.WebShopResponseTransformer;
import kz.allpay.mfs.webshop.generated.request.MerchantType;
import kz.allpay.mfs.webshop.generated.request.WebShopRequestType;
import kz.allpay.mfs.webshop.generated.response.TransationType;
import kz.allpay.mfs.webshop.generated.response.WebShopResponseType;
import kz.allpay.mfs.webshop.signature.SignatureUtils;
import org.bouncycastle.openssl.PEMReader;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.math.BigDecimal;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static kz.allpay.mfs.restejb.TestPageServlet.mockTransactionStatusDataBase;

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
            @QueryParam("transactionTimeOutInSeconds") int transactionTimeOutInSeconds,
            @QueryParam("pemInput") String pemInput,
            @QueryParam("pemInputResponse") String pemInputResponse,
            @QueryParam("protocolVersion") String protocolVersion) {

        System.out.println("amount = " + amount);
        System.out.println("shopName = " + shopName);
        System.out.println("terminalId = " + terminalId);
        System.out.println("invoiceNumber = " + invoiceNumber);
        System.out.println("deleteRequiredFields = " + deleteRequiredFields);
        System.out.println("transactionTimeOutInSeconds = " + transactionTimeOutInSeconds);
        System.out.println("pemInput = " + pemInput);
        System.out.println("pemInputResponse = " + pemInputResponse);
        System.out.println("protocolVersion = " + protocolVersion);

        try {
            SavedPublicKeyStorage.put(invoiceNumber, getPublicKey(pemInputResponse));
            final String signedXML = addInvoiceToDatabase(amount, terminalId, invoiceNumber, shopName, pemInput,
                                                          deleteRequiredFields,transactionTimeOutInSeconds, protocolVersion);
            final BASE64Encoder encoder = new BASE64Encoder();
            final String base64XML = encoder.encode(signedXML.getBytes());
            final Map<String, String> result = new HashMap<>();
            result.put("webshopRequest", base64XML);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PublicKey getPublicKey(String pemInputResponse) throws IOException {
        final Reader reader = new StringReader(pemInputResponse);
        final PEMReader pemReader = new PEMReader(reader);
        return (PublicKey) pemReader.readObject();
    }



    private String addInvoiceToDatabase(String amount, String terminalId, String invoiceNumber, String shopName,
                                        String pemInput, Boolean deleteRequiredFields, int transactionTimeOutInSeconds,
                                        String protocolVersion) throws UnsupportedEncodingException {

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
            request.setProtocolVersion((protocolVersion != null && !protocolVersion.isEmpty()) ? BigDecimal.valueOf(Double.valueOf(protocolVersion)) : null);

            MerchantType merchantType = new MerchantType();

            if (!deleteRequiredFields) {
                merchantType.setMerchantID(terminalId);
                merchantType.setWalletID(terminalId);
            }
            request.setMerchant(merchantType);

            WebShopRequestTransformer transformer = new WebShopRequestTransformer();

            PrivateKey privateKey = readPrivateKey(pemInput);

            InputStream myInputStream = new ByteArrayInputStream(transformer.marshallRequestToByteArr(request));
            String signedRequestXml = SignatureUtils.signXML(privateKey, myInputStream);
            mockInvoiceDatabase.put(invoiceNumber, signedRequestXml);
            return signedRequestXml;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKey readPrivateKey(String pemInput) throws IOException {
        try (PEMReader pemReader = new PEMReader(new StringReader(pemInput))) {
            return ((KeyPair) pemReader.readObject()).getPrivate();
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

            final InputStream stream = new ByteArrayInputStream(decodedBytes);
            final WebShopResponseType response = transformer.unmarshallResponse(stream);

            PublicKey publicKey = SavedPublicKeyStorage.get(response.getInvoiceNumber());
            final boolean verified = SignatureUtils.verifySignatureInXML(decodedString, publicKey);
            logger.info("\n\tXML received is verified: " + verified);

            logger.info(response.getTransaction().getStatus());
            mockTransactionStatusDataBase.add(response.getTransaction());
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<WSCResp:WebShopCustomerResponse xsi:schemaLocation=\"http://allpay.kz/xsd/1.0.0/WebShopCustomerResponse.xsd\"\n" +
                    "                        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "                        xmlns:WSCResp=\"http://allpay.kz/xsd/1.0.0/WebShopCustomerResponse.xsd\">\n" +
                    "    <WSCResp:Status>DONE</WSCResp:Status>\n" +
                    "    <WSCResp:Reason></WSCResp:Reason>\n" +
                    "</WSCResp:WebShopCustomerResponse>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<WSCResp:WebShopCustomerResponse xsi:schemaLocation=\"http://allpay.kz/xsd/1.0.0/WebShopCustomerResponse.xsd\"\n" +
                    "                        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "                        xmlns:WSCResp=\"http://allpay.kz/xsd/1.0.0/WebShopCustomerResponse.xsd\">\n" +
                    "    <WSCResp:Status>FAIL</WSCResp:Status>\n" +
                    "    <WSCResp:Reason></WSCResp:Reason>\n" +
                    "</WSCResp:WebShopCustomerResponse>";
        }
    }

    @GET
    @Path("/getTransactions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransationType> getTransactions() {
        return mockTransactionStatusDataBase;
    }
}
