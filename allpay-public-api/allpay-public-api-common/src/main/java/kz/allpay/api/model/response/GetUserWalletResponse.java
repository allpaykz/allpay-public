package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * User: Sanzhar Aubakirov
 * Date: 2/24/17
 */
@ApiModel
public class GetUserWalletResponse extends AbstractResponse {
    private List<String> userAccounts;

    @ApiModelProperty(notes = "Номера счетов пользователя")
    public List<String> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<String> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
