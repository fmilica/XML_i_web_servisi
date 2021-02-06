
package com.xml.portal.organvlasti.data.dao.soap.resenje;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element ref="{http://resenje}Postupak_zalioca" minOccurs="0"/&gt;
 *         &lt;element ref="{http://resenje}Prosledjivanje_zalbe" minOccurs="0"/&gt;
 *         &lt;element ref="{http://resenje}Izjasnjenje_o_zalbi" minOccurs="0"/&gt;
 *         &lt;element ref="{http://resenje}Razlozi_odluke" minOccurs="0"/&gt;
 *         &lt;element ref="{http://resenje}Zalba_na_resenje" minOccurs="0"/&gt;
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
    "postupakZalioca",
    "prosledjivanjeZalbe",
    "izjasnjenjeOZalbi",
    "razloziOdluke",
    "zalbaNaResenje"
})
@XmlRootElement(name = "Obrazlozenje", namespace = "http://resenje")
public class Obrazlozenje {

    @XmlElement(name = "Postupak_zalioca", namespace = "http://resenje")
    protected PostupakZalioca postupakZalioca;
    @XmlElement(name = "Prosledjivanje_zalbe", namespace = "http://resenje")
    protected ProsledjivanjeZalbe prosledjivanjeZalbe;
    @XmlElement(name = "Izjasnjenje_o_zalbi", namespace = "http://resenje")
    protected IzjasnjenjeOZalbi izjasnjenjeOZalbi;
    @XmlElement(name = "Razlozi_odluke", namespace = "http://resenje")
    protected RazloziOdluke razloziOdluke;
    @XmlElement(name = "Zalba_na_resenje", namespace = "http://resenje", defaultValue = "\u041f\u0440\u043e\u0442\u0438\u0432 \u043e\u0432\u043e\u0433 \u0440\u0435\u0448\u0435\u045a\u0430 \u043d\u0438\u0458\u0435 \u0434\u043e\u043f\u0443\u0448\u0442\u0435\u043d\u0430 \u0436\u0430\u043b\u0431\u0430 \u0432\u0435\u045b \u0441\u0435, \u0443 \u0441\u043a\u043b\u0430\u0434\u0443 \u0441\u0430 \u0417\u0430\u043a\u043e\u043d\u043e\u043c \u043e \u0443\u043f\u0440\u0430\u0432\u043d\u0438\u043c \u0441\u043f\u043e\u0440\u043e\u0432\u0438\u043c\u0430, \u043c\u043e\u0436\u0435 \u043f\u043e\u043a\u0440\u0435\u043d\u0443\u0442\u0438 \u0443\u043f\u0440\u0430\u0432\u043d\u0438 \u0441\u043f\u043e\u0440 \u0442\u0443\u0436\u0431\u043e\u043c \u0423\u043f\u0440\u0430\u0432\u043d\u043e\u043c \u0441\u0443\u0434\u0443 \u0443 \u0411\u0435\u043e\u0433\u0440\u0430\u0434\u0443, \u0443 \u0440\u043e\u043a\u0443 \u043e\u0434 30 \u0434\u0430\u043d\u0430 \u043e\u0434 \u0434\u0430\u043d\u0430 \u043f\u0440\u0438\u0458\u0435\u043c\u0430 \u0440\u0435\u0448\u0435\u045a\u0430. \u0422\u0430\u043a\u0441\u0430 \u043d\u0430 \u0442\u0443\u0436\u0431\u0443 \u0438\u0437\u043d\u043e\u0441\u0438 390,00 \u0434\u0438\u043d\u0430\u0440\u0430.")
    protected ZalbaNaResenje zalbaNaResenje;

    /**
     * Gets the value of the postupakZalioca property.
     * 
     * @return
     *     possible object is
     *     {@link PostupakZalioca }
     *     
     */
    public PostupakZalioca getPostupakZalioca() {
        return postupakZalioca;
    }

    /**
     * Sets the value of the postupakZalioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostupakZalioca }
     *     
     */
    public void setPostupakZalioca(PostupakZalioca value) {
        this.postupakZalioca = value;
    }

    /**
     * Gets the value of the prosledjivanjeZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link ProsledjivanjeZalbe }
     *     
     */
    public ProsledjivanjeZalbe getProsledjivanjeZalbe() {
        return prosledjivanjeZalbe;
    }

    /**
     * Sets the value of the prosledjivanjeZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProsledjivanjeZalbe }
     *     
     */
    public void setProsledjivanjeZalbe(ProsledjivanjeZalbe value) {
        this.prosledjivanjeZalbe = value;
    }

    /**
     * Gets the value of the izjasnjenjeOZalbi property.
     * 
     * @return
     *     possible object is
     *     {@link IzjasnjenjeOZalbi }
     *     
     */
    public IzjasnjenjeOZalbi getIzjasnjenjeOZalbi() {
        return izjasnjenjeOZalbi;
    }

    /**
     * Sets the value of the izjasnjenjeOZalbi property.
     * 
     * @param value
     *     allowed object is
     *     {@link IzjasnjenjeOZalbi }
     *     
     */
    public void setIzjasnjenjeOZalbi(IzjasnjenjeOZalbi value) {
        this.izjasnjenjeOZalbi = value;
    }

    /**
     * Gets the value of the razloziOdluke property.
     * 
     * @return
     *     possible object is
     *     {@link RazloziOdluke }
     *     
     */
    public RazloziOdluke getRazloziOdluke() {
        return razloziOdluke;
    }

    /**
     * Sets the value of the razloziOdluke property.
     * 
     * @param value
     *     allowed object is
     *     {@link RazloziOdluke }
     *     
     */
    public void setRazloziOdluke(RazloziOdluke value) {
        this.razloziOdluke = value;
    }

    /**
     * Gets the value of the zalbaNaResenje property.
     * 
     * @return
     *     possible object is
     *     {@link ZalbaNaResenje }
     *     
     */
    public ZalbaNaResenje getZalbaNaResenje() {
        return zalbaNaResenje;
    }

    /**
     * Sets the value of the zalbaNaResenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZalbaNaResenje }
     *     
     */
    public void setZalbaNaResenje(ZalbaNaResenje value) {
        this.zalbaNaResenje = value;
    }

}
