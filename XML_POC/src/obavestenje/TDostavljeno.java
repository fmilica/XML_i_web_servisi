//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 09:56:30 PM CET 
//


package obavestenje;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TDostavljeno.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TDostavljeno">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="imenovanom"/>
 *     &lt;enumeration value="arhivi"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TDostavljeno")
@XmlEnum
public enum TDostavljeno {

    @XmlEnumValue("imenovanom")
    IMENOVANOM("imenovanom"),
    @XmlEnumValue("arhivi")
    ARHIVI("arhivi");
    private final String value;

    TDostavljeno(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TDostavljeno fromValue(String v) {
        for (TDostavljeno c: TDostavljeno.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
