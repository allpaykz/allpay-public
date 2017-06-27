package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Результат, который содержит дополнительное поле типа String
 *
 * @author magzhan.karasayev
 * @since 05/04/17 7:13 PM
 */
@ApiModel
public class StringResponse extends AbstractResponse implements Serializable {
    private String result;

    public StringResponse(String result, String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
        setResult(result);
    }

    public StringResponse(String result) {
        super("Operation is completed", "Ok");
        setResult(result);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
