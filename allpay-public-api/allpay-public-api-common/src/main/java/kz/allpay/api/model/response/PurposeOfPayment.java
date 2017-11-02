package kz.allpay.api.model.response;

import java.io.Serializable;

public class PurposeOfPayment extends AbstractResponse implements Serializable {
    private String textIfNotEmpty;
    private Integer commentCanBeEmpty;

    public PurposeOfPayment(String userMessage, String developerMessage, String textIfNotEmpty, Integer commentCanBeEmpty) {
        super(userMessage, developerMessage);
        this.textIfNotEmpty = textIfNotEmpty;
        this.commentCanBeEmpty = commentCanBeEmpty;
    }

    public PurposeOfPayment(String textIfNotEmpty, Integer commentCanBeEmpty) {
        this.textIfNotEmpty = textIfNotEmpty;
        this.commentCanBeEmpty = commentCanBeEmpty;
    }

    public PurposeOfPayment(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
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