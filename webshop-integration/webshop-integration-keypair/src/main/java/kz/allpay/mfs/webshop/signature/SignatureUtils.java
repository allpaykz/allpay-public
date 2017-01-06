package kz.allpay.mfs.webshop.signature; /**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Collections.singletonList;

/**
 * @author sanzhar.aubakirov
 */
public class SignatureUtils {

    public static final Logger logger = Logger.getLogger(SignatureUtils.class.getName());

    public static String signXML(PrivateKey aPrivate,
                                 InputStream resourceAsStream)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyException, SAXException, IOException, ParserConfigurationException, MarshalException, XMLSignatureException, TransformerException, XPathExpressionException {
        return signXML(aPrivate, resourceAsStream, null);
    }

    public static String signXML(PrivateKey aPrivate,
                                 InputStream resourceAsStream, XPathExpression xPathExpression)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyException, SAXException, IOException, ParserConfigurationException, MarshalException, XMLSignatureException, TransformerException, XPathExpressionException {
        /*
        final String providerName = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

        final XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM",
                (Provider) Class.forName(providerName)
                        .newInstance());
        */
        XMLSignatureFactory fac = null;
        try {
            fac = XMLSignatureFactory.getInstance("DOM", "XMLDSig");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        final Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
                singletonList(fac.newTransform(Transform.ENVELOPED, (XMLStructure) null)),
                null,
                null);
        final SignedInfo signedInfo = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS,
                        (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), singletonList(ref));

        Reader normalizedReader = getNormalizedReader(new InputStreamReader(resourceAsStream));

        final DocumentBuilderFactory dbf = getDocumentBuilderFactory();
        final Document doc = dbf.newDocumentBuilder().parse(new InputSource(normalizedReader));
        final DOMSignContext dsc = new DOMSignContext(aPrivate, getSignTargetElement(xPathExpression, doc));
        final XMLSignature signature = fac.newXMLSignature(signedInfo, null);
        signature.sign(dsc);

        logger.info("Document successfully signed");

