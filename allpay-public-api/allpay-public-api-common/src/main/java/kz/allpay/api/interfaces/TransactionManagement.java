package kz.allpay.api.interfaces;

import kz.allpay.api.exception.GeneralException;
import kz.allpay.api.exception.LoginNotValidException;
import kz.allpay.api.model.bluepay.BluePayTxResponse;
import kz.allpay.api.model.bluepay.BluepayDeviceToServerRequestMessage;
import kz.allpay.api.model.request.*;
import kz.allpay.api.model.response.*;

/**
 * Этот интерфейс содержит методы создания и управления жизненным циклом транзакций.
 *
 * User: Sanzhar Aubakirov
 * Date: 11/16/16
 */
public interface TransactionManagement {

    /**
     * Получение информации о транзакции по ее номеру
     */
    public TransactionInfoResponse getTransaction(TransactionInfoRequest request) throws GeneralException;

    /**
     * Получение списка транзакций по фильтрующим критериям.
     */
    public TransactionHistoryResponse getTransactionHistory(Long dateFrom, Long dateTo, Integer offset, Integer limit, String language)
            throws LoginNotValidException, GeneralException;

    /**
     * Получение списка изранных платежей по логину пользователя
     */
    public FavoritePaymentsResponse getFavoritePayments(FavoritePaymentsRequest request) throws LoginNotValidException, GeneralException;

    /**
     * Проверка доступности наличных, прав и тд без фактического создания транзакций
     */
    public TransactionInfoResponse validateTransactionForP2PSend(
            final CreatePendingTransactionForP2PSendRequest request) throws GeneralException;

    /**
     * Создание и завершение транзакции перевода денег. Результатом будет либо завершенная транзакция, либо сообщение об ошибке.
     * Холдирование не поддерживается
     */
    public TransactionInfoResponse createTransactionForP2PSend(
            final CreatePendingTransactionForP2PSendRequest request) throws GeneralException;

    TransactionInfoResponse createTransactionForP2PSendByAccountNumber(
            final CreatePendingTransactionForP2PSendRequestByAccountNumber request) throws GeneralException;


    /**
     * Проверка доступности наличных, прав и тд без фактического создания транзакций
     */
    public TransactionInfoResponse validateTransactionForPayByMerchantId(
            final CreatePendingTransactionForP2PPayByMerchantId request) throws GeneralException;

    NativePayWebResponse validateTransactionNativePay(
            CreatePendingTransactionForNativePay request) throws GeneralException;

    TransactionInfoResponse createTransactionNativePay(
            CreatePendingTransactionForNativePay request) throws GeneralException;

    /**
     * Создание и завершение транзакции перевода денег. Результатом будет либо завершенная транзакция, либо сообщение об ошибке
     * Холдирование не поддерживается
     */
    public TransactionInfoResponse createTransactionForPayByMerchantId(
            final CreatePendingTransactionForP2PPayByMerchantId request) throws GeneralException;

    /**
     * Функция для транзакций поддерживающих холдирование. Завершение транзакции
     */
    public TransactionInfoResponse completeTransaction(
            final TransactionInfoRequest request) throws GeneralException;

    /**
     * Функция для транзакций поддерживающих холдирование. Отклонение транзакции
     */
    public TransactionInfoResponse declineTransaction(
            final TransactionInfoRequest request) throws GeneralException;

    BluePayTxResponse createBluePayTx(BluepayDeviceToServerRequestMessage message) throws GeneralException;
}
