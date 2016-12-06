package kz.allpay.mfs.webshop.signature;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.io.Reader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Iterator;

/**
 * @author magzhan.karassayev
 */
public class SignatureServiceSoapImpl implements SignatureService {

    public static XPathExpression getXpathExpression() throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        // there's no default implementation for NamespaceContext...seems kind of silly, no?
        xpath.setNamespaceContext(new NamespaceContext() {
            public String getNamespaceURI(String prefix) {
                if (prefix == null) throw new NullPointerException("Null prefix");
                else if ("SOAP-ENV".equals(prefix)) return "http://schemas.xmlsoap.org/soap/envelope/";
                else if ("xml".equals(prefix)) return XMLConstants.XML_NS_URI;
                return XMLConstants.NULL_NS_URI;
            }
            // This method isn't necessary for XPath processing.
            public String getPrefix(String uri) {
                throw new UnsupportedOperationException();
            }
            // This method isn't necessary for XPath processing either.
            public Iterator getPrefixes(String uri) {
                throw new UnsupportedOperationException();
            }
        });

        // note that all the elements in the expression are prefixed with our namespace mapping!
        XPathExpression expr = xpath.compile("//SOAP-ENV:Header[1]");
        return expr;
    }

    @Override
    public String signXML(PrivateKey aPrivate, InputStream resourceAsStream) throws SignatureException {
        try {
            return SignatureUtils.signXML(aPrivate, resourceAsStream, getXpathExpression());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new SignatureException("Could not sign message", e);
        }
    }

    @Override
    public boolean verifySignatureInXML(Reader reader, PublicKey key) throws SignatureException {
        try {
            return SignatureUtils.verifySignatureInXML(reader, key);
        }  catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new SignatureException("Could not verify message", e);
        }
    }
}
