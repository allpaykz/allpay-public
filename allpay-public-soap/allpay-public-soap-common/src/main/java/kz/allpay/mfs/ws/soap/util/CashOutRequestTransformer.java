package kz.allpay.mfs.ws.soap.util;

import kz.allpay.mfs.ws.soap.generated.v1_0.CashOutRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Created by aigerim on 1/17/19.
 */
public class CashOutRequestTransformer {

    private static final Logger log = Logger.getLogger("CashOutRequest");

    public static String marshallToXML(CashOutRequest cashInRequest) throws JAXBException {
        log.info("Start CashOutRequest marshallToXML");
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(CashOutRequest.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

        QName qName = new QName("http://allpay.kz/xsd/1.0.0/OnlineTransactionCashOut.xsd", "cashOutRequest");
        JAXBElement<CashOutRequest> root = new JAXBElement<>(qName, CashOutRequest.class, cashInRequest);

        jaxbMarshaller.marshal(root, stringWriter);

        log.info("Finish CashOutRequest marshallToXML");
        return stringWriter.toString();
    }

}
