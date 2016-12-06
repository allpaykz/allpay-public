@javax.xml.bind.annotation.XmlSchema(
        namespace = Constants.REQUEST_XSD,
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "WSReq", namespaceURI = Constants.REQUEST_XSD)
        })
package kz.allpay.mfs.webshop.generated.request;

import kz.allpay.mfs.webshop.Constants;

import javax.xml.bind.annotation.XmlNs;