
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


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
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;sequence&gt;
 *             &lt;element name="Ime_zalilac"&gt;
 *               &lt;complexType&gt;
 *                 &lt;simpleContent&gt;
 *                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                     &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                   &lt;/extension&gt;
 *                 &lt;/simpleContent&gt;
 *               &lt;/complexType&gt;
 *             &lt;/element&gt;
 *             &lt;element name="Prezime_zalilac"&gt;
 *               &lt;complexType&gt;
 *                 &lt;simpleContent&gt;
 *                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                     &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                   &lt;/extension&gt;
 *                 &lt;/simpleContent&gt;
 *               &lt;/complexType&gt;
 *             &lt;/element&gt;
 *           &lt;/sequence&gt;
 *           &lt;element name="Naziv_zalilac"&gt;
 *             &lt;complexType&gt;
 *               &lt;simpleContent&gt;
 *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                   &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;/extension&gt;
 *               &lt;/simpleContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="Naziv_organa_vlasti" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Mesto_organa_vlasti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Ulica_organa_vlasti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Ulicni_broj_organa_vlasti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Clan" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Stav" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Tacka" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Zakon" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Naziv_sluzbenog_glasnika" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Broj_sluzbenog_glasnika" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="razlog_zalbe" use="required" type="{http://resenje}Razlog_zalbe" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "Opis_zalbe", namespace = "http://resenje")
public class OpisZalbe {

    @XmlElementRefs({
        @XmlElementRef(name = "Clan", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Mesto_organa_vlasti", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Tacka", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Ulica_organa_vlasti", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Naziv_sluzbenog_glasnika", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Naziv_organa_vlasti", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Ulicni_broj_organa_vlasti", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Naziv_zalilac", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Zakon", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Ime_zalilac", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Broj_sluzbenog_glasnika", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Stav", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Prezime_zalilac", namespace = "http://resenje", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "razlog_zalbe", required = true)
    protected RazlogZalbe razlogZalbe;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link OpisZalbe.NazivOrganaVlasti }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link OpisZalbe.NazivZalilac }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link OpisZalbe.ImeZalilac }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link OpisZalbe.PrezimeZalilac }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

    /**
     * Gets the value of the razlogZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link RazlogZalbe }
     *     
     */
    public RazlogZalbe getRazlogZalbe() {
        return razlogZalbe;
    }

    /**
     * Sets the value of the razlogZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link RazlogZalbe }
     *     
     */
    public void setRazlogZalbe(RazlogZalbe value) {
        this.razlogZalbe = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ImeZalilac {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;

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

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class NazivOrganaVlasti {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "property")
        protected String property;

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

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class NazivZalilac {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "property")
        protected String property;

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

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class PrezimeZalilac {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;

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

    }

}
