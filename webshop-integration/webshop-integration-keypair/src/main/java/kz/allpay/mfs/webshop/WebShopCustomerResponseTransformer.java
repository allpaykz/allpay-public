package kz.allpay.mfs.webshop;

import kz.allpay.mfs.webshop.generated.customer.response.ObjectFactory;
import kz.allpay.mfs.webshop.generated.customer.response.WebShopCustomerResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringReader;
import java.util.logging.Logger;

/**
 * Created by aigerim on 2/13/18.
 */
public class WebShopCustomerResponseTransformer {

    private static final JAXBContext jaxbContext = initJAXBContext(); // thread safe so it is static
    private static final Logger log = Logger.getLogger("WebShopCustomerResponse");

    public static WebShopCustomerResponse fromXml(String xml) throws JAXBException {
        WebShopCustomerResponse result = ((JAXBElement<WebShopCustomerResponse>) jaxbContext.createUnmarshaller()
                .unmarshal(new StringReader(xml.trim().replaceFirst("^([\\W]+)<", "<")))).getValue();
        return result;
    }

    public static WebShopCustomerResponse fromXml(InputStream is) throws JAXBException {
        WebShopCustomerResponse result = ((JAXBElement<WebShopCustomerResponse>) jaxbContext.createUnmarshaller()
                .unmarshal(is)).getValue();
        return result;
    }

    private static JAXBContext initJAXBContext() {
        try {
            return JAXBContext.newInstance(ObjectFactory.class);
        } catch (JAXBException e) {
            log.severe("could not initialize jaxbContext");
            log.severe(e.getMessage());
            return null;
        }
    }

}
