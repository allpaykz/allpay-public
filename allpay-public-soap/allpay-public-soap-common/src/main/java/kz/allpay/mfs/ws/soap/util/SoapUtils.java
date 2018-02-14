package kz.allpay.mfs.ws.soap.util;

import java.util.Deque;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.soap.SOAPFaultException;
import net.logstash.logback.encoder.org.apache.commons.lang.exception.ExceptionUtils;
import net.logstash.logback.stacktrace.StackElementFilter;
import net.logstash.logback.stacktrace.StackHasher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author magzhan.karasayev
 * @since 12/20/16 4:56 PM
 */
public class SoapUtils {

    private static final Log log = LogFactory.getLog(SoapUtils.class);

    private static SOAPFaultException createSoapFaultException(
        String targetNamespace, String developerMessage, String userMessage,
        final Exception e) {
        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault soapFault = soapFactory.createFault(
                    userMessage,
                    new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client")
            );

            Detail detail = soapFault.addDetail();

            createDetailEntry(targetNamespace, developerMessage, detail, "developerMessage");

            createDetailEntry(targetNamespace, userMessage, detail, "userMessage");

            final String hashFromException = getHashFromException(ExceptionUtils.getRootCause(e));
            createDetailEntry(targetNamespace, hashFromException, detail, "stackHash");

            return new SOAPFaultException(soapFault);
        } catch (SOAPException e1) {
            log.error("We tried to generate good soap error message. Sorry", e1);
            throw new RuntimeException(e1);
        }
    }

    private static void createDetailEntry(final String targetNamespace,
                                          final String msg,
                                          final Detail detail,
                                          final String name) throws SOAPException {

        QName entryName = new QName(targetNamespace, name);
        DetailEntry entry = detail.addDetailEntry(entryName);
        entry.addTextNode(msg);
    }

    public static SOAPFaultException createSoapFaultException(String targetNamespace, Exception e) {
        return createSoapFaultException(targetNamespace, e.getClass().getCanonicalName(),
                e.getMessage() != null ? e.getMessage() : e.getClass().getCanonicalName(), e
        );
    }

    private static String getHashFromException(final Throwable rootCause) {

        final Deque<String> hash;
        try {
            final StackHasher stackHasher = new StackHasher(new StackElementFilter() {
                @Override
                public boolean accept(final StackTraceElement stackTraceElement) {

                    if (stackTraceElement.isNativeMethod()) {
                        return true;
                    }

                    return false;
                }
            });

            hash = stackHasher.hexHashes(rootCause);
            return hash.stream().collect(Collectors.joining());
        } catch (Exception e) {
            log.error(e);
            return "";
        }
    }
}
