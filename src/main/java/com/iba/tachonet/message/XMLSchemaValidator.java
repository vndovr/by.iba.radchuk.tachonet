package com.iba.tachonet.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Vladimir Radchuk
 * 
 */
public class XMLSchemaValidator implements ErrorHandler {
    private static final Log log = LogFactory.getLog(XMLSchemaValidator.class);

    private static byte[] xsd;

    static {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("tcn.xsd");
            for (int b = is.read(); b != -1; b = is.read())
                baos.write(b);
            xsd = baos.toByteArray();
            log.info("XSD file loaded. XSD validation is turned on.");
        } catch (Exception e) {
            xsd = null;
            log.error("XMLSchemaValidator.static {}", e);
            log.error("XSD validation is turned off!");
        }
    }

    /**
     * Default constructor
     */
    public XMLSchemaValidator() {
        super();
    }

    /**
     * Validates the XML document using specified schema
     * 
     * @param xml
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public String validate(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        factory.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaSource",
                new ByteArrayInputStream(xsd));

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(this);
            builder.parse(new InputSource(new StringReader(xml)));
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
     */
    public void error(SAXParseException exception) throws SAXException {
        log.error(this.getClass().getName() + ".error()", exception);
        throw exception;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
     */
    public void fatalError(SAXParseException exception) throws SAXException {
        log.error(this.getClass().getName() + ".fatalError()", exception);
        throw exception;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
     */
    public void warning(SAXParseException exception) throws SAXException {
        log.error(this.getClass().getName() + ".warning()", exception);
    }
}
