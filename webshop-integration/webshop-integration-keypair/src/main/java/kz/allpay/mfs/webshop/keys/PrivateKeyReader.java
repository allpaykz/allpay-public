package kz.allpay.mfs.webshop.keys;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;

import java.io.*;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

/**
 * User: Sanzhar Aubakirov
 * Date: 4/24/16
 */
public class PrivateKeyReader extends AKeyReader {

    /**
     * Use this method to load Private key from file
     * It is supposed that Key was save to file system using {@link KeySaver}
     * <p> Use this only for Private Key</p>
     *
     * @param keyFile Full path to persisted Key in file system
     * @return loaded Key or throws exception if any error occurred
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey loadPrivateKeyFromFile(
            final InputStream keyFile) throws IOException, InvalidKeySpecException {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        final Reader reader = new InputStreamReader(keyFile);
        final PEMReader pemReader = new PEMReader(reader);
        final KeyPair keyPair = (KeyPair) pemReader.readObject();
        return keyPair.getPrivate();
    }
}
