//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.01 at 06:37:50 PM CET 
//


package com.xml.portal.eposta.data.dao.pismo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primalac" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="naslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prilog" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *       &lt;attribute name="tipPriloga" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "primalac",
    "naslov",
    "sadrzaj",
    "prilog"
})
@XmlRootElement(name = "pismo")
public class Pismo {

    @XmlElement(required = true)
    protected String primalac;
    @XmlElement(required = true)
    protected String naslov;
    @XmlElement(required = true)
    protected String sadrzaj;
    @XmlElement(required = true)
    protected byte[] prilog;
    @XmlAttribute(name = "tipPriloga")
    @XmlSchemaType(name = "anySimpleType")
    protected String tipPriloga;

    /**
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimalac(String value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSadrzaj(String value) {
        this.sadrzaj = value;
    }

    /**
     * Gets the value of the prilog property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPrilog() {
        return prilog;
    }

    /**
     * Sets the value of the prilog property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPrilog(byte[] value) {
        this.prilog = value;
    }

    /**
     * Gets the value of the tipPriloga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPriloga() {
        return tipPriloga;
    }

    /**
     * Sets the value of the tipPriloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPriloga(String value) {
        this.tipPriloga = value;
    }

}