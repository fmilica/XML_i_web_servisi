
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tip_odluke.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Tip_odluke"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="\u043e\u0441\u043d\u043e\u0432\u0430\u043d\u0430"/&gt;
 *     &lt;enumeration value="\u043d\u0435\u043e\u0441\u043d\u043e\u0432\u0430\u043d\u0430"/&gt;
 *     &lt;enumeration value="\u043f\u043e\u043d\u0438\u0448\u0442\u0435\u043d\u0430"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Tip_odluke")
@XmlEnum
public enum TipOdluke {

    @XmlEnumValue("\u043e\u0441\u043d\u043e\u0432\u0430\u043d\u0430")
    \u041e\u0421\u041d\u041e\u0412\u0410\u041d\u0410("\u043e\u0441\u043d\u043e\u0432\u0430\u043d\u0430"),
    @XmlEnumValue("\u043d\u0435\u043e\u0441\u043d\u043e\u0432\u0430\u043d\u0430")
    \u041d\u0415\u041e\u0421\u041d\u041e\u0412\u0410\u041d\u0410("\u043d\u0435\u043e\u0441\u043d\u043e\u0432\u0430\u043d\u0430"),
    @XmlEnumValue("\u043f\u043e\u043d\u0438\u0448\u0442\u0435\u043d\u0430")
    \u041f\u041e\u041d\u0418\u0428\u0422\u0415\u041d\u0410("\u043f\u043e\u043d\u0438\u0448\u0442\u0435\u043d\u0430");
    private final String value;

    TipOdluke(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipOdluke fromValue(String v) {
        for (TipOdluke c: TipOdluke.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
