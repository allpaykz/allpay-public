package kz.allpay.mfs.restejb;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author magzhan.karasayev
 * @since 5/12/16 12:50 AM
 */
public class TestPropertiesUtils {
    private static Properties properties = null;

    /**
     * returns demo.properties containing parameters from maven
     * @return
     */
    private static Properties getProps() {
        if (properties == null) {
            Properties props = new Properties();
            try (InputStream in = TestPropertiesUtils.class.getClassLoader().getResourceAsStream("demo.properties")) {
                props.load(in);
                properties = props;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    /**
     * comes from profile
     */
    public static String getServicesIp() {
        return getProps().getProperty("webshop.services.ip");
    }

    /**
     * comes from profile
     */
    public static String getServicesPort() {
        return getProps().getProperty("webshop.services.port");
    }

    /**
     * comes from profile
     */
    public static String getWhereToRedirect() {
        return getProps().getProperty("where.to.redirect");
    }
}
