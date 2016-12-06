package kz.allpay.mfs.webshop;

import kz.allpay.mfs.webshop.generated.response.WebShopResponseType;

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
public class WebShopResponseTransformer {

    private static final JAXBContext jc;

    static {
        try {
            jc = JAXBContext.newInstance(WebShopResponseType.class);
        } catch (JAXBException ex) {
            Logger.getLogger(WebShopResponseTransformer.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public WebShopResponseType unmarshallResponse(InputStream source) throws JAXBException {
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        synchronized (unmarshaller) {
            return (WebShopResponseType) jc.createUnmarshaller().unmarshal(source);
        }
    }

    private ByteArrayOutputStream marshallResponseToBAOS(WebShopResponseType request) {
        try {
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, Constants.RESPONSE_XSD);
            synchronized (marshaller) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                marshaller.marshal(request, baos);
                return baos;
            }
        } catch (JAXBException ex) {
            Logger.getLogger(WebShopResponseTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String marshallResponseToString(WebShopResponseType request) throws UnsupportedEncodingException {
        ByteArrayOutputStream baos = marshallResponseToBAOS(request);
        return baos != null ? baos.toString("UTF-8") : null;
    }

    public byte[] marshallResponseToByteArr(WebShopResponseType request) {
        ByteArrayOutputStream baos = marshallResponseToBAOS(request);
        return baos != null ? baos.toByteArray() : null;
    }

}
