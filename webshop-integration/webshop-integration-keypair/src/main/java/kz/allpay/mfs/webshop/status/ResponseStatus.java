package kz.allpay.mfs.webshop.status;

/**
 * User: Sanzhar Aubakirov
 * Date: 5/2/16
 */
public enum ResponseStatus {
    SUCCESS,
    ERROR_NO_SUCH_USER,
    ERROR_NOT_ENOUGH_MONEY,
    ERROR_USER_CANCELED,
    ERROR_TIMEOUT,
    ERROR_ALREADY_PAID
}
