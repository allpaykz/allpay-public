package kz.allpay.mfs.webshop.keys;

import kz.allpay.mfs.webshop.Constants;

import java.io.File;
import java.io.IOException;
import java.security.*;

/**
 * User: Sanzhar Aubakirov
 * Date: 4/24/16
 */
public class KeyGenerator {

    /**
     * This method generated Public and Private keys and return KeyPair
     *
     * @throws IOException
     */
    public KeyPair generate() throws IOException {
        final SecureRandom secureRandom = getStrongRandomWithoutException();
        final KeyPairGenerator keyGen = getGeneratorWithoutException();
        keyGen.initialize(Constants.bytesForKey, secureRandom);

        final KeyPair pair = keyGen.generateKeyPair();

        return pair;
    }

    /**
     * This method generated Public and Private keys and save them to specified location
     *
     * @param path       full path where to save
     * @param namePrefix prefix for key file names
     * @throws IOException
     */
    public void generateAndSave(final String path, final String namePrefix) throws IOException {
        final File dir = new File(path);
        if (!dir.exists()) {
            System.err.println("Directory specified does not exists:\t" + path);
        }

        final KeyPair pair = generate();

        final PrivateKey priv = pair.getPrivate();
        final String privateKeyFullPath = dir.getAbsolutePath() + "/" + namePrefix + Constants.PRIVATE_KEY_POSTFIX;
        KeySaver.saveKeyToFile(privateKeyFullPath, priv);
        System.out.println("Private key saved");

        final PublicKey pub = pair.getPublic();
        final String publicKeyFullPath = dir.getAbsolutePath() + "/" + namePrefix + Constants.PUBLIC_KEY_POSTFIX;
        KeySaver.saveKeyToFile(publicKeyFullPath, pub);
        System.out.println("Public key saved");
    }

    /**
     * Just to avoid exception handling. We know that in Java 8 this exists
     */
    private SecureRandom getStrongRandomWithoutException() {
        try {
            return SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchProviderException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Just to avoid exception handling. We know that this algorithms always exists
     */
    private KeyPairGenerator getGeneratorWithoutException() {
        try {
            return KeyPairGenerator.getInstance("RSA", "SunRsaSign");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
