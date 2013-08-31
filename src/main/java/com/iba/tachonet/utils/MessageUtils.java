package com.iba.tachonet.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author Vladimir Radchuk
 * 
 */
public final class MessageUtils {

    /**
     * Default constructor
     */
    private MessageUtils() {
        super();
    }

    /**
     * Returns the XML presentation of the object
     * 
     * @param o
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    public static final String marshall(Object o) throws JAXBException,
            UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JAXBContext context = JAXBContext.newInstance("com.iba.tachonet.bean");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(o, baos);
        return baos.toString("utf-8");
    }

    /**
     * Returns the object from its XML presentation
     * 
     * @param xml
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    public static final Object unmarshall(String xml) throws JAXBException,
            UnsupportedEncodingException {
        JAXBContext jc = JAXBContext.newInstance("com.iba.tachonet.bean");
        Unmarshaller u = jc.createUnmarshaller();
        return u.unmarshal(new ByteArrayInputStream(xml.getBytes("utf-8")));
    }
}
