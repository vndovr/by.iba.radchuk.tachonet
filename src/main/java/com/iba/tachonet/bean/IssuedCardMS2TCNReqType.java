//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-793 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.12.07 at 08:59:42 PM EET 
//

package com.iba.tachonet.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for IssuedCard_MS2TCN_ReqType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="IssuedCard_MS2TCN_ReqType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:eu.cec.tren.tcn}IssuedCardType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="DrivingLicenseIssuingNation" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IssuedCard_MS2TCN_ReqType")
@XmlSeeAlso( { IssuedCardTCN2MSResType.class })
public class IssuedCardMS2TCNReqType extends IssuedCardType {

    @XmlAttribute(name = "DrivingLicenseIssuingNation", required = true)
    protected String drivingLicenseIssuingNation;

    /**
     * Gets the value of the drivingLicenseIssuingNation property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDrivingLicenseIssuingNation() {
        return drivingLicenseIssuingNation;
    }

    /**
     * Sets the value of the drivingLicenseIssuingNation property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDrivingLicenseIssuingNation(String value) {
        this.drivingLicenseIssuingNation = value;
    }

}
