package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.TransactionInfo;
import kz.allpay.api.model.TransactionInformation;

import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class TransactionHistoryOPResponse extends AbstractResponse {
    private List<TransactionInformation> transactionInformations;

    public TransactionHistoryOPResponse(List<TransactionInformation> transactionInformations) {
        this.transactionInformations = transactionInformations;
        setUserMessage("");
        setDeveloperMessage("");
    }


    @ApiModelProperty(notes = "Список транзакций с информацией по каждой из них")
    public List<TransactionInformation> getTransactionInfos() {
        return transactionInformations;
    }

    public void setTransactionInfos(List<TransactionInformation> transactionInformations) {
        this.transactionInformations = transactionInformations;
    }
}
