package kz.allpay.api.model.request;

import io.swagger.annotations.ApiModelProperty;
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

    public AbstractRequest() {
        lang = Language.RU;
    }

    @ApiModelProperty
    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    @ApiModelProperty(hidden = true)
    public String convertAndGetLangAsStringLowerCase() {
        return lang.toString().toLowerCase();
    }

}
