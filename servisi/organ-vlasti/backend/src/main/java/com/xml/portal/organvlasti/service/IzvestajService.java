package com.xml.portal.organvlasti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.organvlasti.business.IzvestajBusiness;
import com.xml.portal.organvlasti.data.dao.exception.Greska;
import com.xml.portal.organvlasti.data.dao.izvestaj.Izvestaj;
import com.xml.portal.organvlasti.data.dao.izvestaj.ListaIzvestaj;
import com.xml.portal.organvlasti.data.dao.obavestenje.ListaObavestenja;
import com.xml.portal.organvlasti.data.dao.obavestenje.Obavestenje;

@RestController
@RequestMapping(value = "organvlasti/izvestaj", produces = MediaType.APPLICATION_XML_VALUE)
public class IzvestajService {

	@Autowired
	private IzvestajBusiness izvestajBusiness;
	
	
	@GetMapping
	public ResponseEntity<Object> getAllIzvestaje() {
		ListaIzvestaj izvestaji = izvestajBusiness.getAll();
		return new ResponseEntity<>(izvestaji, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getIzvestaj(@PathVariable("id") String id) {
		Izvestaj izvestaj = izvestajBusiness.getById(id);
		if (izvestaj == null) {
			Greska greska = new Greska("Izvestaj sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		} else {
	    	return new ResponseEntity<>(izvestaj, HttpStatus.OK);
		}
	}
	
	@GetMapping("/generisi")
	private ResponseEntity<Object> generisiIzvestaj(){
		Izvestaj izvestaj = izvestajBusiness.generisiIzvestaj();
		return new ResponseEntity<Object>(izvestaj, HttpStatus.OK);
	}
}
