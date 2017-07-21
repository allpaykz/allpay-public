package kz.allpay.api.model.bluepay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import kz.allpay.api.model.response.AbstractResponse;

/**
 * Contains nothing
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class BluePayTxResponse extends AbstractResponse implements Serializable {
    private BluePayTxInfo transactionInformation;

    public BluePayTxResponse() {
    }

    public BluePayTxResponse(BluePayTxInfo transactionInformation){
        this.transactionInformation = transactionInformation;
    }

    public BluePayTxResponse(BluePayTxInfo transactionInformation, String userMessage, String developerMessage){
        this.transactionInformation = transactionInformation;
        this.setUserMessage(userMessage);
        this.setDeveloperMessage(developerMessage);
    }

    @ApiModelProperty(notes = "Информация о транзакции")
    public BluePayTxInfo getTransactionInformation() {
        return transactionInformation;
    }

    public void setTransactionInformation(BluePayTxInfo transactionInformation) {
        this.transactionInformation = transactionInformation;
    }
}
