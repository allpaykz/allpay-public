package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.NambaTransactionInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class NambaTransactionHistoryResponse extends AbstractResponse implements Serializable {

    public NambaTransactionHistoryResponse(List<NambaTransactionInfo> nambaTransactionInfos) {
        this.nambaTransactionInfos = nambaTransactionInfos;
        setUserMessage("");
        setDeveloperMessage("");
    }

    private List<NambaTransactionInfo> nambaTransactionInfos;

    @ApiModelProperty(notes = "Список транзакций с информацией по каждой из них")
    public List<NambaTransactionInfo> getNambaTransactionInfos() {
        return nambaTransactionInfos;
    }

    public void setNambaTransactionInfos(List<NambaTransactionInfo> nambaTransactionInfos) {
        this.nambaTransactionInfos = nambaTransactionInfos;
    }
}
