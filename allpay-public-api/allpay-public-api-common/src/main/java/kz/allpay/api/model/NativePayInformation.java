package kz.allpay.api.model;

import org.w3c.dom.Element;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aigerim on 10/3/17.
 */
public class NativePayInformation {

    private String prv_txn;
    private Integer result;
    private String comment;
    private String currency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal sum;
    private BigInteger txn_id;
    private Map<String, String> extraFields = new HashMap<>();

    public String getPrv_txn() {
        return prv_txn;
    }

    public void setPrv_txn(String prv_txn) {
        this.prv_txn = prv_txn;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigInteger getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(BigInteger txn_id) {
        this.txn_id = txn_id;
    }

    public Map<String, String> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(Map<String, String> extraFields) {
        this.extraFields = extraFields;
    }
}
