package kz.allpay.soap.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
public class PropertiesUtils {
    private static Properties properties = null;

    /**
     * returns demo.properties containing parameters from maven
     * @return
     */
    private static Properties getProps() {
        if (properties == null) {
            Properties props = new Properties();
            try (InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("demo.properties")) {
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
    public static String getApiUrl() {
        return getProps().getProperty("allpay.api.url");
    }
}
