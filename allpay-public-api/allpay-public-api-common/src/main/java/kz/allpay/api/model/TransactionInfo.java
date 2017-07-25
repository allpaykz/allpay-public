package kz.allpay.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Класс модели, содержащий информацию о транзакции
 *
 * @author magzhan.karasayev
 * @since 11/16/16 7:36 PM
 */
@ApiModel
public class TransactionInfo implements Serializable {

    private String transactionNumber;
    private String utn;
    private Date postedOn;
    private String description;
    private BigDecimal amountCredit;
    private BigDecimal amountDebit;
    private BigDecimal runningBalance;
    private String fromUser;
    private String toUser;
    private String onlineTransactionId;
    private BigDecimal amount;
    private BigDecimal charges;
    private String name;
    private String status;
    private Boolean parent;
    private String comment;

    public TransactionInfo charges(final BigDecimal charges) {
        this.charges = charges;
        return this;
    }

    public TransactionInfo name(final String name) {
        this.name = name;
        return this;
    }

    public TransactionInfo status(final String status) {
        this.status = status;
        return this;
    }


    public BigDecimal getCharges() {
        return charges;
    }

    public void setCharges(BigDecimal charges) {
        this.charges = charges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionInfo amount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public void setUtn(String utn) {
        this.utn = utn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmountCredit(BigDecimal amountCredit) {
        this.amountCredit = amountCredit;
    }

    public void setAmountDebit(BigDecimal amountDebit) {
        this.amountDebit = amountDebit;
    }

    public void setRunningBalance(BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public void setOnlineTransactionId(String onlineTransactionId) {
        this.onlineTransactionId = onlineTransactionId;
    }

    @ApiModelProperty(notes = "Номер транзакции")
    public String getTransactionNumber() {
        return transactionNumber;
    }

    @ApiModelProperty(notes = "Уникальный айди транзакции")
    public String getUtn() {
        return utn;
    }

    @ApiModelProperty(notes = "Дата проведения")
    public Date getPostedOn() {
        return postedOn;
    }

    @ApiModelProperty(notes = "Описание")
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(notes = "Какая сумма пришла")
    public BigDecimal getAmountCredit() {
        return amountCredit;
    }

    @ApiModelProperty(notes = "Какая сумма ушла")
    public BigDecimal getAmountDebit() {
        return amountDebit;
    }

    @ApiModelProperty(notes = "Сколько денег на счету")
    public BigDecimal getRunningBalance() {
        return runningBalance;
    }

    @ApiModelProperty(notes = "От кого платеж")
    public String getFromUser() {
        return fromUser;
    }

    @ApiModelProperty(notes = "Кому предназачен платеж")
    public String getToUser() {
        return toUser;
    }

    @ApiModelProperty(notes = "ID онлайн транзакции")
    public String getOnlineTransactionId() {
        return onlineTransactionId;
    }

    public TransactionInfo transactionNumber(final String transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public TransactionInfo utn(final String utn) {
        this.utn = utn;
        return this;
    }

    public TransactionInfo postedOn(final Date postedOn) {
        this.postedOn = postedOn;
        return this;
    }

    public TransactionInfo description(final String description) {
        this.description = description;
        return this;
    }

    public TransactionInfo amountCredit(final BigDecimal amountCredit) {
        this.amountCredit = amountCredit;
        return this;
    }

    public TransactionInfo amountDebit(final BigDecimal amountDebit) {
        this.amountDebit = amountDebit;
        return this;
    }

    public TransactionInfo runningBalance(final BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
        return this;
    }

    public TransactionInfo fromUser(final String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public TransactionInfo toUser(final String toUser) {
        this.toUser = toUser;
        return this;
    }

    public TransactionInfo onlineTransactionId(final String onlineTransactionId) {
        this.onlineTransactionId = onlineTransactionId;
        return this;
    }

    public TransactionInfo comment(final String comment) {
        this.comment = comment;
        return this;
    }

    public Boolean getParent() {
        return parent;
    }

    public TransactionInfo setParent(Boolean parent) {
        this.parent = parent;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

