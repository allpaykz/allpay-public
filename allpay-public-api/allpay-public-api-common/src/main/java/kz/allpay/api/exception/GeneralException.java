package kz.allpay.api.exception;

import io.swagger.annotations.ApiModel;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/20/16
 */
@ApiModel
public class GeneralException extends AbstractWebApplicationException{
    public GeneralException(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }
}
