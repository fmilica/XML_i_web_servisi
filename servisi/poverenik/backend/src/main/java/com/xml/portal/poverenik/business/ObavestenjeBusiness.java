package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.obavestenje.ListaObavestenja;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ObavestenjeRepository;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ObavestenjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/obavestenje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/obavestenje_fo.xsl";
	
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
					"/poverenik/Obavestenje", 
					"src/main/resources/data/sparql/korisnikObavestenja.rq", 
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
			// vezivanje obavestenja i zahteva
			obavestenje.setVocab("http://www.xml.com/predicate/");
			obavestenje.setRel("pred:vezanZahtev");
			obavestenje.setHref("http://zahtev/" + zahtevId);
			// vezivanje obavestenja i korisnika
			obavestenje.getPodnosilac().setVocab("http://www.xml.com/predicate/");
			obavestenje.getPodnosilac().setRel("pred:vezanGradjanin");
			obavestenje.getPodnosilac().setHref("http://korisnik/" + userEmail);
			// cuvanje u bazama
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
	
	public String generateHTML(String id) throws Exception {
		DokumentiTransformator transformer = null;

		try {
			transformer = new DokumentiTransformator();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String obavestenje = obavestenjeRepository.findByIdRaw(id);
		String ok = "";
		String htmlPath = "src/main/resources/data/html/obavestenje_" + id + ".html";

		try {
			ok = transformer.generateHTML(obavestenje, htmlPath, XSL_FILE);
			if (ok.length()>0)
				return htmlPath;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String generatePDF(String id) throws Exception {
		DokumentiTransformator transformer = null;

		try {
			transformer = new DokumentiTransformator();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		String obavestenje = obavestenjeRepository.findByIdRaw(id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/obavestenje_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(obavestenje, pdfPath, XSL_FO_FILE);
			if (ok.length()>0)
				return pdfPath;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
