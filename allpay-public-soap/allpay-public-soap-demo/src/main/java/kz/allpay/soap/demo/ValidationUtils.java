package kz.allpay.soap.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
public class ValidationUtils {

    /**
     * Validate phone number
     *
     * @param fullPhoneNumber It is phone number (without prefix +7). 11 digits, example 77002200051 (7 700 220 0051)
     * @return true only if number is valid
     */
    public static Boolean validateFullPhoneNumber(final String fullPhoneNumber) {
        if (!NVL(fullPhoneNumber)) {
            return false;
        }
        final Pattern alphaNumericP = Pattern.compile("[0-9]{11}");
        final Matcher matcher = alphaNumericP.matcher(fullPhoneNumber);
        return matcher.matches();
    }

    /**
     * Returns true if object not empty and not null
     *
     * @param object checked object
     * @return true/false
     */
    public static Boolean NVL(Object object) {

        if (object == null) return false;

        if (object instanceof String) {
            if (((String) object).isEmpty()) return false;
        }

        return true;
    }
}
