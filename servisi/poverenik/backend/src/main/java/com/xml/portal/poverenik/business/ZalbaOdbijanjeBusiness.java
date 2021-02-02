package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ListaZalbiOdbijanje;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ZalbaOdbijanjeRepository;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ZalbaOdbijanjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zalba_odbijanje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zalba_odbijanje_fo.xsl";
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	@Autowired
	private ZalbaOdbijanjeRepository zalbaOdbijanjeRepository;
	
	public ListaZalbiOdbijanje getAll() {
		ListaZalbiOdbijanje zalbeOdbijanje = new ListaZalbiOdbijanje();
		zalbeOdbijanje.setZalbaOdbijanje(zalbaOdbijanjeRepository.findAll());
		return zalbeOdbijanje;
	}
	
	public ListaZalbiOdbijanje getAllByGradjanin(String userEmail) {
		List<String> zalbeOdbijanjeIds;
		ListaZalbiOdbijanje zalbeOdbijanje = null;
		try {
			zalbeOdbijanjeIds = QueryMetadata.query(
					"/poverenik/ZalbaOdbijanje", 
					"src/main/resources/data/sparql/korisnikZalbeOdbijanje.rq", 
					this.KORISNIK_NAMESPACE + userEmail);
			zalbeOdbijanje = new ListaZalbiOdbijanje();
			zalbeOdbijanje.setZalbaOdbijanje(zalbaOdbijanjeRepository.findAllByGradjanin(zalbeOdbijanjeIds));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zalbeOdbijanje;
	}
	
	public ZalbaOdbijanje getById(String id) {
		ZalbaOdbijanje loaded = null;
		try {
			loaded = zalbaOdbijanjeRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loaded;
	}
	
	public String create(ZalbaOdbijanje zalbaOdbijanje, String zahtevId, String userEmail) {
		String documentId = null;
		try {
			// vezivanje zalbe odbijanje i zahteva
			zalbaOdbijanje.setVocab("http://www.xml.com/predicate/");
			zalbaOdbijanje.setRel("pred:vezanZahtev");
			zalbaOdbijanje.setHref("http://zahtev/" + zahtevId);
			// vezivanje zalbe odbijanje i korisnika
			zalbaOdbijanje.getPodaciOPodnosiocuZalbe().setVocab("http://www.xml.com/predicate/");
			zalbaOdbijanje.getPodaciOPodnosiocuZalbe().setRel("pred:vezanGradjanin");
			zalbaOdbijanje.getPodaciOPodnosiocuZalbe().setHref("http://korisnik/" + userEmail);
			// cuvanje u bazama
			documentId = zalbaOdbijanjeRepository.save(zalbaOdbijanje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentId;
	}
	
	public boolean storeMetadata(ZalbaOdbijanje zalbaOdbijanje) {
		try {
			StoreMetadata.store(zalbaOdbijanje);
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
		
		String zalbOdbijanje = zalbaOdbijanjeRepository.findByIdRaw(id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/zalba_odbijanje_" + id + ".html";

		try {
			ok = transformer.generateHTML(zalbOdbijanje, htmlPath, XSL_FILE);
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
		
		String zalbOdbijanje = zalbaOdbijanjeRepository.findByIdRaw(id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/zalba_odbijanje_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(zalbOdbijanje, pdfPath, XSL_FO_FILE);
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
