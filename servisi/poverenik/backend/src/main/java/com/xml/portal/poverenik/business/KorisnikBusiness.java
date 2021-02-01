package com.xml.portal.poverenik.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.xml.portal.poverenik.data.dao.korisnik.Korisnik;
import com.xml.portal.poverenik.data.dao.korisnik.ObjectFactory;
import com.xml.portal.poverenik.data.repository.KorisnikRepository;
import com.xml.portal.poverenik.dto.KorisnikRegistracijaDTO;

public class KorisnikBusiness implements UserDetailsService {

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik korisnik = null;
		try {
			korisnik = korisnikRepository.findByEmail(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return korisnik;
	}
	
	public boolean register(KorisnikRegistracijaDTO registracija) {
		// provera da li vec postoji sa unetim emailom
		Korisnik existing = korisnikRepository.findByEmail(registracija.getEmail());
		boolean saved = false;
		if (existing == null) {
			Korisnik korisnik = new ObjectFactory().createKorisnikFromRegistracija(registracija);
			korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
			try {
				saved = korisnikRepository.save(korisnik);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return saved;
		} else {
			return false;
		}
	}

}
