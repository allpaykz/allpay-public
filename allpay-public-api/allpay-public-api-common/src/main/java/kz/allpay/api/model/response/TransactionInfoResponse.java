package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.TransactionInfo;
import kz.allpay.api.model.TransactionInformation;

import java.io.Serializable;

/**
 * Contains nothing
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class TransactionInfoResponse extends AbstractResponse implements Serializable {
    private TransactionInformation transactionInformation;

    public TransactionInfoResponse() {
    }

    public TransactionInfoResponse(TransactionInformation transactionInformation){
        this.transactionInformation = transactionInformation;
    }

    public TransactionInfoResponse(TransactionInformation transactionInformation, String userMessage, String developerMessage){
        this.transactionInformation = transactionInformation;
        this.setUserMessage(userMessage);
        this.setDeveloperMessage(developerMessage);
    }

    @ApiModelProperty(notes = "Информация о транзакции")
    public TransactionInformation getTransactionInformation() {
        return transactionInformation;
    }

    public void setTransactionInformation(TransactionInformation transactionInformation) {
        this.transactionInformation = transactionInformation;
    }
}
