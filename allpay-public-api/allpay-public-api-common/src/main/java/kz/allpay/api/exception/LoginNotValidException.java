package kz.allpay.api.exception;

import kz.allpay.api.model.Language;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/20/16
 */
public class LoginNotValidException extends AbstractWebApplicationException {

    public LoginNotValidException(Language lang) {
        super(lang);
    }

    public LoginNotValidException(String userMessage) {
        super(userMessage);
    }

    public LoginNotValidException(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }
}
