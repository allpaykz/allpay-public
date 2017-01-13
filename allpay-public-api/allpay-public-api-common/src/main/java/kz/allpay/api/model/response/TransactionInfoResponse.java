package kz.allpay.api.model.response;

import kz.allpay.api.model.TransactionInfo;
import kz.allpay.api.model.TransactionInformation;

/**
 * Contains nothing
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
public class TransactionInfoResponse extends AbstractResponse {
    private TransactionInformation transactionInformation;

    public TransactionInfoResponse() {

    }

    public TransactionInfoResponse(TransactionInformation transactionInformation, String userMessage, String developerMessage){
        this.transactionInformation = transactionInformation;
        this.setUserMessage(userMessage);
        this.setDeveloperMessage(developerMessage);
    }

    public TransactionInformation getTransactionInformation() {
        return transactionInformation;
    }

    public void setTransactionInformation(TransactionInformation transactionInformation) {
        this.transactionInformation = transactionInformation;
    }
}
