package kz.allpay.mfs.ws.soap.util;

import kz.allpay.mfs.ws.soap.generated.v1_0.*;

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
public class PayRequestTransformer {

    private static final Logger log = Logger.getLogger("CashInRequest");

    public static String marshallToXML(VostokPlatPayRequest vostokPlatPayRequest) throws JAXBException {
        return marshallToXML(
                vostokPlatPayRequest,
                new QName("http://allpay.kz/xsd/1.0.0/OnlineTransactionVostokPlat.xsd", "vostokPlatPayRequest"),
                VostokPlatPayRequest.class
        );
    }

    public static String marshallToXML(PayForServicesRequest payForServicesRequest) throws JAXBException {
        return marshallToXML(
                payForServicesRequest,
                new QName("http://allpay.kz/xsd/1.0.0/OnlineTransactionPayForServices.xsd", "payForServicesRequest"),
                PayForServicesRequest.class
        );
    }

    public static String marshallToXML(PayForGoodsRequest payForGoodsRequest) throws JAXBException {
        return marshallToXML(
                payForGoodsRequest,
                new QName("http://allpay.kz/xsd/1.0.0/OnlineTransactionPayForGoods.xsd", "payForGoodsRequest"),
                PayForGoodsRequest.class
        );
    }

    public static String marshallToXML(CashOutThroughPayRequest cashOutRequest) throws JAXBException {
        return marshallToXML(
                cashOutRequest,
                new QName("http://allpay.kz/xsd/1.0.0/OnlineTransactionCashOutThroughPay.xsd", "cashOutThroughPayRequest"),
                CashOutThroughPayRequest.class
        );
    }

    private static <T> String marshallToXML(T vostokPlatPayRequest, QName qName, Class<T> clazz) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

        JAXBElement<T> root = new JAXBElement<>(qName, clazz, vostokPlatPayRequest);

        jaxbMarshaller.marshal(root, stringWriter);

        return stringWriter.toString();
    }

}
