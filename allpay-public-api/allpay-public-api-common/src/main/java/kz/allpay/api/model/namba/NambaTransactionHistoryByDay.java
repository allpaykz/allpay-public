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
public class NambaTransactionHistoryByDay implements Serializable {

    private Long day;
    private BigDecimal sumByDay;
    private BigDecimal cashbackByDay;
    private LinkedList<NambaTransactionInfo> transactions;

    public NambaTransactionHistoryByDay() {
        this.transactions = new LinkedList<>();
    }

    public NambaTransactionHistoryByDay(Long day, BigDecimal sumByDay, BigDecimal cashbackByDay, LinkedList<NambaTransactionInfo> transactions) {
        this.sumByDay = sumByDay;
        this.cashbackByDay = cashbackByDay;
        this.transactions = transactions;
    }

    @ApiModelProperty(notes = "The day in the format of timestamp in milliseconds")
    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    @ApiModelProperty(notes = "The sum of incomes or outcomes or both during a day.")
    public BigDecimal getSumByDay() {
        return sumByDay;
    }

    public void setSumByDay(BigDecimal sumByDay) {
        this.sumByDay = sumByDay;
    }

    @ApiModelProperty(notes = "The sum of cashbacks during a day.")
    public BigDecimal getCashbackByDay() {
        return cashbackByDay;
    }

    public void setCashbackByDay(BigDecimal cashbackByDay) {
        this.cashbackByDay = cashbackByDay;
    }

    @ApiModelProperty(notes = "The list of transactions during a day.")
    public LinkedList<NambaTransactionInfo> getTransactions() {
        return transactions;
    }

    public void setTransactions(LinkedList<NambaTransactionInfo> transactions) {
        this.transactions = transactions;
    }
}
