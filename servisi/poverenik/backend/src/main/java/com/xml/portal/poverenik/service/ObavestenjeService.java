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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.ObavestenjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.obavestenje.ListaObavestenja;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.dto.pretraga.ObavestenjePretraga;

@RestController
@RequestMapping(value = "poverenik/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@GetMapping("postoji/{id}")
	public ResponseEntity<Object> getObavestenjeZaZahtev(@PathVariable("id") String id) {
		boolean obavestenjePostoji = obavestenjeBusiness.getByZahtevId(id);
		if(!obavestenjePostoji) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*
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
	*/
	
    @GetMapping("/pretrazi")
    public ResponseEntity<Object> obicnaPretraga(@RequestParam("sadrzaj") String content) throws Exception {
		ListaObavestenja filtriranaObavestenja = obavestenjeBusiness.getAllByContent(content);
		return new ResponseEntity<>(filtriranaObavestenja, HttpStatus.OK);
    }
    
    @PostMapping("/pretrazi-napredno")
    public ResponseEntity<Object> naprednaPretraga(@RequestBody ObavestenjePretraga params) {
    	ListaObavestenja filtriranaObavestenja = obavestenjeBusiness.getAllNapredna(params);
		return new ResponseEntity<>(filtriranaObavestenja, HttpStatus.OK);
    }
	
	@GetMapping("/generisiHTML/{id}")
	public ResponseEntity<Object> generisiHTML(@PathVariable("id") String id) throws Exception {

		String path = obavestenjeBusiness.generateHTML(id);
		
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

		String path = obavestenjeBusiness.generatePDF(id);
		
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

    	Obavestenje obavestenje = obavestenjeBusiness.getById(id);
		if (obavestenje == null) {
			Greska greska = new Greska("Obavestenje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = StoreMetadata.generisiRDF(obavestenje);
		
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

    	Obavestenje obavestenje = obavestenjeBusiness.getById(id);
		if (obavestenje == null) {
			Greska greska = new Greska("Obavestenje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = QueryMetadata.generisiJSON("/poverenik/Obavestenje", "http://obavetsenje", id);
    	
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
