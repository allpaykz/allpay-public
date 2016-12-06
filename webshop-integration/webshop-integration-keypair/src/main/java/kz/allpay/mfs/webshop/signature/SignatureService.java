package kz.allpay.mfs.webshop.signature;

import java.io.InputStream;
import java.io.Reader;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author magzhan.karasayev
 * @since 5/20/16 7:03 PM
 */
public interface SignatureService {

    public String signXML(PrivateKey aPrivate, InputStream resourceAsStream) throws SignatureException;
    public boolean verifySignatureInXML(Reader reader, final PublicKey key) throws SignatureException;

    public static class SignatureException extends Exception {
        public SignatureException() {}
        public SignatureException(String message) {
            super(message);
        }
        public SignatureException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
