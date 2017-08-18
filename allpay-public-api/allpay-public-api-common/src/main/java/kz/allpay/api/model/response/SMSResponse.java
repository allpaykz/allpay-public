package kz.allpay.api.model.response;

import java.io.Serializable;

/**
 * Created by yerzhan on 8/18/17.
 */
public class SMSResponse extends AbstractResponse implements Serializable {

    private static final long serialVersionUID = 4728992711112183518L;
    private String sender;
    private String receiver;
    private String msg;
    private String sentTime;
    private String receivedTime;
    private String operator;
    private String msgType;
    private String status;
    private String errorMsg;

    public SMSResponse(){
    }

    public SMSResponse(String sender, String receiver, String msg, String sentTime, String receivedTime,
                       String operator, String msgType, String status, String errorMsg) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
        this.sentTime = sentTime;
        this.receivedTime = receivedTime;
        this.operator = operator;
        this.msgType = msgType;
        this.status = status;
        this.errorMsg = errorMsg;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
