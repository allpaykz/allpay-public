package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by aigerim on 11/11/19.
 */
public class SimpleResponse implements Serializable {

    private String userMessage;
    private String developerMessage;

    public SimpleResponse() {
    }

    public SimpleResponse(String userMessage, String developerMessage) {
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
