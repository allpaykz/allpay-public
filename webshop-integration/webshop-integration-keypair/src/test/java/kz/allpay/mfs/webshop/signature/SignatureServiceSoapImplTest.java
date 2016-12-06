package kz.allpay.mfs.webshop.signature;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.logging.Logger;

public class SignatureServiceSoapImplTest {

    private SignatureServiceSoapImpl signatureServiceSoap = new SignatureServiceSoapImpl();

    private static final Logger logger = Logger.getLogger(SignatureServiceSoapImplTest.class.getName());

    static {
        TestUtils.setXmlDsigFinestLogging();
    }

    @Test
    public void testSignXML() throws Exception {
        InputStream is = SignatureServiceSoapImplTest.class.getClassLoader().getResourceAsStream(
                "soap-messages/getTransactionRequest-1-not-signed.xml");
        String s = signatureServiceSoap.signXML(TestUtils.getKeyPair().getPrivate(), is);
        System.out.println("s = " + s);
        System.out.flush();
    }

    @Test(enabled = false)
    public void testVerifySignatureInXML() throws Exception {
        InputStream is = SignatureServiceSoapImplTest.class.getClassLoader().getResourceAsStream(
                "soap-messages/getTransactionRequest-1.xml");
        boolean re = signatureServiceSoap.verifySignatureInXML(new InputStreamReader(is), TestUtils.getKeyPair().getPublic());
        Assert.assertTrue(re);
    }

    @Test(enabled = false)
    public void testVerifySignatureInXML_Formatted() throws Exception {
        InputStream is = SignatureServiceSoapImplTest.class.getClassLoader().getResourceAsStream(
                "soap-messages/getTransactionRequest-1-formatted-same-signature.xml");
        boolean re = signatureServiceSoap.verifySignatureInXML(new InputStreamReader(is), TestUtils.getKeyPair().getPublic());
        Assert.assertTrue(re);
    }

    @Test(enabled = false)
    public void testSignAndVerifySignature() throws Exception {
        InputStream is = SignatureServiceSoapImplTest.class.getClassLoader().getResourceAsStream(
                "soap-messages/getTransactionRequest-1-not-signed.xml");
        String s = signatureServiceSoap.signXML(TestUtils.getKeyPair().getPrivate(), is);
        boolean re = signatureServiceSoap.verifySignatureInXML(new StringReader(s), TestUtils.getKeyPair().getPublic());
        Assert.assertTrue(re);
    }
}