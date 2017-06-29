package kz.allpay.api.model.request;

import java.math.BigDecimal;

/**
 * User: Sanzhar Aubakirov
 * Date: 11/16/16
 */
public class CreatePendingTransactionForP2PPayByMerchantId extends AbstractRequest {
    private BigDecimal amount;
    private String account;
    private String terminalId;
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

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
