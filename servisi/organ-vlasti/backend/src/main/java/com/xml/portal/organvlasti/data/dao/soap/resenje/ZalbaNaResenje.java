
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="rok_za_tuzbu" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" default="30" /&gt;
 *       &lt;attribute name="taksa_tuzbe" type="{http://www.w3.org/2001/XMLSchema}double" default="390.00" /&gt;
 *       &lt;attribute name="zakon" type="{http://www.w3.org/2001/XMLSchema}string" default="\u0417\u0430\u043a\u043e\u043d \u043e \u0443\u043f\u0440\u0430\u0432\u043d\u0438\u043c \u0441\u043f\u043e\u0440\u043e\u0432\u0438\u043c\u0430" /&gt;
 *       &lt;attribute name="sud" type="{http://www.w3.org/2001/XMLSchema}string" default="\u0423\u043f\u0440\u0430\u0432\u043d\u0438 \u0441\u0443\u0434 \u0443 \u0411\u0435\u043e\u0433\u0440\u0430\u0434\u0443" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "Zalba_na_resenje")
public class ZalbaNaResenje {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "rok_za_tuzbu")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger rokZaTuzbu;
    @XmlAttribute(name = "taksa_tuzbe")
    protected Double taksaTuzbe;
    @XmlAttribute(name = "zakon")
    protected String zakon;
    @XmlAttribute(name = "sud")
    protected String sud;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the rokZaTuzbu property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRokZaTuzbu() {
        if (rokZaTuzbu == null) {
            return new BigInteger("30");
        } else {
            return rokZaTuzbu;
        }
    }

    /**
     * Sets the value of the rokZaTuzbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRokZaTuzbu(BigInteger value) {
        this.rokZaTuzbu = value;
    }

    /**
     * Gets the value of the taksaTuzbe property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getTaksaTuzbe() {
        if (taksaTuzbe == null) {
            return  390.0D;
        } else {
            return taksaTuzbe;
        }
    }

    /**
     * Sets the value of the taksaTuzbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTaksaTuzbe(Double value) {
        this.taksaTuzbe = value;
    }

    /**
     * Gets the value of the zakon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZakon() {
        if (zakon == null) {
            return "\u0417\u0430\u043a\u043e\u043d \u043e \u0443\u043f\u0440\u0430\u0432\u043d\u0438\u043c \u0441\u043f\u043e\u0440\u043e\u0432\u0438\u043c\u0430";
        } else {
            return zakon;
        }
    }

    /**
     * Sets the value of the zakon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZakon(String value) {
        this.zakon = value;
    }

    /**
     * Gets the value of the sud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSud() {
        if (sud == null) {
            return "\u0423\u043f\u0440\u0430\u0432\u043d\u0438 \u0441\u0443\u0434 \u0443 \u0411\u0435\u043e\u0433\u0440\u0430\u0434\u0443";
        } else {
            return sud;
        }
    }

    /**
     * Sets the value of the sud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSud(String value) {
        this.sud = value;
    }

}
