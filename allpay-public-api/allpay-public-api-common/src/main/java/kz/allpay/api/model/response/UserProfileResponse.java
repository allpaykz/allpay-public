package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:12 PM
 */
@ApiModel
public class UserProfileResponse extends AbstractResponse implements Serializable {

    private String loginName;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthDay;

    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private BigDecimal blockedBalance;

    private Date lastLogin;

    private String email;
    private String nickname;
    private String alertMethod;
    private Boolean identifiedUser;

    private Boolean emailReceipts;

    private String primaryAccountNumber;

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

    @ApiModelProperty(notes = "Email пользователя")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(notes = "Псевдоним пользователя")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @ApiModelProperty(notes = "Метод уведомления пользователя")
    public String getAlertMethod() {
        return alertMethod;
    }

    public void setAlertMethod(String alertMethod) {
        this.alertMethod = alertMethod;
    }

    @ApiModelProperty(notes = "Квитанций на email")
    public Boolean getEmailReceipts() {
        return emailReceipts;
    }

    public void setEmailReceipts(Boolean emailReceipts) {
        this.emailReceipts = emailReceipts;
    }

    @ApiModelProperty(notes = "Пользователь идентифицирован")
    public Boolean getIdentifiedUser() {
        return identifiedUser;
    }

    public void setIdentifiedUser(Boolean identifiedUser) {
        this.identifiedUser = identifiedUser;
    }

    @ApiModelProperty(notes = "Список номеров аккаунтов")
    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    public void setPrimaryAccountNumber(String accountNumbers) {
        this.primaryAccountNumber = accountNumbers;
    }

    @ApiModelProperty(notes = "Пол")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ApiModelProperty(notes = "День рождения")
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}

