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

import com.iba.tachonet.OutgoingMessage;

/**
 * <p>
 * Java class for MS2TCN_CheckIssuedCards_ReqType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MS2TCN_CheckIssuedCards_ReqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{urn:eu.cec.tren.tcn}Header_MS2TCN_ReqType"/>
 *         &lt;element name="Body" type="{urn:eu.cec.tren.tcn}MS2TCN_CheckIssuedCards_ReqBodyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MS2TCN_CheckIssuedCards_ReqType", propOrder = { "header",
        "body" })
public class MS2TCNCheckIssuedCardsReqType implements OutgoingMessage {

    @XmlElement(name = "Header", required = true)
    protected HeaderMS2TCNReqType header;
    @XmlElement(name = "Body", required = true)
    protected MS2TCNCheckIssuedCardsReqBodyType body;

    /**
     * Gets the value of the header property.
     * 
     * @return possible object is {@link HeaderMS2TCNReqType }
     * 
     */
    public HeaderMS2TCNReqType getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *            allowed object is {@link HeaderMS2TCNReqType }
     * 
     */
    public void setHeader(HeaderMS2TCNReqType value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return possible object is {@link MS2TCNCheckIssuedCardsReqBodyType }
     * 
     */
    public MS2TCNCheckIssuedCardsReqBodyType getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *            allowed object is {@link MS2TCNCheckIssuedCardsReqBodyType }
     * 
     */
    public void setBody(MS2TCNCheckIssuedCardsReqBodyType value) {
        this.body = value;
    }

}