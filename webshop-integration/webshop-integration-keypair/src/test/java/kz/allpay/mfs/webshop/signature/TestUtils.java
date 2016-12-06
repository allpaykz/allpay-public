package kz.allpay.mfs.webshop.signature;

import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.keys.PublicKeyReader;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author magzhan.karasayev
 * @since 6/14/16 2:12 PM
 */
public class TestUtils {

    public static void setXmlDsigFinestLogging() {
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINEST);
        Logger l = Logger.getLogger("org.jcp.xml.dsig.internal.dom");
        l.setLevel(Level.FINEST);
        l.addHandler(handler);
    }

    public static KeyPair getKeyPair() throws IOException, InvalidKeySpecException {
        InputStream privateKeyStream = TestUtils.class.getClassLoader().getResourceAsStream("mockKeys/TEST.priv.pem");
        PrivateKey privateKey = PrivateKeyReader.loadPrivateKeyFromFile(privateKeyStream);
        InputStream publicKeyStream = TestUtils.class.getClassLoader().getResourceAsStream("mockKeys/TEST.pub.pem");
        PublicKey publicKey = PublicKeyReader.loadPublicKeyFromFile(publicKeyStream);
        return new KeyPair(publicKey, privateKey );
    }
}
