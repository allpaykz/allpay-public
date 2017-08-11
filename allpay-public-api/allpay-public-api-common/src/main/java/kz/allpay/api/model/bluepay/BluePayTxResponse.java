package kz.allpay.api.model.bluepay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import kz.allpay.api.model.response.AbstractResponse;

/**
 * @author magzhan.karasayev
 * @since 11/16/16 7:13 PM
 */
@ApiModel
public class BluePayTxResponse implements Serializable {
    private String m; // error code
    private String c; // comment

    public BluePayTxResponse(String m) {
        this.m = m;
    }

    public BluePayTxResponse(String m, String c) {
        this.m = m;
        this.c = c;
    }

    public BluePayTxResponse() {
    }

    @ApiModelProperty(notes = "Информация о транзакции")
    public String getM() {
        return m;
    }

    public BluePayTxResponse setM(String m) {
        this.m = m;
        return this;
    }

    @ApiModelProperty(notes = "Опциональный комментарий(зарезервировано)")
    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
