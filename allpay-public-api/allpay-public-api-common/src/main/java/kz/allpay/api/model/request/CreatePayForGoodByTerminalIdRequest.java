package kz.allpay.api.model.request;

import java.math.BigDecimal;

/**
 * Created by aigerim on 9/5/18.
 */
public class CreatePayForGoodByTerminalIdRequest extends ValidatePayForGoodByTerminalIdRequest {

    private String rrn;

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }
}
