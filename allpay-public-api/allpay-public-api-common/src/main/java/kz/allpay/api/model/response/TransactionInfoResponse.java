package kz.allpay.api.model.response;

import kz.allpay.api.model.TransactionInfo;

/**
 * Contains nothing
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
public class TransactionInfoResponse extends AbstractResponse {
    private TransactionInfo transactionInfo;

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }
}
