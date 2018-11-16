package kz.allpay.api.model.namba;

import kz.allpay.api.model.response.AbstractResponse;

import java.math.BigDecimal;

/**
 * Created by aigerim on 11/16/18.
 */
public class NambaReceiptAmount extends AbstractResponse {

    private BigDecimal sumNetReceipt;

    public NambaReceiptAmount() {
    }

    public NambaReceiptAmount(String userMessage, String developerMessage, BigDecimal sumNetReceipt) {
        super(userMessage, developerMessage);
        this.sumNetReceipt = sumNetReceipt;
    }

    public NambaReceiptAmount(BigDecimal sumNetReceipt) {
        this.sumNetReceipt = sumNetReceipt;
    }

    public BigDecimal getSumNetReceipt() {
        return sumNetReceipt;
    }

    public void setSumNetReceipt(BigDecimal sumNetReceipt) {
        this.sumNetReceipt = sumNetReceipt;
    }
}
