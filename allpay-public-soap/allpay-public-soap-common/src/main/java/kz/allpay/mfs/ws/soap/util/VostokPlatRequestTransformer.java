package kz.allpay.mfs.ws.soap.util;

import kz.allpay.mfs.ws.soap.generated.v1_0.VostokPlatPayRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Created by aigerim on 2/26/18.
 */
public class VostokPlatRequestTransformer {

    private static final Logger log = Logger.getLogger("CashInRequest");

    public static String marshallToXML(VostokPlatPayRequest vostokPlatPayRequest) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(VostokPlatPayRequest.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

        QName qName = new QName("http://allpay.kz/xsd/1.0.0/OnlineTransactionVostokPlat.xsd", "vostokPlatPayRequest");
        JAXBElement<VostokPlatPayRequest> root = new JAXBElement<>(qName, VostokPlatPayRequest.class, vostokPlatPayRequest);

        jaxbMarshaller.marshal(root, stringWriter);

        return stringWriter.toString();
    }

}
