package kz.allpay.api.interfaces;


import kz.allpay.api.exception.GeneralException;
import kz.allpay.api.exception.LoginNotValidException;
import kz.allpay.api.model.request.*;
import kz.allpay.api.model.response.DefaultResponse;
import kz.allpay.api.model.response.GetUserWalletResponse;
import kz.allpay.api.model.response.UserProfileResponse;

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
    public DefaultResponse changeAlertMethod(ChangeAlertRequest request) throws GeneralException;

    /**
     * Изменение пина
     */
    public DefaultResponse changePin(ChangePinRequest request) throws LoginNotValidException, GeneralException;

    /**
     * Логаут сессии
     */
    public DefaultResponse sessionLogout();

    /**
     * Отвязка устройства
     */
    public DefaultResponse unbindDevice(UnbindDeviceRequest request) throws GeneralException;

    /**
     * Получить кошельки текущего юзера
     */
    public GetUserWalletResponse getUserWallet(String lang) throws GeneralException;


    Boolean acceptTermAndConditions() throws GeneralException;

    Boolean checkAcceptanceOfTermsAndConditions() throws GeneralException;
}
