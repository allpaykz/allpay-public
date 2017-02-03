package kz.allpay.api.model.request;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
public class ChangeAlertRequest extends AbstractRequest {

    private String notificationMethod;


    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}