        return documentToStringConverter(doc);
    }

    private static Element getSignTargetElement(XPathExpression xPathExpression, Document doc) throws XPathExpressionException {
        if (xPathExpression == null) {
            return doc.getDocumentElement();
        }

        Element result = (Element) xPathExpression.evaluate(doc, XPathConstants.NODE);
        return result;
    }

    public static boolean verifySignatureInXML(final String signedXMLString, final PublicKey key) throws Exception {
        // Instantiate the document to be validated
        StringReader reader = new StringReader(signedXMLString);
        return verifySignatureInXML(reader, key);
    }

    public static boolean verifySignatureInXML(Reader reader, final PublicKey key) throws Exception {
        Reader normalizedReader = getNormalizedReader(reader);

        // Instantiate the document to be validated
        final DocumentBuilderFactory dbf = getDocumentBuilderFactory();
        final InputSource is = new InputSource();
        is.setCharacterStream(normalizedReader);

        final Document doc = dbf.newDocumentBuilder().parse(is);

        // Find Signature element
        final NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() != 1) {
            throw new Exception("Cannot find unique Signature element, " +
                    nl.getLength() + " found");
        }

        // Create a DOM XMLSignatureFactory that will be used to unmarshal the
        // document containing the XMLSignature
        final XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a DOMValidateContext and specify a KeyValue KeySelector
        // and document context
        final DOMValidateContext valContext = new DOMValidateContext (key, nl.item(0));

        // unmarshal the XMLSignature
        final XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        // Validate the XMLSignature (generated above)
        boolean coreValidity = signature.validate(valContext);

        // Check core validation status
        if (coreValidity == false) {
            logger.warning("Signature failed core validation");
            boolean sv = signature.getSignatureValue().validate(valContext);
            logger.warning("signature validation status: " + sv);
            // check the validation status of each Reference
            Iterator i = signature.getSignedInfo().getReferences().iterator();
            for (int j = 0; i.hasNext(); j++) {
                boolean refValid =
                        ((Reference) i.next()).validate(valContext);
                logger.info("ref[" + j + "] validity status: " + refValid);
            }
            return false;
        } else {
            logger.info("Signature passed core validation");
            return true;
        }
    }

    private static Reader getNormalizedReader(Reader reader) throws IOException, TransformerException {
        return reader;
        /*
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(new StringReader(
                        "<xsl:stylesheet version=\"1.0\"" +
                                "   xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">" +
                                "<xsl:output method=\"xml\" omit-xml-declaration=\"yes\"/>" +
                                "  <xsl:strip-space elements=\"*\"/>" +
                                "  <xsl:template match=\"@*|node()\">" +
                                "   <xsl:copy>" +
                                "    <xsl:apply-templates select=\"@*|node()\"/>" +
                                "   </xsl:copy>" +
                                "  </xsl:template>" +
                                "</xsl:stylesheet>"
                ))
        );
        Source source = new StreamSource(reader);
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        transformer.transform(source, result);
        String res = sw.toString();
        logger.fine("string after xslt:" + res);
        return new StringReader(sw.toString());
        */
    }

    private static DocumentBuilderFactory getDocumentBuilderFactory() {
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
//        dbf.setIgnoringElementContentWhitespace(true);
        return dbf;
    }

    public static String documentToStringConverter(Document document) {
        try (ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream()) {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            Result result = new StreamResult(outputByteStream);
            transformer.transform(new DOMSource(document), result);

            return outputByteStream.toString("UTF-8");
        } catch (IOException | TransformerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * KeySelector which retrieves the public key out of the
     * KeyValue element and returns it.
     * NOTE: If the key algorithm doesn't match signature algorithm,
     * then the public key will be ignored.
     */
    private static class KeyValueKeySelector extends KeySelector {
        public KeySelectorResult select(KeyInfo keyInfo,
                                        KeySelector.Purpose purpose,
                                        AlgorithmMethod method,
                                        XMLCryptoContext context)
                throws KeySelectorException {
            if (keyInfo == null) {
                throw new KeySelectorException("Null KeyInfo object!");
            }
            SignatureMethod sm = (SignatureMethod) method;
            List list = keyInfo.getContent();

            for (int i = 0; i < list.size(); i++) {
                XMLStructure xmlStructure = (XMLStructure) list.get(i);
                if (xmlStructure instanceof KeyValue) {
                    PublicKey pk = null;
                    try {
                        pk = ((KeyValue) xmlStructure).getPublicKey();
                    } catch (KeyException ke) {
                        throw new KeySelectorException(ke);
                    }
                    // make sure algorithm is compatible with method
                    if (algEquals(sm.getAlgorithm(), pk.getAlgorithm())) {
                        return new SimpleKeySelectorResult(pk);
                    }
                }
            }
            throw new KeySelectorException("No KeyValue element found!");
        }

        //@@@FIXME: this should also work for key types other than DSA/RSA
        static boolean algEquals(String algURI, String algName) {
            if (algName.equalsIgnoreCase("DSA") &&
                    algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) {
                return true;
            } else if (algName.equalsIgnoreCase("RSA") &&
                    algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * XML Canonicalization using ALGO_ID_C14N_EXCL_WITH_COMMENTS
     */
    public static byte[] canonicalizedByteArray(final String toCanonicalize) {
        final Canonicalizer canon;
        try {
            canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS);
            final byte canonXmlBytes[] = canon.canonicalize(toCanonicalize.getBytes());
            return canonXmlBytes;
        } catch (InvalidCanonicalizerException | CanonicalizationException | SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Manual canonicalization failed. Check that you have proper libraries installed. Normally this should never happen");
        }
    }

    /**
     * XML Canonicalization using ALGO_ID_C14N_EXCL_WITH_COMMENTS
     */
    public static String canonicalizedString(final String toCanonicalize) {
        return new String(canonicalizedByteArray(toCanonicalize), StandardCharsets.UTF_8);
    }

    private static class SimpleKeySelectorResult implements KeySelectorResult {
        private PublicKey pk;

        SimpleKeySelectorResult(PublicKey pk) {
            this.pk = pk;
        }

        public Key getKey() {
            return pk;
        }
    }
}
