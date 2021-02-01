package com.xml.portal.poverenik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.KorisnikBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.korisnik.Korisnik;
import com.xml.portal.poverenik.dto.KorisnikPrijavaDTO;
import com.xml.portal.poverenik.dto.KorisnikRegistracijaDTO;
import com.xml.portal.poverenik.security.TokenUtils;

@RestController
@RequestMapping(value = "poverenik/korisnik", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class KorisnikService {

	@Autowired
	private KorisnikBusiness korisnikBusiness;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/prijava")
	public ResponseEntity<Object> prijava(@RequestBody KorisnikPrijavaDTO korisnikPrijava) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					korisnikPrijava.getEmail(), korisnikPrijava.getLozinka()));
		} catch (Exception e) {
			e.printStackTrace();
			Greska greska = new Greska("Pogresan email ili lozinka.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(greska);
		}
		Korisnik korisnik = (Korisnik) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = tokenUtils.generateToken(korisnik.getEmail(), korisnik.getUloga());
		long expiresIn = tokenUtils.getExpiredIn();
	    
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Authorization", "Bearer " + jwtToken);
	    responseHeaders.set("Expires-In", String.valueOf(expiresIn));
	    responseHeaders.set("Access-Control-Expose-Headers", "Authorization, Expires-In");
	    responseHeaders.set("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

	    return ResponseEntity.ok().headers(responseHeaders).build();
	}
	
	@PostMapping("/registracija")
    public ResponseEntity<Object> registracija(@RequestBody KorisnikRegistracijaDTO registracija) {
    	boolean storedXml = korisnikBusiness.register(registracija);
    	if (!storedXml) {
    		Greska greska = new Greska("Greska u registraciji. Korisnik sa unetim email-om vec postoji.");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(greska);
    	}
    	return new ResponseEntity<>(registracija, HttpStatus.CREATED);
    }
}
