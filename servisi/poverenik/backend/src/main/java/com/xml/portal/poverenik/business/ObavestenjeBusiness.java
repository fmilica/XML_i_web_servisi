package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.obavestenje.ListaObavestenja;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ObavestenjeRepository;

public class ObavestenjeBusiness {
	
	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	public ListaObavestenja getAll() {
		ListaObavestenja obavestenja = new ListaObavestenja();
		obavestenja.setObavestenje(obavestenjeRepository.findAll());
		return obavestenja;
	}
	
	public ListaObavestenja getAllByGradjanin(String userEmail) {
		List<String> obavestenjeIds;
		ListaObavestenja obavestenja = null;
		try {
			obavestenjeIds = QueryMetadata.query(
					"/poverenik/Zahtev", 
					"src/main/resources/data/sparql/korisnikZahtevi.rq", 
					this.KORISNIK_NAMESPACE + userEmail);
			obavestenja = new ListaObavestenja();
			obavestenja.setObavestenje(obavestenjeRepository.findAllByGradjanin(obavestenjeIds));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obavestenja;
	}
	
	public Obavestenje getById(String id) {
		Obavestenje loaded = null;
		try {
			loaded = obavestenjeRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loaded;
	}
	
	public String create(Obavestenje obavestenje, String zahtevId, String userEmail) {
		String documentId = null;
		try {
			//obavestenje.setRel("pred:vezanGradjanin");
			obavestenje.setHref("http://zahtev/" + zahtevId);
			documentId = obavestenjeRepository.save(obavestenje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentId;
	}
	
	public boolean storeMetadata(Obavestenje obavestenje) {
		try {
			StoreMetadata.store(obavestenje);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
