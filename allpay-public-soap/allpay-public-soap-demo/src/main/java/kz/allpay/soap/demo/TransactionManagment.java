package kz.allpay.soap.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.allpay.mfs.webshop.keys.PrivateKeyReader;
import kz.allpay.mfs.webshop.keys.PublicKeyReader;
import kz.allpay.mfs.ws.soap.generated.v1_0.*;
import kz.allpay.mfs.ws.soap.handlers.SecuritySoapHandlerClient;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0Client;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
@Stateless
@Path("api")
public class TransactionManagment {

    private static final Log logger = LogFactory.getLog(TransactionManagment.class.getName());
    private static final Gson gson = new GsonBuilder().create();

    @GET
    @Path("transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public String transactions() {
        return gson.toJson(DataBase.getResponseDatabase());
    }

    @POST
    @Path("completeTransaction")
    public void completeTransaction(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, InvalidKeySpecException {

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


        // Parsing login name of user
        // We will authorize request from this user
        final String dirtyLoginName = req.getParameter("loginName");
        logger.info("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        logger.info("loginName\t"+loginName);

        // Parsing  transactionId
        // We will complete transaction using this transactionId
        final String dirtyTransactionNumber = req.getParameter("txNumber");
        logger.info("dirtyTransactionNumber\t"+dirtyTransactionNumber);
        final String transactionId = dirtyTransactionNumber.replaceAll("[^0-9]","");
        logger.info("transactionId\t"+transactionId);

        // Создаем соап клиента, по ссылке из проперти файлов.
        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(
                PropertiesUtils.getApiUrl(),
                Arrays.asList(new SecuritySoapHandlerClient(certificateIdInput,
                        PrivateKeyReader.loadPrivateKeyFromFile(new ByteArrayInputStream(pem.getBytes("UTF-8"))),
                        PublicKeyReader.loadPublicKeyFromFile(new ByteArrayInputStream(pemInputResponse.getBytes("UTF-8")))
                ))
        );

        // Вытаскиваем транзакцию из вашей локальной БД
        final CompleteTransactionResponse response = DataBase.getResponseDatabase().get(transactionId);

        // ----
        // Создаем запрос на завершение транзакции
        final CompleteTransactionRequest request =  new CompleteTransactionRequest();

        // Идентификатор транзакции в вашей системе. Должен быть уникален на всегда
        request.setGUID(response.getTransactionInfo().getGUID());

        // Айди транзакции в системе allpay.
        request.setTransactionId(response.getTransactionInfo().getTransactionId());
        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();

        // Язык, от него зависят язык текстов в ответах от сервера
        header.setLang(Language.RU);

        // Логин агента. От имени этого логина совершается запрос в системе
        header.setRequester(loginName);

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

        // Запускаем запрос
        final CompleteTransactionResponse completeTransaction;
        try {
            completeTransaction = srv.completeTransaction(request);
        } catch (Exception e) {
            ExceptionHandler.handleException(req, resp, e);
            return;
        }

        logger.info(completeTransaction.getTransactionInfo().getTransactionStatus());

        // Обновляем статус запроса в локальное БД
        DataBase.getResponseDatabase().put(completeTransaction.getTransactionInfo().getTransactionId().toString(), completeTransaction);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("<html>\n" +
                                           "<body>\n" +
                                           "    <div>Request successfully finished</div>\n" +
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
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @POST
    @Path("declineTransaction")
    public void declineTransaction(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, InvalidKeySpecException {
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


        // Parsing login name of user
        // We will authorize request from this user
        final String dirtyLoginName = req.getParameter("loginName");
        logger.info("dirtyLoginName\t"+dirtyLoginName);
        final String loginName = dirtyLoginName.replaceAll("[^0-9]", "");
        logger.info("loginName\t"+loginName);

        // Parsing  transactionId
        // We will complete transaction using this transactionId
        final String dirtyTransactionNumber = req.getParameter("txNumber");
        logger.info("dirtyTransactionNumber\t"+dirtyTransactionNumber);
        final String transactionId = dirtyTransactionNumber.replaceAll("[^0-9]","");
        logger.info("transactionId\t"+transactionId);

        // Создаем соап клиента, по ссылке из проперти файлов.
        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(
                PropertiesUtils.getApiUrl(),
                Arrays.asList(new SecuritySoapHandlerClient(certificateIdInput,
                        PrivateKeyReader.loadPrivateKeyFromFile(new ByteArrayInputStream(pem.getBytes("UTF-8"))),
                        PublicKeyReader.loadPublicKeyFromFile(new ByteArrayInputStream(pemInputResponse.getBytes("UTF-8")))
                ))
        );

        // Вытаскиваем транзакцию из вашей локальной БД
        final CompleteTransactionResponse response = DataBase.getResponseDatabase().get(transactionId);

        // ----
        // Создаем запрос на отмену транзакции
        final DeclineTransactionRequest request = new DeclineTransactionRequest();

        // Идентификатор транзакции в вашей системе. Должен быть уникален на всегда
        request.setGUID(response.getTransactionInfo().getGUID());

        // Айди транзакции в системе allpay.
        request.setTransactionId(response.getTransactionInfo().getTransactionId());

        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();

        // Язык, от него зависят язык текстов в ответах от сервера
        header.setLang(Language.RU);

        // Логин агента. От имени этого логина совершается запрос в системе
        header.setRequester(loginName);

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

        // Запускаем запрос
        final CompleteTransactionResponse completeTransaction;
        try {
            completeTransaction = srv.declineTransaction(request);
        } catch (Exception e) {
            ExceptionHandler.handleException(req, resp, e);
            return;
        }

        logger.info(completeTransaction.getTransactionInfo().getTransactionStatus());

        DataBase.getResponseDatabase().put(completeTransaction.getTransactionInfo().getTransactionId().toString(), completeTransaction);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("<html>\n" +
                                       "<body>\n" +
                                       "    <div>Request successfully finished</div>\n" +
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
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @POST
    @Path("checkUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkUser(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, InvalidKeySpecException {
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
        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(
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
            completeTransaction = srv.checkUser(request);
        } catch (Exception e) {
            return ExceptionHandler.handleExceptionAsJson(req, resp, e);
        }
        return gson.toJson(completeTransaction);
    }
}
