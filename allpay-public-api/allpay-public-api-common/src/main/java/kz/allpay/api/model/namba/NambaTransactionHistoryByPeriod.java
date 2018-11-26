package kz.allpay.api.model.namba;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by aigerim on 11/26/18.
 */
@ApiModel
public class NambaTransactionHistoryByPeriod implements Serializable {

    private BigDecimal sumByPeriod;
    private LinkedList<NambaTransactionHistoryByDay> transactionsByDays;

    public NambaTransactionHistoryByPeriod() {
    }

    public NambaTransactionHistoryByPeriod(BigDecimal sumByPeriod, LinkedList<NambaTransactionHistoryByDay> transactionsByDays) {
        this.sumByPeriod = sumByPeriod;
        this.transactionsByDays = transactionsByDays;
    }

    @ApiModelProperty(notes = "The sum of incomes or outcomes or both during the period.")
    public BigDecimal getSumByPeriod() {
        return sumByPeriod;
    }

    public void setSumByPeriod(BigDecimal sumByPeriod) {
        this.sumByPeriod = sumByPeriod;
    }

    @ApiModelProperty(notes = "The list of transactions by day during the period.")
    public LinkedList<NambaTransactionHistoryByDay> getTransactionsByDays() {
        return transactionsByDays;
    }

    public void setTransactionsByDays(LinkedList<NambaTransactionHistoryByDay> transactionsByDays) {
        this.transactionsByDays = transactionsByDays;
    }
}
