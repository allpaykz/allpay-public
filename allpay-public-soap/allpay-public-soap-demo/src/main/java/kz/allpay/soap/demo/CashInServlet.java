package kz.allpay.soap.demo;

import kz.allpay.mfs.ws.soap.generated.v1_0.CashInRequest;
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
import java.net.MalformedURLException;
import java.util.Arrays;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
public class CashInServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        final String loginName = req.getParameter("loginName");
        final String toUser = req.getParameter("toUser");
        final String amount = req.getParameter("amount");

        System.out.println(loginName);
        System.out.println(toUser);
        System.out.println(amount);

//        TransactionManagementV1_0 srv = TransactionManagementV1_0Client.getService(
//                "http://hppav:8080/mfs-public-soap/transaction-management/v1.0?wsdl",
//                Arrays.<Handler>asList(new SecuritySoapHandlerClient())
//        );
//        String res = srv.echo("");
//        CashInRequest cashInRequest = new CashInRequest();
//
//        srv.createCashInTransaction(cashInRequest);

        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setCharacterEncoding("UTF-8");
    }
}
