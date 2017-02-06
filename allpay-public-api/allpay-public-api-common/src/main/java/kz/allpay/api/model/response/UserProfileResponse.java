package kz.allpay.api.model.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:12 PM
 */
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
        this.setUserMessage("");
        this.setDeveloperMessage("");
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getBlockedBalance() {
        blockedBalance = currentBalance.subtract(availableBalance);
        return blockedBalance;
    }

    public void setBlockedBalance(BigDecimal blockedBalance) {
        this.blockedBalance = blockedBalance;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<String> getUserAccountsList() {
        return userAccountsList;
    }

    public void setUserAccountsList(List<String> userAccountsList) {
        this.userAccountsList = userAccountsList;
    }
}

