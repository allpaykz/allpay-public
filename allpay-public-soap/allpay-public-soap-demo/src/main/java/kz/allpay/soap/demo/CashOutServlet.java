package kz.allpay.soap.demo;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import kz.allpay.mfs.ws.soap.generated.v1_0.CashOutRequest;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
public class CashOutServlet extends HttpServlet {
    private static final Log logger = LogFactory.getLog(CashOutServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        // Parsing login name of user
        // We will transfer money from this user to agent
        final String dirtyFromUserLoginName = req.getParameter("fromUser");
        logger.info("dirtyFromUserLoginName\t"+dirtyFromUserLoginName);
        final String fromUser = dirtyFromUserLoginName.replaceAll("[^0-9]", "");
        logger.info("fromUser\t"+fromUser);

        // Parsing authorization token
        // We will transfer money using this token
        final String dirtyToken = req.getParameter("token");
        logger.info("dirtyToken\t"+dirtyToken);
        final String token = dirtyToken.replaceAll("[^0-9]","");
        logger.info("token\t"+token);

        // Parsing amount of money to transfer
        final String dirtyAmount = req.getParameter("amount");
        logger.info("dirtyAmount\t"+dirtyAmount);
        final BigDecimal amount = BigDecimal.valueOf(Long.parseLong(dirtyAmount));
        logger.info("amount\t"+amount);

        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(
                " http://192.168.1.159:8080/allpay-public-soap/transaction-management/v1.0?wsdl",
                Arrays.asList(new SecuritySoapHandlerClient())
        );

        final CashOutRequest cashOutRequest = getCashOutRequest(fromUser, token);

        srv.createCashOutTransaction(cashOutRequest);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Request successfully finished");
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * Generate CashOutRequest entity by parameters from HTTP form
     * @param fromUser login name of user. from this account money will transfer to agent
     * @param token authorization token. take it from user mobile application
     * @return created instance of request
     */
    private CashOutRequest getCashOutRequest(String fromUser, String token) {
        final CashOutRequest cashOutRequest = new CashOutRequest();
        cashOutRequest.setToken(token);
        cashOutRequest.setGUID(UUID.randomUUID().toString());
        cashOutRequest.setFromUserName(fromUser);
        OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        header.setTimestamp(new XMLGregorianCalendarImpl());

        /**
         * This field is important. Using this field Allpay system will recognize your certificates
         */
        header.setRequester("55695325"); // This value is given by Allpay, when you start integration


        cashOutRequest.setHeader(header);
        return cashOutRequest;
    }

    /**
     * @param loginName requester
     * @param toUser    toUserName
     * @param amount    amount
     * @return created instance of request
     */
}

