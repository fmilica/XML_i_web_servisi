//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.02 at 11:48:47 PM CET 
//


package com.xml.portal.organvlasti.data.dao.izvestaj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for Zalbe_stat complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Zalbe_stat"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Ukupno_zalbi" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Ukupno_zbog_nepostupanja" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="Ukupno_zbog_odbijanja" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zalbe_stat", propOrder = {
    "ukupnoZalbi",
    "ukupnoZbogNepostupanja",
    "ukupnoZbogOdbijanja"
})
public class ZalbeStat {

    @XmlElement(name = "Ukupno_zalbi")
    protected int ukupnoZalbi;
    @XmlElement(name = "Ukupno_zbog_nepostupanja")
    protected int ukupnoZbogNepostupanja;
    @XmlElement(name = "Ukupno_zbog_odbijanja")
    protected int ukupnoZbogOdbijanja;

    /**
     * Gets the value of the ukupnoZalbi property.
     * 
     */
    public int getUkupnoZalbi() {
        return ukupnoZalbi;
    }

    /**
     * Sets the value of the ukupnoZalbi property.
     * 
     */
    public void setUkupnoZalbi(int value) {
        this.ukupnoZalbi = value;
    }

    /**
     * Gets the value of the ukupnoZbogNepostupanja property.
     * 
     */
    public int getUkupnoZbogNepostupanja() {
        return ukupnoZbogNepostupanja;
    }

    /**
     * Sets the value of the ukupnoZbogNepostupanja property.
     * 
     */
    public void setUkupnoZbogNepostupanja(int value) {
        this.ukupnoZbogNepostupanja = value;
    }

    /**
     * Gets the value of the ukupnoZbogOdbijanja property.
     * 
     */
    public int getUkupnoZbogOdbijanja() {
        return ukupnoZbogOdbijanja;
    }

    /**
     * Sets the value of the ukupnoZbogOdbijanja property.
     * 
     */
    public void setUkupnoZbogOdbijanja(int value) {
        this.ukupnoZbogOdbijanja = value;
    }

	@Override
	public String toString() {
		return "ZalbeStat [ukupnoZalbi=" + ukupnoZalbi + ", ukupnoZbogNepostupanja=" + ukupnoZbogNepostupanja
				+ ", ukupnoZbogOdbijanja=" + ukupnoZbogOdbijanja + "]";
	}

}
