package kz.allpay.mfs.restejb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Sanzhar Aubakirov
 * Date: 4/27/16
 */

/**
 * This servlet is to sign form data and redirect to our MFS application
 */
public class TestPageServlet extends HttpServlet {

    public final static Map<String, String> mockTransactionStatusDataBase = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(req.getParameter("webshopRequest"));
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "http://" + TestPropertiesUtils.getWhereToRedirect() + "/mfs/WebShopPayment.xhtml");
        response.setCharacterEncoding("UTF-8");
    }

}
