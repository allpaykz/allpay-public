package kz.allpay.api.model.request;

/**
 * Created by yerzhan.khibatkhanuly on 3/27/17.
 */
public class UnbindDeviceFromDeviceApplicationRequest extends AbstractRequest {

    private String loginName;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
