package com.xml.portal.poverenik.service;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.ZalbaOdbijanjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;

@RestController
@RequestMapping(value = "poverenik/zalba-odbijanje", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaOdbijanjeService {

	@Autowired
	private ZalbaOdbijanjeBusiness zalbaOdbijanjeBusiness;
	
	
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
    public ResponseEntity<Object> addZalbaOdbijanje(@RequestBody ZalbaOdbijanje zalbaOdbijanje) {
    	ZalbaOdbijanje stored = zalbaOdbijanjeBusiness.create(zalbaOdbijanje);
    	boolean storedMetadata = zalbaOdbijanjeBusiness.storeMetadata(zalbaOdbijanje);
    	
    	
    	if (stored == null) {
    		Greska greska = new Greska("Greska u kreiranju Zahteva.");
    		return ResponseEntity.status(500).body(greska);
    	} else {
    		if (storedMetadata) {
			    return new ResponseEntity<>(stored, HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Zalbe zbog odbijanja.");
    			return ResponseEntity.status(500).body(greska);
    		}
    	}
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
}
