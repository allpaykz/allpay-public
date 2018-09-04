package kz.allpay.api.model.response;

import java.math.BigDecimal;

/**
 * Created by aigerim on 9/4/18.
 */
public class ValidatePayForGoodByTerminalIdResponse {

    private BigDecimal transactionAmount;
    private String paymentOptionName;
    private String operationTypeFE;
    private String companyAlias;
    private String loginClient;
    private BigDecimal cashbackAmount;
    private BigDecimal comissionAmount;

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

    public String getCompanyAlias() {
        return companyAlias;
    }

    public void setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias;
    }

    public String getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }

    public BigDecimal getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(BigDecimal cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public BigDecimal getComissionAmount() {
        return comissionAmount;
    }

    public void setComissionAmount(BigDecimal comissionAmount) {
        this.comissionAmount = comissionAmount;
    }
}
