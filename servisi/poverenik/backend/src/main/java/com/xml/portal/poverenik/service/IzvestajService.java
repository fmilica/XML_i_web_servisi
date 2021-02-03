package com.xml.portal.poverenik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.IzvestajBusiness;

@RestController
@RequestMapping(value = "poverenik/izvestaj", produces = MediaType.APPLICATION_XML_VALUE)
public class IzvestajService {

	@Autowired
	private IzvestajBusiness izvestajBusiness;
	
	@GetMapping("/generisi")
	private ResponseEntity<Object> generisiIzvestaj(){
		izvestajBusiness.generisiIzvestaj();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
