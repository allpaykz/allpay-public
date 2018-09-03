package kz.allpay.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by aigerim on 9/3/18.
 */
public class CashbackAmount implements Serializable {

    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private String cashbackType;
    private BigDecimal cashbackAmount;

    public CashbackAmount() {
    }

    public CashbackAmount(BigDecimal fromAmount, BigDecimal toAmount, String cashbackType, BigDecimal cashbackAmount) {
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.cashbackType = cashbackType;
        this.cashbackAmount = cashbackAmount;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    public String getCashbackType() {
        return cashbackType;
    }

    public void setCashbackType(String cashbackType) {
        this.cashbackType = cashbackType;
    }

    public BigDecimal getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(BigDecimal cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }
}
