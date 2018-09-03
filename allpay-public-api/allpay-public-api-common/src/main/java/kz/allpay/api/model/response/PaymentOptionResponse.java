package kz.allpay.api.model.response;

import java.math.BigDecimal;

/**
 * Created by aigerim on 9/4/18.
 */
public class PaymentOptionResponse {

    private String paymentOptionName;
    private BigDecimal balance;
    private String accountId;

    public PaymentOptionResponse() {
    }

    public PaymentOptionResponse(String paymentOptionName, BigDecimal balance, String accountId) {
        this.paymentOptionName = paymentOptionName;
        this.balance = balance;
        this.accountId = accountId;
    }

    public String getPaymentOptionName() {
        return paymentOptionName;
    }

    public void setPaymentOptionName(String paymentOptionName) {
        this.paymentOptionName = paymentOptionName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
