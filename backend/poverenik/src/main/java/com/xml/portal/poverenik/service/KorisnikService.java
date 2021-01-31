package com.xml.portal.poverenik.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.xml.portal.poverenik.business.KorisnikBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.korisnik.Korisnik;
import com.xml.portal.poverenik.dto.KorisnikPrijavaDTO;
import com.xml.portal.poverenik.dto.KorisnikRegistracijaDTO;
import com.xml.portal.poverenik.security.TokenUtils;

@Service
@Path("/korisnik")
@CrossOrigin()
public class KorisnikService {

	@Autowired
	private KorisnikBusiness korisnikBusiness;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@POST
	@Path("/prijava")
	@Produces("application/xml")
	public Response prijava(KorisnikPrijavaDTO korisnikPrijava) {
		Response r;
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					korisnikPrijava.getEmail(), korisnikPrijava.getLozinka()));
		} catch (Exception e) {
			e.printStackTrace();
			Greska greska = new Greska("Pogresan email ili lozinka.");
			r = Response.status(404).type("application/xml").entity(greska).header("Access-Control-Allow-Origin", "*").build();
			return r;
		}
		Korisnik korisnik = (Korisnik) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = tokenUtils.generateToken(korisnik.getEmail(), korisnik.getUloga());
		long expiresIn = tokenUtils.getExpiredIn();

		r = Response.ok().type("application/xml")
				.header("Access-Control-Allow-Origin", "*")
				.header("Authorization", "Bearer " + jwtToken)
				.header("Expires-In", String.valueOf(expiresIn)).build();
		return r;
	}
	
    @POST
    @Path("/registracija")
    @Produces("application/xml")
    public Response registracija(KorisnikRegistracijaDTO registracija) {
    	boolean storedXml = korisnikBusiness.register(registracija);
    	Response r;
    	if (!storedXml) {
    		Greska greska = new Greska("Greska u registraciji. Korisnik sa unetim email-om vec postoji.");
			r = Response.status(500).type("application/xml").entity(greska).header("Access-Control-Allow-Origin", "*").build();
    	} else {
    		r = Response.ok(registracija).type("application/xml").header("Access-Control-Allow-Origin", "*").build();	
		}
        return r;
    }
}
