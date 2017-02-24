package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * Ответ - родитель всех ответов
 *
 * User: Sanzhar Aubakirov
 * Date: 11/16/16
 */

public abstract class AbstractResponse {
    private String userMessage;
    private String developerMessage;


    public AbstractResponse(String userMessage, String developerMessage) {
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
    }

    public AbstractResponse() {

    }

    @ApiModelProperty(notes = "")
    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    @ApiModelProperty(notes = "")
    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
