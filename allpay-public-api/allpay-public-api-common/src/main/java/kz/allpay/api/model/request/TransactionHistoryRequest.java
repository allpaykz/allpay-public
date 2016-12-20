package kz.allpay.api.model.request;

import java.util.Date;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
public class TransactionHistoryRequest extends AbstractRequest {
    private String loginName;
    private Date fromDate;
    private Date toDate;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
