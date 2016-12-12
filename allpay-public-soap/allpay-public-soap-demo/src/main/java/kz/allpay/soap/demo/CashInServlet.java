package kz.allpay.soap.demo;

import kz.allpay.mfs.signature.keyproviders.StaticTestKeyProvider;
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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
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

        // Создаем соап клиента, по ссылке из проперти файлов.
        final TransactionManagementV1_0 srv;
        try {
            srv = TransactionManagementV1_0Client.getService(PropertiesUtil.getApiUrl(),
                    Arrays.asList(new SecuritySoapHandlerClient(123, new StaticTestKeyProvider().getPrivateKey("stub")))
            );
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        // Создаем объект запроса
        // loginName это логин агента, от него идёт запрос в система Allpay
        // toUser это логин клиента
        // amount - сумма запроса
        final CashInRequest cashInRequest = getCashInRequest(loginName, toUser, amount);

        // Посылаем запрос на CashIn
        final CompleteTransactionResponse cashInTransaction = srv.createCashInTransaction(cashInRequest);

        logger.info(cashInTransaction.getTransactionInfo().getTransactionStatus());

        // Записываем ответ в БД
        final String transactionId = cashInTransaction.getTransactionInfo().getTransactionId().toString();
        DataBase.getResponseDatabase().put(transactionId, cashInTransaction);

        // Редиректим юзера на страницу со списком транзакций
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("<html>\n" +
                                           "<body>\n" +
                                           "    <div>Request successfully finished</div>\n" +
                                           "    <div>Transaction number:</div>\n" +
                                           "    <div>" +transactionId+ "</div>\n" +
                                           "    <div id=\"counter\">5</div>\n" +
                                           "    <script>\n" +
                                           "        setInterval(function() {\n" +
                                           "            var div = document.querySelector(\"#counter\");\n" +
                                           "            var count = div.textContent * 1 - 1;\n" +
                                           "            div.textContent = count;\n" +
                                           "            if (count <= 0) {\n" +
                                           "                location.href=\"" + req.getContextPath() +"/transactions.jsp\";\n" +
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
     * @param loginName requester of request
     * @param toUser    toUserName which user
     * @param amount    amount how much money
     * @return created instance of request
     */
    private CashInRequest getCashInRequest(String loginName, String toUser, BigDecimal amount) {
        final CashInRequest cashInRequest = new CashInRequest();
        cashInRequest.setAmount(amount);
        cashInRequest.setToUserName(toUser);
        // Идентификатор транзакции в вашей системе. Должен быть уникален на всегда
        cashInRequest.setGUID(UUID.randomUUID().toString());
        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        header.setRequester(loginName);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        try {
            header.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Calendar not configured");
        }
        cashInRequest.setHeader(header);
        return cashInRequest;
    }
}
