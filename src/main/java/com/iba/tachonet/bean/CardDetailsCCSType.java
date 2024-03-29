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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for CardDetailsCCSType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CardDetailsCCSType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:eu.cec.tren.tcn}CardDetailsType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:eu.cec.tren.tcn}anon1"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CardStatus" use="required" type="{urn:eu.cec.tren.tcn}CardStatusCodeEnumType" />
 *       &lt;attribute name="StartOfValidityDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="ExpiryDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardDetailsCCSType", propOrder = { "workshopDetails",
        "driverDetails" })
public class CardDetailsCCSType extends CardDetailsType {

    @XmlElement(name = "WorkshopDetails")
    protected WorkshopDetailsType workshopDetails;
    @XmlElement(name = "DriverDetails")
    protected DriverDetailsCCSType driverDetails;
    @XmlAttribute(name = "CardStatus", required = true)
    protected CardStatusCodeEnumType cardStatus;
    @XmlAttribute(name = "StartOfValidityDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startOfValidityDate;
    @XmlAttribute(name = "ExpiryDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expiryDate;

    /**
     * Gets the value of the workshopDetails property.
     * 
     * @return possible object is {@link WorkshopDetailsType }
     * 
     */
    public WorkshopDetailsType getWorkshopDetails() {
        return workshopDetails;
    }

    /**
     * Sets the value of the workshopDetails property.
     * 
     * @param value
     *            allowed object is {@link WorkshopDetailsType }
     * 
     */
    public void setWorkshopDetails(WorkshopDetailsType value) {
        this.workshopDetails = value;
    }

    /**
     * Gets the value of the driverDetails property.
     * 
     * @return possible object is {@link DriverDetailsCCSType }
     * 
     */
    public DriverDetailsCCSType getDriverDetails() {
        return driverDetails;
    }

    /**
     * Sets the value of the driverDetails property.
     * 
     * @param value
     *            allowed object is {@link DriverDetailsCCSType }
     * 
     */
    public void setDriverDetails(DriverDetailsCCSType value) {
        this.driverDetails = value;
    }

    /**
     * Gets the value of the cardStatus property.
     * 
     * @return possible object is {@link CardStatusCodeEnumType }
     * 
     */
    public CardStatusCodeEnumType getCardStatus() {
        return cardStatus;
    }

    /**
     * Sets the value of the cardStatus property.
     * 
     * @param value
     *            allowed object is {@link CardStatusCodeEnumType }
     * 
     */
    public void setCardStatus(CardStatusCodeEnumType value) {
        this.cardStatus = value;
    }

    /**
     * Gets the value of the startOfValidityDate property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getStartOfValidityDate() {
        return startOfValidityDate;
    }

    /**
     * Sets the value of the startOfValidityDate property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setStartOfValidityDate(XMLGregorianCalendar value) {
        this.startOfValidityDate = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

}
