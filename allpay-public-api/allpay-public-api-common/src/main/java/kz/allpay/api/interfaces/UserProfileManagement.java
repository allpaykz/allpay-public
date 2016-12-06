package kz.allpay.api.interfaces;

import kz.allpay.api.model.request.ChangeAlertRequest;
import kz.allpay.api.model.request.ChangePinRequest;
import kz.allpay.api.model.request.UserProfileRequest;
import kz.allpay.api.model.response.DefaultResponse;

import javax.ws.rs.core.Response;

/**
 * Этот класс содержит запросы для управления своим профилем
 * @author magzhan.karasayev
 * @since 11/16/16 6:56 PM
 */
public interface UserProfileManagement {
    /**
     * Получение общей информации о пользователе
     */
    public Response getUserInfo(UserProfileRequest request);

    /**
     * Изменения метода уведомления
     */
    public DefaultResponse changeAlertMethod(ChangeAlertRequest request);

    /**
     * Изменение пина
     */
    public DefaultResponse changePin(ChangePinRequest request);

}
