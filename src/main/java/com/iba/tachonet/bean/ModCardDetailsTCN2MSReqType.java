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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ModCardDetails_TCN2MS_ReqType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ModCardDetails_TCN2MS_ReqType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:eu.cec.tren.tcn}ModCardDetailsType">
 *       &lt;sequence>
 *         &lt;element name="DeclaredBy" type="{urn:eu.cec.tren.tcn}DeclaredByMSType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModCardDetails_TCN2MS_ReqType", propOrder = { "declaredBy" })
@XmlSeeAlso( { ModCardDetailsMS2TCNResType.class })
public class ModCardDetailsTCN2MSReqType extends ModCardDetailsType {

    @XmlElement(name = "DeclaredBy", required = true)
    protected DeclaredByMSType declaredBy;

    /**
     * Gets the value of the declaredBy property.
     * 
     * @return possible object is {@link DeclaredByMSType }
     * 
     */
    public DeclaredByMSType getDeclaredBy() {
        return declaredBy;
    }

    /**
     * Sets the value of the declaredBy property.
     * 
     * @param value
     *            allowed object is {@link DeclaredByMSType }
     * 
     */
    public void setDeclaredBy(DeclaredByMSType value) {
        this.declaredBy = value;
    }

}
