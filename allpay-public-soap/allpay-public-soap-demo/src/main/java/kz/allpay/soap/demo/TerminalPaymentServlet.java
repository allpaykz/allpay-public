package kz.allpay.soap.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.keys.PublicKeyReader;
import kz.allpay.mfs.ws.soap.generated.v1_0.*;
import kz.allpay.mfs.ws.soap.handlers.SecuritySoapHandlerClient;
import kz.allpay.mfs.ws.soap.v1_1.CashInTransactionManagementV1_1;
import kz.allpay.mfs.ws.soap.v1_1.CashInTransactionManagementV1_1Client;
import kz.allpay.mfs.ws.soap.v1_1.TransactionManagementV1_1;
import kz.allpay.mfs.ws.soap.v1_1.TransactionManagementV1_1Client;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by aigerim on 2/23/18.
 */
public class TerminalPaymentServlet extends HttpServlet {

    private static final Log logger = LogFactory.getLog(TerminalPaymentServlet.class.getName());
    private static final Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String operationType = request.getParameter("operationType");
        switch (operationType) {
            case "VOSTOKPLAT":
                try {
                    payThroughPayVostokPlat(request, response);
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
                break;
            case "CASHIN":
                payCashIn(request, response);
                break;
            default:
                throw new RuntimeException("Wrong operationType: " + operationType);
        }
    }

    private void payCashIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Pem input
        final String pem = request.getParameter("pemInput");
        logger.info("pem\t"+pem);

        // Pem input response
        final String pemInputResponse = request.getParameter("pemInputResponse");
        logger.info("pemInputResponse\t"+pem);

        // certificateIdInput
        final String certificateIdInputAsString = request.getParameter("certificateIdInput");
        Integer certificateIdInput = Integer.parseInt(certificateIdInputAsString);
        logger.info("certificateIdInput\t"+certificateIdInput);

