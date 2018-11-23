package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by aigerim on 9/6/18.
 */
@ApiModel
public class NambaTransactionFullInfoResponse implements Serializable {

    private Long createdOn;
    private BigDecimal transactionAmount;
    private BigDecimal cashbackAmount;
    private BigDecimal comissionAmount;
    private String paymentOptionName;
    private String companyAlias;
    private String companyImageIconUrl;
    private String companyFieldOfActivity;
    private BigDecimal transactionNumber;
    private String serviceName;
    private String serviceImageIconUrl;
    private String serviceAccountNumber;
    private String loginOfReceiverP2P;
    private String loginOfSenderP2P;
    private String nameOfSenderP2P;
    private String nameOfReceiverP2P;
    private String surnameOfSenderP2P;
    private String surnameOReceiverP2P;
    private String status;
    private String currencySymbol;
    private String transactionType;
    private String transactionTypeId;
    private String transactionComment;
    private String accountId;

    public Long getCreatedOn() {
        return createdOn;
    }

    public NambaTransactionFullInfoResponse setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public NambaTransactionFullInfoResponse setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public BigDecimal getCashbackAmount() {
        return cashbackAmount;
    }

    public NambaTransactionFullInfoResponse setCashbackAmount(BigDecimal cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
        return this;
    }

    public BigDecimal getComissionAmount() {
        return comissionAmount;
    }

    public NambaTransactionFullInfoResponse setComissionAmount(BigDecimal comissionAmount) {
        this.comissionAmount = comissionAmount;
        return this;
    }

    public String getPaymentOptionName() {
        return paymentOptionName;
    }

    public NambaTransactionFullInfoResponse setPaymentOptionName(String paymentOptionName) {
        this.paymentOptionName = paymentOptionName;
        return this;
    }

    public String getCompanyAlias() {
        return companyAlias;
    }

    public NambaTransactionFullInfoResponse setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias;
        return this;
    }

    public String getCompanyFieldOfActivity() {
        return companyFieldOfActivity;
    }

    public NambaTransactionFullInfoResponse setCompanyFieldOfActivity(String companyFieldOfActivity) {
        this.companyFieldOfActivity = companyFieldOfActivity;
        return this;
    }

    public BigDecimal getTransactionNumber() {
        return transactionNumber;
    }

    public NambaTransactionFullInfoResponse setTransactionNumber(BigDecimal transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public NambaTransactionFullInfoResponse setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getServiceAccountNumber() {
        return serviceAccountNumber;
    }

    public NambaTransactionFullInfoResponse setServiceAccountNumber(String serviceAccountNumber) {
        this.serviceAccountNumber = serviceAccountNumber;
        return this;
    }

    public String getLoginOfReceiverP2P() {
        return loginOfReceiverP2P;
    }

    public NambaTransactionFullInfoResponse setLoginOfReceiverP2P(String loginOfReceiverP2P) {
        this.loginOfReceiverP2P = loginOfReceiverP2P;
        return this;
    }

    public String getLoginOfSenderP2P() {
        return loginOfSenderP2P;
    }

    public NambaTransactionFullInfoResponse setLoginOfSenderP2P(String loginOfSenderP2P) {
        this.loginOfSenderP2P = loginOfSenderP2P;
        return this;
    }

    public String getNameOfSenderP2P() {
        return nameOfSenderP2P;
    }

    public NambaTransactionFullInfoResponse setNameOfSenderP2P(String nameOfSenderP2P) {
        this.nameOfSenderP2P = nameOfSenderP2P;
        return this;
    }

    public String getNameOfReceiverP2P() {
        return nameOfReceiverP2P;
    }

    public NambaTransactionFullInfoResponse setNameOfReceiverP2P(String nameOfReceiverP2P) {
        this.nameOfReceiverP2P = nameOfReceiverP2P;
        return this;
    }

    public String getSurnameOfSenderP2P() {
        return surnameOfSenderP2P;
    }

    public NambaTransactionFullInfoResponse setSurnameOfSenderP2P(String surnameOfSenderP2P) {
        this.surnameOfSenderP2P = surnameOfSenderP2P;
        return this;
    }

    public String getSurnameOReceiverP2P() {
        return surnameOReceiverP2P;
    }

    public NambaTransactionFullInfoResponse setSurnameOReceiverP2P(String surnameOReceiverP2P) {
        this.surnameOReceiverP2P = surnameOReceiverP2P;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public NambaTransactionFullInfoResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public NambaTransactionFullInfoResponse setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public NambaTransactionFullInfoResponse setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public NambaTransactionFullInfoResponse setTransactionTypeId(String transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
        return this;
    }

    public String getTransactionComment() {
        return transactionComment;
    }

    public NambaTransactionFullInfoResponse setTransactionComment(String transactionComment) {
        this.transactionComment = transactionComment;
        return this;
    }

    public String getCompanyImageIconUrl() {
        return companyImageIconUrl;
    }

    public NambaTransactionFullInfoResponse setCompanyImageIconUrl(String companyImageIconUrl) {
        this.companyImageIconUrl = companyImageIconUrl;
        return this;
    }

    public String getServiceImageIconUrl() {
        return serviceImageIconUrl;
    }

    public NambaTransactionFullInfoResponse setServiceImageIconUrl(String serviceImageIconUrl) {
        this.serviceImageIconUrl = serviceImageIconUrl;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public NambaTransactionFullInfoResponse setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }
}
