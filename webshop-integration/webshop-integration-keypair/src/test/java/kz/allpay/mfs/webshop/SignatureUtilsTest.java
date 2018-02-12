package kz.allpay.mfs.webshop;

import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import kz.allpay.mfs.webshop.keys.KeyGenerator;
import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.keys.PublicKeyReader;
import kz.allpay.mfs.webshop.signature.SignatureUtils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import static kz.allpay.mfs.webshop.Constants.PRIVATE_KEY1_FILE;
import static kz.allpay.mfs.webshop.Constants.PRIVATE_KEY_FILE;
import static kz.allpay.mfs.webshop.Constants.PUBLIC_KEY_FILE;

/**
 * User: Sanzhar Aubakirov
 * Date: 4/25/16
 */
public class SignatureUtilsTest {


    private static final String TMP_FOLDER = System.getProperty("java.io.tmpdir");
    private static final String NAME_PREFIX = "TEST";

    static {
        /**
         * com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException:
         * You must initialize the xml-security library correctly before you use it.
         * Call the static method "com.sun.org.apache.xml.internal.security.Init.init();"
         * to do that before you use any functionality from that library.
         */
        com.sun.org.apache.xml.internal.security.Init.init();
    }

    @Test
    public void generateAndSave() throws IOException {
        final KeyGenerator kg = new KeyGenerator();
        kg.generateAndSave(TMP_FOLDER, NAME_PREFIX);
    }

    @Test(groups = "init")
    public void read() throws IOException, InvalidKeySpecException, NoSuchProviderException {
        InputStream privateKeyStream = SignatureUtilsTest.class.getResourceAsStream(PRIVATE_KEY_FILE);
        InputStream publicKeyStream = SignatureUtilsTest.class.getResourceAsStream(PUBLIC_KEY_FILE);
        PublicKey publicKey = PublicKeyReader.loadPublicKeyFromFile(publicKeyStream);
        PrivateKey privateKey = PrivateKeyReader.loadPrivateKeyFromFile(privateKeyStream);

        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(publicKey);
    }

    @Test(groups = "verify")
    public static void signVerify_whenSigned_shouldPassVerify() throws Exception {
        final String signedResponseDocument = signResponse(PRIVATE_KEY_FILE);
        Assert.assertTrue(verifySignature(signedResponseDocument), "signature verification status must be right");
    }

    @Test(groups = "verify")
    public static void signVerify_whenCalledWithWrongKey_shouldFail() throws Exception {
        final String signedResponseDocument = signResponse(PRIVATE_KEY1_FILE);
        Assert.assertFalse(verifySignature(signedResponseDocument), "signature verification status must be right");
    }

    @Test(groups = "verify")
    public static void signVerify_whenCalledWithSomeFormatting_shouldFail() throws Exception {
        final String signedResponseDocument = signResponse(PRIVATE_KEY_FILE).replaceAll("    <WSResp", "<WSResp");
        Assert.assertFalse(verifySignature(signedResponseDocument), "signature verification status must be right");
    }

    @Test(groups = "verify")
    public static void signVerify_whenCalledWithSomeFormattingAndWrongKeyFile_shouldFail() throws Exception {
        final String signedResponseDocument = signResponse(PRIVATE_KEY1_FILE).replaceAll("    <WSResp", "<WSResp");
        Assert.assertFalse(verifySignature(signedResponseDocument), "signature verification status must be right");
    }

    private static String signResponse(String privateKeyFile) throws IOException, InvalidKeySpecException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyException, SAXException, ParserConfigurationException, MarshalException, XMLSignatureException, TransformerException, XPathExpressionException {
        InputStream responseXML = SignatureUtilsTest.class.getResourceAsStream("/WebShopResponse.xml");

        InputStream privateKeyStream = SignatureUtilsTest.class.getResourceAsStream(privateKeyFile);
        PrivateKey privateKey = PrivateKeyReader.loadPrivateKeyFromFile(privateKeyStream);
        String res = SignatureUtils.signXML(privateKey, responseXML);
        System.out.println(res);
        validateResponseXSD(res);
        return res;
    }

    private static void validateResponseXSD(String signedResponseDocument) throws SAXException, IOException {
        URL responseSchema = new URL(Constants.RESPONSE_XSD);
        Schema responseSchemaFile = newSchema(responseSchema);
        Validator responseValidator = responseSchemaFile.newValidator();
        responseValidator.validate(new StreamSource(new ByteArrayInputStream(signedResponseDocument.getBytes())));
    }

