package kz.allpay.api.model.request;

import java.math.BigDecimal;

public class CreatePendingTransactionForP2PSendRequestByAccountNumber extends AbstractRequest {
    private BigDecimal amount;
    // optional - default will be used in case if it's null
    private String account;
    private String toAccountNumber;
    private String comment;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
