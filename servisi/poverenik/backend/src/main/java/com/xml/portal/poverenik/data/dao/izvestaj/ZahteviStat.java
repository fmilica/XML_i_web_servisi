//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.02 at 11:48:47 PM CET 
//


package com.xml.portal.poverenik.data.dao.izvestaj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for Zahtevi_stat complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Zahtevi_stat"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Ukupno_zahteva" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Ukupno_prihvacenih_zahteva" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Ukupno_odbijenih_zahtevi" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Ukupno_nerazresenih_zahteva" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zahtevi_stat", propOrder = {
    "ukupnoZahteva",
    "ukupnoPrihvacenihZahteva",
    "ukupnoOdbijenihZahtevi",
    "ukupnoNerazresenihZahteva"
})
public class ZahteviStat {

    @XmlElement(name = "Ukupno_zahteva")
    protected int ukupnoZahteva;
    @XmlElement(name = "Ukupno_prihvacenih_zahteva")
    protected int ukupnoPrihvacenihZahteva;
    @XmlElement(name = "Ukupno_odbijenih_zahtevi")
    protected int ukupnoOdbijenihZahtevi;
    @XmlElement(name = "Ukupno_nerazresenih_zahteva")
    protected int ukupnoNerazresenihZahteva;

    /**
     * Gets the value of the ukupnoZahteva property.
     * 
     */
    public int getUkupnoZahteva() {
        return ukupnoZahteva;
    }

    /**
     * Sets the value of the ukupnoZahteva property.
     * 
     */
    public void setUkupnoZahteva(int value) {
        this.ukupnoZahteva = value;
    }

    /**
     * Gets the value of the ukupnoPrihvacenihZahteva property.
     * 
     */
    public int getUkupnoPrihvacenihZahteva() {
        return ukupnoPrihvacenihZahteva;
    }

    /**
     * Sets the value of the ukupnoPrihvacenihZahteva property.
     * 
     */
    public void setUkupnoPrihvacenihZahteva(int value) {
        this.ukupnoPrihvacenihZahteva = value;
    }

    /**
     * Gets the value of the ukupnoOdbijenihZahtevi property.
     * 
     */
    public int getUkupnoOdbijenihZahtevi() {
        return ukupnoOdbijenihZahtevi;
    }

    /**
     * Sets the value of the ukupnoOdbijenihZahtevi property.
     * 
     */
    public void setUkupnoOdbijenihZahtevi(int value) {
        this.ukupnoOdbijenihZahtevi = value;
    }

    /**
     * Gets the value of the ukupnoNerazresenihZahteva property.
     * 
     */
    public int getUkupnoNerazresenihZahteva() {
        return ukupnoNerazresenihZahteva;
    }

    /**
     * Sets the value of the ukupnoNerazresenihZahteva property.
     * 
     */
    public void setUkupnoNerazresenihZahteva(int value) {
        this.ukupnoNerazresenihZahteva = value;
    }

	@Override
	public String toString() {
		return "ZahteviStat [ukupnoZahteva=" + ukupnoZahteva + ", ukupnoPrihvacenihZahteva=" + ukupnoPrihvacenihZahteva
				+ ", ukupnoOdbijenihZahtevi=" + ukupnoOdbijenihZahtevi + ", ukupnoNerazresenihZahteva="
				+ ukupnoNerazresenihZahteva + "]";
	}

}
