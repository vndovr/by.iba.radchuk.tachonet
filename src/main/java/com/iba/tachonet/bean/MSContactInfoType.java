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
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MSContactInfoType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MSContactInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Phone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Fax" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EMail" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MSContactInfoType")
public class MSContactInfoType {

    @XmlAttribute(name = "Phone")
    protected String phone;
    @XmlAttribute(name = "Fax")
    protected String fax;
    @XmlAttribute(name = "EMail")
    protected String eMail;

    /**
     * Gets the value of the phone property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

}
