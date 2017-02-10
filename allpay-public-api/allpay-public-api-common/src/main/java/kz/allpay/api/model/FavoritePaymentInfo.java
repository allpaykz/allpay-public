package kz.allpay.api.model;

import java.util.Date;

/**
 * Класс модели, содержащий информацию о избранных платежах
 * <p>
 * Created by magzhan on 2/9/17.
 */
public class FavoritePaymentInfo {
    private String serviceId;
    private String accountNumber;
    private Boolean trackable;
    private Boolean hasExtraInfo;
    private Date extraInfoLastUpdate;
    private String extraInfo;
    private Boolean notified;
    private String alias;

    public FavoritePaymentInfo(String serviceId, String accountNumber, Boolean trackable, Boolean hasExtraInfo, Date extraInfoLastUpdate, String extraInfo, Boolean notified, String alias) {
        this.serviceId = serviceId;
        this.accountNumber = accountNumber;
        this.trackable = trackable;
        this.hasExtraInfo = hasExtraInfo;
        this.extraInfoLastUpdate = extraInfoLastUpdate;
        this.extraInfo = extraInfo;
        this.notified = notified;
        this.alias = alias;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean getTrackable() {
        return trackable;
    }

    public void setTrackable(Boolean trackable) {
        this.trackable = trackable;
    }

    public Boolean getHasExtraInfo() {
        return hasExtraInfo;
    }

    public void setHasExtraInfo(Boolean hasExtraInfo) {
        this.hasExtraInfo = hasExtraInfo;
    }

    public Date getExtraInfoLastUpdate() {
        return extraInfoLastUpdate;
    }

    public void setExtraInfoLastUpdate(Date extraInfoLastUpdate) {
        this.extraInfoLastUpdate = extraInfoLastUpdate;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
