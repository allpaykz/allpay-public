package kz.allpay.mfs.ws.soap.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author magzhan.karasayev
 * @since 1/10/17 1:50 PM
 */
public class LocalizedException extends Exception {

    private static final long serialVersionUID = -9196746632249708331L;

    public static final Locale RU_LOCALE = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();

    final private String bundleKey;
    final private Object[] messageArgs;
    final Locale defaultLocale;

    public LocalizedException(Locale defaultLocale, String bundleKey, Object[] messageArgs) {
        this.defaultLocale = defaultLocale;
        this.bundleKey = bundleKey;
        this.messageArgs = messageArgs;
    }

    public LocalizedException(String bundleKey, Object[] messageArgs) {
        this(Locale.getDefault(), bundleKey, messageArgs);
    }

    @Override
    public String getMessage() {
        return MessageFormat.format(ResourceBundle.getBundle("kz.allpay.mfs.ws.soap.exception.Exception", defaultLocale).getString(bundleKey), messageArgs);
    }

    @Override
    public String getLocalizedMessage() {
        return MessageFormat.format(ResourceBundle.getBundle("kz.allpay.mfs.ws.soap.exception.Exception").getString(bundleKey), messageArgs);
    }
}
