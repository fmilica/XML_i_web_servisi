//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.08 at 11:02:37 PM CET 
//


package com.xml.portal.poverenik.data.dao.obavestenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.xml.portal.poverenik.data.dao.GenericXML;
import com.xml.portal.poverenik.data.dao.PrintUtil;
import com.xml.portal.poverenik.data.dao.tipovi.TLice;
import com.xml.portal.poverenik.data.dao.tipovi.TOrganVlasti;



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
 *         &lt;element name="Organ_vlasti" type="{http://obavestenje}TOrgan_vlasti"/>
 *         &lt;element name="Broj_predmeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Podnosilac" type="{http://obavestenje}TLice"/>
 *         &lt;element ref="{http://obavestenje}Uvid_u_dokument"/>
 *         &lt;element name="Ukupan_trosak_kopija">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>double">
 *                 &lt;attribute name="valuta" fixed="RSD">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;length value="3"/>
 *                       &lt;pattern value="[A-Z]{3}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="ziro_racun" type="{http://www.w3.org/2001/XMLSchema}string" fixed="840-742328-843-30" />
 *                 &lt;attribute name="poziv_na_broj" default="97">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;length value="2"/>
 *                       &lt;pattern value="[0-9]{2}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="dostavljeno" use="required" type="{http://obavestenje}TDostavljeno" />
 *       &lt;attribute name="datum" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "organVlasti",
    "brojPredmeta",
    "podnosilac",
    "uvidUDokument",
    "ukupanTrosakKopija"
})
@XmlRootElement(name = "Obavestenje", namespace = "http://obavestenje")
public class Obavestenje implements GenericXML {

    @XmlElement(name = "Organ_vlasti", namespace = "http://obavestenje", required = true)
    protected TOrganVlasti organVlasti;
    @XmlElement(name = "Broj_predmeta", namespace = "http://obavestenje", required = true)
    protected String brojPredmeta;
    @XmlElement(name = "Podnosilac", namespace = "http://obavestenje", required = true)
    protected TLice podnosilac;
    @XmlElement(name = "Uvid_u_dokument", namespace = "http://obavestenje", required = true)
    protected UvidUDokument uvidUDokument;
    @XmlElement(name = "Ukupan_trosak_kopija", namespace = "http://obavestenje", required = true)
    protected Obavestenje.UkupanTrosakKopija ukupanTrosakKopija;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "dostavljeno", required = true)
    protected TDostavljeno dostavljeno;
    @XmlAttribute(name = "datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAttribute(name = "vocab")
    protected String vocab;
    @XmlAttribute(name = "about")
    protected String about;
    @XmlAttribute(name = "rel")
    protected String rel;
    @XmlAttribute(name = "href")
    protected String href;

    /**
     * Gets the value of the organVlasti property.
     * 
     * @return
     *     possible object is
     *     {@link TOrganVlasti }
     *     
     */
    public TOrganVlasti getOrganVlasti() {
        return organVlasti;
    }

    /**
     * Sets the value of the organVlasti property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOrganVlasti }
     *     
     */
    public void setOrganVlasti(TOrganVlasti value) {
        this.organVlasti = value;
    }

    /**
     * Gets the value of the brojPredmeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojPredmeta() {
        return brojPredmeta;
    }

    /**
     * Sets the value of the brojPredmeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojPredmeta(String value) {
        this.brojPredmeta = value;
    }

    /**
     * Gets the value of the podnosilac property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPodnosilac(TLice value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the uvidUDokument property.
     * 
     * @return
     *     possible object is
     *     {@link UvidUDokument }
     *     
     */
    public UvidUDokument getUvidUDokument() {
        return uvidUDokument;
    }

    /**
     * Sets the value of the uvidUDokument property.
     * 
     * @param value
     *     allowed object is
     *     {@link UvidUDokument }
     *     
     */
    public void setUvidUDokument(UvidUDokument value) {
        this.uvidUDokument = value;
    }

    /**
     * Gets the value of the ukupanTrosakKopija property.
     * 
     * @return
     *     possible object is
     *     {@link Obavestenje.UkupanTrosakKopija }
     *     
     */
    public Obavestenje.UkupanTrosakKopija getUkupanTrosakKopija() {
        return ukupanTrosakKopija;
    }

    /**
     * Sets the value of the ukupanTrosakKopija property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obavestenje.UkupanTrosakKopija }
     *     
     */
    public void setUkupanTrosakKopija(Obavestenje.UkupanTrosakKopija value) {
        this.ukupanTrosakKopija = value;
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
     * Gets the value of the dostavljeno property.
     * 
     * @return
     *     possible object is
     *     {@link TDostavljeno }
     *     
     */
    public TDostavljeno getDostavljeno() {
        return dostavljeno;
    }

    /**
     * Sets the value of the dostavljeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDostavljeno }
     *     
     */
    public void setDostavljeno(TDostavljeno value) {
        this.dostavljeno = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>double">
     *       &lt;attribute name="valuta" fixed="RSD">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;length value="3"/>
     *             &lt;pattern value="[A-Z]{3}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="ziro_racun" type="{http://www.w3.org/2001/XMLSchema}string" fixed="840-742328-843-30" />
     *       &lt;attribute name="poziv_na_broj" default="97">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;length value="2"/>
     *             &lt;pattern value="[0-9]{2}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class UkupanTrosakKopija {

        @XmlValue
        protected double value;
        @XmlAttribute(name = "valuta")
        protected String valuta;
        @XmlAttribute(name = "ziro_racun")
        protected String ziroRacun;
        @XmlAttribute(name = "poziv_na_broj")
        protected String pozivNaBroj;

        /**
         * Gets the value of the value property.
         * 
         */
        public double getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(double value) {
            this.value = value;
        }

        /**
         * Gets the value of the valuta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValuta() {
            if (valuta == null) {
                return "RSD";
            } else {
                return valuta;
            }
        }

        /**
         * Sets the value of the valuta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValuta(String value) {
            this.valuta = value;
        }

        /**
         * Gets the value of the ziroRacun property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZiroRacun() {
            if (ziroRacun == null) {
                return "840-742328-843-30";
            } else {
                return ziroRacun;
            }
        }

        /**
         * Sets the value of the ziroRacun property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZiroRacun(String value) {
            this.ziroRacun = value;
        }

        /**
         * Gets the value of the pozivNaBroj property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPozivNaBroj() {
            if (pozivNaBroj == null) {
                return "97";
            } else {
                return pozivNaBroj;
            }
        }

        /**
         * Sets the value of the pozivNaBroj property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPozivNaBroj(String value) {
            this.pozivNaBroj = value;
        }

    }
    
	@Override
	public String toString() {
		PrintUtil.printObavestenje(this);
		return PrintUtil.getOutString();
	}

}
