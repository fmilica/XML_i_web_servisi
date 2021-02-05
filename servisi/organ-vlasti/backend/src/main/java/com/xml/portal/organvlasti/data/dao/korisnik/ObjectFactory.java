//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.30 at 04:56:49 AM CET 
//


package com.xml.portal.organvlasti.data.dao.korisnik;

import javax.xml.bind.annotation.XmlRegistry;

import com.xml.portal.organvlasti.data.dao.zahtev.ListaZahteva;
import com.xml.portal.organvlasti.dto.KorisnikRegistracijaDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xml.portal.organvlasti.data.dao.korisnik package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xml.portal.organvlasti.data.dao.korisnik
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListaKorisnika }
     * 
     */
    public ListaKorisnika createListaKorisnika() {
        return new ListaKorisnika();
    }

    /**
     * Create an instance of {@link Korisnik }
     * 
     */
    public Korisnik createKorisnik() {
        return new Korisnik();
    }
    
    public Korisnik createKorisnikFromRegistracija(KorisnikRegistracijaDTO registracija) {
    	Korisnik korisnik = new Korisnik();
    	korisnik.setUloga("ROLE_GRADJANIN");
    	korisnik.setEmail(registracija.getEmail());
    	korisnik.setLozinka(registracija.getLozinka());
    	korisnik.setIme(registracija.getIme());
    	korisnik.setPrezime(registracija.getPrezime());
    	return korisnik;
    }
    
    public ListaZahteva createListaZahteva() {
    	return new ListaZahteva();
    }

}
