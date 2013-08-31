//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-793 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.12.07 at 08:59:42 PM EET 
//

package com.iba.tachonet.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MemberStateType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MemberStateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MSContactInfo" type="{urn:eu.cec.tren.tcn}MSContactInfoType" minOccurs="0"/>
 *         &lt;element name="DriverDetails" type="{urn:eu.cec.tren.tcn}DriverDetailsCICType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MemberStateCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MSStatusCode" use="required" type="{urn:eu.cec.tren.tcn}MSStatusCodeEnumType" />
 *       &lt;attribute name="MSStatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MemberStateType", propOrder = { "msContactInfo",
        "driverDetails" })
public class MemberStateType {

    @XmlElement(name = "MSContactInfo")
    protected MSContactInfoType msContactInfo;
    @XmlElement(name = "DriverDetails")
    protected List<DriverDetailsCICType> driverDetails;
    @XmlAttribute(name = "MemberStateCode", required = true)
    protected String memberStateCode;
    @XmlAttribute(name = "MSStatusCode", required = true)
    protected MSStatusCodeEnumType msStatusCode;
    @XmlAttribute(name = "MSStatusMessage")
    protected String msStatusMessage;

    /**
     * Gets the value of the msContactInfo property.
     * 
     * @return possible object is {@link MSContactInfoType }
     * 
     */
    public MSContactInfoType getMSContactInfo() {
        return msContactInfo;
    }

    /**
     * Sets the value of the msContactInfo property.
     * 
     * @param value
     *            allowed object is {@link MSContactInfoType }
     * 
     */
    public void setMSContactInfo(MSContactInfoType value) {
        this.msContactInfo = value;
    }

    /**
     * Gets the value of the driverDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the driverDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDriverDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DriverDetailsCICType }
     * 
     * 
     */
    public List<DriverDetailsCICType> getDriverDetails() {
        if (driverDetails == null) {
            driverDetails = new ArrayList<DriverDetailsCICType>();
        }
        return this.driverDetails;
    }

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

    /**
     * Gets the value of the msStatusCode property.
     * 
     * @return possible object is {@link MSStatusCodeEnumType }
     * 
     */
    public MSStatusCodeEnumType getMSStatusCode() {
        return msStatusCode;
    }

    /**
     * Sets the value of the msStatusCode property.
     * 
     * @param value
     *            allowed object is {@link MSStatusCodeEnumType }
     * 
     */
    public void setMSStatusCode(MSStatusCodeEnumType value) {
        this.msStatusCode = value;
    }

    /**
     * Gets the value of the msStatusMessage property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMSStatusMessage() {
        return msStatusMessage;
    }

    /**
     * Sets the value of the msStatusMessage property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setMSStatusMessage(String value) {
        this.msStatusMessage = value;
    }

}