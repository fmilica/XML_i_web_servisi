package com.xml.portal.poverenik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.ZahtevBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zahtev.ListaZahteva;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;

@RestController
@RequestMapping(value = "poverenik/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevService {

	@Autowired
	private ZahtevBusiness zahtevBusiness;
	
	@GetMapping
	public ResponseEntity<Object> getAllZahtevi() {
		ListaZahteva zahtevi = zahtevBusiness.getAll();
		return new ResponseEntity<>(zahtevi, HttpStatus.OK);
	}
	
	@GetMapping("/korisnik")
	public ResponseEntity<Object> getGradjaninZahtevi(@RequestParam String userEmail) {
		ListaZahteva zahtevi = zahtevBusiness.getAllByGradjanin(userEmail);
		return new ResponseEntity<>(zahtevi, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getZahtev(@PathVariable("id") String id) {
		Zahtev zahtev = zahtevBusiness.getById(id);
		if (zahtev == null) {
			Greska greska = new Greska("Zahtev sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else {
	    	return new ResponseEntity<>(zahtev, HttpStatus.OK);
		}
	}
	
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addZahtev(@RequestBody Zahtev zahtev, 
    										@RequestParam String userEmail) {
    	String storedDocumentId = zahtevBusiness.create(zahtev, userEmail);
    	boolean storedMetadata = zahtevBusiness.storeMetadata(zahtev);
    	
    	if (storedDocumentId == null) {
    		Greska greska = new Greska("Greska u kreiranju Zahteva.");
			return ResponseEntity.status(500).body(greska);
    	} else {
    		zahtev.setId(storedDocumentId);
    		if (storedMetadata) {
		    	return new ResponseEntity<>(zahtev, HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Zahteva.");
    			return ResponseEntity.status(500).body(greska);
    		}
    	}
    }
}
