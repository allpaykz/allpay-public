//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.13 at 10:54:43 AM ALMT 
//


package kz.allpay.mfs.webshop.generated.customer.response;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebShopCustomerResponseStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WebShopCustomerResponseStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DONE"/>
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="FAIL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WebShopCustomerResponseStatus")
@XmlEnum
public enum WebShopCustomerResponseStatus {

    DONE,
    PENDING,
    FAIL;

    public String value() {
        return name();
    }

    public static WebShopCustomerResponseStatus fromValue(String v) {
        return valueOf(v);
    }

}
