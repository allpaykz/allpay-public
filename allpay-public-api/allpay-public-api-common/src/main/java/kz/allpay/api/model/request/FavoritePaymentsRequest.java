package kz.allpay.api.model.request;

/**
 * Класс модели, содержащий запрос о избранных платежах
 * Created by magzhan on 2/9/17.
 */

public class FavoritePaymentsRequest extends AbstractRequest {

    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
