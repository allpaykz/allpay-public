package kz.allpay.api.model.request;

import kz.allpay.api.model.Language;

/**
 * Запрос - родитель всех запросов
 *
 * User: Sanzhar Aubakirov
 * Date: 11/16/16
 */
public class AbstractRequest {
    /**
     * It is important to know !!!
     * DEFAULT LANGUAGE is RUSSIAN
     */
    private Language lang = Language.RU;
    private String token;

    public AbstractRequest() {
        lang = Language.RU;
    }


    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