    @Test
    public void signAndValidateRequest() throws Exception {
        InputStream privateKeyStream = SignatureUtilsTest.class.getResourceAsStream(PRIVATE_KEY_FILE);
        PrivateKey privateKey = PrivateKeyReader.loadPrivateKeyFromFile(privateKeyStream);

        InputStream requestXML = SignatureUtilsTest.class.getResourceAsStream("/WebShopRequest.xml");
        final String signedRequestDocument = SignatureUtils.signXML(privateKey, requestXML);
        System.out.println(signedRequestDocument);

        // Validate signed xml
        Schema schema = newSchema(new URL(Constants.REQUEST_XSD));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new ByteArrayInputStream(signedRequestDocument.getBytes())));
    }

    private static boolean verifySignature(String signedResponseDocument) throws Exception {
        InputStream publicKeyStream = SignatureUtilsTest.class.getResourceAsStream(PUBLIC_KEY_FILE);
        PublicKey publicKey = PublicKeyReader.loadPublicKeyFromFile(publicKeyStream);
        return SignatureUtils.verifySignatureInXML(signedResponseDocument, publicKey);
    }

    private static Schema newSchema(URL schemaFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return factory.newSchema(schemaFile);
        } catch (SAXException e) {
            throw new SkipException("could not create schema - it is not available", e);
        }
    }

    /**
     * This test show what is canonicalization and what it is exactly do
     * To understand difference between original xml and result
     * check sout output and original Canonicalization_demonstration.xml
     */
    @Test
    public static void testCanonicalization() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, ParserConfigurationException, IOException, SAXException, TransformException, InvalidCanonicalizerException, CanonicalizationException, Base64DecodingException {
        printCanonicalized(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS,
                SignatureUtilsTest.class.getResourceAsStream("/Canonicalization_demonstration.xml"));
        printCanonicalized(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS,
                           SignatureUtilsTest.class.getResourceAsStream("/Canonicalization_demonstration.xml"));
    }

    /**
     * This test show that digest is usual SHA1 digest encoded into Base64
     * Important that result is critically depends on canonicalization method
     */
    @Test
    public static void testDigest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, ParserConfigurationException, IOException, SAXException, TransformException, InvalidCanonicalizerException, CanonicalizationException, Base64DecodingException {
        System.out.println("\n\tDigest_demonstration.xml will produce 'w4I/gjh5HCynhh2yKA1qgRmo6rY=' as a digest" +
                                   "\n\tif you Sign it with ALGO_ID_C14N_WITH_COMMENTS canonicalizer\n");
        final String withCommentsXML = canonicalize(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS,
                                                    SignatureUtilsTest.class.getResourceAsStream("/Digest_demonstration.xml"));
        System.out.println(getSHA1andConvertToBase64(withCommentsXML));

        final String omitCommentsXML = canonicalize(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS,
                                                    SignatureUtilsTest.class.getResourceAsStream("/Digest_demonstration.xml"));
        System.out.println(getSHA1andConvertToBase64(omitCommentsXML));
    }

    private static String canonicalize(final String CANONICALIZER,
                                       final InputStream xml) throws InvalidCanonicalizerException, ParserConfigurationException, IOException, SAXException, CanonicalizationException {
        final Canonicalizer canon = Canonicalizer.getInstance(CANONICALIZER);
        final byte canonXmlBytes[] = canon.canonicalize(convertInputStreamToByteArray(xml));
        return new String(canonXmlBytes);
    }

    private static void printCanonicalized(final String CANONICALIZER,
                                           final InputStream xml) throws InvalidCanonicalizerException, ParserConfigurationException, IOException, SAXException, CanonicalizationException {
        final String canonicalized = canonicalize(CANONICALIZER, xml);
        System.out.println("\n\t " + CANONICALIZER + "\n" + canonicalized + "\n\n");
    }

    private static byte[] convertInputStreamToByteArray(final InputStream inputStream) throws IOException {
        final int bufferSize = inputStream.available();
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString().getBytes();
    }

    private static String getSHA1andConvertToBase64(
            final String xml) throws NoSuchAlgorithmException, UnsupportedEncodingException, Base64DecodingException {

        final MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(xml.getBytes("UTF-8"));

        BigInteger digest = new BigInteger(1, crypt.digest());
        return Base64.encode(digest);
    }
}

