package kz.allpay.soap.demo;

import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.keys.PublicKeyReader;
import kz.allpay.mfs.ws.soap.generated.v1_0.CashOutRequest;
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
import java.io.ByteArrayInputStream;
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
public class CashOutServlet extends HttpServlet {
    private static final Log logger = LogFactory.getLog(CashOutServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        // Pem input
        final String pem = req.getParameter("pemInput");
        logger.info("pem\t"+pem);

        // Pem input response
        final String pemInputResponse = req.getParameter("pemInputResponse");
        logger.info("pemInputResponse\t"+pem);

        // certificateIdInput
        final String certificateIdInputAsString = req.getParameter("certificateIdInput");
        Integer certificateIdInput = Integer.parseInt(certificateIdInputAsString);
        logger.info("certificateIdInput\t"+certificateIdInput);


        // Parsing login name of an agent
        // We will transfer money from agent to user
        final String dirtyLoginName = req.getParameter("loginName");
        logger.info("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        logger.info("loginName\t"+loginName);

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

        // Создаем соап клиента, по ссылке из проперти файлов.
        final TransactionManagementV1_0 srv;
        try {
            srv = TransactionManagementV1_0Client.getService(PropertiesUtil.getApiUrl(),
                    Arrays.asList(new SecuritySoapHandlerClient(certificateIdInput,
                            PrivateKeyReader.loadPrivateKeyFromFile(new ByteArrayInputStream(pem.getBytes("UTF-8"))),
                            PublicKeyReader.loadPublicKeyFromFile(new ByteArrayInputStream(pemInputResponse.getBytes("UTF-8")))
                    ))
            );
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        // Создаем объект запроса
        // loginName это логин агента, от него идёт запрос в система Allpay
        // fromUser это логин клиента, с него снимается сумма
        // token это токен клиента, он генерируется в приложении allpay
        // amount - сумма запроса
        final CashOutRequest cashOutRequest = getCashOutRequest(fromUser, token, loginName, amount);

        // Посылаем запрос на сервер
        CompleteTransactionResponse cashOutTransaction = srv.createCashOutTransaction(cashOutRequest);

        logger.info(cashOutTransaction.getTransactionInfo().getTransactionStatus());

        // Записываем ответ в БД
        final String transactionId = cashOutTransaction.getTransactionInfo().getTransactionId().toString();
        DataBase.getResponseDatabase().put(transactionId, cashOutTransaction);

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
     * Generate CashOutRequest entity by parameters from HTTP form
     * @param fromUser login name of user. from this account money will transfer to agent
     * @param token authorization token. take it from user mobile application
     * @param loginName Login of agent
     * @return created instance of request
     */
    private CashOutRequest getCashOutRequest(String fromUser, String token, String loginName, BigDecimal amount) {
        final CashOutRequest cashOutRequest = new CashOutRequest();
        cashOutRequest.setToken(token);

        // Идентификатор транзакции в вашей системе. Должен быть уникален на всегда
        cashOutRequest.setGUID(UUID.randomUUID().toString());

        cashOutRequest.setFromUserName(fromUser);
        cashOutRequest.setAmount(amount);
        OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        try {
            header.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Calendar not configured");
        }

        /**
         * This field is important. Using this field Allpay system will recognize your certificates
         */
        if (ValidationUtils.NVL(loginName)) {
            header.setRequester(loginName); // This value is given by Allpay, when you start integration
        } else {
            header.setRequester("55695325"); // This value is given by Allpay, when you start integration
        }


        cashOutRequest.setHeader(header);
        return cashOutRequest;
    }
}

