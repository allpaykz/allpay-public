package kz.allpay.mfs.webshop.signature;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
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
        String s = signatureServiceSoap.signXML(TestUtils.getKeyPair().getPrivate(), cannonicalizeInputStream(is));
        System.out.println("s = " + s);
        System.out.flush();
    }

    @Test
    public void testVerifySignatureInXML() throws Exception {
        InputStream is = SignatureServiceSoapImplTest.class.getClassLoader().getResourceAsStream(
                "soap-messages/getTransactionRequest-1.xml");
        boolean re = signatureServiceSoap.verifySignatureInXML(new InputStreamReader(cannonicalizeInputStream(is)), TestUtils.getKeyPair().getPublic());
        Assert.assertTrue(re);
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Test
    public void testVerifySignatureInXML_Formatted() throws Exception {
        InputStream is = SignatureServiceSoapImplTest.class.getClassLoader().getResourceAsStream(
                "soap-messages/getTransactionRequest-1-formatted-same-signature.xml");
        boolean re = signatureServiceSoap.verifySignatureInXML(new InputStreamReader(cannonicalizeInputStream(is)), TestUtils.getKeyPair().getPublic());
        Assert.assertTrue(re);
    }

    private ByteArrayInputStream cannonicalizeInputStream(InputStream is) {
        org.apache.xml.security.Init.init();
        byte[] bytes = SignatureUtils.canonicalizedByteArray(convertStreamToString(is));
        return new ByteArrayInputStream(bytes);
    }

    @Test
    public void testSignAndVerifySignature() throws Exception {
        InputStream is = SignatureServiceSoapImplTest.class.getClassLoader().getResourceAsStream(
                "soap-messages/getTransactionRequest-1-not-signed.xml");
        String s = signatureServiceSoap.signXML(TestUtils.getKeyPair().getPrivate(), cannonicalizeInputStream(is));
        Reader reader = new InputStreamReader(cannonicalizeInputStream(new ByteArrayInputStream(s.getBytes("UTF-8"))));
        boolean re = signatureServiceSoap.verifySignatureInXML(reader, TestUtils.getKeyPair().getPublic());
        Assert.assertTrue(re);
    }
}