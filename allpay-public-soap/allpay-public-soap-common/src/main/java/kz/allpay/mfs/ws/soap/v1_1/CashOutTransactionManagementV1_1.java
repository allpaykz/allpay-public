package kz.allpay.mfs.ws.soap.v1_1;

import kz.allpay.mfs.ws.soap.generated.v1_0.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by aigerim on 8/6/18.
 */
@WebService(targetNamespace = CashOutTransactionManagementV1_1.TARGET_NAMESPACE)
public interface CashOutTransactionManagementV1_1 {

    public static final String TARGET_NAMESPACE = "http://www.allpay.kz/mfs/soap/CashOutTransactionManagement/1.1";
    public static final String SERVICE = "CashOutTransactionManagementService";

    /**
     * for certificate testing purposes
     * @param echo
     * @return
     */
    @WebMethod(action = "echo")
    public String echo(@WebParam(name = "Echo") String echo);

    @WebMethod(action = "createCashOutThroughPayment")
    public TerminalPaymentPayResponse createCashOutThroughPayment(@WebParam(name = "CashOutThroughPayRequest") CashOutThroughPayRequest request);

    @WebMethod(action = "checkCashOutThroughPayRequest")
    public CheckUserResponse checkCashOutThroughPayRequest(@WebParam(name = "CashOutThroughPayCheckRequest") CashOutThroughPayCheckRequest request);

}
