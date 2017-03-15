package kz.allpay.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class TransactionHistoryRequest extends AbstractRequest {
    private String loginName;
    private Long dateFrom;
    private Long dateTo;
    private Integer offset;
    private Integer limit;

    @ApiModelProperty(notes = "Отступ, с какого номера начать по порядку")
    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @ApiModelProperty(notes = "Сколько транзакций вернуть")
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @ApiModelProperty(notes = "Логин")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @ApiModelProperty(notes = "Дата начала")
    public Long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Long dateFrom) {
        this.dateFrom = dateFrom;
    }

    @ApiModelProperty(notes = "Дата конца")
    public Long getDateTo() {
        return dateTo;
    }

    public void setDateTo(Long dateTo) {
        this.dateTo = dateTo;
    }
}
