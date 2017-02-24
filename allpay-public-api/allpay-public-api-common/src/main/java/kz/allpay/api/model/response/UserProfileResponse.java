package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:12 PM
 */
@ApiModel
public class UserProfileResponse extends AbstractResponse {

    private String loginName;
    private String firstName;
    private String lastName;

    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private BigDecimal blockedBalance;

    private Date lastLogin;

    private List<String> userAccountsList = new ArrayList<>();

    public UserProfileResponse() {
        super();
    }

    public UserProfileResponse(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }

    @ApiModelProperty(notes = "Логин пользователя")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @ApiModelProperty(notes = "Имя пользователя")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty(notes = "Фамилия пользователя")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty(notes = "Доступные деньги пользователя")
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    @ApiModelProperty(notes = "Текущие деньги пользователя")
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    @ApiModelProperty(notes = "Блокированные деньги пользователя")
    public BigDecimal getBlockedBalance() {
        return blockedBalance;
    }

    public void setBlockedBalance(BigDecimal blockedBalance) {
        this.blockedBalance = blockedBalance;
    }

    @ApiModelProperty(notes = "Дата последнего входа пользователя")
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @ApiModelProperty(notes = "Номера счетов пользователя")
    public List<String> getUserAccountsList() {
        return userAccountsList;
    }

    public void setUserAccountsList(List<String> userAccountsList) {
        this.userAccountsList = userAccountsList;
    }
}

