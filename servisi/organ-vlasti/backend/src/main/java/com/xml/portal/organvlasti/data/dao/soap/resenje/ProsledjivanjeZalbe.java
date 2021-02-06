
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
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element name="Clan" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;element name="Stav" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;element name="Tacka" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;element name="Zakon" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="datum_prosledjivanja" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
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
@XmlRootElement(name = "Prosledjivanje_zalbe", namespace = "http://resenje")
public class ProsledjivanjeZalbe {

    @XmlElementRefs({
        @XmlElementRef(name = "Stav", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Clan", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Zakon", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Tacka", namespace = "http://resenje", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "datum_prosledjivanja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumProsledjivanja;

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
     * {@link String }
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
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
     * Gets the value of the datumProsledjivanja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumProsledjivanja() {
        return datumProsledjivanja;
    }

    /**
     * Sets the value of the datumProsledjivanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumProsledjivanja(XMLGregorianCalendar value) {
        this.datumProsledjivanja = value;
    }

}
