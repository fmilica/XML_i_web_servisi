package com.xml.portal.poverenik.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

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
import org.w3c.dom.Document;

import com.xml.portal.poverenik.data.dao.resenje.DOMParser;
import com.xml.portal.poverenik.business.ResenjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.dto.ResenjeDTO;
import com.xml.portal.poverenik.dto.pretraga.ResenjePretraga;

@RestController
@RequestMapping(value = "poverenik/resenje", produces = "application/xml;charset=utf-8")
@CrossOrigin(origins = "http://localhost:4200")
public class ResenjeService {

	@Autowired
	private ResenjeBusiness resenjeBusiness;
	
	@GetMapping
	public ResponseEntity<Object> getAllResenja() {
		Object resenja = resenjeBusiness.getAll();
		return new ResponseEntity<>(resenja, HttpStatus.OK);
	}
	
	@GetMapping("/korisnik")
	public ResponseEntity<Object> getGradjaninResenja(@RequestParam String userEmail) {
		Object resenja = resenjeBusiness.getAllByGradjanin(userEmail);
		return new ResponseEntity<>(resenja, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getZahtev(@PathVariable("id") String id) {
		Object resenje = resenjeBusiness.getById(id);
		if (resenje == null) {
			Greska greska = new Greska("Resenje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else {
			return new ResponseEntity<>(resenje, HttpStatus.OK);	
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> addResenje(@RequestBody String resenje,
								    		@RequestParam String zahtevId,
								    		@RequestParam String zalbaId,
											@RequestParam String userEmail) throws Exception {
    	String resenjeString = resenjeBusiness.create(resenje, zahtevId, zalbaId, userEmail);
    	String storedMetadata = resenjeBusiness.storeMetadata(resenjeString);
    	String soapEndpointUrl = "http://localhost:8082/ws/resenje";
		
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		
		SOAPBody soapBody = envelope.getBody();
		soapBody.addDocument(this.stringToDocument(resenjeString));

		System.out.println();
		System.out.println(soapBody);

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");
		// Send SOAP Message to SOAP Server
		SOAPMessage soapResponse = soapConnection.call(soapMessage, soapEndpointUrl);

		System.out.println("Response SOAP Message:");
		soapResponse.writeTo(System.out);
		System.out.println();
    	if (resenjeString == null) {
    		Greska greska = new Greska("Greska u kreiranju Resenja.");
    		return ResponseEntity.status(500).body(greska);
    	} else {
    		if (storedMetadata != null) {
    			return new ResponseEntity<>(new ResenjeDTO(storedMetadata, zahtevId, zalbaId, userEmail), HttpStatus.OK);
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Resenja.");
    			return ResponseEntity.status(500).body(greska);
    		}
    	}
    }
	
    @GetMapping("/pretrazi")
    public ResponseEntity<Object> obicnaPretraga(@RequestParam("sadrzaj") String content) throws Exception {
		Object filtriranaResenja = resenjeBusiness.getAllByContent(content);
		return new ResponseEntity<>(filtriranaResenja, HttpStatus.OK);
    }
    
    @PostMapping("/pretrazi-napredno")
    public ResponseEntity<Object> naprednaPretraga(@RequestBody ResenjePretraga params) {
    	Object filtriranaResenja = resenjeBusiness.getAllNapredna(params);
		return new ResponseEntity<>(filtriranaResenja, HttpStatus.OK);
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
    
    @GetMapping("/generisiRDF/{id}")
	public ResponseEntity<Object> generisiRDF(@PathVariable("id") String id) throws Exception {

    	Object resenje = resenjeBusiness.getById(id);
		if (resenje == null) {
			Greska greska = new Greska("Resenje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = StoreMetadata.generisiRDF(resenje);
		
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

    	Object resenje = resenjeBusiness.getById(id);
		if (resenje == null) {
			Greska greska = new Greska("Resenje sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
		
    	String path = QueryMetadata.generisiJSON("/poverenik/Resenje", "http://resenje", id);
    	
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
    
    private Document stringToDocument(String xmlString) {
		FileOutputStream outResenjeTemp = null;
		try {
			outResenjeTemp = new FileOutputStream(new File("src/main/resources/data/schema/resenje/temp_resenje.xml"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			outResenjeTemp.write(xmlString.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		DOMParser parser = new DOMParser();
		parser.buildDocument("src/main/resources/data/schema/resenje/temp_resenje.xml");
		Document resenje = parser.getDocument();
		
		return resenje;
	}
}

