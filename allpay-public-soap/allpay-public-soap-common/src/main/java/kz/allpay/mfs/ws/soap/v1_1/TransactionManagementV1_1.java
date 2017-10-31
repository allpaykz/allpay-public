package kz.allpay.mfs.ws.soap.v1_1;


import kz.allpay.mfs.ws.soap.generated.v1_0.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author magzhan.karasayev
 * @since 5/12/16 7:35 PM
 */
@WebService(targetNamespace = TransactionManagementV1_1.TARGET_NAMESPACE)
public interface TransactionManagementV1_1 {

    public static final String TARGET_NAMESPACE = "http://www.allpay.kz/mfs/soap/TransactionManagement/1.1";
    public static final String SERVICE = "TransactionManagementService";

    /**
     * for certificate testing purposes
     * @param echo
     * @return
     */
    @WebMethod(action = "echo")
    public String echo(@WebParam(name = "Echo") String echo);

    @WebMethod(action = "createCashInTransaction")
    public CompleteTransactionResponse createCashInTransaction(@WebParam(name = "CashInRequest") CashInRequest request);

    @WebMethod(action = "createCashOutTransaction")
    public CompleteTransactionResponse createCashOutTransaction(@WebParam(name = "CashOutRequest") CashOutRequest request);

    @WebMethod(action = "completeTransaction")
    public CompleteTransactionResponse completeTransaction(@WebParam(name = "CompleteTransactionRequest") CompleteTransactionRequest request);

    @WebMethod(action = "declineTransaction")
    public CompleteTransactionResponse declineTransaction(@WebParam(name = "DeclineTransactionRequest") DeclineTransactionRequest request);

    @WebMethod(action = "checkUser")
    public CheckUserResponse checkUser(@WebParam(name = "CheckUserRequest") CheckUserRequest request);

    @WebMethod(action = "checkUserAndValidateCashIn")
    public CheckUserResponse checkUserAndValidateCashIn(@WebParam(name = "CheckUserRequest") CheckUserRequest request);

    @WebMethod(action = "availableBalance")
    public AvailableBalanceResponse availableBalance(@WebParam(name = "AvailableBalanceRequest") AvailableBalanceRequest request);

    @WebMethod(action = "vostokPlatCheck")
    public VostokPlatCheckResponse vostokPlatCheck(@WebParam(name = "VostokPlatCheckRequest") VostokPlatCheckRequest vostokPlatCheckRequest);

    @WebMethod(action = "vostokPlatPay")
    public VostokPlatPayResponse vostokPlatPay(@WebParam(name = "VostokPlatPayRequest") VostokPlatPayRequest vostokPlatPayRequest);
}
