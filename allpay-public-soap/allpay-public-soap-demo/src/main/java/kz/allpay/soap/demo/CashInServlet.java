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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

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

        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(PropertiesUtil.getApiUrl(),
                                                                                   Arrays.asList(new SecuritySoapHandlerClient())
        );

        final CashInRequest cashInRequest = getCashInRequest(loginName, toUser, amount);


        final CompleteTransactionResponse cashInTransaction = srv.createCashInTransaction(cashInRequest);

        logger.info(cashInTransaction.getTransactionInfo().getTransactionStatus());

        DataBase.getResponseDatabase().put(cashInTransaction.getTransactionInfo().getTransactionId().toString(), cashInTransaction);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("<html>\n" +
                                           "<body>\n" +
                                           "    <div>Request successfully finished</div>\n" +
                                           "    <div id=\"counter\">5</div>\n" +
                                           "    <script>\n" +
                                           "        setInterval(function() {\n" +
                                           "            var div = document.querySelector(\"#counter\");\n" +
                                           "            var count = div.textContent * 1 - 1;\n" +
                                           "            div.textContent = count;\n" +
                                           "            if (count <= 0) {\n" +
                                           "                location.href=\"/transactions.jsp\";\n" +
                                           "            }\n" +
                                           "        }, 1000);\n" +
                                           "    </script>\n" +
                                           "</body>\n" +
                                           "</html>");
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
