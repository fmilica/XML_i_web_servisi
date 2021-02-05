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

import com.xml.portal.poverenik.business.ZalbaOdbijanjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ListaZalbiOdbijanje;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.dto.pretraga.ZalbaOdbijanjePretraga;

@RestController
@RequestMapping(value = "poverenik/zalba-odbijanje", produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class ZalbaOdbijanjeService {

	@Autowired
	private ZalbaOdbijanjeBusiness zalbaOdbijanjeBusiness;
	
	@GetMapping
	public ResponseEntity<Object> getAllZalbeOdbijanje() {
		ListaZalbiOdbijanje zalbeOdbijanje = zalbaOdbijanjeBusiness.getAll();
		return new ResponseEntity<>(zalbeOdbijanje, HttpStatus.OK);
	}
	
	@GetMapping("/korisnik")
	public ResponseEntity<Object> getGradjaninZalbeOdbijanje(@RequestParam String userEmail) {
		ListaZalbiOdbijanje zalbeOdbijanje = zalbaOdbijanjeBusiness.getAllByGradjanin(userEmail);
		return new ResponseEntity<>(zalbeOdbijanje, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getZalbaOdbijanje(@PathVariable("id") String id) {
		ZalbaOdbijanje zalbaOdbijanje = zalbaOdbijanjeBusiness.getById(id);
		if (zalbaOdbijanje == null) {
			Greska greska = new Greska("Zalba zbog odbijanja sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else {
			return new ResponseEntity<>(zalbaOdbijanje, HttpStatus.OK);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addZalbaOdbijanje(@RequestBody ZalbaOdbijanje zalbaOdbijanje,
													@RequestParam String zahtevId,
													@RequestParam String userEmail) {
		String storedDocumentId = zalbaOdbijanjeBusiness.create(zalbaOdbijanje, zahtevId, userEmail);
    	boolean storedMetadata = zalbaOdbijanjeBusiness.storeMetadata(zalbaOdbijanje);
    	
    	
    	if (storedDocumentId == null) {
    		Greska greska = new Greska("Greska u kreiranju Zalbe zbog odbijanje.");
    		return ResponseEntity.status(500).body(greska);
    	} else {
    		zalbaOdbijanje.setId(storedDocumentId);
    		if (storedMetadata) {
			    return new ResponseEntity<>(zalbaOdbijanje, HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Zalbe zbog odbijanja.");
    			return ResponseEntity.status(500).body(greska);
    		}
    	}
    }
	
    @GetMapping("/pretrazi")
    public ResponseEntity<Object> obicnaPretraga(@RequestParam("sadrzaj") String content) throws Exception {
		ListaZalbiOdbijanje filtriraneZalbeOdbijanje = zalbaOdbijanjeBusiness.getAllByContent(content);
		return new ResponseEntity<>(filtriraneZalbeOdbijanje, HttpStatus.OK);
    }
    
    @PostMapping("/pretrazi-napredno")
    public ResponseEntity<Object> naprednaPretraga(@RequestBody ZalbaOdbijanjePretraga params) {
    	ListaZalbiOdbijanje filtriraneZalbeOdbijanje = zalbaOdbijanjeBusiness.getAllNapredna(params);
		return new ResponseEntity<>(filtriraneZalbeOdbijanje, HttpStatus.OK);
    }
    
    @GetMapping("/generisiHTML/{id}")
	public ResponseEntity<Object> generisiHTML(@PathVariable("id") String id) throws Exception {

		String path = zalbaOdbijanjeBusiness.generateHTML(id);
		
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

		String path = zalbaOdbijanjeBusiness.generatePDF(id);
		
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

    	ZalbaOdbijanje zalbaOdbijanje = zalbaOdbijanjeBusiness.getById(id);
		if (zalbaOdbijanje == null) {
			Greska greska = new Greska("Zalba na odbijanje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = StoreMetadata.generisiRDF(zalbaOdbijanje);
		
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

    	ZalbaOdbijanje zalbaOdbijanje = zalbaOdbijanjeBusiness.getById(id);
		if (zalbaOdbijanje == null) {
			Greska greska = new Greska("Zalba na odbijanje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = QueryMetadata.generisiJSON("/poverenik/ZalbaOdbijanje", "http://zalbaodbijanje", id);
    	
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
