package kz.allpay.mfs.webshop.signature;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class SignatureServiceImplTest {

    private SignatureServiceImpl signatureService = new SignatureServiceImpl();

    private static final Logger logger = Logger.getLogger(SignatureServiceImplTest.class.getName());

    static {
        TestUtils.setXmlDsigFinestLogging();
    }

    @Test
    public void testSignXML() throws Exception {
        InputStream is = SignatureServiceImplTest.class.getClassLoader().getResourceAsStream(
                "messages/webshopRequest-not-signed.xml");
        Assert.assertNotNull(is);
        String res = signatureService.signXML(TestUtils.getKeyPair().getPrivate(), is);
        System.out.println("res = " + res);
    }

    @Test
    public void testVerifySignatureInXML() throws Exception {
        InputStream is = SignatureServiceImplTest.class.getClassLoader().getResourceAsStream(
                "messages/webshopRequest-signed.xml");
        Assert.assertNotNull(is);
        boolean res = signatureService.verifySignatureInXML(new InputStreamReader(is), TestUtils.getKeyPair().getPublic());
        System.out.println("res = " + res);
    }

    @Test
    public void testVerifySignatureInXMLFormatted() throws Exception {
        InputStream is = SignatureServiceImplTest.class.getClassLoader().getResourceAsStream(
                "messages/webshopRequest-signed-formatted.xml");
        Assert.assertNotNull(is);
        boolean res = signatureService.verifySignatureInXML(new InputStreamReader(is), TestUtils.getKeyPair().getPublic());
        System.out.println("res = " + res);
    }
}