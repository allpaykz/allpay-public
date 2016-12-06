package kz.allpay.soap.demo;

import kz.allpay.mfs.ws.soap.generated.v1_0.CashInRequest;
import kz.allpay.mfs.ws.soap.generated.v1_0.CompleteTransactionResponse;
import kz.allpay.mfs.ws.soap.generated.v1_0.Language;
import kz.allpay.mfs.ws.soap.generated.v1_0.OnlineTransactionRequestHeader;
import kz.allpay.mfs.ws.soap.handlers.SecuritySoapHandlerClient;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0Client;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.handler.Handler;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
public class CashInServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        // Parsing login name of an agent
        // We will transfer money from agent to user
        final String dirtyLoginName = req.getParameter("loginName");
        System.out.println("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        System.out.println("loginName\t"+loginName);

        // Parsing login name of user
        // We will transfer money to this login name
        final String dirtyToUser = req.getParameter("toUser");
        System.out.println("dirtyToUser\t"+dirtyToUser);
        final String toUser = dirtyToUser.replaceAll("[^0-9]","");
        System.out.println("toUser\t"+toUser);

        // Parsing amount of money to transfer
        final String dirtyAmount = req.getParameter("amount");
        System.out.println("dirtyAmount\t"+dirtyAmount);
        final BigDecimal amount = BigDecimal.valueOf(Long.parseLong(dirtyAmount));
        System.out.println("amount\t"+amount);

        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(
                " http://192.168.1.159:8080/allpay-public-soap/transaction-management/v1.0?wsdl",
                Arrays.asList(new SecuritySoapHandlerClient())
        );

        final CashInRequest cashInRequest = getCashInRequest(loginName, toUser, amount);


        final CompleteTransactionResponse cashInTransaction = srv.createCashInTransaction(cashInRequest);

        System.out.println(cashInTransaction.getTransactionInfo().getTransactionStatus());

        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * Generate CashInRequest entity by parameters from HTTP form
     * @param loginName requester
     * @param toUser    toUserName
     * @param amount    amount
     * @return created instance of request
     */
    private CashInRequest getCashInRequest(String loginName, String toUser, BigDecimal amount) {
        final CashInRequest cashInRequest = new CashInRequest();
        cashInRequest.setAmount(amount);
        cashInRequest.setToUserName(toUser);
        cashInRequest.setGUID(UUID.randomUUID().toString());
        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        header.setRequester(loginName);
        cashInRequest.setHeader(header);
        return cashInRequest;
    }

    /**
     * Validate phone number
     *
     * @param fullPhoneNumber It is phone number (without prefix +7). 11 digits, example 77002200051 (7 700 220 0051)
     * @return true only if number is valid
     */
    public static Boolean validateFullPhoneNumber(final String fullPhoneNumber) {
        if (!NVL(fullPhoneNumber)) {
            return false;
        }
        final Pattern alphaNumericP = Pattern.compile("[0-9]{11}");
        final Matcher matcher = alphaNumericP.matcher(fullPhoneNumber);
        return matcher.matches();
    }

    /**
     * Returns true if object not empty and not null
     *
     * @param object checked object
     * @return true/false
     */
    public static Boolean NVL(Object object) {

        if (object == null) return false;

        if (object instanceof String) {
            if (((String) object).isEmpty()) return false;
        }

        return true;
    }
}
