package kz.allpay.soap.demo;

import kz.allpay.mfs.ws.soap.generated.v1_0.CashInRequest;
import kz.allpay.mfs.ws.soap.generated.v1_0.CompleteTransactionResponse;
import kz.allpay.mfs.ws.soap.generated.v1_0.Language;
import kz.allpay.mfs.ws.soap.generated.v1_0.OnlineTransactionRequestHeader;
import kz.allpay.mfs.ws.soap.handlers.SecuritySoapHandlerClient;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0Client;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
public class CashInServlet extends HttpServlet{
    private static final Log logger = LogFactory.getLog(CashInServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        // Parsing login name of an agent
        // We will transfer money from agent to user
        final String dirtyLoginName = req.getParameter("loginName");
        logger.info("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        logger.info("loginName\t"+loginName);

        // Parsing login name of user
        // We will transfer money to this login name
        final String dirtyToUser = req.getParameter("toUser");
        logger.info("dirtyToUser\t"+dirtyToUser);
        final String toUser = dirtyToUser.replaceAll("[^0-9]","");
        logger.info("toUser\t"+toUser);

        // Parsing amount of money to transfer
        final String dirtyAmount = req.getParameter("amount");
        logger.info("dirtyAmount\t"+dirtyAmount);
        final BigDecimal amount = BigDecimal.valueOf(Long.parseLong(dirtyAmount));
        logger.info("amount\t"+amount);

        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(
                " http://192.168.1.159:8080/allpay-public-soap/transaction-management/v1.0?wsdl",
                Arrays.asList(new SecuritySoapHandlerClient())
        );

        final CashInRequest cashInRequest = getCashInRequest(loginName, toUser, amount);


        final CompleteTransactionResponse cashInTransaction = srv.createCashInTransaction(cashInRequest);

        logger.info(cashInTransaction.getTransactionInfo().getTransactionStatus());

        DataBase.getResponseDatabase().put(cashInTransaction.getTransactionInfo().getTransactionId().toString(), cashInTransaction);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Request successfully finished");
        response.getWriter().flush();
        response.getWriter().close();
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
}
