package kz.allpay.api.model.response;

import kz.allpay.api.model.TransactionInfo;

import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
public class TransactionHistoryResponse extends AbstractResponse {
    private List<TransactionInfo> transactionInfos;

    public List<TransactionInfo> getTransactionInfos() {
        return transactionInfos;
    }

    public void setTransactionInfos(List<TransactionInfo> transactionInfos) {
        this.transactionInfos = transactionInfos;
    }
}
