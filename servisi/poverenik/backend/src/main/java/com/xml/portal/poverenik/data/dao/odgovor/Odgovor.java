//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.05 at 11:30:19 AM CET 
//


package com.xml.portal.poverenik.data.dao.odgovor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="Izjasnjenje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id_zalbe" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "izjasnjenje"
})
@XmlRootElement(name = "Odgovor")
public class Odgovor {

    @XmlElement(name = "Izjasnjenje", required = true)
    protected String izjasnjenje;
    @XmlAttribute(name = "id_zalbe")
    protected String idZalbe;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the izjasnjenje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIzjasnjenje() {
        return izjasnjenje;
    }

    /**
     * Sets the value of the izjasnjenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIzjasnjenje(String value) {
        this.izjasnjenje = value;
    }

    /**
     * Gets the value of the idZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdZalbe() {
        return idZalbe;
    }

    /**
     * Sets the value of the idZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdZalbe(String value) {
        this.idZalbe = value;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String value) {
    	this.id = value;
    }

}
