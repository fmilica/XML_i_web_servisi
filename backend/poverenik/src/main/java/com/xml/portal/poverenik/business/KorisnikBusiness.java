package com.xml.portal.poverenik.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xml.portal.poverenik.data.dao.korisnik.Korisnik;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class KorisnikBusiness implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String email){
        // ako se ne radi nasledjivanje, paziti gde sve treba da se proveri email
        Korisnik korisnik = this.getById(email);
        return korisnik;
        
    }
    
    public Korisnik getById(String id) {
		Object ret = null;
		try {
			ret = RetrieveXML.retrieve(Korisnik.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (Korisnik)ret;
		}
		return null;
	}
	
	public Korisnik create(Korisnik korisnik) {
		Object ret = null;
		try {
			Korisnik korisnikUpis = new Korisnik(korisnik);
			korisnikUpis.setLozinka(passwordEncoder.encode(korisnikUpis.getLozinka()));
			ret = StoreXML.store(korisnikUpis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (Korisnik)ret;
		}
		return null;
	}
	
	public boolean storeMetadata(Korisnik korisnik) {
		try {
			StoreMetadata.store(korisnik);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
