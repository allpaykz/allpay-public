package kz.allpay.api.exception;

import kz.allpay.api.model.Language;

import javax.ejb.ApplicationException;
import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * If you do not specify language, RU will be used by default;
 */
@ApplicationException(rollback = true)
public abstract class AbstractWebApplicationException extends Exception implements Serializable {
    private final String userMessage;
    private final String developerMessage;
    private final Language lang;

    public AbstractWebApplicationException(Language lang) {
        this.lang = lang;
        this.developerMessage = this.getClass().getName();

        final ResourceBundle messages = parseRestApiResourceBundleWithLocale(lang);
        this.userMessage = getUserMessageByClassName(messages);
    }


    AbstractWebApplicationException(String userMessage) {
        this.lang = Language.RU;
        this.userMessage = userMessage;
        developerMessage = this.getClass().getName();
    }

    AbstractWebApplicationException(String userMessage, String developerMessage) {
        this.lang = Language.RU;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    private ResourceBundle parseRestApiResourceBundleWithLocale(final Language lang) {
        return ResourceBundle.getBundle("kz.allpay.api.resources.Messages", new Locale(lang.name().toLowerCase()));
    }

    private String getUserMessageByClassName(ResourceBundle messages) {
        try {
            return messages.getString(this.getClass().getName());
        } catch (MissingResourceException mre) {
            return messages.getString(GeneralException.class.getName());
        }
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
