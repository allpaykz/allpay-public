package kz.allpay.mfs.webshop.status;

/**
 * Created by aigerim on 2/9/18.
 */
public enum CustomerWebshopResponseStatusEnum {

    PENDING("1"),
    DONE("0"),
    FAIL("2"),
    UNKNOWN("");

    private String statusCode;

    CustomerWebshopResponseStatusEnum(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
