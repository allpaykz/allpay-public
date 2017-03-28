package kz.allpay.mfs.restejb;

import java.security.PublicKey;
import java.util.HashMap;

/**
 * @author magzhan.karasayev
 * @since 3/10/17 5:12 PM
 */
public class SavedPublicKeyStorage {
    private static final HashMap<String, PublicKey> map = new HashMap<>();

    public static void put(String invoiceNumber, PublicKey publicKey) {
        map.put(invoiceNumber, publicKey);
    }

    public static PublicKey get(String invoiceNumber) {
        return map.get(invoiceNumber);
    }
}
