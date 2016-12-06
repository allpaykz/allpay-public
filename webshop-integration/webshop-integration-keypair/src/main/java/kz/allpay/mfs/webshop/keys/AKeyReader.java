package kz.allpay.mfs.webshop.keys;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * User: Sanzhar Aubakirov
 * Date: 4/25/16
 */
public class AKeyReader {

    public static KeyFactory getKeyFactoryWithoutException() {
        try {
            return KeyFactory.getInstance("RSA", "SunRsaSign");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
