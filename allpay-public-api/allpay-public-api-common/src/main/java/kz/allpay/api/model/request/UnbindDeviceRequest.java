package kz.allpay.api.model.request;

/**
 * Created by Erzhan on 26.12.2016.
 */
public class UnbindDeviceRequest extends AbstractRequest {
    private String pinCode;
    private String deviceId;
    private String uniqueNumber;

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }
}
