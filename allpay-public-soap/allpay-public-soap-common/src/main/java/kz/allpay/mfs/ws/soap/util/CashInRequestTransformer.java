package kz.allpay.mfs.ws.soap.util;

import javax.xml.bind.*;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Created by aigerim on 2/21/18.
 */
public class CashInRequestTransformer {

    private static final Logger log = Logger.getLogger("CashInRequest");

    public static String marshallToXML(Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        StringWriter writer = new StringWriter();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    
}
