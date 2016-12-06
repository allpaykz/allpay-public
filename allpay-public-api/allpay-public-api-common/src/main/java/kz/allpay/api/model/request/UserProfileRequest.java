package kz.allpay.api.model.request;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:04 PM
 */
public class UserProfileRequest extends AbstractRequest {
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
