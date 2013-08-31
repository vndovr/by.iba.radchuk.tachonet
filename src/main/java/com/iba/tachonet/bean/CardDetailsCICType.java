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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for CardDetailsCICType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CardDetailsCICType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:eu.cec.tren.tcn}CardDetailsType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CardStatus" use="required" type="{urn:eu.cec.tren.tcn}CardStatusCodeEnumType" />
 *       &lt;attribute name="StartOfValidityDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardDetailsCICType")
public class CardDetailsCICType extends CardDetailsType {

    @XmlAttribute(name = "CardNumber")
    protected String cardNumber;
    @XmlAttribute(name = "CardStatus", required = true)
    protected CardStatusCodeEnumType cardStatus;
    @XmlAttribute(name = "StartOfValidityDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startOfValidityDate;
    @XmlAttribute(name = "ExpiryDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expiryDate;

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
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
