package kz.allpay.api.model.request;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/26/16
 */
public class SessionLogoutRequest extends AbstractRequest {

    private String loginName;
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
