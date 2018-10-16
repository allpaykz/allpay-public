package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import kz.allpay.api.model.TerminalCashback;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by aigerim on 9/3/18.
 */
@ApiModel
public class BusinessTypeCategoryResponse implements Serializable {

    private String companyAlias;
    private String fieldOfActivity;
    private String iconImageUrl;
    private List<TerminalCashback> terminalCashbacks;
    private Date creationDate;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String prettyUrl;

    public String getCompanyAlias() {
        return companyAlias;
    }

    public void setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public String getIconImageUrl() {
        return iconImageUrl;
    }

    public void setIconImageUrl(String iconImageUrl) {
        this.iconImageUrl = iconImageUrl;
    }

    public List<TerminalCashback> getTerminalCashbacks() {
        return terminalCashbacks;
    }

    public void setTerminalCashbacks(List<TerminalCashback> terminalCashbacks) {
        this.terminalCashbacks = terminalCashbacks;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getPrettyUrl() {
        return prettyUrl;
    }

    public void setPrettyUrl(String prettyUrl) {
        this.prettyUrl = prettyUrl;
    }
}
