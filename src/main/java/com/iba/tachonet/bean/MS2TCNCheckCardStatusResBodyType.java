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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MS2TCN_CheckCardStatus_ResBodyType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MS2TCN_CheckCardStatus_ResBodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchCriteria" type="{urn:eu.cec.tren.tcn}SearchCriteria_MS2TCN_ResType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MS2TCN_CheckCardStatus_ResBodyType", propOrder = { "searchCriteria" })
public class MS2TCNCheckCardStatusResBodyType {

    @XmlElement(name = "SearchCriteria", required = true)
    protected List<SearchCriteriaMS2TCNResType> searchCriteria;

    /**
     * Gets the value of the searchCriteria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the searchCriteria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getSearchCriteria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchCriteriaMS2TCNResType }
     * 
     * 
     */
    public List<SearchCriteriaMS2TCNResType> getSearchCriteria() {
        if (searchCriteria == null) {
            searchCriteria = new ArrayList<SearchCriteriaMS2TCNResType>();
        }
        return this.searchCriteria;
    }

}
