
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Razlog_zalbe.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Razlog_zalbe"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435"/&gt;
 *     &lt;enumeration value="\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435 \u0443 \u0446\u0435\u043b\u043e\u0441\u0442\u0438"/&gt;
 *     &lt;enumeration value="\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435 \u0443 \u0437\u0430\u043a\u043e\u043d\u0441\u043a\u043e\u043c \u0440\u043e\u043a\u0443"/&gt;
 *     &lt;enumeration value="\u043e\u0434\u0431\u0438\u0458\u0430\u045a\u0435/\u043e\u0434\u0431\u0430\u0446\u0438\u0432\u0430\u045a\u0435"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Razlog_zalbe")
@XmlEnum
public enum RazlogZalbe {

    @XmlEnumValue("\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435")
    \u041d\u0415\u041f\u041e\u0421\u0422\u0423\u041f\u0410\u040a\u0415("\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435"),
    @XmlEnumValue("\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435 \u0443 \u0446\u0435\u043b\u043e\u0441\u0442\u0438")
    \u041d\u0415\u041f\u041e\u0421\u0422\u0423\u041f\u0410\u040a\u0415_\u0423_\u0426\u0415\u041b\u041e\u0421\u0422\u0418("\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435 \u0443 \u0446\u0435\u043b\u043e\u0441\u0442\u0438"),
    @XmlEnumValue("\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435 \u0443 \u0437\u0430\u043a\u043e\u043d\u0441\u043a\u043e\u043c \u0440\u043e\u043a\u0443")
    \u041d\u0415\u041f\u041e\u0421\u0422\u0423\u041f\u0410\u040a\u0415_\u0423_\u0417\u0410\u041a\u041e\u041d\u0421\u041a\u041e\u041c_\u0420\u041e\u041a\u0423("\u043d\u0435\u043f\u043e\u0441\u0442\u0443\u043f\u0430\u045a\u0435 \u0443 \u0437\u0430\u043a\u043e\u043d\u0441\u043a\u043e\u043c \u0440\u043e\u043a\u0443"),
    @XmlEnumValue("\u043e\u0434\u0431\u0438\u0458\u0430\u045a\u0435/\u043e\u0434\u0431\u0430\u0446\u0438\u0432\u0430\u045a\u0435")
    \u041e\u0414\u0411\u0418\u0408\u0410\u040a\u0415_\u041e\u0414\u0411\u0410\u0426\u0418\u0412\u0410\u040a\u0415("\u043e\u0434\u0431\u0438\u0458\u0430\u045a\u0435/\u043e\u0434\u0431\u0430\u0446\u0438\u0432\u0430\u045a\u0435");
    private final String value;

    RazlogZalbe(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RazlogZalbe fromValue(String v) {
        for (RazlogZalbe c: RazlogZalbe.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}