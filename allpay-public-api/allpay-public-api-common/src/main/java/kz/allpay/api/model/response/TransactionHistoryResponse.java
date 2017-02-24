package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.TransactionInfo;

import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class TransactionHistoryResponse extends AbstractResponse {

    public TransactionHistoryResponse(List<TransactionInfo> transactionInfos) {
        this.transactionInfos = transactionInfos;
        setUserMessage("");
        setDeveloperMessage("");
    }

    private List<TransactionInfo> transactionInfos;

    @ApiModelProperty(notes = "Список транзакций с информацией по каждой из них")
    public List<TransactionInfo> getTransactionInfos() {
        return transactionInfos;
    }

    public void setTransactionInfos(List<TransactionInfo> transactionInfos) {
        this.transactionInfos = transactionInfos;
    }
}
