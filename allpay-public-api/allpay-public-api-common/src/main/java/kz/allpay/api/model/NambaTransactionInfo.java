package kz.allpay.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Класс модели, содержащий информацию о транзакции
 *
 * @author magzhan.karasayev
 * @since 11/16/16 7:36 PM
 */
@ApiModel
public class NambaTransactionInfo implements Serializable {

    private Long createdOn;
    private BigDecimal transactionAmount;
    private BigDecimal cashbackAmount;
    private String paymentOptionName;
    private String companyAlias;
    private BigDecimal transactionNumber;
    private String serviceName;
    private String loginOfReceiverP2P;
    private String loginOfSenderP2P;
    private String nameOfSenderP2P;
    private String nameOfReceiverP2P;
    private String surnameOfSenderP2P;
    private String surnameOReceiverP2P;
    private String status;
    private String currencySymbol;
    private String transactionType;


    public String getTransactionType() {
        return transactionType;
    }

    public NambaTransactionInfo setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public NambaTransactionInfo setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public NambaTransactionInfo setStatus(String status) {
        this.status = status;
        return this;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public NambaTransactionInfo setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public NambaTransactionInfo setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public BigDecimal getCashbackAmount() {
        return cashbackAmount;
    }

    public NambaTransactionInfo setCashbackAmount(BigDecimal cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
        return this;
    }

    public String getPaymentOptionName() {
        return paymentOptionName;
    }

    public NambaTransactionInfo setPaymentOptionName(String paymentOptionName) {
        this.paymentOptionName = paymentOptionName;
        return this;
    }

    public String getCompanyAlias() {
        return companyAlias;
    }

    public NambaTransactionInfo setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias;
        return this;
    }

    public BigDecimal getTransactionNumber() {
        return transactionNumber;
    }

    public NambaTransactionInfo setTransactionNumber(BigDecimal transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public NambaTransactionInfo setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getLoginOfReceiverP2P() {
        return loginOfReceiverP2P;
    }

    public NambaTransactionInfo setLoginOfReceiverP2P(String loginOfReceiverP2P) {
        this.loginOfReceiverP2P = loginOfReceiverP2P;
        return this;
    }

    public String getLoginOfSenderP2P() {
        return loginOfSenderP2P;
    }

    public NambaTransactionInfo setLoginOfSenderP2P(String loginOfSenderP2P) {
        this.loginOfSenderP2P = loginOfSenderP2P;
        return this;
    }

    public String getNameOfSenderP2P() {
        return nameOfSenderP2P;
    }

    public NambaTransactionInfo setNameOfSenderP2P(String nameOfSenderP2P) {
        this.nameOfSenderP2P = nameOfSenderP2P;
        return this;
    }

    public String getNameOfReceiverP2P() {
        return nameOfReceiverP2P;
    }

    public NambaTransactionInfo setNameOfReceiverP2P(String nameOfReceiverP2P) {
        this.nameOfReceiverP2P = nameOfReceiverP2P;
        return this;
    }

    public String getSurnameOfSenderP2P() {
        return surnameOfSenderP2P;
    }

    public NambaTransactionInfo setSurnameOfSenderP2P(String surnameOfSenderP2P) {
        this.surnameOfSenderP2P = surnameOfSenderP2P;
        return this;
    }

    public String getSurnameOReceiverP2P() {
        return surnameOReceiverP2P;
    }

    public NambaTransactionInfo setSurnameOReceiverP2P(String surnameOReceiverP2P) {
        this.surnameOReceiverP2P = surnameOReceiverP2P;
        return this;
    }
    
}

