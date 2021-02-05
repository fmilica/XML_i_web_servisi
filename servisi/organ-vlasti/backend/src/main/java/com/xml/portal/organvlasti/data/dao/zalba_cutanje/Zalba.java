//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.08 at 11:32:08 PM CET 
//


package com.xml.portal.organvlasti.data.dao.zalba_cutanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="Osnova_zalbe" type="{http://zalbacutanje}TZakonska_osnova"/>
 *         &lt;element name="Naziv_organa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Razlog_zalbe">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Nije postupio"/>
 *               &lt;enumeration value="Nije postupio u celosti"/>
 *               &lt;enumeration value="U zakonskom roku"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Podaci_o_zahtevu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://zalbacutanje}Podnosilac_zalbe"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"osnovaZalbe",
    "nazivOrgana",
    "razlogZalbe",
    "podaciOZahtevu",
    "podnosilacZalbe"
})
@XmlRootElement(name = "Zalba", namespace = "http://zalbacutanje")
public class Zalba {

	@XmlElement(name = "Osnova_zalbe", namespace = "http://zalbacutanje", required = true)
    protected TZakonskaOsnova osnovaZalbe;
    @XmlElement(name = "Naziv_organa", namespace = "http://zalbacutanje", required = true)
    protected String nazivOrgana;
    @XmlElement(name = "Razlog_zalbe", namespace = "http://zalbacutanje", required = true)
    protected String razlogZalbe;
    @XmlElement(name = "Podaci_o_zahtevu", namespace = "http://zalbacutanje", required = true)
    protected String podaciOZahtevu;
    @XmlElement(name = "Podnosilac_zalbe", namespace = "http://zalbacutanje", required = true)
    protected Podnosilac podnosilacZalbe;
    
    /**
     * Gets the value of the osnovaZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link TZakonskaOsnova }
     *     
     */
    public TZakonskaOsnova getOsnovaZalbe() {
        return osnovaZalbe;
    }

    /**
     * Sets the value of the osnovaZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZakonskaOsnova }
     *     
     */
    public void setOsnovaZalbe(TZakonskaOsnova value) {
        this.osnovaZalbe = value;
    }

    /**
     * Gets the value of the nazivOrgana property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivOrgana() {
        return nazivOrgana;
    }

    /**
     * Sets the value of the nazivOrgana property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivOrgana(String value) {
        this.nazivOrgana = value;
    }

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
     * Gets the value of the podaciOZahtevu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodaciOZahtevu() {
        return podaciOZahtevu;
    }

    /**
     * Sets the value of the podaciOZahtevu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodaciOZahtevu(String value) {
        this.podaciOZahtevu = value;
    }

    /**
     * Gets the value of the podnosilacZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link Podnosilac }
     *     
     */
    public Podnosilac getPodnosilacZalbe() {
        return podnosilacZalbe;
    }

    /**
     * Sets the value of the podnosilacZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Podnosilac }
     *     
     */
    public void setPodnosilacZalbe(Podnosilac value) {
        this.podnosilacZalbe = value;
    }

}