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

import com.xml.portal.poverenik.business.ZalbaCutanjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.dao.zalba_cutanje.ListaZalbiCutanje;
import com.xml.portal.poverenik.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.dto.pretraga.ZalbaCutanjePretraga;

@RestController
@RequestMapping(value = "poverenik/zalba-cutanje", produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class ZalbaCutanjeService {

	@Autowired
	private ZalbaCutanjeBusiness zalbaCutanjeBusiness;
	
	@GetMapping
	public ResponseEntity<Object> getAllZalbeCutanje() {
		ListaZalbiCutanje zalbeCutanje = zalbaCutanjeBusiness.getAll();
		return new ResponseEntity<>(zalbeCutanje, HttpStatus.OK);
	}
	
	@GetMapping("/korisnik")
	public ResponseEntity<Object> getGradjaninZalbeCutanje(@RequestParam String userEmail) {
		ListaZalbiCutanje zalbeCutanje = zalbaCutanjeBusiness.getAllByGradjanin(userEmail);
		return new ResponseEntity<>(zalbeCutanje, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getZalbaCutanje(@PathVariable("id") String id) {
		ZalbaCutanje zalbaCutanje = zalbaCutanjeBusiness.getById(id);
		if (zalbaCutanje == null) {
			Greska greska = new Greska("Zalba na cutanje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else {
			return new ResponseEntity<>(zalbaCutanje, HttpStatus.OK);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addZalbaCutanje(@RequestBody ZalbaCutanje zalbaCutanje,
												@RequestParam String zahtevId,
												@RequestParam String userEmail) {
		String storedDocumentId = zalbaCutanjeBusiness.create(zalbaCutanje, zahtevId, userEmail);
    	boolean storedMetadata = zalbaCutanjeBusiness.storeMetadata(zalbaCutanje);
    	
    	if (storedDocumentId == null) {
    		Greska greska = new Greska("Greska u kreiranju Zalbe na cutanje.");
    		return ResponseEntity.status(500).body(greska);
    	} else {
    		zalbaCutanje.setId(storedDocumentId);
    		if (storedMetadata) {
    			return new ResponseEntity<>(zalbaCutanje, HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Zalbe na cutanje.");
    			return ResponseEntity.status(500).body(greska);
    		}
    	}
    }
	
    @GetMapping("/pretrazi")
    public ResponseEntity<Object> obicnaPretraga(@RequestParam("sadrzaj") String content) throws Exception {
		ListaZalbiCutanje filtriraneZalbeCutanje = zalbaCutanjeBusiness.getAllByContent(content);
		return new ResponseEntity<>(filtriraneZalbeCutanje, HttpStatus.OK);
    }
    
    @PostMapping("/pretrazi-napredno")
    public ResponseEntity<Object> naprednaPretraga(@RequestBody ZalbaCutanjePretraga params) {
    	ListaZalbiCutanje filtriraneZalbeCutanje = zalbaCutanjeBusiness.getAllNapredna(params);
		return new ResponseEntity<>(filtriraneZalbeCutanje, HttpStatus.OK);
    }
	
    @GetMapping("/generisiHTML/{id}")
	public ResponseEntity<Object> generisiHTML(@PathVariable("id") String id) throws Exception {

		String path = zalbaCutanjeBusiness.generateHTML(id);
		
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

		String path = zalbaCutanjeBusiness.generatePDF(id);
		
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

    	ZalbaCutanje zalbaCutanje = zalbaCutanjeBusiness.getById(id);
		if (zalbaCutanje == null) {
			Greska greska = new Greska("Zalba na cutanje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = StoreMetadata.generisiRDF(zalbaCutanje);
		
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

    	ZalbaCutanje zalbaCutanje = zalbaCutanjeBusiness.getById(id);
		if (zalbaCutanje == null) {
			Greska greska = new Greska("Zalba na cutanje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = QueryMetadata.generisiJSON("/poverenik/ZalbaCutanje", "http://zalbacutanje", id);
    	
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
    
    
    @PutMapping(path = "/odustani/{id}")
    public ResponseEntity<Object> odustaniOdZalbe(@PathVariable("id") String id) {
		ZalbaCutanje zalbaCutanje = zalbaCutanjeBusiness.getById(id);
		if (zalbaCutanje == null) {
			Greska greska = new Greska("Zalba na cutanje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else if (zalbaCutanje.getPrekinut() == true) {
			Greska greska = new Greska("Zalba na cutanje sa prosledjenim identifikatorom je vec prekinuta.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(greska);
		} else {
			boolean updated = zalbaCutanjeBusiness.odustani(id, zalbaCutanje);
			if (!updated) {
				Greska greska = new Greska("Greska u izmeni Zalbe na cutanje.");
				return ResponseEntity.status(500).body(greska);
			}
			return new ResponseEntity<>(zalbaCutanje, HttpStatus.OK);
		}
    }
}