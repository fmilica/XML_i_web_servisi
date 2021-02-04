package com.xml.portal.poverenik.service;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.ZahtevBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zahtev.ListaZahteva;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;

@RestController
@RequestMapping(value = "poverenik/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "https://localhost:4200")
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
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> resiZahtev(@PathVariable("id") String id) {
		Zahtev zahtev = zahtevBusiness.getById(id);
		if (zahtev == null) {
			Greska greska = new Greska("Zahtev sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else if (zahtev.getRazresen() == true) {
			Greska greska = new Greska("Zahtev sa prosledjenim identifikatorom je vec razresen.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(greska);
		} else {
			boolean updated = zahtevBusiness.update(id, zahtev);
			if (!updated) {
				Greska greska = new Greska("Greska u izmeni Zahteva.");
				return ResponseEntity.status(500).body(greska);
			}
			return new ResponseEntity<>(zahtev, HttpStatus.OK);
		}
    }
    
    @GetMapping("/generisiHTML/{id}")
	public ResponseEntity<Object> generisiHTML(@PathVariable("id") String id) throws Exception {

		String path = zahtevBusiness.generateHTML(id);
		
		try {
			File file = new File(path);
			FileInputStream stream = new FileInputStream(file);
			return new ResponseEntity<>(IOUtils.toByteArray(stream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			Greska greska = new Greska("Greska prilikom generisanja html-a.");
			return ResponseEntity.status(500).body(greska);
		}
	
	}
    
    @GetMapping("/generisiPDF/{id}")
	public ResponseEntity<Object> generisiPDF(@PathVariable("id") String id) throws Exception {

		String path = zahtevBusiness.generatePDF(id);
		
		try {
			File file = new File(path);
			FileInputStream stream = new FileInputStream(file);
			return new ResponseEntity<>(IOUtils.toByteArray(stream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			Greska greska = new Greska("Greska prilikom generisanja pdf-a.");
			return ResponseEntity.status(500).body(greska);
		}
	
	}
    
    @GetMapping("/generisiRDF/{id}")
	public ResponseEntity<Object> generisiRDF(@PathVariable("id") String id) throws Exception {

    	Zahtev zahtev = zahtevBusiness.getById(id);
		if (zahtev == null) {
			Greska greska = new Greska("Zahtev sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = StoreMetadata.generisiRDF(zahtev);
		
		try {
			File file = new File(path);
			FileInputStream stream = new FileInputStream(file);
			return new ResponseEntity<>(IOUtils.toByteArray(stream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			Greska greska = new Greska("Greska prilikom generisanja rdf-a.");
			return ResponseEntity.status(500).body(greska);
		}
	
	}
    
    @GetMapping("/generisiJSON/{id}")
	public ResponseEntity<Object> generisiJSON(@PathVariable("id") String id) throws Exception {

    	Zahtev zahtev = zahtevBusiness.getById(id);
		if (zahtev == null) {
			Greska greska = new Greska("Zahtev sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = QueryMetadata.generisiJSON("/poverenik/Zahtev", "http://zahtev", id);
    	
		try {
			File file = new File(path);
			FileInputStream stream = new FileInputStream(file);
			return new ResponseEntity<>(IOUtils.toByteArray(stream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			Greska greska = new Greska("Greska prilikom generisanja json-a.");
			return ResponseEntity.status(500).body(greska);
		}
	
	}
}
