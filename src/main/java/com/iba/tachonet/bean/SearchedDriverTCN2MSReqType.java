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
 * Java class for SearchedDriver_TCN2MS_ReqType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SearchedDriver_TCN2MS_ReqType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:eu.cec.tren.tcn}SearchedDriverType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="SurnameSearchKey" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FirstNameSearchKey" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchedDriver_TCN2MS_ReqType")
@XmlSeeAlso( { SearchedDriverMS2TCNResType.class })
public class SearchedDriverTCN2MSReqType extends SearchedDriverType {

    @XmlAttribute(name = "SurnameSearchKey", required = true)
    protected String surnameSearchKey;
    @XmlAttribute(name = "FirstNameSearchKey", required = true)
    protected String firstNameSearchKey;

    /**
     * Gets the value of the surnameSearchKey property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSurnameSearchKey() {
        return surnameSearchKey;
    }

    /**
     * Sets the value of the surnameSearchKey property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSurnameSearchKey(String value) {
        this.surnameSearchKey = value;
    }

    /**
     * Gets the value of the firstNameSearchKey property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFirstNameSearchKey() {
        return firstNameSearchKey;
    }

    /**
     * Sets the value of the firstNameSearchKey property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFirstNameSearchKey(String value) {
        this.firstNameSearchKey = value;
    }

}
