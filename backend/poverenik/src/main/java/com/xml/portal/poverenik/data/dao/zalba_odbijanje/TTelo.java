//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.08 at 11:32:30 PM CET 
//


package com.xml.portal.poverenik.data.dao.zalba_odbijanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTelo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TTelo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="razlog_zalbe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zakon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTelo", propOrder = {
    "razlogZalbe",
    "clan",
    "zakon"
})
public class TTelo {

    @XmlElement(name = "razlog_zalbe", required = true)
    protected String razlogZalbe;
    @XmlElement(required = true)
    protected String clan;
    @XmlElement(required = true)
    protected String zakon;

    /**
     * Gets the value of the razlogZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazlogZalbe() {
        return razlogZalbe;
    }

    /**
     * Sets the value of the razlogZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazlogZalbe(String value) {
        this.razlogZalbe = value;
    }

    /**
     * Gets the value of the clan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClan() {
        return clan;
    }

    /**
     * Sets the value of the clan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClan(String value) {
        this.clan = value;
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
