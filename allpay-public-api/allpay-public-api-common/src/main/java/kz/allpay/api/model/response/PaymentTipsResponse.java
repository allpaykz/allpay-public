package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.PaymentComment;

import java.io.Serializable;

public class PaymentTipsResponse extends AbstractResponse implements Serializable {
    private PaymentComment paymentComment;

    public PaymentTipsResponse(String userMessage, String developerMessage, PaymentComment paymentComment) {
        super(userMessage, developerMessage);
        this.paymentComment = paymentComment;
    }

    public PaymentTipsResponse(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }

    public PaymentTipsResponse(PaymentComment paymentComment) {
        this.paymentComment = paymentComment;
    }

    @ApiModelProperty(notes = "Подсказка назначение платежа")
    public PaymentComment getPaymentComment() {
        return paymentComment;
    }

    public void setPaymentComment(PaymentComment paymentComment) {
        this.paymentComment = paymentComment;
    }
}