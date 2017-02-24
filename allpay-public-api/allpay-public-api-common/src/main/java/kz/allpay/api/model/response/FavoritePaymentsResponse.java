package kz.allpay.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.allpay.api.model.FavoritePaymentInfo;
import java.util.List;

/**
 * Класс модели, содержащий ответ о избранных платежах
 * Created by magzhan on 2/9/17.
 */

@ApiModel
public class FavoritePaymentsResponse extends AbstractResponse {

    private List<FavoritePaymentInfo> favoritePaymentInfos;

    public FavoritePaymentsResponse(List<FavoritePaymentInfo> favoritePaymentInfos) {
        this.favoritePaymentInfos = favoritePaymentInfos;
        setUserMessage("");
        setDeveloperMessage("");
    }


    @ApiModelProperty(notes = "Информация об избранных платежах")
    public List<FavoritePaymentInfo> getFavoritePaymentInfos() {
        return favoritePaymentInfos;
    }

    public void setFavoritePaymentInfos(List<FavoritePaymentInfo> favoritePaymentInfos) {
        this.favoritePaymentInfos = favoritePaymentInfos;
    }
}
