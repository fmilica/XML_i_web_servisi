
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://resenje}Opis_zalbe"/&gt;
 *         &lt;element ref="{http://resenje}Odluka"/&gt;
 *         &lt;element ref="{http://resenje}Obrazlozenje"/&gt;
 *         &lt;element name="Poverenik" type="{http://resenje}TOsoba"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="broj_resenja" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="datum_resenja" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *       &lt;attribute name="tip_odluke" use="required" type="{http://resenje}Tip_odluke" /&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="rel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "opisZalbe",
    "odluka",
    "obrazlozenje",
    "poverenik"
})
@XmlRootElement(name = "Resenje", namespace = "http://resenje")
public class Resenje {

    @XmlElement(name = "Opis_zalbe", namespace = "http://resenje", required = true)
    protected OpisZalbe opisZalbe;
    @XmlElement(name = "Odluka", namespace = "http://resenje", required = true)
    protected Odluka odluka;
    @XmlElement(name = "Obrazlozenje", namespace = "http://resenje", required = true)
    protected Obrazlozenje obrazlozenje;
    @XmlElement(name = "Poverenik", namespace = "http://resenje", required = true)
    protected TOsoba poverenik;
    @XmlAttribute(name = "broj_resenja", required = true)
    protected String brojResenja;
    @XmlAttribute(name = "datum_resenja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumResenja;
    @XmlAttribute(name = "tip_odluke", required = true)
    protected TipOdluke tipOdluke;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "vocab")
    protected String vocab;
    @XmlAttribute(name = "about")
    protected String about;
    @XmlAttribute(name = "rel")
    protected String rel;
    @XmlAttribute(name = "href")
    protected String href;

    /**
     * Gets the value of the opisZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link OpisZalbe }
     *     
     */
    public OpisZalbe getOpisZalbe() {
        return opisZalbe;
    }

    /**
     * Sets the value of the opisZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpisZalbe }
     *     
     */
    public void setOpisZalbe(OpisZalbe value) {
        this.opisZalbe = value;
    }

    /**
     * Gets the value of the odluka property.
     * 
     * @return
     *     possible object is
     *     {@link Odluka }
     *     
     */
    public Odluka getOdluka() {
        return odluka;
    }

    /**
     * Sets the value of the odluka property.
     * 
     * @param value
     *     allowed object is
     *     {@link Odluka }
     *     
     */
    public void setOdluka(Odluka value) {
        this.odluka = value;
    }

    /**
     * Gets the value of the obrazlozenje property.
     * 
     * @return
     *     possible object is
     *     {@link Obrazlozenje }
     *     
     */
    public Obrazlozenje getObrazlozenje() {
        return obrazlozenje;
    }

    /**
     * Sets the value of the obrazlozenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obrazlozenje }
     *     
     */
    public void setObrazlozenje(Obrazlozenje value) {
        this.obrazlozenje = value;
    }

    /**
     * Gets the value of the poverenik property.
     * 
     * @return
     *     possible object is
     *     {@link TOsoba }
     *     
     */
    public TOsoba getPoverenik() {
        return poverenik;
    }

    /**
     * Sets the value of the poverenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOsoba }
     *     
     */
    public void setPoverenik(TOsoba value) {
        this.poverenik = value;
    }

    /**
     * Gets the value of the brojResenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojResenja() {
        return brojResenja;
    }

    /**
     * Sets the value of the brojResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojResenja(String value) {
        this.brojResenja = value;
    }

    /**
     * Gets the value of the datumResenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumResenja() {
        return datumResenja;
    }

    /**
     * Sets the value of the datumResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumResenja(XMLGregorianCalendar value) {
        this.datumResenja = value;
    }

    /**
     * Gets the value of the tipOdluke property.
     * 
     * @return
     *     possible object is
     *     {@link TipOdluke }
     *     
     */
    public TipOdluke getTipOdluke() {
        return tipOdluke;
    }

    /**
     * Sets the value of the tipOdluke property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipOdluke }
     *     
     */
    public void setTipOdluke(TipOdluke value) {
        this.tipOdluke = value;
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
     * Gets the value of the vocab property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVocab() {
        return vocab;
    }

    /**
     * Sets the value of the vocab property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVocab(String value) {
        this.vocab = value;
    }

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }

    /**
     * Gets the value of the rel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRel() {
        return rel;
    }

    /**
     * Sets the value of the rel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRel(String value) {
        this.rel = value;
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

}
