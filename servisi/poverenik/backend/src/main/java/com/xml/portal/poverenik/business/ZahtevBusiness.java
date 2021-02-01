package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.zahtev.ListaZahteva;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ZahtevRepository;

public class ZahtevBusiness {

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	public ListaZahteva getAll() {
		ListaZahteva zahtevi = new ListaZahteva();
		zahtevi.setZahtev(zahtevRepository.findAll());
		return zahtevi;
	}
	
	public ListaZahteva getAllByGradjanin(String userEmail) {
		List<String> zahtevIds;
		ListaZahteva zahtevi = null;
		try {
			zahtevIds = QueryMetadata.query(
					"/poverenik/Zahtev", 
					"src/main/resources/data/sparql/korisnikZahtevi.rq", 
					this.KORISNIK_NAMESPACE + userEmail);
			zahtevi = new ListaZahteva();
			zahtevi.setZahtev(zahtevRepository.findAllByGradjanin(zahtevIds));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zahtevi;
	}
	
	public Zahtev getById(String id) {
		Zahtev loaded = null;
		try {
			loaded = zahtevRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loaded;
	}
	
	public String create(Zahtev zahtev, String userEmail) {
		String documentId = null;
		try {
			zahtev.setRel("pred:vezanGradjanin");
			zahtev.setHref("http://korisnik/" + userEmail);
			documentId = zahtevRepository.save(zahtev);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentId;
	}
	
	public boolean storeMetadata(Zahtev zahtev) {
		try {
			StoreMetadata.store(zahtev);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
