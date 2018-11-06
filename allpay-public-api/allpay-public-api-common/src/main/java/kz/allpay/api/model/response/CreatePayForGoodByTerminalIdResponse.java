package kz.allpay.api.model.response;

import java.math.BigDecimal;

/**
 * Created by aigerim on 9/5/18.
 */
public class CreatePayForGoodByTerminalIdResponse extends ValidatePayForGoodByTerminalIdResponse{

    private BigDecimal transactionNumber;
    private String transactionStatus;

    public BigDecimal getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(BigDecimal transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
