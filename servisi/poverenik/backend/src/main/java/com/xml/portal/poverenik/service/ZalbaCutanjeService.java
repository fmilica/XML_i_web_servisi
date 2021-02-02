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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.ZalbaCutanjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zalba_cutanje.ZalbaCutanje;

@RestController
@RequestMapping(value = "poverenik/zalba-cutanje", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaCutanjeService {

	@Autowired
	private ZalbaCutanjeBusiness zalbaCutanjeBusiness;
	
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
    public ResponseEntity<Object> addZalbaCutanje(ZalbaCutanje zalbaCutanje) {
    	ZalbaCutanje stored = zalbaCutanjeBusiness.create(zalbaCutanje);
    	boolean storedMetadata = zalbaCutanjeBusiness.storeMetadata(zalbaCutanje);
    	
    	if (stored == null) {
    		Greska greska = new Greska("Greska u kreiranju Zalbe na cutanje.");
    		return ResponseEntity.status(500).body(greska);
    	} else {
    		if (storedMetadata) {
    			return new ResponseEntity<>(stored, HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Zalbe na cutanje.");
    			return ResponseEntity.status(500).body(greska);
    		}
    	}
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
}