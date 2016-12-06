package kz.allpay.mfs.webshop.signature;

import java.io.InputStream;
import java.io.Reader;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author magzhan.karasayev
 * @since 5/20/16 7:03 PM
 */
public class SignatureServiceImpl implements SignatureService {

    @Override
    public String signXML(PrivateKey aPrivate, InputStream resourceAsStream) throws SignatureException {
        try {
            return SignatureUtils.signXML(aPrivate, resourceAsStream);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new SignatureException("Could not sign message", e);
        }
    }

    @Override
    public boolean verifySignatureInXML(Reader reader, PublicKey key) throws SignatureException {
        try {
            return SignatureUtils.verifySignatureInXML(reader, key);
        }  catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new SignatureException("Could not verify message", e);
        }
    }
}
