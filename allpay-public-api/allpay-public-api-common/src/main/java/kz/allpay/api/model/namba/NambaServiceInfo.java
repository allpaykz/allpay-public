package kz.allpay.api.model.namba;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by aigerim on 10/18/18.
 */
@ApiModel
public class NambaServiceInfo implements Serializable {

    private String serviceName;
    private String iconImageUrl;
    private Long transactionDate;
    private Long publicCode;
    private String utilityAccountNumber;

    public NambaServiceInfo() {
    }

    public NambaServiceInfo(String serviceName, String iconImageUrl, Long transactionDate, Long publicCode, String utilityAccountNumber) {
        this.serviceName = serviceName;
        this.iconImageUrl = iconImageUrl;
        this.transactionDate = transactionDate;
        this.publicCode = publicCode;
        this.utilityAccountNumber = utilityAccountNumber;
    }

    public NambaServiceInfo(String serviceName, Long transactionDate, Long publicCode, String utilityAccountNumber) {
        this.serviceName = serviceName;
        this.transactionDate = transactionDate;
        this.publicCode = publicCode;
        this.utilityAccountNumber = utilityAccountNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIconImageUrl() {
        return iconImageUrl;
    }

    public void setIconImageUrl(String iconImageUrl) {
        this.iconImageUrl = iconImageUrl;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(Long publicCode) {
        this.publicCode = publicCode;
    }

    public String getUtilityAccountNumber() {
        return utilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        this.utilityAccountNumber = utilityAccountNumber;
    }
}
