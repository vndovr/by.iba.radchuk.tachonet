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
 * Java class for StatusCodeEnumType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="StatusCodeEnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="InvalidFormat"/>
 *     &lt;enumeration value="Timeout"/>
 *     &lt;enumeration value="ServerError"/>
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="NotAvailable"/>
 *     &lt;enumeration value="AccessDenied"/>
 *     &lt;enumeration value="TooManyRequests"/>
 *     &lt;enumeration value="NotYetConnected"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusCodeEnumType")
@XmlEnum
public enum StatusCodeEnumType {

    @XmlEnumValue("InvalidFormat")
    INVALID_FORMAT("InvalidFormat"), @XmlEnumValue("Timeout")
    TIMEOUT("Timeout"), @XmlEnumValue("ServerError")
    SERVER_ERROR("ServerError"), OK("OK"), @XmlEnumValue("NotAvailable")
    NOT_AVAILABLE("NotAvailable"), @XmlEnumValue("AccessDenied")
    ACCESS_DENIED("AccessDenied"), @XmlEnumValue("TooManyRequests")
    TOO_MANY_REQUESTS("TooManyRequests"), @XmlEnumValue("NotYetConnected")
    NOT_YET_CONNECTED("NotYetConnected");
    private final String value;

    StatusCodeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusCodeEnumType fromValue(String v) {
        for (StatusCodeEnumType c : StatusCodeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}