package kz.allpay.api.model.namba;

import kz.allpay.api.model.response.AbstractResponse;

import java.math.BigDecimal;

/**
 * Created by aigerim on 11/16/18.
 */
public class NambaExpenseAmount extends AbstractResponse {

    private BigDecimal sumNetExpense;

    public NambaExpenseAmount() {
    }

    public NambaExpenseAmount(BigDecimal sumNetExpense) {
        this.sumNetExpense = sumNetExpense;
    }

    public NambaExpenseAmount(String userMessage, String developerMessage, BigDecimal sumNetExpense) {
        super(userMessage, developerMessage);
        this.sumNetExpense = sumNetExpense;
    }

    public BigDecimal getSumNetExpense() {
        return sumNetExpense;
    }

    public void setSumNetExpense(BigDecimal sumNetExpense) {
        this.sumNetExpense = sumNetExpense;
    }
}
