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
 * Java class for DeclaredByMSType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DeclaredByMSType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:eu.cec.tren.tcn}DeclaredByType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="MemberStateCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeclaredByMSType")
public class DeclaredByMSType extends DeclaredByType {

    @XmlAttribute(name = "MemberStateCode", required = true)
    protected String memberStateCode;

    /**
     * Gets the value of the memberStateCode property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMemberStateCode() {
        return memberStateCode;
    }

    /**
     * Sets the value of the memberStateCode property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setMemberStateCode(String value) {
        this.memberStateCode = value;
    }

}
