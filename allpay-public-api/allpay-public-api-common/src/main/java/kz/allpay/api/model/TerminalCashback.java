package kz.allpay.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by aigerim on 9/3/18.
 */
public class TerminalCashback implements Serializable {

    private BigDecimal cashbackFromAmount;
    private BigDecimal cashbackToAmount;
    private String cashbackType;
    private BigDecimal cashbackAmount;
    private String terminalId;
    private Boolean active;

    public TerminalCashback() {
    }

    public TerminalCashback(BigDecimal cashbackFromAmount, BigDecimal cashbackToAmount, String cashbackType,
                            BigDecimal cashbackAmount, String terminalId, Boolean active) {
        this.cashbackFromAmount = cashbackFromAmount;
        this.cashbackToAmount = cashbackToAmount;
        this.cashbackType = cashbackType;
        this.cashbackAmount = cashbackAmount;
        this.terminalId = terminalId;
        this.active = active;
    }

    public BigDecimal getCashbackFromAmount() {
        return cashbackFromAmount;
    }

    public void setCashbackFromAmount(BigDecimal cashbackFromAmount) {
        this.cashbackFromAmount = cashbackFromAmount;
    }

    public BigDecimal getCashbackToAmount() {
        return cashbackToAmount;
    }

    public void setCashbackToAmount(BigDecimal cashbackToAmount) {
        this.cashbackToAmount = cashbackToAmount;
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

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
