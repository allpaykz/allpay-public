package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;

/**
 * Результат, который содержит дополнительное поле типа Boolean
 *
 * @author magzhan.karasayev
 * @since 05/04/17 7:13 PM
 */
@ApiModel
public class BooleanResponse extends AbstractResponse {
    private Boolean result;

    public BooleanResponse(Boolean result, String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
        setResult(result);
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
