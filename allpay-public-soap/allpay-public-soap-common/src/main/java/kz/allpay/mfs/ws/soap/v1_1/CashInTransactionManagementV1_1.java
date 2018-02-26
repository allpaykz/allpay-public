package kz.allpay.mfs.ws.soap.v1_1;

import kz.allpay.mfs.ws.soap.generated.v1_0.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by aigerim on 2/21/18.
 */
@WebService(targetNamespace = CashInTransactionManagementV1_1.TARGET_NAMESPACE)
public interface CashInTransactionManagementV1_1 {

    public static final String TARGET_NAMESPACE = "http://www.allpay.kz/mfs/soap/CashInTransactionManagement/1.1";
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

    @WebMethod(action = "checkUser")
    public CheckUserResponse checkUser(@WebParam(name = "CheckUserRequest") CheckUserRequest request);

    @WebMethod(action = "checkUserAndValidateCashIn")
    public CheckUserResponse checkUserAndValidateCashIn(@WebParam(name = "CheckUserRequest") CheckUserRequest request);

}
