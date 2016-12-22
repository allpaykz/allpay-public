package kz.allpay.mfs.ws.soap.v1_0;

import kz.allpay.mfs.signature.keyproviders.StaticTestKeyProvider;
import kz.allpay.mfs.ws.soap.handlers.SecuritySoapHandlerClient;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;

/**
 * @author magzhan.karasayev
 * @since 10/6/16 6:13 PM
 */
public class TransactionManagementV1_0Client {
    public static void main(String[] args) throws IOException, InvalidKeySpecException {
        TransactionManagementV1_0 srv = getService("http://hppav:8080/mfs-public-soap/transaction-management/v1.0?wsdl");
        srv.checkUser(null);
    }

    public static TransactionManagementV1_0 getService(String serviceUrl) throws IOException, InvalidKeySpecException {
        Handler securitySoapHandlerClient = new SecuritySoapHandlerClient(123, new StaticTestKeyProvider().getPrivateKey(0/*"FIXME"*/), new StaticTestKeyProvider().getPublicKey(0/*"FIXME"*/));
        List<Handler> handlers = Arrays.asList(securitySoapHandlerClient);
        return getService(serviceUrl, handlers);
    }

    public static TransactionManagementV1_0 getService(String serviceUrl, final List<Handler> handlers) throws MalformedURLException {
        URL url = new URL(serviceUrl);

        QName qname = new QName(TransactionManagementV1_0.TARGET_NAMESPACE, TransactionManagementV1_0.SERVICE);

        Service srv = Service.create(url, qname);
        srv.setHandlerResolver(new HandlerResolver() {
            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                return handlers;
            }
        });
        return srv.getPort(TransactionManagementV1_0.class);
    }
}
