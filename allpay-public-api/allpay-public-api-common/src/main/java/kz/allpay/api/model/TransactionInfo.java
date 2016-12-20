package kz.allpay.api.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Класс модели, содержащий информацию о транзакции
 *
 * @author magzhan.karasayev
 * @since 11/16/16 7:36 PM
 */
public class TransactionInfo {

    private String transactionNumber;
    private String utn;
    private Date postedOn;
    private String description;
    private BigDecimal amountCredit;
    private BigDecimal amountDebit;
    private BigDecimal runningBalance;
    private String fromUser;
    private String toUser;
    private String onlineTransactionId;
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionInfo amount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public void setUtn(String utn) {
        this.utn = utn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmountCredit(BigDecimal amountCredit) {
        this.amountCredit = amountCredit;
    }

    public void setAmountDebit(BigDecimal amountDebit) {
        this.amountDebit = amountDebit;
    }

    public void setRunningBalance(BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public void setOnlineTransactionId(String onlineTransactionId) {
        this.onlineTransactionId = onlineTransactionId;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public String getUtn() {
        return utn;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmountCredit() {
        return amountCredit;
    }

    public BigDecimal getAmountDebit() {
        return amountDebit;
    }

    public BigDecimal getRunningBalance() {
        return runningBalance;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public String getOnlineTransactionId() {
        return onlineTransactionId;
    }

    public TransactionInfo transactionNumber(final String transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public TransactionInfo utn(final String utn) {
        this.utn = utn;
        return this;
    }

    public TransactionInfo postedOn(final Date postedOn) {
        this.postedOn = postedOn;
        return this;
    }

    public TransactionInfo description(final String description) {
        this.description = description;
        return this;
    }

    public TransactionInfo amountCredit(final BigDecimal amountCredit) {
        this.amountCredit = amountCredit;
        return this;
    }

    public TransactionInfo amountDebit(final BigDecimal amountDebit) {
        this.amountDebit = amountDebit;
        return this;
    }

    public TransactionInfo runningBalance(final BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
        return this;
    }

    public TransactionInfo fromUser(final String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public TransactionInfo toUser(final String toUser) {
        this.toUser = toUser;
        return this;
    }

    public TransactionInfo onlineTransactionId(final String onlineTransactionId) {
        this.onlineTransactionId = onlineTransactionId;
        return this;
    }


}

