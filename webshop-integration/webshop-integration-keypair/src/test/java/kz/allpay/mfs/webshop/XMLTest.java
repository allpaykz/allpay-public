package kz.allpay.mfs.webshop;

import kz.allpay.mfs.webshop.generated.request.MerchantType;
import kz.allpay.mfs.webshop.generated.request.WebShopRequestType;
import kz.allpay.mfs.webshop.generated.customer.response.WebShopCustomerResponse;
import kz.allpay.mfs.webshop.generated.response.WebShopResponseType;
import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.signature.SignatureUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static kz.allpay.mfs.webshop.Constants.PRIVATE_KEY_FILE;


/**
 * @author aben.zhakenov
 */
public class XMLTest {


    @Test
    public void requestTransformTest() {
        System.out.println();
        System.out.println("test request");
        try {
            InputStream is = XMLTest.class.getResourceAsStream("/WebShopRequest.xml");

            Assert.assertNotNull(is);

            WebShopRequestTransformer transformer = new WebShopRequestTransformer();

            WebShopRequestType req = transformer.unmarshallRequest(is);
            Assert.assertNotNull(req);

            System.out.println("Shop Name = " + req.getShopName());
            System.out.println("Invoice Num = " + req.getInvoiceNumber());
            System.out.println("Failure link = " + req.getFailureLink());
            System.out.println("Response link = " + req.getResponseURL());

            System.out.println(transformer.marshallRequestToString(req));


        } catch (Exception ex) {
            Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void responseTransformTest() {
        System.out.println();
        System.out.println("test response");
        try {
            InputStream is = XMLTest.class.getResourceAsStream("/WebShopResponse.xml");
            Assert.assertNotNull(is);

            WebShopResponseTransformer transformer = new WebShopResponseTransformer();

            WebShopResponseType resp = transformer.unmarshallResponse(is);
            Assert.assertNotNull(resp);

            System.out.println("Invoice Num = " + resp.getInvoiceNumber());

            System.out.println(transformer.marshallResponseToString(resp));


        } catch (Exception ex) {
            Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Test
    public void marshalTest() throws IOException, InvalidKeySpecException, TransformerException, MarshalException, InstantiationException, XPathExpressionException, ParserConfigurationException, NoSuchAlgorithmException, IllegalAccessException, SAXException, InvalidAlgorithmParameterException, ClassNotFoundException, KeyException, XMLSignatureException, JAXBException {
        final WebShopRequestType request = new WebShopRequestType();
        request.setShopName("SHOP_NAME");

        request.setInvoiceNumber("INVOICE_NUMBER");

        request.setTotalCost(BigDecimal.valueOf(Double.valueOf(1)));
        request.setSuccessLink("TEST_SUCCES_LINK");
        request.setFailureLink("TEST_FAILURE_LINK");

        MerchantType merchantType = new MerchantType();

        merchantType.setMerchantID("terminalId");
        merchantType.setWalletID("terminalId");
        request.setMerchant(merchantType);

        WebShopRequestTransformer transformer = new WebShopRequestTransformer();
        String marshalled = transformer.marshallRequestToString(request);
        System.out.println(marshalled);
        WebShopRequestType requestType = transformer.unmarshallRequest(new ByteArrayInputStream(marshalled.getBytes()));


        InputStream privateKeyStream = XMLTest.class.getResourceAsStream(PRIVATE_KEY_FILE);
        PrivateKey privateKey = PrivateKeyReader.loadPrivateKeyFromFile(privateKeyStream);

        InputStream myInputStream = new ByteArrayInputStream(transformer.marshallRequestToByteArr(request));
        String signedRequestXml = SignatureUtils.signXML(privateKey, myInputStream);

        System.out.println(signedRequestXml);
    }

    @Test
    public void customerResponseTransformTest() {
        System.out.println();
        System.out.println("test customer response");
        try {
            InputStream is = XMLTest.class.getResourceAsStream("/WebShopCustomerResponse.xml");

            Assert.assertNotNull(is);

            WebShopCustomerResponse req = WebShopCustomerResponseTransformer.fromXml(is);
            Assert.assertNotNull(req);

            System.out.println("Status = " + req.getStatus());
            System.out.println("Reason = " + req.getReason());

        } catch (Exception ex) {
            Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
