
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element name="Detaljan_opis_podnetog_zahteva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Detaljan_opis_odgovora_na_zahtev" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Detaljan_opis_odluke" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Clan" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Stav" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Tacka" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Zakon" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "content"
})
@XmlRootElement(name = "Razlozi_odluke")
public class RazloziOdluke {

    @XmlElementRefs({
        @XmlElementRef(name = "Clan", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Zakon", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Stav", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Detaljan_opis_odgovora_na_zahtev", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Detaljan_opis_podnetog_zahteva", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Tacka", namespace = "http://resenje", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Detaljan_opis_odluke", namespace = "http://resenje", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;

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
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
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

}
