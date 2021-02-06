
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the resenje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OpisZalbeImeZalilac_QNAME = new QName("http://resenje", "Ime_zalilac");
    private final static QName _OpisZalbePrezimeZalilac_QNAME = new QName("http://resenje", "Prezime_zalilac");
    private final static QName _OpisZalbeNazivZalilac_QNAME = new QName("http://resenje", "Naziv_zalilac");
    private final static QName _OpisZalbeNazivOrganaVlasti_QNAME = new QName("http://resenje", "Naziv_organa_vlasti");
    private final static QName _OpisZalbeMestoOrganaVlasti_QNAME = new QName("http://resenje", "Mesto_organa_vlasti");
    private final static QName _OpisZalbeUlicaOrganaVlasti_QNAME = new QName("http://resenje", "Ulica_organa_vlasti");
    private final static QName _OpisZalbeUlicniBrojOrganaVlasti_QNAME = new QName("http://resenje", "Ulicni_broj_organa_vlasti");
    private final static QName _OpisZalbeClan_QNAME = new QName("http://resenje", "Clan");
    private final static QName _OpisZalbeStav_QNAME = new QName("http://resenje", "Stav");
    private final static QName _OpisZalbeTacka_QNAME = new QName("http://resenje", "Tacka");
    private final static QName _OpisZalbeZakon_QNAME = new QName("http://resenje", "Zakon");
    private final static QName _OpisZalbeNazivSluzbenogGlasnika_QNAME = new QName("http://resenje", "Naziv_sluzbenog_glasnika");
    private final static QName _OpisZalbeBrojSluzbenogGlasnika_QNAME = new QName("http://resenje", "Broj_sluzbenog_glasnika");
    private final static QName _OdlukaOdbijanje_QNAME = new QName("http://resenje", "Odbijanje");
    private final static QName _RazloziOdlukeDetaljanOpisPodnetogZahteva_QNAME = new QName("http://resenje", "Detaljan_opis_podnetog_zahteva");
    private final static QName _RazloziOdlukeDetaljanOpisOdgovoraNaZahtev_QNAME = new QName("http://resenje", "Detaljan_opis_odgovora_na_zahtev");
    private final static QName _RazloziOdlukeDetaljanOpisOdluke_QNAME = new QName("http://resenje", "Detaljan_opis_odluke");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: resenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OpisZalbe }
     * 
     */
    public OpisZalbe createOpisZalbe() {
        return new OpisZalbe();
    }

    /**
     * Create an instance of {@link PostupakZalioca }
     * 
     */
    public PostupakZalioca createPostupakZalioca() {
        return new PostupakZalioca();
    }

    /**
     * Create an instance of {@link TOsoba }
     * 
     */
    public TOsoba createTOsoba() {
        return new TOsoba();
    }

    /**
     * Create an instance of {@link Resenje }
     * 
     */
    public Resenje createResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link OpisZalbe.ImeZalilac }
     * 
     */
    public OpisZalbe.ImeZalilac createOpisZalbeImeZalilac() {
        return new OpisZalbe.ImeZalilac();
    }

    /**
     * Create an instance of {@link OpisZalbe.PrezimeZalilac }
     * 
     */
    public OpisZalbe.PrezimeZalilac createOpisZalbePrezimeZalilac() {
        return new OpisZalbe.PrezimeZalilac();
    }

    /**
     * Create an instance of {@link OpisZalbe.NazivZalilac }
     * 
     */
    public OpisZalbe.NazivZalilac createOpisZalbeNazivZalilac() {
        return new OpisZalbe.NazivZalilac();
    }

    /**
     * Create an instance of {@link OpisZalbe.NazivOrganaVlasti }
     * 
     */
    public OpisZalbe.NazivOrganaVlasti createOpisZalbeNazivOrganaVlasti() {
        return new OpisZalbe.NazivOrganaVlasti();
    }

    /**
     * Create an instance of {@link Odluka }
     * 
     */
    public Odluka createOdluka() {
        return new Odluka();
    }

    /**
     * Create an instance of {@link Nalog }
     * 
     */
    public Nalog createNalog() {
        return new Nalog();
    }

    /**
     * Create an instance of {@link Obrazlozenje }
     * 
     */
    public Obrazlozenje createObrazlozenje() {
        return new Obrazlozenje();
    }

    /**
     * Create an instance of {@link PostupakZalioca.PodnosenjeZalbe }
     * 
     */
    public PostupakZalioca.PodnosenjeZalbe createPostupakZaliocaPodnosenjeZalbe() {
        return new PostupakZalioca.PodnosenjeZalbe();
    }

    /**
     * Create an instance of {@link PostupakZalioca.PodnosenjeZahteva }
     * 
     */
    public PostupakZalioca.PodnosenjeZahteva createPostupakZaliocaPodnosenjeZahteva() {
        return new PostupakZalioca.PodnosenjeZahteva();
    }

    /**
     * Create an instance of {@link ProsledjivanjeZalbe }
     * 
     */
    public ProsledjivanjeZalbe createProsledjivanjeZalbe() {
        return new ProsledjivanjeZalbe();
    }

    /**
     * Create an instance of {@link IzjasnjenjeOZalbi }
     * 
     */
    public IzjasnjenjeOZalbi createIzjasnjenjeOZalbi() {
        return new IzjasnjenjeOZalbi();
    }

    /**
     * Create an instance of {@link RazloziOdluke }
     * 
     */
    public RazloziOdluke createRazloziOdluke() {
        return new RazloziOdluke();
    }

    /**
     * Create an instance of {@link ZalbaNaResenje }
     * 
     */
    public ZalbaNaResenje createZalbaNaResenje() {
        return new ZalbaNaResenje();
    }

    /**
     * Create an instance of {@link TOsoba.Ime }
     * 
     */
    public TOsoba.Ime createTOsobaIme() {
        return new TOsoba.Ime();
    }

    /**
     * Create an instance of {@link TOsoba.Prezime }
     * 
     */
    public TOsoba.Prezime createTOsobaPrezime() {
        return new TOsoba.Prezime();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OpisZalbe.ImeZalilac }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Ime_zalilac", scope = OpisZalbe.class)
    public JAXBElement<OpisZalbe.ImeZalilac> createOpisZalbeImeZalilac(OpisZalbe.ImeZalilac value) {
        return new JAXBElement<OpisZalbe.ImeZalilac>(_OpisZalbeImeZalilac_QNAME, OpisZalbe.ImeZalilac.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OpisZalbe.PrezimeZalilac }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Prezime_zalilac", scope = OpisZalbe.class)
    public JAXBElement<OpisZalbe.PrezimeZalilac> createOpisZalbePrezimeZalilac(OpisZalbe.PrezimeZalilac value) {
        return new JAXBElement<OpisZalbe.PrezimeZalilac>(_OpisZalbePrezimeZalilac_QNAME, OpisZalbe.PrezimeZalilac.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OpisZalbe.NazivZalilac }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Naziv_zalilac", scope = OpisZalbe.class)
    public JAXBElement<OpisZalbe.NazivZalilac> createOpisZalbeNazivZalilac(OpisZalbe.NazivZalilac value) {
        return new JAXBElement<OpisZalbe.NazivZalilac>(_OpisZalbeNazivZalilac_QNAME, OpisZalbe.NazivZalilac.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OpisZalbe.NazivOrganaVlasti }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Naziv_organa_vlasti", scope = OpisZalbe.class)
    public JAXBElement<OpisZalbe.NazivOrganaVlasti> createOpisZalbeNazivOrganaVlasti(OpisZalbe.NazivOrganaVlasti value) {
        return new JAXBElement<OpisZalbe.NazivOrganaVlasti>(_OpisZalbeNazivOrganaVlasti_QNAME, OpisZalbe.NazivOrganaVlasti.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Mesto_organa_vlasti", scope = OpisZalbe.class)
    public JAXBElement<String> createOpisZalbeMestoOrganaVlasti(String value) {
        return new JAXBElement<String>(_OpisZalbeMestoOrganaVlasti_QNAME, String.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Ulica_organa_vlasti", scope = OpisZalbe.class)
    public JAXBElement<String> createOpisZalbeUlicaOrganaVlasti(String value) {
        return new JAXBElement<String>(_OpisZalbeUlicaOrganaVlasti_QNAME, String.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Ulicni_broj_organa_vlasti", scope = OpisZalbe.class)
    public JAXBElement<String> createOpisZalbeUlicniBrojOrganaVlasti(String value) {
        return new JAXBElement<String>(_OpisZalbeUlicniBrojOrganaVlasti_QNAME, String.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Clan", scope = OpisZalbe.class)
    public JAXBElement<BigInteger> createOpisZalbeClan(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeClan_QNAME, BigInteger.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Stav", scope = OpisZalbe.class)
    public JAXBElement<BigInteger> createOpisZalbeStav(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeStav_QNAME, BigInteger.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Tacka", scope = OpisZalbe.class)
    public JAXBElement<BigInteger> createOpisZalbeTacka(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeTacka_QNAME, BigInteger.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Zakon", scope = OpisZalbe.class)
    public JAXBElement<String> createOpisZalbeZakon(String value) {
        return new JAXBElement<String>(_OpisZalbeZakon_QNAME, String.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Naziv_sluzbenog_glasnika", scope = OpisZalbe.class)
    public JAXBElement<String> createOpisZalbeNazivSluzbenogGlasnika(String value) {
        return new JAXBElement<String>(_OpisZalbeNazivSluzbenogGlasnika_QNAME, String.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Broj_sluzbenog_glasnika", scope = OpisZalbe.class)
    public JAXBElement<String> createOpisZalbeBrojSluzbenogGlasnika(String value) {
        return new JAXBElement<String>(_OpisZalbeBrojSluzbenogGlasnika_QNAME, String.class, OpisZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Odbijanje", scope = Odluka.class)
    public JAXBElement<String> createOdlukaOdbijanje(String value) {
        return new JAXBElement<String>(_OdlukaOdbijanje_QNAME, String.class, Odluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Clan", scope = ProsledjivanjeZalbe.class)
    public JAXBElement<BigInteger> createProsledjivanjeZalbeClan(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeClan_QNAME, BigInteger.class, ProsledjivanjeZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Stav", scope = ProsledjivanjeZalbe.class)
    public JAXBElement<BigInteger> createProsledjivanjeZalbeStav(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeStav_QNAME, BigInteger.class, ProsledjivanjeZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Tacka", scope = ProsledjivanjeZalbe.class)
    public JAXBElement<BigInteger> createProsledjivanjeZalbeTacka(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeTacka_QNAME, BigInteger.class, ProsledjivanjeZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Zakon", scope = ProsledjivanjeZalbe.class)
    public JAXBElement<String> createProsledjivanjeZalbeZakon(String value) {
        return new JAXBElement<String>(_OpisZalbeZakon_QNAME, String.class, ProsledjivanjeZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Detaljan_opis_podnetog_zahteva", scope = RazloziOdluke.class)
    public JAXBElement<String> createRazloziOdlukeDetaljanOpisPodnetogZahteva(String value) {
        return new JAXBElement<String>(_RazloziOdlukeDetaljanOpisPodnetogZahteva_QNAME, String.class, RazloziOdluke.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Detaljan_opis_odgovora_na_zahtev", scope = RazloziOdluke.class)
    public JAXBElement<String> createRazloziOdlukeDetaljanOpisOdgovoraNaZahtev(String value) {
        return new JAXBElement<String>(_RazloziOdlukeDetaljanOpisOdgovoraNaZahtev_QNAME, String.class, RazloziOdluke.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Detaljan_opis_odluke", scope = RazloziOdluke.class)
    public JAXBElement<String> createRazloziOdlukeDetaljanOpisOdluke(String value) {
        return new JAXBElement<String>(_RazloziOdlukeDetaljanOpisOdluke_QNAME, String.class, RazloziOdluke.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Clan", scope = RazloziOdluke.class)
    public JAXBElement<BigInteger> createRazloziOdlukeClan(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeClan_QNAME, BigInteger.class, RazloziOdluke.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Stav", scope = RazloziOdluke.class)
    public JAXBElement<BigInteger> createRazloziOdlukeStav(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeStav_QNAME, BigInteger.class, RazloziOdluke.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Tacka", scope = RazloziOdluke.class)
    public JAXBElement<BigInteger> createRazloziOdlukeTacka(BigInteger value) {
        return new JAXBElement<BigInteger>(_OpisZalbeTacka_QNAME, BigInteger.class, RazloziOdluke.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "Zakon", scope = RazloziOdluke.class)
    public JAXBElement<String> createRazloziOdlukeZakon(String value) {
        return new JAXBElement<String>(_OpisZalbeZakon_QNAME, String.class, RazloziOdluke.class, value);
    }

}