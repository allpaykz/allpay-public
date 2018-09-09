package kz.allpay.api.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * User: Sanzhar Aubakirov
 * Date: 6/30/16
 */
@ApiModel
public enum Language implements Serializable {
    EN, RU, KK;

    public static Language fromString(String lang) {
        if (lang == null || lang.isEmpty()) {
            return RU;
        }
        for (Language language : values()) {
            if (language.name().equalsIgnoreCase(lang)) {
                return language;
            }
        }
        return RU;
    }
}
