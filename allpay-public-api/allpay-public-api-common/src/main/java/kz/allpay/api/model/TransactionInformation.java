package kz.allpay.api.model;


import java.math.BigDecimal;

/**
 * Created by yerzhan.khibatkhanuly on 1/12/17.
 */
public class TransactionInformation {

    private String channelId;
    private String transactionType;
    private BigDecimal amount;
    private String fromUser;
    private String toUser;
    private String rrn;
    private BigDecimal transactionNumber;
    private String description;
    private String chargeAmount;
    private String currencyCode;
    private String terminalId;
    private String status;

    private String statusI18n;
    private String typeI18n;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(BigDecimal transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getStatusI18n() {
        return statusI18n;
    }

    public void setStatusI18n(String statusI18n) {
        this.statusI18n = statusI18n;
    }

    public String getTypeI18n() {
        return typeI18n;
    }

    public void setTypeI18n(String typeI18n) {
        this.typeI18n = typeI18n;
    }
}
