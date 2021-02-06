//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.06 at 03:36:25 PM CET 
//


package paket;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for TPoverenik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPoverenik">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Naziv">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="datetype" type="{http://www.w3.org/2001/XMLSchema}string" fixed="xs:string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Sediste" type="{http://tipovi}TAdresa"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPoverenik", namespace = "http://tipovi", propOrder = {
    "naziv",
    "sediste"
})
public class TPoverenik {

    @XmlElement(name = "Naziv", required = true, defaultValue = "\u041f\u043e\u0432\u0435\u0440\u0435\u043d\u0438\u043ay \u0437\u0430 \u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u0458\u0435 \u043e\u0434 \u0458\u0430\u0432\u043d\u043e\u0433 \u0437\u043d\u0430\u0447\u0430\u0458\u0430 \u0438 \u0437\u0430\u0448\u0442\u0438\u0442\u0443 \u043f\u043e\u0434\u0430\u0442\u0430\u043a\u0430 \u043e \u043b\u0438\u0447\u043d\u043e\u0441\u0442\u0438")
    protected TPoverenik.Naziv naziv;
    @XmlElement(name = "Sediste", required = true)
    protected TAdresa sediste;

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link TPoverenik.Naziv }
     *     
     */
    public TPoverenik.Naziv getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPoverenik.Naziv }
     *     
     */
    public void setNaziv(TPoverenik.Naziv value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the sediste property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresa }
     *     
     */
    public TAdresa getSediste() {
        return sediste;
    }

    /**
     * Sets the value of the sediste property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresa }
     *     
     */
    public void setSediste(TAdresa value) {
        this.sediste = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="datetype" type="{http://www.w3.org/2001/XMLSchema}string" fixed="xs:string" />
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
    public static class Naziv {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "property")
        protected String property;
        @XmlAttribute(name = "datetype")
        protected String datetype;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datetype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatetype() {
            if (datetype == null) {
                return "xs:string";
            } else {
                return datetype;
            }
        }

        /**
         * Sets the value of the datetype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatetype(String value) {
            this.datetype = value;
        }

    }

}
