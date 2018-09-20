package kz.allpay.api.model.response;

import java.io.Serializable;

/**
 * Created by aigerim on 8/24/18.
 */
public class ServicePublicInfo implements Serializable {

    private String name;
    private String responseType;
    private String inputFormat;
    private Boolean validationSupported;
    private Integer inputMinLength;
    private Integer inputMaxLength;
    private Integer order;
    private Long publicCode;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public Boolean getValidationSupported() {
        return validationSupported;
    }

    public void setValidationSupported(Boolean validationSupported) {
        this.validationSupported = validationSupported;
    }

    public Integer getInputMinLength() {
        return inputMinLength;
    }

    public void setInputMinLength(Integer inputMinLength) {
        this.inputMinLength = inputMinLength;
    }

    public Integer getInputMaxLength() {
        return inputMaxLength;
    }

    public void setInputMaxLength(Integer inputMaxLength) {
        this.inputMaxLength = inputMaxLength;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(Long publicCode) {
        this.publicCode = publicCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
