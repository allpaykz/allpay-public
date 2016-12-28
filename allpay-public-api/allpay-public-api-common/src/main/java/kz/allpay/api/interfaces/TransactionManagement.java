package kz.allpay.api.interfaces;

import kz.allpay.api.exception.GeneralException;
import kz.allpay.api.exception.LoginNotValidException;
import kz.allpay.api.model.request.CreatePendingTransactionForP2PPayByMerchantId;
import kz.allpay.api.model.request.CreatePendingTransactionForP2PSendRequest;
import kz.allpay.api.model.request.TransactionHistoryRequest;
import kz.allpay.api.model.request.TransactionInfoRequest;
import kz.allpay.api.model.response.TransactionInfoResponse;
import kz.allpay.api.model.response.TransactionHistoryResponse;

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
    public TransactionInfoResponse getTransaction(TransactionInfoRequest request);

    /**
     * Получение списка транзакций по фильтрующим критериям.
     */
    public TransactionHistoryResponse getTransactionHistory(TransactionHistoryRequest request) throws LoginNotValidException, GeneralException;

    /**
     * Проверка доступности наличных, прав и тд без фактического создания транзакций
     */
    public TransactionInfoResponse validateTransactionForP2PSend(
            final TransactionInfoRequest request);

    /**
     * Создание и завершение транзакции перевода денег. Результатом будет либо завершенная транзакция, либо сообщение об ошибке.
     * Холдирование не поддерживается
     */
    public TransactionInfoResponse createTransactionForP2PSend(
            final CreatePendingTransactionForP2PSendRequest request);

    /**
     * Проверка доступности наличных, прав и тд без фактического создания транзакций
     */
    public TransactionInfoResponse validateTransactionForPayByMerchantId(
            final CreatePendingTransactionForP2PPayByMerchantId request);

    /**
     * Создание и завершение транзакции перевода денег. Результатом будет либо завершенная транзакция, либо сообщение об ошибке
     * Холдирование не поддерживается
     */
    public TransactionInfoResponse createTransactionForPayByMerchantId(
            final CreatePendingTransactionForP2PPayByMerchantId request);

    /**
     * Функция для транзакций поддерживающих холдирование. Завершение транзакции
     */
    public TransactionInfoResponse completeTransaction(
            final TransactionInfoRequest request);

    /**
     * Функция для транзакций поддерживающих холдирование. Отклонение транзакции
     */
    public TransactionInfoResponse declineTransaction(
            final TransactionInfoRequest request);

}