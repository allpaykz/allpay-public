package kz.allpay.api.model.request;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 */
public class CreatePendingTransactionForNativePay extends AbstractRequest {
    private BigDecimal amount;
    private String serviceId;
    private String utilityAccountNumber;
    private String comment;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUtilityAccountNumber() {
        return utilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        this.utilityAccountNumber = utilityAccountNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
