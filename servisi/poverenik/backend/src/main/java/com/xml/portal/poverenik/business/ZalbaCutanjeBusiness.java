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
import com.xml.portal.poverenik.dto.pretraga.ZalbaCutanjePretraga;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ZalbaCutanjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zalba_cutanje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zalba_cutanje_fo.xsl";
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	private final String GRAPH_URI = "/poverenik/ZalbaCutanje";
	private final String QUERY_PATH = "src/main/resources/data/sparql/napredna/zalba-cutanje/";
	
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
	
	public ListaZalbiCutanje getAllByContent(String content) {
		ListaZalbiCutanje filtriraneZalbeCutanje = new ListaZalbiCutanje();
		filtriraneZalbeCutanje.setZalbaCutanje(zalbaCutanjeRepository.findAllByContent(content));
		return filtriraneZalbeCutanje;
	}
	
	public ListaZalbiCutanje getAllNapredna(ZalbaCutanjePretraga params) {
		List<String> zalbeCutanjeIds;
		ListaZalbiCutanje zalbeCutanje  = null;
		
		String vezanGradjanin = params.getVezanGradjanin();
		if (!vezanGradjanin.equals("?vezanGradjanin")) {
			vezanGradjanin = "<" + vezanGradjanin + ">";
			params.setVezanGradjanin(vezanGradjanin);
		}
		String primalacNaziv = params.getPrimalacNaziv();
		if (!primalacNaziv.equals("?primalacNaziv")) {
			primalacNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
			params.setPrimalacNaziv(primalacNaziv);
		}
		// naziv
		String podnosilacNaziv = params.getPodnosilacNaziv();
		if (!podnosilacNaziv.equals("?podnosilacNaziv")) {
			podnosilacNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
			params.setPodnosilacNaziv(podnosilacNaziv);
		}
		// ime i prezime
		String podnosilacIme = params.getPodnosilacIme();
		if (!podnosilacIme.equals("?podnosilacIme")) {
			podnosilacIme += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
			params.setPodnosilacIme(podnosilacIme);
		}
		String podnosilacPrezime = params.getPodnosilacPrezime();
		if (!podnosilacPrezime.equals("?podnosilacPrezime")) {
			podnosilacPrezime += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
			params.setPodnosilacPrezime(podnosilacPrezime);
		}
		
		try {
			if (params.getOperator().equals("AND")) {
				if ((!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) 
						&& ((!params.getPodnosilacIme().equals("?podnosilacIme"))
								|| (!params.getPodnosilacPrezime().equals("?podnosilacPrezime")))) {
					zalbeCutanjeIds = new ArrayList<String>();
				} else {
					// validna AND pretraga
					if (!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) {
						zalbeCutanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaCutanjeNaziv.rq", 
								params.createNazivArray());
					} else if ((!params.getPodnosilacIme().equals("?podnosilacIme")) || (!params.getPodnosilacPrezime().equals("?podnosilacPrezime"))) {
						zalbeCutanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaCutanjeImePrezime.rq", 
								params.createImePrezimeArray());
					} else {
						// samo po zajednickim parametrima
						zalbeCutanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaCutanje.rq", 
								params.createCommonArray());
					}
				}
			} else {
				// OR pretraga
				vezanGradjanin = params.getVezanGradjanin();
				if (vezanGradjanin.equals("?vezanGradjanin")) {
					vezanGradjanin = "<>";
					params.setVezanGradjanin(vezanGradjanin);
				}
				primalacNaziv = params.getPrimalacNaziv();
				if (primalacNaziv.equals("?primalacNaziv")) {
					primalacNaziv = "\"\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.setPrimalacNaziv(primalacNaziv);
				}
				podnosilacNaziv = params.getPodnosilacNaziv();
				if (podnosilacNaziv.equals("?podnosilacNaziv")) {
					podnosilacNaziv = "\"\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.setPodnosilacNaziv(podnosilacNaziv);
				}
				podnosilacIme = params.getPodnosilacIme();
				if (podnosilacIme.equals("?podnosilacIme")) {
					podnosilacIme = "\"\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.setPodnosilacIme(podnosilacIme);
				}
				podnosilacPrezime = params.getPodnosilacPrezime();
				if (podnosilacPrezime.equals("?podnosilacPrezime")) {
					podnosilacPrezime = "\"\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.setPodnosilacPrezime(podnosilacPrezime);
				}
				if ((!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) 
						&& ((!params.getPodnosilacIme().equals("?podnosilacIme"))
								|| (!params.getPodnosilacPrezime().equals("?podnosilacPrezime")))) {
					zalbeCutanjeIds = QueryMetadata.query(
							GRAPH_URI, 
							QUERY_PATH + "naprednaZalbaCutanjeORSve.rq", 
							params.createAllArray());
				} else {
					if (!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) {
						zalbeCutanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaCutanjeORNaziv.rq", 
								params.createNazivArray());
					} else {
						zalbeCutanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaCutanjeORImePrezime.rq", 
								params.createImePrezimeArray());
					}
				}
			}
			zalbeCutanje = new ListaZalbiCutanje();
			zalbeCutanje.setZalbaCutanje(zalbaCutanjeRepository.findAllByGradjanin(zalbeCutanjeIds));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zalbeCutanje;
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
			zalbaCutanje.getPrimalacZalbe().getNaziv().setProperty("pred:primalacNaziv");
			if (zalbaCutanje.getZalba().getPodnosilacZalbe().getNaziv() != null) {
				// ima naziv
				zalbaCutanje.getZalba().getPodnosilacZalbe().getNaziv().setProperty("pred:podnosilacNaziv");
			} else {
				// ima ime i prezime
				zalbaCutanje.getZalba().getPodnosilacZalbe().getIme().setProperty("pred:podnosilacIme");
				zalbaCutanje.getZalba().getPodnosilacZalbe().getPrezime().setProperty("pred:podnosilacPrezime");
			}
			// kada se kreira -> nije razresena
			zalbaCutanje.setRazresen(false);
			// kada se kreira -> nije izjasnjena
			zalbaCutanje.setIzjasnjen(false);
			// kada se kreira -> nije prekinuta
			zalbaCutanje.setPrekinut(false);
			// kada se kreira -> ne ceka na odgovor
			zalbaCutanje.setCeka(false);
			// cuvanje u bazama
			documentId = zalbaCutanjeRepository.save(zalbaCutanje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentId;
	}
	
	public boolean update(String zalbaCutanjeId, ZalbaCutanje zalbaCutanje) {
		zalbaCutanje.setRazresen(true);
		return zalbaCutanjeRepository.update(zalbaCutanjeId, zalbaCutanje);
	}
	
	public boolean cekanjeTrue(String zalbaCutanjeId, ZalbaCutanje zalbaCutanje) {
		zalbaCutanje.setCeka(true);
		return zalbaCutanjeRepository.cekanjeTrue(zalbaCutanjeId, zalbaCutanje);
	}
	
	public boolean izjasnjenjeTrue(String zalbaCutanjeId, ZalbaCutanje zalbaCutanje) {
		zalbaCutanje.setIzjasnjen(true);
		return zalbaCutanjeRepository.izjasnjenjeTrue(zalbaCutanjeId, zalbaCutanje);
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
