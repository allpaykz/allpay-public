package kz.allpay.api.model.request;

import java.math.BigDecimal;

/**
 * User: Sanzhar Aubakirov
 * Date: 11/16/16
 */
public class CreatePendingTransactionForP2PSendRequest extends AbstractRequest {
    private BigDecimal amount;
    // optional - default will be used in case if it's null
    private String account;
    // one of these two is required
    private String toAccountNumber;
    private String toLoginName;
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

    public String getToLoginName() {
        return toLoginName;
    }

    public void setToLoginName(String toLoginName) {
        this.toLoginName = toLoginName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
