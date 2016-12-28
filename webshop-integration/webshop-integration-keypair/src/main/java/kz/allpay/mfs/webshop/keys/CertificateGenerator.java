package kz.allpay.mfs.webshop.keys;

import kz.allpay.mfs.webshop.Constants;
import sun.security.x509.*;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * User: Sanzhar Aubakirov
 * Date: 5/12/16
 */
public class CertificateGenerator {


    /**
     * Create a self-signed X.509 Certificate
     *
     * @param dn
     * @param pair
     * @param days
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static X509Certificate generateCertificate(String dn, KeyPair pair, int days)
            throws GeneralSecurityException, IOException {

        final PrivateKey privateKey = pair.getPrivate();
        final X509CertInfo info = new X509CertInfo();
        final Date from = new Date();
        final Date to = new Date(from.getTime() + days * 86400000l);
        final CertificateValidity interval = new CertificateValidity(from, to);
        final BigInteger sn = new BigInteger(64, new SecureRandom());
        final X500Name owner = new X500Name(dn);

        info.set(X509CertInfo.VALIDITY, interval);
        info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(sn));
        info.set(X509CertInfo.SUBJECT, new CertificateSubjectName(owner));
        info.set(X509CertInfo.ISSUER, new CertificateIssuerName(owner));
        info.set(X509CertInfo.KEY, new CertificateX509Key(pair.getPublic()));
        info.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
        AlgorithmId algo = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);
        info.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algo));

        // Sign the cert to identify the algorithm that's used.
        X509CertImpl cert = new X509CertImpl(info);
        cert.sign(privateKey, Constants.SIGNING_ALGORITHM);

        // Update the algorith, and resign.
        algo = (AlgorithmId) cert.get(X509CertImpl.SIG_ALG);
        info.set(CertificateAlgorithmId.NAME + "" + CertificateAlgorithmId.ALGORITHM, algo);
        cert = new X509CertImpl(info);
        cert.sign(privateKey, Constants.SIGNING_ALGORITHM);
        return cert;
    }
}
