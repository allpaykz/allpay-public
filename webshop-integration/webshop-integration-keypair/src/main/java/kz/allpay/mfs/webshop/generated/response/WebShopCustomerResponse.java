package kz.allpay.mfs.webshop.generated.response;

import kz.allpay.mfs.webshop.Constants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.logging.Logger;


/**
 * <p>Java class for WebShopCustomerResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="WebShopCustomerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Reason">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */

/**
 * Created by aigerim on 2/9/18.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebShopCustomerResponse", propOrder = {
        "status",
        "reason"
}, namespace = Constants.CUSTOMER_RESPONSE_XSD)
@XmlRootElement(name = "WebShopCustomerResponse", namespace = Constants.CUSTOMER_RESPONSE_XSD)
public class WebShopCustomerResponse implements Serializable{

    @XmlElement(name = "Status", required = true, namespace = Constants.CUSTOMER_RESPONSE_XSD)
    protected String status;
    @XmlElement(name = "Reason", required = true, namespace = Constants.CUSTOMER_RESPONSE_XSD)
    protected String reason;

    private static final JAXBContext jaxbContext = initJAXBContext(); // thread safe so it is static
    private static final Logger log = Logger.getLogger("WebShopCustomerResponse");

    public static WebShopCustomerResponse fromXml(String xml) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        WebShopCustomerResponse result = (WebShopCustomerResponse) unmarshaller.unmarshal(new StringReader(xml.trim().replaceFirst("^([\\W]+)<", "<")));
        return result;
    }

    public static WebShopCustomerResponse fromXml(InputStream is) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        WebShopCustomerResponse result = (WebShopCustomerResponse) unmarshaller.unmarshal(is);
        return result;
    }

    private static JAXBContext initJAXBContext() {
        try {
            return JAXBContext.newInstance(WebShopCustomerResponse.class);
        } catch (JAXBException e) {
            log.severe("could not initialize jaxbContext");
            log.severe(e.getMessage());
            return null;
        }
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the reason property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReason(String value) {
        this.reason = value;
    }

}
