package kz.allpay.mfs.webshop;

/**
 * User: Sanzhar Aubakirov
 * Date: 4/25/16
 */
public class Constants {
    public final static Integer bytesForKey = 1024;
    public static final String PRIVATE_KEY_POSTFIX = ".priv";
    public static final String PUBLIC_KEY_POSTFIX = ".pub";

    public static final String SIGNING_ALGORITHM = "SHA1withRSA";

    public static final String PRIVATE_KEY_FILE = "/mockKeys/TEST.priv.pem";
    public static final String PRIVATE_KEY1_FILE = "/mockKeys/TEST1.priv.pem";
    public static final String PUBLIC_KEY_FILE = "/mockKeys/TEST.pub.pem";

    public static final String PRIVATE_WRONG_KEY_FILE = "/mockKeys/TEST_WRONG_KEY.priv.pem";
    public static final String PUBLIC_WRONG_KEY_FILE = "/mockKeys/TEST_WRONG_KEY.pub.pem";

    public static final String RESPONSE_XSD="https://allpay.kz/xsd/1.0.0/WebShopResponse.xsd";
    public static final String CUSTOMER_RESPONSE_XSD="https://allpay.kz/xsd/1.0.0/WebShopCustomerResponse.xsd";
    public static final String REQUEST_XSD="https://allpay.kz/xsd/1.0.0/WebShopRequest.xsd";
}
