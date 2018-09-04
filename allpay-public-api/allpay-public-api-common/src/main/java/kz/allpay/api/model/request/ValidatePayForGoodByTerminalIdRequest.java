package kz.allpay.api.model.request;

import java.math.BigDecimal;

/**
 * Created by aigerim on 9/4/18.
 */
public class ValidatePayForGoodByTerminalIdRequest extends AbstractRequest {

    private BigDecimal transactionAmount;
    private String paymentOptionName;
    private String operationTypeFE;
    private String terminalId;

    public ValidatePayForGoodByTerminalIdRequest() {
    }

    public ValidatePayForGoodByTerminalIdRequest(BigDecimal transactionAmount, String paymentOptionName,
                                                 String operationTypeFE, String terminalId) {
        this.transactionAmount = transactionAmount;
        this.paymentOptionName = paymentOptionName;
        this.operationTypeFE = operationTypeFE;
        this.terminalId = terminalId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getPaymentOptionName() {
        return paymentOptionName;
    }

    public void setPaymentOptionName(String paymentOptionName) {
        this.paymentOptionName = paymentOptionName;
    }

    public String getOperationTypeFE() {
        return operationTypeFE;
    }

    public void setOperationTypeFE(String operationTypeFE) {
        this.operationTypeFE = operationTypeFE;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

}
