package kz.allpay.mfs.ws.soap.util;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * @author magzhan.karasayev
 * @since 12/20/16 4:56 PM
 */
public class SoapUtils {

    public static SOAPFaultException createSoapFaultException(
            String targetNamespace, String developerMessage, String userMessage) {
        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault soapFault = soapFactory.createFault(
                    userMessage,
                    new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client")
            );

            Detail detail = soapFault.addDetail();

            QName entryName = new QName(targetNamespace, "developerMessage");
            DetailEntry entry = detail.addDetailEntry(entryName);
            entry.addTextNode(developerMessage);

            QName entryName1 = new QName(targetNamespace, "userMessage");
            DetailEntry entry1 = detail.addDetailEntry(entryName1);
            entry1.addTextNode(userMessage);

            return new SOAPFaultException(soapFault);
        } catch (SOAPException e1) {
            e1.printStackTrace();
            throw new RuntimeException(e1);
        }
    }

    public static SOAPFaultException createSoapFaultException(String targetNamespace, Exception e) {
        return createSoapFaultException(targetNamespace, e.getClass().getCanonicalName(),
                e.getMessage() != null ? e.getMessage() : e.getClass().getCanonicalName()
        );
    }
}
