package kz.allpay.api.model.bluepay;

import java.math.BigDecimal;

/**
 * @author magzhan.karasayev
 * @since 25.07.17 20:00
 */
public class BluepayDeviceToServerRequestMessage {
    private final BigDecimal amount;
    private final String deviceId;
    private final String mobileAppMessage;
    private String base64Signature;

    public BluepayDeviceToServerRequestMessage(BigDecimal amount, String deviceId, String mobileAppMessage) {
        this.amount = amount;
        this.deviceId = deviceId;
        this.mobileAppMessage = mobileAppMessage;
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

    public String getDataToSign() {
        return amount + deviceId + mobileAppMessage;
    }

    public void setBase64Signature(String base64Signature) {
        this.base64Signature = base64Signature;
    }
}
