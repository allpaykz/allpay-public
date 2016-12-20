package kz.allpay.api.model.request;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
public class TransactionHistoryRequest extends AbstractRequest {
    private String loginName;
    private Long dateFrom;
    private Long dateTo;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Long getDateTo() {
        return dateTo;
    }

    public void setDateTo(Long dateTo) {
        this.dateTo = dateTo;
    }
}
