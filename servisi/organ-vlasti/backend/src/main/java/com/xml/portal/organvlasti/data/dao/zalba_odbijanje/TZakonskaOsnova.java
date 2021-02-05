//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.11 at 06:19:42 PM CET 
//


package com.xml.portal.organvlasti.data.dao.zalba_odbijanje;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TZakonska_osnova complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TZakonska_osnova">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Clan" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Stav" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Zakon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZakonska_osnova", namespace = "http://zalbaodbijanje", propOrder = {
    "clan",
    "stav",
    "zakon"
})
public class TZakonskaOsnova {

    @XmlElement(name = "Clan", namespace = "http://zalbaodbijanje", required = true, defaultValue="22")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger clan;
    @XmlElement(name = "Stav", namespace = "http://zalbaodbijanje", required = true, defaultValue = "1")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger stav;
    @XmlElement(name = "Zakon", namespace = "http://zalbaodbijanje", required = true, defaultValue = "\u0417\u0430\u043a\u043e\u043d \u043e \u0441\u043b\u043e\u0431\u043e\u0434\u043d\u043e\u043c \u043f\u0440\u0438\u0441\u0442\u0443\u043f\u0443 \u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u0458\u0430\u043c\u0430 \u043e\u0434 \u0458\u0430\u0432\u043d\u043e\u0433 \u0437\u043d\u0430\u0447\u0430\u0458\u0430")
    protected String zakon;

    /**
     * Gets the value of the clan property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getClan() {
        return clan;
    }

    /**
     * Sets the value of the clan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setClan(BigInteger value) {
        this.clan = value;
    }

    /**
     * Gets the value of the stav property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStav() {
        return stav;
    }

    /**
     * Sets the value of the stav property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStav(BigInteger value) {
        this.stav = value;
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
        return zakon;
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

}
