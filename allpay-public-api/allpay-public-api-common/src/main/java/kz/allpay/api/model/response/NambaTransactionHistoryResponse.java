package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.namba.NambaTransactionHistoryByPeriod;
import kz.allpay.api.model.namba.NambaTransactionInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class NambaTransactionHistoryResponse extends AbstractResponse implements Serializable {

    private NambaTransactionHistoryByPeriod historyByPeriod;

    public NambaTransactionHistoryResponse(NambaTransactionHistoryByPeriod historyByPeriod) {
        this.historyByPeriod = historyByPeriod;
        setUserMessage("");
        setDeveloperMessage("");
    }

    @ApiModelProperty(notes = "Список транзакций с информацией по каждой из них в отсортированном по дням формате.")
    public NambaTransactionHistoryByPeriod getHistoryByPeriod() {
        return historyByPeriod;
    }

    public void setHistoryByPeriod(NambaTransactionHistoryByPeriod historyByPeriod) {
        this.historyByPeriod = historyByPeriod;
    }
}
