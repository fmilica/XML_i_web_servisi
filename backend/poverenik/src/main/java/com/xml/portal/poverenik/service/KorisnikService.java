package com.xml.portal.poverenik.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.KorisnikBusiness;
import com.xml.portal.poverenik.business.KorisnikDTO;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.korisnik.Korisnik;
import com.xml.portal.poverenik.data.dao.korisnik.KorisnikToken;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.security.TokenUtils;

@RestController
@Path("/korisnik")
@CrossOrigin(origins = "http://localhost:4200")
public class KorisnikService {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private KorisnikBusiness korisnikBusiness;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML_VALUE)
	public Response getKorisnik(@PathParam("id") String id) {
		Korisnik korisnik = korisnikBusiness.getById(id);
		Response r;
		if(korisnik == null) {
			Greska greska = new Greska("Korisnik sa prosledjenim identifikatorom ne postoji.");
			r = Response.status(404).type("application/xml").entity(greska).build();
		} else {
			r = Response.ok().type("application/xml").entity(korisnik).build();	
		}
		return r;
	}
	
	
	@POST
	 public Response addObavestenje(Korisnik zahtev, @Context UriInfo uriInfo) {
		Korisnik stored = korisnikBusiness.create(zahtev);
    	boolean storedMetadata = korisnikBusiness.storeMetadata(zahtev);
    	
    	Response r;
    	if (stored == null) {
    		Greska greska = new Greska("Greska u kreiranju Korisnika.");
			r = Response.status(500).type("application/xml").entity(greska).build();
    	} else {
    		if (storedMetadata) {
			    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			    builder.path(stored.getId());
			    r = Response.created(builder.build()).type("application/xml").entity(stored).build();
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Korisnika.");
    			r = Response.status(500).type("application/xml").entity(greska).build();
    		}
    	}
        return r;
	}
	
	@PostMapping(value = "/prijava")
	public Response prijava() {
		Authentication authentication = null;
		KorisnikDTO authenticationRequest = new KorisnikDTO("osvezenje@email.com", "sifra");
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getLozinka()));
		} catch (Exception e) {
			return Response.status(500).type("application/xml").entity("Pogrešan e-mail ili lozinka.").build();
		}

		// Kreiraj token za tog korisnika
		Korisnik user = (Korisnik) authentication.getPrincipal();

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		String jwt = tokenUtils.generateToken(user.getKorisnickoIme(), user.getUloga()); // prijavljujemo se na sistem sa
																						// email adresom
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return Response.ok().type("application/xml").entity(new KorisnikToken(jwt, (long) expiresIn)).build();
	}
	

}
