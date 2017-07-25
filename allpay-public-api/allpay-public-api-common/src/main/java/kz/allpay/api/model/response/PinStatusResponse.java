package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by aigerim on 7/25/17.
 */
@ApiModel
public class PinStatusResponse  extends AbstractResponse implements Serializable {

    private Boolean isExpired = false;
    private Boolean isReset = false;

    public PinStatusResponse() {
        super();
    }

    public PinStatusResponse(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }

    @ApiModelProperty(notes = "Срок действия пароля истек")
    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    @ApiModelProperty(notes = "Пароль бы сброшен админом")
    public Boolean getIsReset() {
        return isReset;
    }

    public void setReset(Boolean reset) {
        isReset = reset;
    }
}
