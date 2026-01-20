package kz.allpay.mfs.ws.soap.v1_2;

import kz.allpay.mfs.ws.soap.generated.v1_0.CashInRequest;
import kz.allpay.mfs.ws.soap.generated.v1_0.CheckUserRequest;
import kz.allpay.mfs.ws.soap.generated.v1_0.CheckUserResponse;
import kz.allpay.mfs.ws.soap.generated.v1_0.TerminalPaymentPayResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by baglan on 1/19/26.
 */
@WebService(targetNamespace = CashInTransactionManagementV1_2.TARGET_NAMESPACE)
public interface CashInTransactionManagementV1_2 {

    public static final String TARGET_NAMESPACE = "http://www.innopay.kz/mfs/soap/CashInTransactionManagement/1.2";
    public static final String SERVICE = "CashInTransactionManagementService";

    /**
     * for certificate testing purposes
     * @param echo
     * @return
     */
    @WebMethod(action = "echo")
    public String echo(@WebParam(name = "Echo") String echo);

    @WebMethod(action = "createCashInPayment")
    public TerminalPaymentPayResponse createCashInPayment(@WebParam(name = "CashInRequest") CashInRequest request);

    @WebMethod(action = "createCashInPaymentAvtobys")
    public TerminalPaymentPayResponse createCashInPaymentAvtobys(@WebParam(name = "CashInRequest") CashInRequest request);

    @WebMethod(action = "checkUser")
    public CheckUserResponse checkUser(@WebParam(name = "CheckUserRequest") CheckUserRequest request);

    @WebMethod(action = "checkUserAndValidateCashIn")
    public CheckUserResponse checkUserAndValidateCashIn(@WebParam(name = "CheckUserRequest") CheckUserRequest request);

}
