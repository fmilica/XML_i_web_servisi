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
 * <p>Java class for TPrimalac complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPrimalac">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="naziv_primaoca" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adresa_primaoca" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrimalac", propOrder = {
    "nazivPrimaoca",
    "adresaPrimaoca"
})
public class TPrimalac {

    @XmlElement(name = "naziv_primaoca", required = true)
    protected String nazivPrimaoca;
    @XmlElement(name = "adresa_primaoca", required = true)
    protected String adresaPrimaoca;

    /**
     * Gets the value of the nazivPrimaoca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivPrimaoca() {
        return nazivPrimaoca;
    }

    /**
     * Sets the value of the nazivPrimaoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivPrimaoca(String value) {
        this.nazivPrimaoca = value;
    }

    /**
     * Gets the value of the adresaPrimaoca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresaPrimaoca() {
        return adresaPrimaoca;
    }

    /**
     * Sets the value of the adresaPrimaoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresaPrimaoca(String value) {
        this.adresaPrimaoca = value;
    }

}
