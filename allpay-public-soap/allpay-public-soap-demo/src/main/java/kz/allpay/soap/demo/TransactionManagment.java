package kz.allpay.soap.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import kz.allpay.mfs.ws.soap.generated.v1_0.*;
import kz.allpay.mfs.ws.soap.handlers.SecuritySoapHandlerClient;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0;
import kz.allpay.mfs.ws.soap.v1_0.TransactionManagementV1_0Client;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Arrays;

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
    @Produces(MediaType.APPLICATION_JSON)
    public void completeTransaction(@Context HttpServletRequest req) throws MalformedURLException {

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
        logger.info("transactionId\t"+transactionId)
        ;
        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(PropertiesUtil.getApiUrl(),
                                                                                   Arrays.asList(new SecuritySoapHandlerClient())
        );
        final CompleteTransactionResponse response = DataBase.getResponseDatabase().get(transactionId);

        final CompleteTransactionRequest request =  new CompleteTransactionRequest();
        request.setGUID(response.getTransactionInfo().getGUID());
        request.setTransactionId(response.getTransactionInfo().getTransactionId());
        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        header.setRequester(loginName);
        header.setTimestamp(new XMLGregorianCalendarImpl());
        request.setHeader(header);

        srv.completeTransaction(request);
    }

    @POST
    @Path("declineTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public void declineTransaction(@Context HttpServletRequest req) throws MalformedURLException {
        // Parsing login name of user
        // We will authorize request from this user
        final String dirtyFromUserLoginName = req.getParameter("fromUser");
        logger.info("dirtyFromUserLoginName\t"+dirtyFromUserLoginName);
        final String fromUser = dirtyFromUserLoginName.replaceAll("[^0-9]", "");
        logger.info("fromUser\t"+fromUser);

        // Parsing  transactionId
        // We will complete transaction using this transactionId
        final String dirtyTransactionNumber = req.getParameter("txNumber");
        logger.info("dirtyTransactionNumber\t"+dirtyTransactionNumber);
        final String transactionId = dirtyTransactionNumber.replaceAll("[^0-9]","");
        logger.info("transactionId\t"+transactionId)
        ;
        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(PropertiesUtil.getApiUrl(),
                                                                                   Arrays.asList(new SecuritySoapHandlerClient())
        );
        final CompleteTransactionResponse response = DataBase.getResponseDatabase().get(transactionId);

        final DeclineTransactionRequest request = new DeclineTransactionRequest();
        request.setGUID(response.getTransactionInfo().getGUID());
        request.setTransactionId(response.getTransactionInfo().getTransactionId());
        final OnlineTransactionRequestHeader header = new OnlineTransactionRequestHeader();
        header.setLang(Language.RU);
        header.setRequester(fromUser);
        header.setTimestamp(new XMLGregorianCalendarImpl());
        request.setHeader(header);

        srv.declineTransaction(request);
    }

}
