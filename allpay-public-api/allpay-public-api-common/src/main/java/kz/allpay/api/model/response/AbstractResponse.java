package kz.allpay.api.model.response;

/**
 * Ответ - родитель всех ответов
 *
 * User: Sanzhar Aubakirov
 * Date: 11/16/16
 */
public abstract class AbstractResponse {
    private String userMessage;
    private String developerMessage;

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
