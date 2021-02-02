package com.xml.portal.poverenik.service;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.xml.portal.poverenik.business.ResenjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;

@RestController
@RequestMapping(value = "poverenik/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeService {

	@Autowired
	private ResenjeBusiness resenjeBusiness;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getZahtev(@PathVariable("id") String id) {
		Document resenje = resenjeBusiness.getById(id);
		if (resenje == null) {
			Greska greska = new Greska("Resenje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else {
			return new ResponseEntity<>(resenje, HttpStatus.OK);	
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addResenje(@RequestBody String xmlResenje) {
    	Document stored = resenjeBusiness.create(xmlResenje);
    	String storedId = resenjeBusiness.storeMetadata(stored);
    	
    	if (stored == null) {
    		Greska greska = new Greska("Greska u kreiranju Resenja.");
    		return ResponseEntity.status(500).body(greska);
    	} else {
    		if (storedId != null) {
    			return new ResponseEntity<>(stored, HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Resenja.");
    			return ResponseEntity.status(500).body(greska);
    		}
    	}
    }
	
	@GetMapping("/generisiHTML/{id}")
	public ResponseEntity<Object> generisiHTML(@PathVariable("id") String id) throws Exception {

		String path = resenjeBusiness.generateHTML(id);
		
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

		String path = resenjeBusiness.generatePDF(id);
		
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
}

