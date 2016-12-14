package kz.allpay.mfs.signature.keyproviders;

import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.keys.PublicKeyReader;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * Provides the same key from resources
 * @author magzhan.karasayev
 * @since 5/19/16 12:55 AM
 */
public class StaticTestKeyProvider implements KeyProvider {

    @Override
    public PublicKey getPublicKey(Integer certificateNumber) throws IOException, InvalidKeySpecException {
        InputStream publicKeyStream = getClass().getClassLoader().getResourceAsStream("mockKeys/TEST.pub.pem");
        PublicKey publicKey = PublicKeyReader.loadPublicKeyFromFile(publicKeyStream);
        return publicKey ;
    }

    @Override
    public PrivateKey getPrivateKey(Integer certificateNumber) throws IOException, InvalidKeySpecException {
        InputStream privateKeyStream = getClass().getClassLoader().getResourceAsStream("mockKeys/TEST.priv.pem");
        PrivateKey privateKey = PrivateKeyReader.loadPrivateKeyFromFile(privateKeyStream);
        return privateKey;
    }

}
