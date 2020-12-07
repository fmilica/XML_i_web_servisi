//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 09:57:22 PM CET 
//


package zalba_odbijen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPodnosilac complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPodnosilac">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="ime_i_prezime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="naziv_podnosioca" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element name="adresa_podnosioca" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="drugi_podaci_za_kontakt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPodnosilac", propOrder = {
    "imeIPrezime",
    "nazivPodnosioca",
    "adresaPodnosioca",
    "drugiPodaciZaKontakt"
})
public class TPodnosilac {

    @XmlElement(name = "ime_i_prezime")
    protected String imeIPrezime;
    @XmlElement(name = "naziv_podnosioca")
    protected String nazivPodnosioca;
    @XmlElement(name = "adresa_podnosioca", required = true)
    protected String adresaPodnosioca;
    @XmlElement(name = "drugi_podaci_za_kontakt", required = true)
    protected String drugiPodaciZaKontakt;

    /**
     * Gets the value of the imeIPrezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImeIPrezime() {
        return imeIPrezime;
    }

    /**
     * Sets the value of the imeIPrezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImeIPrezime(String value) {
        this.imeIPrezime = value;
    }

    /**
     * Gets the value of the nazivPodnosioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivPodnosioca() {
        return nazivPodnosioca;
    }

    /**
     * Sets the value of the nazivPodnosioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivPodnosioca(String value) {
        this.nazivPodnosioca = value;
    }

    /**
     * Gets the value of the adresaPodnosioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresaPodnosioca() {
        return adresaPodnosioca;
    }

    /**
     * Sets the value of the adresaPodnosioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresaPodnosioca(String value) {
        this.adresaPodnosioca = value;
    }

    /**
     * Gets the value of the drugiPodaciZaKontakt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugiPodaciZaKontakt() {
        return drugiPodaciZaKontakt;
    }

    /**
     * Sets the value of the drugiPodaciZaKontakt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugiPodaciZaKontakt(String value) {
        this.drugiPodaciZaKontakt = value;
    }

}
