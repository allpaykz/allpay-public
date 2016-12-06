package kz.allpay.api.bundle;

import kz.allpay.api.model.Language;

import javax.ws.rs.core.HttpHeaders;
import javax.xml.bind.DatatypeConverter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by abilkhaiyr on 11/29/16.
 */
public class ApiResourceProvider {


    public static ResourceBundle parseAllpayApiMessagesBundleWithLocale(final String lang) {

        if (lang.equals("en") || lang.equals("ru") || lang.equals("kk")) {
            return ResourceBundle.getBundle("kz.allpay.api.resources.Messages", new Locale(lang));
        }

        return ResourceBundle.getBundle("kz.allpay.api.resources.Messages", new Locale("ru"));

    }

    public static ResourceBundle parseAllpayApiMessagesBundleWithLocale(final Language lang) {
        if (lang == null) {
            return parseAllpayApiMessagesBundleWithLocale(Language.RU);
        }
        return parseAllpayApiMessagesBundleWithLocale(lang.name().toLowerCase());
    }
}
