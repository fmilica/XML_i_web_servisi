
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Podnosenje_zalbe"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="datum_zalbe" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                 &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="rel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Podnosenje_zahteva"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="datum_zahteva" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                 &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="rel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosenjeZalbe",
    "podnosenjeZahteva"
})
@XmlRootElement(name = "Postupak_zalioca")
public class PostupakZalioca {

    @XmlElement(name = "Podnosenje_zalbe", required = true)
    protected PostupakZalioca.PodnosenjeZalbe podnosenjeZalbe;
    @XmlElement(name = "Podnosenje_zahteva", required = true)
    protected PostupakZalioca.PodnosenjeZahteva podnosenjeZahteva;

    /**
     * Gets the value of the podnosenjeZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link PostupakZalioca.PodnosenjeZalbe }
     *     
     */
    public PostupakZalioca.PodnosenjeZalbe getPodnosenjeZalbe() {
        return podnosenjeZalbe;
    }

    /**
     * Sets the value of the podnosenjeZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostupakZalioca.PodnosenjeZalbe }
     *     
     */
    public void setPodnosenjeZalbe(PostupakZalioca.PodnosenjeZalbe value) {
        this.podnosenjeZalbe = value;
    }

    /**
     * Gets the value of the podnosenjeZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link PostupakZalioca.PodnosenjeZahteva }
     *     
     */
    public PostupakZalioca.PodnosenjeZahteva getPodnosenjeZahteva() {
        return podnosenjeZahteva;
    }

    /**
     * Sets the value of the podnosenjeZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostupakZalioca.PodnosenjeZahteva }
     *     
     */
    public void setPodnosenjeZahteva(PostupakZalioca.PodnosenjeZahteva value) {
        this.podnosenjeZahteva = value;
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
     *       &lt;attribute name="datum_zahteva" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *       &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="rel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
    public static class PodnosenjeZahteva {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "datum_zahteva", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumZahteva;
        @XmlAttribute(name = "vocab")
        protected String vocab;
        @XmlAttribute(name = "about")
        protected String about;
        @XmlAttribute(name = "rel")
        protected String rel;
        @XmlAttribute(name = "href")
        protected String href;

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
         * Gets the value of the datumZahteva property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDatumZahteva() {
            return datumZahteva;
        }

        /**
         * Sets the value of the datumZahteva property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDatumZahteva(XMLGregorianCalendar value) {
            this.datumZahteva = value;
        }

        /**
         * Gets the value of the vocab property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVocab() {
            return vocab;
        }

        /**
         * Sets the value of the vocab property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVocab(String value) {
            this.vocab = value;
        }

        /**
         * Gets the value of the about property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAbout() {
            return about;
        }

        /**
         * Sets the value of the about property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAbout(String value) {
            this.about = value;
        }

        /**
         * Gets the value of the rel property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRel() {
            return rel;
        }

        /**
         * Sets the value of the rel property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRel(String value) {
            this.rel = value;
        }

        /**
         * Gets the value of the href property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHref() {
            return href;
        }

        /**
         * Sets the value of the href property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHref(String value) {
            this.href = value;
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
     *       &lt;attribute name="datum_zalbe" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *       &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="rel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
    public static class PodnosenjeZalbe {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "datum_zalbe", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumZalbe;
        @XmlAttribute(name = "vocab")
        protected String vocab;
        @XmlAttribute(name = "about")
        protected String about;
        @XmlAttribute(name = "rel")
        protected String rel;
        @XmlAttribute(name = "href")
        protected String href;

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
         * Gets the value of the datumZalbe property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDatumZalbe() {
            return datumZalbe;
        }

        /**
         * Sets the value of the datumZalbe property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDatumZalbe(XMLGregorianCalendar value) {
            this.datumZalbe = value;
        }

        /**
         * Gets the value of the vocab property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVocab() {
            return vocab;
        }

        /**
         * Sets the value of the vocab property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVocab(String value) {
            this.vocab = value;
        }

        /**
         * Gets the value of the about property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAbout() {
            return about;
        }

        /**
         * Sets the value of the about property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAbout(String value) {
            this.about = value;
        }

        /**
         * Gets the value of the rel property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRel() {
            return rel;
        }

        /**
         * Sets the value of the rel property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRel(String value) {
            this.rel = value;
        }

        /**
         * Gets the value of the href property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHref() {
            return href;
        }

        /**
         * Sets the value of the href property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHref(String value) {
            this.href = value;
        }

    }

}
