package kz.allpay.api.model.bluepay;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yerzhan.khibatkhanuly on 1/12/17.
 */
public class BluePayTxInfo implements Serializable {

    private String channelId;
    private BigDecimal amount;
    private String fromUser;
    private BigDecimal transactionNumber;
    private String chargeAmount;
    private String currencyCode;
    private String terminalId;
    private String status;


    private String statusI18n;


    public String getStatus() {
        return status;
    }

    public BluePayTxInfo setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFromUser() {
        return fromUser;
    }

    public BluePayTxInfo setFromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BluePayTxInfo setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getTransactionNumber() {
        return transactionNumber;
    }

    public BluePayTxInfo setTransactionNumber(BigDecimal transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public BluePayTxInfo setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BluePayTxInfo setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getChannelId() {
        return channelId;
    }

    public BluePayTxInfo setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public BluePayTxInfo setTerminalId(String terminalId) {
        this.terminalId = terminalId;
        return this;
    }

    public String getStatusI18n() {
        return statusI18n;
    }

    public BluePayTxInfo setStatusI18n(String statusI18n) {
        this.statusI18n = statusI18n;
        return this;
    }
}
