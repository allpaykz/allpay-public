package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Результат, который содержит дополнительное поле типа T
 *
 * @author magzhan.karasayev
 * @since 05/04/17 7:13 PM
 */
@ApiModel
public class GenericResponse<T> extends AbstractResponse implements Serializable {
    private T result;

    public GenericResponse(T result, String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
        setResult(result);
    }

    public GenericResponse(T result) {
        super("Operation is completed", "Ok");
        setResult(result);
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
