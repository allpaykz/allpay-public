package kz.allpay.api.client;


import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author magzhan.karasayev
 * @since 11/17/16 5:33 PM
 */
public class ClientStubFactory {
    /**
     * TODO: test it
     */
    public <T> T getTransactionManagementClient(String serviceURI, Class<T> clazz) {
        ClientConfig cc = new ClientConfig().register(JacksonFeature.class);
//                .register(AnotherFeature.class)
//                .register(SomeFilter.class);
        Client resource = ClientBuilder.newClient(cc);

        // create client proxy
        T proxy = WebResourceFactory.newResource(clazz, resource.target(serviceURI));
        return proxy;
    }
}
