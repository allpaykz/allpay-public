package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.NativePayInformation;

import java.io.Serializable;

/**
 * Created by aigerim on 10/3/17.
 */
@ApiModel
public class NativePayWebResponse extends AbstractResponse implements Serializable {

    private NativePayInformation nativePayInformation;

    public NativePayWebResponse() {

    }

    public NativePayWebResponse(NativePayInformation nativePayInformation) {
        this.nativePayInformation = nativePayInformation;
    }

    public NativePayWebResponse(String userMessage, String developerMessage, NativePayInformation nativePayInformation) {
        super(userMessage, developerMessage);
        this.nativePayInformation = nativePayInformation;
    }

    @ApiModelProperty(notes = "Информация о транзакции")
    public NativePayInformation getNativePayInformation() {
        return nativePayInformation;
    }

    public void setNativePayInformation(NativePayInformation nativePayInformation) {
        this.nativePayInformation = nativePayInformation;
    }
}
