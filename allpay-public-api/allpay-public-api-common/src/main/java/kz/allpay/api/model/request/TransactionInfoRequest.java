package kz.allpay.api.model.request;

import io.swagger.annotations.ApiModel;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class TransactionInfoRequest extends AbstractRequest {
    private Long transactionNumber;

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}
