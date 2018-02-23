package kz.allpay.mfs.ws.soap.v1_1;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by aigerim on 2/23/18.
 */
public class CashInTransactionManagementV1_1Client {

    public static CashInTransactionManagementV1_1 getService(String serviceUrl, final List<Handler> handlers) throws MalformedURLException {
        QName qname = new QName(CashInTransactionManagementV1_1.TARGET_NAMESPACE, CashInTransactionManagementV1_1.SERVICE);
        return getTransactionManagementV1_1A(serviceUrl, handlers, CashInTransactionManagementV1_1.class, qname);
    }

    private static <T> T getTransactionManagementV1_1A(String serviceUrl, final List<Handler> handlers, Class<T> serviceEndpointInterface, QName qname) throws MalformedURLException {
        URL url = new URL(serviceUrl);

        Service srv = Service.create(url, qname);
        srv.setHandlerResolver(new HandlerResolver() {
            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                return handlers;
            }
        });
        return srv.getPort(serviceEndpointInterface);
    }

}
