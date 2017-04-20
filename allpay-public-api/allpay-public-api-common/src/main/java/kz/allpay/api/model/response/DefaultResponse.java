package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Результат, который ничего дополнительного не содержит.
 *
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class DefaultResponse extends AbstractResponse implements Serializable {
    public DefaultResponse(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }
}
