//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 09:56:30 PM CET 
//


package obavestenje;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="A4" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="A3" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="CD" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Disketa" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="DVD" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Audio-kaseta" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Video-kaseta" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Skeniranje" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
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
    "a4",
    "a3",
    "cd",
    "disketa",
    "dvd",
    "audioKaseta",
    "videoKaseta",
    "skeniranje"
})
@XmlRootElement(name = "Cene_kopiranja")
public class CeneKopiranja {

    @XmlElement(name = "A4", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger a4;
    @XmlElement(name = "A3", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger a3;
    @XmlElement(name = "CD", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger cd;
    @XmlElement(name = "Disketa", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger disketa;
    @XmlElement(name = "DVD", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger dvd;
    @XmlElement(name = "Audio-kaseta", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger audioKaseta;
    @XmlElement(name = "Video-kaseta", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger videoKaseta;
    @XmlElement(name = "Skeniranje", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger skeniranje;

    /**
     * Gets the value of the a4 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getA4() {
        return a4;
    }

    /**
     * Sets the value of the a4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setA4(BigInteger value) {
        this.a4 = value;
    }

    /**
     * Gets the value of the a3 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getA3() {
        return a3;
    }

    /**
     * Sets the value of the a3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setA3(BigInteger value) {
        this.a3 = value;
    }

    /**
     * Gets the value of the cd property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCD() {
        return cd;
    }

    /**
     * Sets the value of the cd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCD(BigInteger value) {
        this.cd = value;
    }

    /**
     * Gets the value of the disketa property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDisketa() {
        return disketa;
    }

    /**
     * Sets the value of the disketa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDisketa(BigInteger value) {
        this.disketa = value;
    }

    /**
     * Gets the value of the dvd property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDVD() {
        return dvd;
    }

    /**
     * Sets the value of the dvd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDVD(BigInteger value) {
        this.dvd = value;
    }

    /**
     * Gets the value of the audioKaseta property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAudioKaseta() {
        return audioKaseta;
    }

    /**
     * Sets the value of the audioKaseta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAudioKaseta(BigInteger value) {
        this.audioKaseta = value;
    }

    /**
     * Gets the value of the videoKaseta property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVideoKaseta() {
        return videoKaseta;
    }

    /**
     * Sets the value of the videoKaseta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVideoKaseta(BigInteger value) {
        this.videoKaseta = value;
    }

    /**
     * Gets the value of the skeniranje property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSkeniranje() {
        return skeniranje;
    }

    /**
     * Sets the value of the skeniranje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSkeniranje(BigInteger value) {
        this.skeniranje = value;
    }

}
