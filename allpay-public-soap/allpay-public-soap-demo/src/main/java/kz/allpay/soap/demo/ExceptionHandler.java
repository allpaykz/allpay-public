package kz.allpay.soap.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author magzhan.karasayev
 * @since 1/10/17 12:17 PM
 */
public class ExceptionHandler {
    public static void handleException(HttpServletRequest req, HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        String message = e.getLocalizedMessage();
        byte[] b = message.getBytes(); // (Charset.forName("UTF-8"));
        message = new String(b, "utf-8");

        response.setContentType( "text/html" );
        response.setCharacterEncoding( "UTF-8" );
        response.getWriter().write("<html><head><meta charset=\"utf-8\"/></head> \n" +
                "<body>\n" +
                "    <div>Request finished with error</div>\n" +
                "    <div>Error message:</div>\n" +
                "    <div>" + message + "</div>\n" +
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

    public static String handleExceptionAsJson(HttpServletRequest req, HttpServletResponse response, Exception e) throws IOException {
        return "{ \"message\" : " + "\"" + e.getMessage() + "\"}";
    }
}
