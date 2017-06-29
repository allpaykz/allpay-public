package kz.allpay.api.model;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yerzhan.khibatkhanuly on 1/12/17.
 */
public class TransactionInformation implements Serializable {

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
    private String comment;

    private Long createdOn;
    private Long valueDate;

    private String statusI18n;
    private String typeI18n;


    public String getStatus() {
        return status;
    }

    public TransactionInformation setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFromUser() {
        return fromUser;
    }

    public TransactionInformation setFromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getToUser() {
        return toUser;
    }

    public TransactionInformation setToUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransactionInformation setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionInformation setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getTransactionNumber() {
        return transactionNumber;
    }

    public TransactionInformation setTransactionNumber(BigDecimal transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public TransactionInformation setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public TransactionInformation setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getRrn() {
        return rrn;
    }

    public TransactionInformation setRrn(String rrn) {
        this.rrn = rrn;
        return this;
    }

    public String getChannelId() {
        return channelId;
    }

    public TransactionInformation setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public TransactionInformation setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public TransactionInformation setTerminalId(String terminalId) {
        this.terminalId = terminalId;
        return this;
    }

    public String getStatusI18n() {
        return statusI18n;
    }

    public TransactionInformation setStatusI18n(String statusI18n) {
        this.statusI18n = statusI18n;
        return this;
    }

    public String getTypeI18n() {
        return typeI18n;
    }

    public TransactionInformation setTypeI18n(String typeI18n) {
        this.typeI18n = typeI18n;
        return this;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public TransactionInformation setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Long getValueDate() {
        return valueDate;
    }

    public TransactionInformation setValueDate(Long valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public TransactionInformation setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
