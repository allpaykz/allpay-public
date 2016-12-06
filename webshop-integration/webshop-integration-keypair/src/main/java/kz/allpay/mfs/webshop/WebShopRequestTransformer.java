package kz.allpay.mfs.webshop;

import kz.allpay.mfs.webshop.generated.request.WebShopRequestType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author aben.zhakenov
 */
public class WebShopRequestTransformer {

    private static final JAXBContext jc;

    static {
        try {
            jc = JAXBContext.newInstance(WebShopRequestType.class);
        } catch (JAXBException ex) {
            Logger.getLogger(WebShopRequestTransformer.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public WebShopRequestType unmarshallRequest(InputStream source) throws JAXBException {
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        synchronized (unmarshaller) {
            return (WebShopRequestType) jc.createUnmarshaller().unmarshal(source);
        }
    }

    private ByteArrayOutputStream marshallRequestToBAOS(WebShopRequestType request) {
        try {
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, Constants.REQUEST_XSD);
            synchronized (marshaller) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                marshaller.marshal(request, baos);
                return baos;
            }
        } catch (JAXBException ex) {
            Logger.getLogger(WebShopRequestTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String marshallRequestToString(WebShopRequestType request) {
        final ByteArrayOutputStream baos = marshallRequestToBAOS(request);
        try {
            return baos != null ? baos.toString("UTF-8") : null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Problem with using UTF-8 encoding. Very Strange");
        }
    }

    public byte[] marshallRequestToByteArr(WebShopRequestType request) {
        ByteArrayOutputStream baos = marshallRequestToBAOS(request);
        return baos != null ? baos.toByteArray() : null;
    }

}
