package kz.allpay.api.interfaces;

import kz.allpay.api.exception.GeneralException;
import kz.allpay.api.exception.LoginNotValidException;
import kz.allpay.api.model.request.*;
import kz.allpay.api.model.response.UserProfileResponse;

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
    public UserProfileResponse getUserInfo() throws GeneralException;

    /**
     * Изменения метода уведомления
     */
    public Response changeAlertMethod(ChangeAlertRequest request) throws GeneralException;

    /**
     * Изменение пина
     */
    public Response changePin(ChangePinRequest request) throws LoginNotValidException, GeneralException;

    /**
     * Логаут сессии
     */
    public Response sessionLogout();

    /**
     * Отвязка устройства
     */
    public Response unbindDevice(UnbindDeviceRequest request);

    /**
     * Получить кошельки текущего юзера
     */
    public Response getUserWallet();
}
