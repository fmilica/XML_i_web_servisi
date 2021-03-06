//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.08 at 11:32:30 PM CET 
//


package com.xml.portal.organvlasti.data.dao.zalba_odbijanje;

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

import com.xml.portal.organvlasti.data.dao.GenericXML;
import com.xml.portal.organvlasti.data.dao.PrintUtil;
import com.xml.portal.organvlasti.data.dao.tipovi.TLice;
import com.xml.portal.organvlasti.data.dao.tipovi.TPoverenik;


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
 *         &lt;element name="Podaci_o_primaocu" type="{http://zalba_odbijen}TPrimalac"/>
 *         &lt;element name="Podaci_o_zaliocu" type="{http://zalba_odbijen}TZalilac"/>
 *         &lt;element name="Podaci_o_odluci">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Naziv_donosioca_odluke" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="broj_odluke" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="godina" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Telo_zalbe" type="{http://zalba_odbijen}TTelo"/>
 *         &lt;element name="Podaci_o_podnosiocu_zalbe" type="{http://zalba_odbijen}TPodnosilac"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="mesto_podnosenja_zalbe" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datum_podnosenja_zalbe" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="datum_podnosenja_zahteva" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podaciOPrimaocu",
    "podaciOZaliocu",
    "podaciOOdluci",
    "teloZalbe",
    "podaciOPodnosiocuZalbe"
})
@XmlRootElement(name = "Zalba_odbijanje", namespace = "http://zalbaodbijanje")
public class ZalbaOdbijanje implements GenericXML {

    @XmlElement(name = "Podaci_o_primaocu", namespace = "http://zalbaodbijanje", required = true)
    protected TPoverenik podaciOPrimaocu;
    @XmlElement(name = "Podaci_o_zaliocu", namespace = "http://zalbaodbijanje", required = true)
    protected TLice podaciOZaliocu;
    @XmlElement(name = "Podaci_o_odluci", namespace = "http://zalbaodbijanje", required = true)
    protected ZalbaOdbijanje.PodaciOOdluci podaciOOdluci;
    @XmlElement(name = "Telo_zalbe", namespace = "http://zalbaodbijanje", required = true)
    protected TTelo teloZalbe;
    @XmlElement(name = "Podaci_o_podnosiocu_zalbe", namespace = "http://zalbaodbijanje", required = true)
    protected Podnosilac podaciOPodnosiocuZalbe;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "mesto_podnosenja_zalbe")
    protected String mestoPodnosenjaZalbe;
    @XmlAttribute(name = "datum_podnosenja_zalbe")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenjaZalbe;
    @XmlAttribute(name = "datum_podnosenja_zahteva")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenjaZahteva;
    @XmlAttribute(name = "razresen")
    protected boolean razresen;
    @XmlAttribute(name = "izjasnjen")
    protected boolean izjasnjen;
    @XmlAttribute(name = "prekinut")
    protected boolean prekinut;
    @XmlAttribute(name = "ceka")
    protected boolean ceka;
    @XmlAttribute(name = "vocab")
    protected String vocab;
    @XmlAttribute(name = "about")
    protected String about;
    @XmlAttribute(name = "rel")
    protected String rel;
    @XmlAttribute(name = "href")
    protected String href;

    /**
     * Gets the value of the podaciOPrimaocu property.
     * 
     * @return
     *     possible object is
     *     {@link TPoverenik }
     *     
     */
    public TPoverenik getPodaciOPrimaocu() {
        return podaciOPrimaocu;
    }

    /**
     * Sets the value of the podaciOPrimaocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPoverenik }
     *     
     */
    public void setPodaciOPrimaocu(TPoverenik value) {
        this.podaciOPrimaocu = value;
    }

    /**
     * Gets the value of the podaciOZaliocu property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPodaciOZaliocu() {
        return podaciOZaliocu;
    }

    /**
     * Sets the value of the podaciOZaliocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPodaciOZaliocu(TLice value) {
        this.podaciOZaliocu = value;
    }

    /**
     * Gets the value of the podaciOOdluci property.
     * 
     * @return
     *     possible object is
     *     {@link ZalbaOdbijanje.PodaciOOdluci }
     *     
     */
    public ZalbaOdbijanje.PodaciOOdluci getPodaciOOdluci() {
        return podaciOOdluci;
    }

    /**
     * Sets the value of the podaciOOdluci property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZalbaOdbijanje.PodaciOOdluci }
     *     
     */
    public void setPodaciOOdluci(ZalbaOdbijanje.PodaciOOdluci value) {
        this.podaciOOdluci = value;
    }

    /**
     * Gets the value of the teloZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link TTelo }
     *     
     */
    public TTelo getTeloZalbe() {
        return teloZalbe;
    }

    /**
     * Sets the value of the teloZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTelo }
     *     
     */
    public void setTeloZalbe(TTelo value) {
        this.teloZalbe = value;
    }

    /**
     * Gets the value of the podaciOPodnosiocuZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link Podnosilac }
     *     
     */
    public Podnosilac getPodaciOPodnosiocuZalbe() {
        return podaciOPodnosiocuZalbe;
    }

    /**
     * Sets the value of the podaciOPodnosiocuZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Podnosilac }
     *     
     */
    public void setPodaciOPodnosiocuZalbe(Podnosilac value) {
        this.podaciOPodnosiocuZalbe = value;
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
     * Gets the value of the mestoPodnosenjaZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMestoPodnosenjaZalbe() {
        return mestoPodnosenjaZalbe;
    }

    /**
     * Sets the value of the mestoPodnosenjaZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMestoPodnosenjaZalbe(String value) {
        this.mestoPodnosenjaZalbe = value;
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
    
    public boolean getRazresen() {
    	return razresen;
    }
    public void setRazresen(boolean value) {
    	this.razresen = value;
    }
    
    public boolean getIzjasnjen() {
    	return izjasnjen;
    }
    public void setIzjasnjen(boolean value) {
    	this.izjasnjen = value;
    }
    
    public boolean getPrekinut() {
    	return prekinut;
    }
    public void setPrekinut(boolean value) {
    	this.prekinut = value;
    }
    
    public boolean getCeka() {
    	return ceka;
    }
    public void setCeka(boolean value) {
    	this.ceka = value;
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
     *         &lt;element name="naziv_donosioca_odluke" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="broj_odluke" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="godina" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nazivDonosiocaOdluke"
    })
    public static class PodaciOOdluci {

        @XmlElement(name = "Naziv_donosioca_odluke", namespace = "http://zalbaodbijanje", required = true)
        protected String nazivDonosiocaOdluke;
        @XmlAttribute(name = "broj_odluke")
        protected Integer brojOdluke;
        @XmlAttribute(name = "godina")
        protected Integer godina;

        /**
         * Gets the value of the nazivDonosiocaOdluke property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivDonosiocaOdluke() {
            return nazivDonosiocaOdluke;
        }

        /**
         * Sets the value of the nazivDonosiocaOdluke property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivDonosiocaOdluke(String value) {
            this.nazivDonosiocaOdluke = value;
        }

        /**
         * Gets the value of the brojOdluke property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getBrojOdluke() {
            return brojOdluke;
        }

        /**
         * Sets the value of the brojOdluke property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setBrojOdluke(Integer value) {
            this.brojOdluke = value;
        }

        /**
         * Gets the value of the godina property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getGodina() {
            return godina;
        }

        /**
         * Sets the value of the godina property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setGodina(Integer value) {
            this.godina = value;
        }

    }


	@Override
	public String toString() {
		PrintUtil.printZalba(this);
		return PrintUtil.getOutString();
	}

}
