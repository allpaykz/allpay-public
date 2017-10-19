package kz.allpay.api.model;

import java.io.Serializable;

public class PaymentComment implements Serializable {
    private String textIfNotEmpty;
    private Integer commentCanBeEmpty;

    public PaymentComment(String textIfNotEmpty, Integer commentCanBeEmpty) {
        this.textIfNotEmpty = textIfNotEmpty;
        this.commentCanBeEmpty = commentCanBeEmpty;
    }

    public PaymentComment() {
    }

    public String getTextIfNotEmpty() {
        return textIfNotEmpty;
    }

    public void setTextIfNotEmpty(String textIfNotEmpty) {
        this.textIfNotEmpty = textIfNotEmpty;
    }

    public Integer getCommentCanBeEmpty() {
        return commentCanBeEmpty;
    }

    public void setCommentCanBeEmpty(Integer commentCanBeEmpty) {
        this.commentCanBeEmpty = commentCanBeEmpty;
    }
}
