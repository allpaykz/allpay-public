package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import kz.allpay.api.model.CashbackAmount;
import kz.allpay.api.model.CompanyIcon;

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
    private String companyFieldOfActivity;
    private CompanyIcon companyIcon;
    private List<CashbackAmount> cashbacks;
    private Date creationDate;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String prettyURL;

    public String getCompanyAlias() {
        return companyAlias;
    }

    public void setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias;
    }

    public String getCompanyFieldOfActivity() {
        return companyFieldOfActivity;
    }

    public void setCompanyFieldOfActivity(String companyFieldOfActivity) {
        this.companyFieldOfActivity = companyFieldOfActivity;
    }

    public CompanyIcon getCompanyIcon() {
        return companyIcon;
    }

    public void setCompanyIcon(CompanyIcon companyIcon) {
        this.companyIcon = companyIcon;
    }

    public List<CashbackAmount> getCashbacks() {
        return cashbacks;
    }

    public void setCashbacks(List<CashbackAmount> cashbacks) {
        this.cashbacks = cashbacks;
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

    public String getPrettyURL() {
        return prettyURL;
    }

    public void setPrettyURL(String prettyURL) {
        this.prettyURL = prettyURL;
    }
}
