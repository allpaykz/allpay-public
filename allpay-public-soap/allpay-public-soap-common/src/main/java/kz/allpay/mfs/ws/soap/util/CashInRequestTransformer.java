package kz.allpay.mfs.ws.soap.util;

import kz.allpay.mfs.ws.soap.generated.v1_0.CashInRequest;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Created by aigerim on 2/21/18.
 */
public class CashInRequestTransformer {

    private static final Logger log = Logger.getLogger("CashInRequest");

    public static String marshallToXML(CashInRequest cashInRequest) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(CashInRequest.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

        QName qName = new QName("http://allpay.kz/xsd/1.0.0/OnlineTransactionCashIn.xsd", "cashInRequest");
        JAXBElement<CashInRequest> root = new JAXBElement<>(qName, CashInRequest.class, cashInRequest);

        jaxbMarshaller.marshal(root, stringWriter);

        return stringWriter.toString();
    }

    
}
