package kz.allpay.api.exception;

import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.Language;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;
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

    AbstractWebApplicationException(Language lang) {
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
        if (lang == null)
            return ResourceBundle.getBundle("kz.allpay.api.resources.Exceptions", new Locale(Language.RU.name().toLowerCase()));
        else
            return ResourceBundle.getBundle("kz.allpay.api.resources.Exceptions", new Locale(lang.name().toLowerCase()));
    }

    private String getUserMessageByClassName(ResourceBundle messages) {
        try {
            return messages.getString(this.getClass().getName());
        } catch (MissingResourceException mre) {
            return messages.getString(GeneralException.class.getName());
        }
    }

    public Response getResponse() {

        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(developerMessage, userMessage)).build();
    }

    public class ExceptionResponse {
        private String developerMessage;
        private String userMessage;

        ExceptionResponse(String developerMessage, String userMessage) {
            this.developerMessage = developerMessage;
            this.userMessage = userMessage;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public void setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public void setUserMessage(String userMessage) {
            this.userMessage = userMessage;
        }
    }


    public Language getLang() {
        return lang;
    }

    @ApiModelProperty(notes = "Сообщение для пользователя")
    public String getUserMessage() {
        return userMessage;
    }

    @ApiModelProperty(notes = "Сообщение для разработчика")
    public String getDeveloperMessage() {
        return developerMessage;
    }
}
