package kz.allpay.api.model.namba;

import kz.allpay.api.model.response.AbstractResponse;

import java.math.BigDecimal;

/**
 * Created by aigerim on 11/19/18.
 */
public class NambaAmount extends AbstractResponse {

    private BigDecimal amount;

    public NambaAmount() {
    }

    public NambaAmount(String userMessage, String developerMessage, BigDecimal amount) {
        super(userMessage, developerMessage);
        this.amount = amount;
    }

    public NambaAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
