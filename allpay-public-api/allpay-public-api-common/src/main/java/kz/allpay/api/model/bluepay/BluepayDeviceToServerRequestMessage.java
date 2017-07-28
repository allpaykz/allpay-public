package kz.allpay.api.model.bluepay;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author magzhan.karasayev
 * @since 25.07.17 20:00
 */
public class BluepayDeviceToServerRequestMessage implements Serializable {
    private BigDecimal amount;
    private String deviceId;
    private String mobileAppMessage;
    private String devicePublicKeyBase64;
    private String devicePublicKeySignature;
    private String base64Signature;

    public BluepayDeviceToServerRequestMessage() {
    }

    public BluepayDeviceToServerRequestMessage(BigDecimal amount, String deviceId, String mobileAppMessage, String devicePublicKeyBase64, String devicePublicKeySignature) {
        this.amount = amount;
        this.deviceId = deviceId;
        this.mobileAppMessage = mobileAppMessage;
        this.devicePublicKeyBase64 = devicePublicKeyBase64;
        this.devicePublicKeySignature = devicePublicKeySignature;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getMobileAppMessage() {
        return mobileAppMessage;
    }

    public String getBase64Signature() {
        return base64Signature;
    }

    public void setBase64Signature(String base64Signature) {
        this.base64Signature = base64Signature;
    }

    public String getDevicePublicKeyBase64() {
        return devicePublicKeyBase64;
    }

    public String getDevicePublicKeySignature() {
        return devicePublicKeySignature;
    }

    public String getMessageToSign() {
        return new StringBuilder().append(amount).append(deviceId).append(mobileAppMessage).toString();
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setMobileAppMessage(String mobileAppMessage) {
        this.mobileAppMessage = mobileAppMessage;
    }

    public void setDevicePublicKeyBase64(String devicePublicKeyBase64) {
        this.devicePublicKeyBase64 = devicePublicKeyBase64;
    }

    public void setDevicePublicKeySignature(String devicePublicKeySignature) {
        this.devicePublicKeySignature = devicePublicKeySignature;
    }
}
