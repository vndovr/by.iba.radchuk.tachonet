//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-793 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.12.07 at 08:59:42 PM EET 
//

package com.iba.tachonet.bean;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Header_MS2TCN_ReqType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Header_MS2TCN_ReqType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:eu.cec.tren.tcn}HeaderType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="MSRefId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeoutValue" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header_MS2TCN_ReqType")
public class HeaderMS2TCNReqType extends HeaderType {

    @XmlAttribute(name = "MSRefId", required = true)
    protected String msRefId;
    @XmlAttribute(name = "TimeoutValue", required = true)
    protected BigInteger timeoutValue;

    /**
     * Gets the value of the msRefId property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMSRefId() {
        return msRefId;
    }

    /**
     * Sets the value of the msRefId property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setMSRefId(String value) {
        this.msRefId = value;
    }

    /**
     * Gets the value of the timeoutValue property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getTimeoutValue() {
        return timeoutValue;
    }

    /**
     * Sets the value of the timeoutValue property.
     * 
     * @param value
     *            allowed object is {@link BigInteger }
     * 
     */
    public void setTimeoutValue(BigInteger value) {
        this.timeoutValue = value;
    }

}
