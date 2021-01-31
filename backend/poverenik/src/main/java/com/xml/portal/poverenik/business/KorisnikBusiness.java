package com.xml.portal.poverenik.business;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xml.portal.poverenik.data.dao.korisnik.Korisnik;

public class KorisnikBusiness implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new Korisnik();
	}

}
