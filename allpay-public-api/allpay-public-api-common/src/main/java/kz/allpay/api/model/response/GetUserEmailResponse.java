package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by abilkhaiyr on 5/10/17.
 */
@ApiModel
public class GetUserEmailResponse extends AbstractResponse implements Serializable {

    private String userEmail;

    @ApiModelProperty(notes = "Email пользователя")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
