package kz.allpay.api.exception;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/20/16
 */
public class GeneralException extends AbstractWebApplicationException{
    public GeneralException(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }
}
