//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.08 at 11:32:08 PM CET 
//


package com.xml.portal.poverenik.data.dao.zalba_cutanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.xml.portal.poverenik.data.dao.GenericXML;
import com.xml.portal.poverenik.data.dao.PrintUtil;
import com.xml.portal.poverenik.data.dao.tipovi.TPoverenik;


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
 *         &lt;element ref="{http://zalbacutanje}Primalac_zalbe"/>
 *         &lt;element ref="{http://zalbacutanje}Zalba"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="mesto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "primalacZalbe",
    "zalba"
})
@XmlRootElement(name = "Zalba_cutanje", namespace = "http://zalbacutanje")
public class ZalbaCutanje implements GenericXML {

    @XmlElement(name = "Primalac_zalbe", namespace = "http://zalbacutanje", required = true)
    protected TPoverenik primalacZalbe;
    @XmlElement(name = "Zalba", namespace = "http://zalbacutanje", required = true)
    protected Zalba zalba;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "datum_podnosenja_zahteva")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenjaZahteva;
    @XmlAttribute(name = "datum_podnosenja_zalbe")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenjaZalbe;
    @XmlAttribute(name = "mesto")
    protected String mesto;
    @XmlAttribute(name = "razresen")
    protected boolean razresen;
    @XmlAttribute(name = "vocab")
    protected String vocab;
    @XmlAttribute(name = "about")
    protected String about;
    @XmlAttribute(name = "rel")
    protected String rel;
    @XmlAttribute(name = "href")
    protected String href;

    /**
     * Gets the value of the primalacZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link TPoverenik }
     *     
     */
    public TPoverenik getPrimalacZalbe() {
        return primalacZalbe;
    }

    /**
     * Sets the value of the primalacZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPoverenik }
     *     
     */
    public void setPrimalacZalbe(TPoverenik value) {
        this.primalacZalbe = value;
    }

    /**
     * Gets the value of the zalba property.
     * 
     * @return
     *     possible object is
     *     {@link Zalba }
     *     
     */
    public Zalba getZalba() {
        return zalba;
    }

    /**
     * Sets the value of the zalba property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zalba }
     *     
     */
    public void setZalba(Zalba value) {
        this.zalba = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the datumPodnosenjaZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumPodnosenjaZahteva() {
        return datumPodnosenjaZahteva;
    }

    /**
     * Sets the value of the datumPodnosenjaZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumPodnosenjaZahteva(XMLGregorianCalendar value) {
        this.datumPodnosenjaZahteva = value;
    }
    
    /**
     * Gets the value of the datumPodnosenjaZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumPodnosenjaZalbe() {
        return datumPodnosenjaZalbe;
    }

    /**
     * Sets the value of the datumPodnosenjaZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumPodnosenjaZalbe(XMLGregorianCalendar value) {
        this.datumPodnosenjaZalbe = value;
    }

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesto(String value) {
        this.mesto = value;
    }
    
    public boolean getRazresen() {
    	return razresen;
    }
    public void setRazresen(boolean value) {
    	this.razresen = value;
    }
    
    public String getVocab() {
    	return vocab;
    }
    public void setVocab(String value) {
    	this.vocab = value;
    }
    
    public String getAbout() {
    	return about;
    }
    public void setAbout(String value) {
    	this.about = value;
    }
    
    public String getRel() {
    	return rel;
    }
    public void setRel(String value) {
    	this.rel = value;
    }
    
    public String getHref() {
    	return href;
    }
    public void setHref(String value) {
    	this.href = value;
    }
    
	@Override
	public String toString() {
		PrintUtil.printZalbaCutanje(this);
		return PrintUtil.getOutString();
	}
}
