//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-793 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.12.07 at 08:59:42 PM EET 
//

package com.iba.tachonet.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DLStatusCodeEnumType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="DLStatusCodeEnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="Valid"/>
 *     &lt;enumeration value="Invalid"/>
 *     &lt;enumeration value="NotFound"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DLStatusCodeEnumType")
@XmlEnum
public enum DLStatusCodeEnumType {

    @XmlEnumValue("Valid")
    VALID("Valid"), @XmlEnumValue("Invalid")
    INVALID("Invalid"), @XmlEnumValue("NotFound")
    NOT_FOUND("NotFound");
    private final String value;

    DLStatusCodeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DLStatusCodeEnumType fromValue(String v) {
        for (DLStatusCodeEnumType c : DLStatusCodeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
