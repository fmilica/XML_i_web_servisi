//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.04 at 07:56:03 PM CET 
//


package zahtev;

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
 *         &lt;element name="Obavestenje" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="Uvid" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="Kopija" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="Dostavljanje_kopije" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://zahtev}Nacin_dostave"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "obavestenje",
    "uvid",
    "kopija",
    "dostavljanjeKopije"
})
@XmlRootElement(name = "Zahtevi")
public class Zahtevi {

    @XmlElement(name = "Obavestenje")
    protected Object obavestenje;
    @XmlElement(name = "Uvid")
    protected Object uvid;
    @XmlElement(name = "Kopija")
    protected Object kopija;
    @XmlElement(name = "Dostavljanje_kopije")
    protected Zahtevi.DostavljanjeKopije dostavljanjeKopije;

    /**
     * Gets the value of the obavestenje property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getObavestenje() {
        return obavestenje;
    }

    /**
     * Sets the value of the obavestenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setObavestenje(Object value) {
        this.obavestenje = value;
    }

    /**
     * Gets the value of the uvid property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getUvid() {
        return uvid;
    }

    /**
     * Sets the value of the uvid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setUvid(Object value) {
        this.uvid = value;
    }

    /**
     * Gets the value of the kopija property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getKopija() {
        return kopija;
    }

    /**
     * Sets the value of the kopija property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setKopija(Object value) {
        this.kopija = value;
    }

    /**
     * Gets the value of the dostavljanjeKopije property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtevi.DostavljanjeKopije }
     *     
     */
    public Zahtevi.DostavljanjeKopije getDostavljanjeKopije() {
        return dostavljanjeKopije;
    }

    /**
     * Sets the value of the dostavljanjeKopije property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtevi.DostavljanjeKopije }
     *     
     */
    public void setDostavljanjeKopije(Zahtevi.DostavljanjeKopije value) {
        this.dostavljanjeKopije = value;
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
     *         &lt;element ref="{http://zahtev}Nacin_dostave"/>
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
        "nacinDostave"
    })
    public static class DostavljanjeKopije {

        @XmlElement(name = "Nacin_dostave", required = true)
        protected NacinDostave nacinDostave;

        /**
         * Gets the value of the nacinDostave property.
         * 
         * @return
         *     possible object is
         *     {@link NacinDostave }
         *     
         */
        public NacinDostave getNacinDostave() {
            return nacinDostave;
        }

        /**
         * Sets the value of the nacinDostave property.
         * 
         * @param value
         *     allowed object is
         *     {@link NacinDostave }
         *     
         */
        public void setNacinDostave(NacinDostave value) {
            this.nacinDostave = value;
        }

    }

}
