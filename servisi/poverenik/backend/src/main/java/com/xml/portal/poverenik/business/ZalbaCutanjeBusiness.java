package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.zalba_cutanje.ListaZalbiCutanje;
import com.xml.portal.poverenik.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ZalbaCutanjeRepository;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ZalbaCutanjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zalba_cutanje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zalba_cutanje_fo.xsl";
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	@Autowired
	private ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	public ListaZalbiCutanje getAll() {
		ListaZalbiCutanje zalbeCutanje = new ListaZalbiCutanje();
		zalbeCutanje.setZalbaCutanje(zalbaCutanjeRepository.findAll());
		return zalbeCutanje;
	}
	
	public ListaZalbiCutanje getAllByGradjanin(String userEmail) {
		List<String> zalbeCutanjeIds;
		ListaZalbiCutanje zalbeCutanje = null;
		try {
			List<String> userQuery = new ArrayList<String>();
			userQuery.add(this.KORISNIK_NAMESPACE + userEmail);
			zalbeCutanjeIds = QueryMetadata.query(
					"/poverenik/ZalbaCutanje", 
					"src/main/resources/data/sparql/korisnikZalbeCutanje.rq", 
					userQuery);
			zalbeCutanje = new ListaZalbiCutanje();
			zalbeCutanje.setZalbaCutanje(zalbaCutanjeRepository.findAllByGradjanin(zalbeCutanjeIds));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zalbeCutanje;
	}
	
	public ZalbaCutanje getById(String id) {
		ZalbaCutanje loaded = null;
		try {
			loaded = zalbaCutanjeRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loaded;
	}
	
	public String create(ZalbaCutanje zalbaCutanje, String zahtevId, String userEmail) {
		String documentId = null;
		try {
			// vezivanje zalbe cutanje i zahteva
			zalbaCutanje.setVocab("http://www.xml.com/predicate/");
			zalbaCutanje.setRel("pred:vezanZahtev");
			zalbaCutanje.setHref("http://zahtev/" + zahtevId);
			// vezivanje zalbe cutanje i korisnika
			zalbaCutanje.getZalba().getPodnosilacZalbe().setVocab("http://www.xml.com/predicate/");
			zalbaCutanje.getZalba().getPodnosilacZalbe().setRel("pred:vezanGradjanin");
			zalbaCutanje.getZalba().getPodnosilacZalbe().setHref("http://korisnik/" + userEmail);
			// cuvanje u bazama
			documentId = zalbaCutanjeRepository.save(zalbaCutanje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentId;
	}
	
	public boolean storeMetadata(ZalbaCutanje zalbaCutanje) {
		try {
			StoreMetadata.store(zalbaCutanje);
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
		
		String zalbaCutanje = zalbaCutanjeRepository.findByIdRaw(id);
		
		String ok = "";
		String htmlPath = "src/main/resources/data/html/zalba_cutanje_" + id + ".html";

		try {
			ok = transformer.generateHTML(zalbaCutanje, htmlPath, XSL_FILE);
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
		
		String zalbaCutanje = zalbaCutanjeRepository.findByIdRaw(id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/zalba_cutanje_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(zalbaCutanje, pdfPath, XSL_FO_FILE);
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
