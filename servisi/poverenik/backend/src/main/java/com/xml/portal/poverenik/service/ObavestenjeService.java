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

import com.xml.portal.poverenik.business.ObavestenjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.obavestenje.ListaObavestenja;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;

@RestController
@RequestMapping(value = "poverenik/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ObavestenjeService {

	@Autowired
	private ObavestenjeBusiness obavestenjeBusiness;
	
	@GetMapping
	public ResponseEntity<Object> getAllObavestenja() {
		ListaObavestenja obavestenja = obavestenjeBusiness.getAll();
		return new ResponseEntity<>(obavestenja, HttpStatus.OK);
	}
	
	@GetMapping("/korisnik")
	public ResponseEntity<Object> getGradjaninObavestenja(@RequestParam String userEmail) {
		ListaObavestenja obavestenja = obavestenjeBusiness.getAllByGradjanin(userEmail);
		return new ResponseEntity<>(obavestenja, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getObavestenje(@PathVariable("id") String id) {
		Obavestenje obavestenje = obavestenjeBusiness.getById(id);
		if (obavestenje == null) {
			Greska greska = new Greska("Obavestenje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else {
	    	return new ResponseEntity<>(obavestenje, HttpStatus.OK);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addObavestenje(@RequestBody Obavestenje obavestenje,
    											@RequestParam String zahtevId,
    											@RequestParam String userEmail) {
		String storedDocumentId = obavestenjeBusiness.create(obavestenje, zahtevId, userEmail);
    	boolean storedMetadata = obavestenjeBusiness.storeMetadata(obavestenje);
    	
    	if (storedDocumentId == null) {
    		Greska greska = new Greska("Greska u kreiranju Obavestenja.");
			return ResponseEntity.status(500).body(greska);
		} 
    	else {
    		obavestenje.setId(storedDocumentId);
    		if (storedMetadata) {
			    return new ResponseEntity<>(obavestenje, HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Obavestenja.");
    			return ResponseEntity.status(500).body(greska);    		}
    	}
    }
}