//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-793 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.12.07 at 08:59:42 PM EET 
//

package com.iba.tachonet.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.iba.tachonet.IncomingMessage;
import com.iba.tachonet.MessageProcessingException;
import com.iba.tachonet.IncomingMessageVisitor;

/**
 * <p>
 * Java class for TCN2MS_ModCardStatus_ReqType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="TCN2MS_ModCardStatus_ReqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{urn:eu.cec.tren.tcn}Header_TCN2MS_ReqType"/>
 *         &lt;element name="Body" type="{urn:eu.cec.tren.tcn}TCN2MS_ModCardStatus_ReqBodyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCN2MS_ModCardStatus_ReqType", propOrder = { "header", "body" })
public class TCN2MSModCardStatusReqType implements IncomingMessage {

    @XmlElement(name = "Header", required = true)
    protected HeaderTCN2MSReqType header;
    @XmlElement(name = "Body", required = true)
    protected TCN2MSModCardStatusReqBodyType body;

    /**
     * Gets the value of the header property.
     * 
     * @return possible object is {@link HeaderTCN2MSReqType }
     * 
     */
    public HeaderTCN2MSReqType getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *            allowed object is {@link HeaderTCN2MSReqType }
     * 
     */
    public void setHeader(HeaderTCN2MSReqType value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return possible object is {@link TCN2MSModCardStatusReqBodyType }
     * 
     */
    public TCN2MSModCardStatusReqBodyType getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *            allowed object is {@link TCN2MSModCardStatusReqBodyType }
     * 
     */
    public void setBody(TCN2MSModCardStatusReqBodyType value) {
        this.body = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.Message#accept(com.iba.tachonet.IncomingMessageVisitor)
     */
    public void accept(IncomingMessageVisitor visitor)
            throws MessageProcessingException {
        visitor.accept(this);
    }

}