        // Parsing login name of user
        // We will authorize request from this user
        final String dirtyLoginName = request.getParameter("loginName");
        logger.info("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        logger.info("loginName\t"+loginName);

        // Parsing login name of user
        // We will transfer money to this login name
        final String dirtyToUser = request.getParameter("toUserName");
        logger.info("dirtyToUser\t"+dirtyToUser);
        final String toUser = dirtyToUser.replaceAll("[^0-9]","");
        logger.info("toUser\t"+toUser);

        // Parsing amount of money to transfer
        final String dirtyAmount = request.getParameter("amount");
        logger.info("dirtyAmount\t"+dirtyAmount);
        final BigDecimal amount = BigDecimal.valueOf(Long.parseLong(dirtyAmount));
        logger.info("amount\t"+amount);

        // Parsing guid of transaction
        final String guid = request.getParameter("guid");
        logger.info("guid\t" + guid);

        // Создаем соап клиента, по ссылке из проперти файлов.
        final CashInTransactionManagementV1_1 srv;
        try {
            srv = CashInTransactionManagementV1_1Client.getService(PropertiesUtils.getCashInApiUrl(),
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
        // toUser это логин клиента
        // amount - сумма запроса
        final CashInRequest cashInRequest = getCashInRequest(loginName, toUser, amount, guid);

        // Посылаем запрос на CashIn
        final TerminalPaymentPayResponse terminalPaymentPayResponse;
        try {
            terminalPaymentPayResponse = srv.createCashInPayment(cashInRequest);
        } catch (Exception e) {
            ExceptionHandler.handleException(request, response, e);
            return;
        }

        logger.info(terminalPaymentPayResponse.getTerminalPaymentTransactionStatus());

        // Записываем ответ в БД
        final String rrn = cashInRequest.getGUID();
        if (terminalPaymentPayResponse.getTerminalPaymentTransactionStatus() == TerminalPaymentTransactionStatus.PENDING) {
            DataBaseTerminalPayments.getResponseCashInDatabase().put(rrn, terminalPaymentPayResponse);
        }

        // Редиректим юзера на страницу со списком транзакций
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("<html>\n" +
                "<body>\n" +
                "    <div>Request finished</div>\n" +
                "    <div>Transaction number:</div>\n" +
                "    <div>" +rrn+ "</div>\n" +
                "    <div>Reason(optional):</div>\n" +
                "    <div>" + terminalPaymentPayResponse.getReason() + "</div>\n" +
                "    <div>Status:</div>\n" +
                "    <div>" + terminalPaymentPayResponse.getTerminalPaymentTransactionStatus() + "</div>\n" +
                "    <div id=\"counter\">5</div>\n" +
                "    <script>\n" +
                "        setInterval(function() {\n" +
                "            var div = document.querySelector(\"#counter\");\n" +
                "            var count = div.textContent * 1 - 1;\n" +
                "            div.textContent = count;\n" +
                "            if (count <= 0) {\n" +
                "                location.href=\"" + request.getContextPath() +"/transactions.jsp\";\n" +
                "            }\n" +
                "        }, 1000);\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>");
        response.getWriter().flush();
        response.getWriter().close();
    }

    private CashInRequest getCashInRequest(String loginName, String toUser, BigDecimal amount, String guid) {
        final CashInRequest cashInRequest = new CashInRequest();
        cashInRequest.setAmount(amount);
        cashInRequest.setToUserName(toUser);
        // Идентификатор транзакции в вашей системе. Должен быть уникален на всегда
        cashInRequest.setGUID(guid);
        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        header.setRequester(loginName);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        try {
            header.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            cashInRequest.setValueDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Calendar not configured");
        }
        cashInRequest.setHeader(header);
        return cashInRequest;
    }

    public void payThroughPayVostokPlat(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeySpecException {

        // Pem input
        final String pem = request.getParameter("pemInput");
        logger.info("pem\t"+pem);

        // Pem input response
        final String pemInputResponse = request.getParameter("pemInputResponse");
        logger.info("pemInputResponse\t"+pem);

        // certificateIdInput
        final String certificateIdInputAsString = request.getParameter("certificateIdInput");
        Integer certificateIdInput = Integer.parseInt(certificateIdInputAsString);
        logger.info("certificateIdInput\t"+certificateIdInput);


        // Parsing login name of user
        // We will authorize request from this user
        final String dirtyLoginName = request.getParameter("loginName");
        logger.info("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        logger.info("loginName\t"+loginName);

        // Parsing login name of user
        // We will transfer money to this login name
        final String dirtyToUser = request.getParameter("toUserName");
        logger.info("dirtyToUser\t"+dirtyToUser);
        final String toUser = dirtyToUser.replaceAll("[^0-9]","");
        logger.info("toUser\t"+toUser);

        // Parsing amount of money to transfer
        final String dirtyAmount = request.getParameter("amount");
        logger.info("dirtyAmount\t"+dirtyAmount);
        final BigDecimal amount = BigDecimal.valueOf(Long.parseLong(dirtyAmount));
        logger.info("amount\t"+amount);

        // Parsing guid of transaction
        final String rrn = request.getParameter("rrn");
        logger.info("rrn\t" + rrn);

        // Parsing guid of transaction
        final String utilityAccountNumber = request.getParameter("utilityAccountNumber");
        logger.info("utilityAccountNumber\t" + rrn);

        // Создаем соап клиента, по ссылке из проперти файлов.
        final TransactionManagementV1_1 srv;
        try {
            srv = TransactionManagementV1_1Client.getService(PropertiesUtils.getApiUrl(),
                    Arrays.asList(new SecuritySoapHandlerClient(certificateIdInput,
                            PrivateKeyReader.loadPrivateKeyFromFile(new ByteArrayInputStream(pem.getBytes("UTF-8"))),
                            PublicKeyReader.loadPublicKeyFromFile(new ByteArrayInputStream(pemInputResponse.getBytes("UTF-8")))
                    ))
            );
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        final VostokPlatPayRequest vostokPlatPayRequest = getVostokPlatPayRequest(toUser, loginName, utilityAccountNumber,
                amount, rrn);

        final TerminalPaymentPayResponse terminalPaymentPayResponse;
        try {
            terminalPaymentPayResponse = srv.vostokPlatPay(vostokPlatPayRequest);
        } catch (Exception e) {
            ExceptionHandler.handleException(request, response, e);
            return;
        }

        logger.info(terminalPaymentPayResponse.getTerminalPaymentTransactionStatus());

        // Записываем ответ в БД
        DataBaseTerminalPayments.getResponseCashInDatabase().put(rrn, terminalPaymentPayResponse);

        // Редиректим юзера на страницу со списком транзакций
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("<html>\n" +
                "<body>\n" +
                "    <div>Request successfully finished</div>\n" +
                "    <div>Transaction number:</div>\n" +
                "    <div>" +rrn+ "</div>\n" +
                "    <div id=\"counter\">5</div>\n" +
                "    <script>\n" +
                "        setInterval(function() {\n" +
                "            var div = document.querySelector(\"#counter\");\n" +
                "            var count = div.textContent * 1 - 1;\n" +
                "            div.textContent = count;\n" +
                "            if (count <= 0) {\n" +
                "                location.href=\"" + request.getContextPath() +"/transactions.jsp\";\n" +
                "            }\n" +
                "        }, 1000);\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>");
        response.getWriter().flush();
        response.getWriter().close();
    }

    private VostokPlatPayRequest getVostokPlatPayRequest(String userName, String requesterLogin, String utilityAccountNumber,
                                                         BigDecimal amount, String rrn) {
        final VostokPlatPayRequest vostokPlatPayRequest = new VostokPlatPayRequest();
        vostokPlatPayRequest.setUserName(userName);
        vostokPlatPayRequest.setUtilityAccountNumber(utilityAccountNumber);
        vostokPlatPayRequest.setRrn(rrn);
        vostokPlatPayRequest.setAmount(amount);
        vostokPlatPayRequest.setFullAmount(amount);
        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        header.setRequester(requesterLogin);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        try {
            header.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            vostokPlatPayRequest.setValueDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Calendar not configured");
        }
        vostokPlatPayRequest.setHeader(header);
        return vostokPlatPayRequest;
    }

    @POST
    @Path("checkUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkUserWithAgentBalance(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, InvalidKeySpecException {
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
        final String dirtyLoginName = req.getParameter("userLoginName");
        logger.info("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        logger.info("loginName\t"+loginName);

        // Parsing login name of user
        // We will transfer money from this user to agent
        final String dirtyRequester = req.getParameter("loginName");
        logger.info("dirtyRequester\t"+dirtyRequester);
        final String requester = dirtyRequester.replaceAll("[^0-9]", "");
        logger.info("requester\t"+requester);

        // Создаем соап клиента, по ссылке из проперти файлов.
        TransactionManagementV1_1 srv = TransactionManagementV1_1Client.getService(
                PropertiesUtils.getApiUrl(),
                Arrays.asList(new SecuritySoapHandlerClient(certificateIdInput,
                        PrivateKeyReader.loadPrivateKeyFromFile(new ByteArrayInputStream(pem.getBytes("UTF-8"))),
                        PublicKeyReader.loadPublicKeyFromFile(new ByteArrayInputStream(pemInputResponse.getBytes("UTF-8")))
                ))
        );

        // ----
        // Создаем запрос на информацию о клиенте
        final CheckUserRequest request =  new CheckUserRequest();

        // Логин клинтеа, про него мы хотим узнать
        request.setUserName(loginName);

        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();

        // Язык, от него зависят язык текстов в ответах от сервера
        header.setLang(Language.RU);

        // Логин агента. От имени этого логина совершается запрос в системе
        header.setRequester(requester);

        // Дата запроса на стороне запрашивающего
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        try {
            header.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Calendar not configured");
        }
        request.setHeader(header);
        // Запрос создан
        // ----

        // Отправляем запрос
        final CheckUserResponse completeTransaction;
        try {
            completeTransaction = srv.checkUserAndValidateCashIn(request);
        } catch (Exception e) {
            return ExceptionHandler.handleExceptionAsJson(req, resp, e);
        }
        return gson.toJson(completeTransaction);
    }

}
