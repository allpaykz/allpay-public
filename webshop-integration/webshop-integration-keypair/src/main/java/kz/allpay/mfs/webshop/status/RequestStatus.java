package kz.allpay.mfs.webshop.status;

/**
 * User: Sanzhar Aubakirov
 * Date: 5/2/16
 */
public enum RequestStatus {
    SUCCESS,
    ERROR_NO_SUCH_INVOICE_NUMBER,
    ERROR_TIMEOUT,
    ERROR_ALREADY_PAID
}
