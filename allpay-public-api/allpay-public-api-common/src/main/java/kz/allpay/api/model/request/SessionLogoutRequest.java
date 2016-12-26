package kz.allpay.api.model.request;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/26/16
 */
public class SessionLogoutRequest extends AbstractRequest {
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
