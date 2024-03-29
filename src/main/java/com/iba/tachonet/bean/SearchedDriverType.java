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
 * Java class for SearchedDriverType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SearchedDriverType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Surname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FirstName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BirthDate" use="required" type="{urn:eu.cec.tren.tcn}YYYY-MM-DDSpecialStringType" />
 *       &lt;attribute name="PlaceOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DrivingLicenseNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DrivingLicenseIssuingNation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchedDriverType")
@XmlSeeAlso( { SearchedDriverMS2TCNReqType.class,
        SearchedDriverTCN2MSReqType.class })
public abstract class SearchedDriverType {

    @XmlAttribute(name = "Surname", required = true)
    protected String surname;
    @XmlAttribute(name = "FirstName", required = true)
    protected String firstName;
    @XmlAttribute(name = "BirthDate", required = true)
    protected String birthDate;
    @XmlAttribute(name = "PlaceOfBirth")
    protected String placeOfBirth;
    @XmlAttribute(name = "DrivingLicenseNumber")
    protected String drivingLicenseNumber;
    @XmlAttribute(name = "DrivingLicenseIssuingNation")
    protected String drivingLicenseIssuingNation;

    /**
     * Gets the value of the surname property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the placeOfBirth property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets the value of the placeOfBirth property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setPlaceOfBirth(String value) {
        this.placeOfBirth = value;
    }

    /**
     * Gets the value of the drivingLicenseNumber property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    /**
     * Sets the value of the drivingLicenseNumber property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDrivingLicenseNumber(String value) {
        this.drivingLicenseNumber = value;
    }

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
