package kz.allpay.api.interfaces;


import io.swagger.annotations.*;
import kz.allpay.api.exception.AbstractWebApplicationException;
import kz.allpay.api.exception.GeneralException;
import kz.allpay.api.exception.LoginNotValidException;
import kz.allpay.api.model.request.*;
import kz.allpay.api.model.response.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Этот класс содержит запросы для управления своим профилем
 * @author magzhan.karasayev
 * @since 11/16/16 6:56 PM
 */
public interface UserProfileManagement {

    public BooleanResponse requestNumberChange(String numberToChangeTo) throws GeneralException;

    public BooleanResponse changeNumberByTan(String tan) throws GeneralException;

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

    /**
     * Сервис для отвязки устройства из приложения
     */
    public DefaultResponse unbindDeviceFromDeviceApplication(UnbindDeviceFromDeviceApplicationRequest request) throws GeneralException;

    DefaultResponse acceptTermsAndConditions() throws GeneralException;

    DefaultResponse checkAcceptanceOfTermsAndConditions() throws GeneralException;

    /**
     * сервиса для проверки пароля
     */
    DefaultResponse checkPassword() throws GeneralException;

    DefaultResponse changeUserEmail(String email) throws GeneralException;

    DefaultResponse changeNickname(String nickname) throws GeneralException;
}
