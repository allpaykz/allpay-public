package kz.allpay.api.model.bluepay;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yerzhan.khibatkhanuly on 1/12/17.
 */
public class BluePayTxInfo implements Serializable {

    private BigDecimal a; // amount
    private String f; //fromUser
    private BigDecimal t; //transactionNumber
    private String c; //chargeAmount
    private String cc; //currencyCode
    private String ti; //terminalId
    private String s; //statusI18n

    public BigDecimal getA() {
        return this.a;
    }

    public void setA(final BigDecimal a) {
        this.a = a;
    }

    public String getF() {
        return this.f;
    }

    public void setF(final String f) {
        this.f = f;
    }

    public BigDecimal getT() {
        return this.t;
    }

    public void setT(final BigDecimal t) {
        this.t = t;
    }

    public String getC() {
        return this.c;
    }

    public void setC(final String c) {
        this.c = c;
    }

    public String getCc() {
        return this.cc;
    }

    public void setCc(final String cc) {
        this.cc = cc;
    }

    public String getTi() {
        return this.ti;
    }

    public void setTi(final String ti) {
        this.ti = ti;
    }

    public String getS() {
        return this.s;
    }

    public void setS(final String s) {
        this.s = s;
    }
}
