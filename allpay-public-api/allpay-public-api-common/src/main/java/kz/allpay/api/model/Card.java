package kz.allpay.api.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel
public class Card implements Serializable {

    private String merchantID;
    private String hbid;
    private String cardID;
    private String cardMask;
    private Byte approve;
    private String reference;

    public Card(String merchantID, String hbid, String cardID, String cardMask, Byte approve, String reference) {
        this.merchantID = merchantID;
        this.hbid = hbid;
        this.cardID = cardID;
        this.cardMask = cardMask;
        this.approve = approve;
        this.reference = reference;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getHbid() {
        return hbid;
    }

    public void setHbid(String hbid) {
        this.hbid = hbid;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardMask() {
        return cardMask;
    }

    public void setCardMask(String cardMask) {
        this.cardMask = cardMask;
    }

    public Byte getApprove() {
        return approve;
    }

    public void setApprove(Byte approve) {
        this.approve = approve;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
