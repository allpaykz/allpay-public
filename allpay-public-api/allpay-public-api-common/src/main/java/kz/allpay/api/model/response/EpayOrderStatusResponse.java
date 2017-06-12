package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by yerzhan.khibatkhanuly on 6/12/17.
 */
@ApiModel
public class EpayOrderStatusResponse extends AbstractResponse implements Serializable {
    private String payment;
    private String status;
    private String result;

    public EpayOrderStatusResponse() {super();}

    public EpayOrderStatusResponse(String payment, String status, String result, String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
        this.payment = payment;
        this.status = status;
        this.result = result;
    }


    @ApiModelProperty(notes = "payment")
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @ApiModelProperty(notes = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ApiModelProperty(notes = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
