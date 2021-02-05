//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.08 at 11:02:37 PM CET 
//


package com.xml.portal.organvlasti.data.dao.obavestenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TVremeMesto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TVremeMesto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Vreme" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="Pocetno_vreme">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="23"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Krajnje_vreme">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="23"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Mesto_uvida" type="{http://obavestenje}TAdresa_uvida"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TVremeMesto", propOrder = {
    "datum",
    "vreme",
    "pocetnoVreme",
    "krajnjeVreme",
    "mestoUvida"
})
public class TVremeMesto {

    @XmlElement(name = "Datum", namespace = "http://obavestenje", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "Vreme", namespace = "http://obavestenje", required = true)
    protected String vreme;
    @XmlElement(name = "Pocetno_vreme", namespace = "http://obavestenje")
    protected int pocetnoVreme;
    @XmlElement(name = "Krajnje_vreme", namespace = "http://obavestenje")
    protected int krajnjeVreme;
    @XmlElement(name = "Mesto_uvida", namespace = "http://obavestenje", required = true)
    protected TAdresaUvida mestoUvida;

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
     * Gets the value of the vreme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVreme() {
        return vreme;
    }

    /**
     * Sets the value of the vreme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVreme(String value) {
        this.vreme = value;
    }

    /**
     * Gets the value of the pocetnoVreme property.
     * 
     */
    public int getPocetnoVreme() {
        return pocetnoVreme;
    }

    /**
     * Sets the value of the pocetnoVreme property.
     * 
     */
    public void setPocetnoVreme(int value) {
        this.pocetnoVreme = value;
    }

    /**
     * Gets the value of the krajnjeVreme property.
     * 
     */
    public int getKrajnjeVreme() {
        return krajnjeVreme;
    }

    /**
     * Sets the value of the krajnjeVreme property.
     * 
     */
    public void setKrajnjeVreme(int value) {
        this.krajnjeVreme = value;
    }

    /**
     * Gets the value of the mestoUvida property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresaUvida }
     *     
     */
    public TAdresaUvida getMestoUvida() {
        return mestoUvida;
    }

    /**
     * Sets the value of the mestoUvida property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresaUvida }
     *     
     */
    public void setMestoUvida(TAdresaUvida value) {
        this.mestoUvida = value;
    }

}