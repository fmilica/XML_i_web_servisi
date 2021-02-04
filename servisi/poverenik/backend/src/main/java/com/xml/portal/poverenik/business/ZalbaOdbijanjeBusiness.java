package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ListaZalbiOdbijanje;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ZalbaOdbijanjeRepository;
import com.xml.portal.poverenik.dto.pretraga.ZalbaOdbijanjePretraga;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ZalbaOdbijanjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zalba_odbijanje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zalba_odbijanje_fo.xsl";
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	private final String GRAPH_URI = "/poverenik/ZalbaOdbijanje";
	private final String QUERY_PATH = "src/main/resources/data/sparql/napredna/zalba-odbijanje/";
	
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
			List<String> userQuery = new ArrayList<String>();
			userQuery.add(this.KORISNIK_NAMESPACE + userEmail);
			zalbeOdbijanjeIds = QueryMetadata.query(
					"/poverenik/ZalbaOdbijanje", 
					"src/main/resources/data/sparql/korisnikZalbeOdbijanje.rq", 
					userQuery);
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
	
	public ListaZalbiOdbijanje getAllByContent(String content) {
		ListaZalbiOdbijanje filtriraneZalbeOdbijanje = new ListaZalbiOdbijanje();
		filtriraneZalbeOdbijanje.setZalbaOdbijanje(zalbaOdbijanjeRepository.findAllByContent(content));
		return filtriraneZalbeOdbijanje;
	}
	
	public ListaZalbiOdbijanje getAllNapredna(ZalbaOdbijanjePretraga params) {
		List<String> zalbeOdbijanjeIds;
		ListaZalbiOdbijanje zalbeOdbijanje  = null;
		
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
					zalbeOdbijanjeIds = new ArrayList<String>();
				} else {
					// validna AND pretraga
					if (!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) {
						zalbeOdbijanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaOdbijanjeNaziv.rq", 
								params.createNazivArray());
					} else if ((!params.getPodnosilacIme().equals("?podnosilacIme")) || (!params.getPodnosilacPrezime().equals("?podnosilacPrezime"))) {
						zalbeOdbijanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaOdbijanjeImePrezime.rq", 
								params.createImePrezimeArray());
					} else {
						// samo po zajednickim parametrima
						zalbeOdbijanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaOdbijanje.rq", 
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
					zalbeOdbijanjeIds = QueryMetadata.query(
							GRAPH_URI, 
							QUERY_PATH + "naprednaZalbaCutanjeORSve.rq", 
							params.createAllArray());
				} else {
					if (!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) {
						zalbeOdbijanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaOdbijanjeORNaziv.rq", 
								params.createNazivArray());
					} else {
						zalbeOdbijanjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaZalbaOdbijanjeORImePrezime.rq", 
								params.createImePrezimeArray());
					}
				}
			}
			zalbeOdbijanje = new ListaZalbiOdbijanje();
			zalbeOdbijanje.setZalbaOdbijanje(zalbaOdbijanjeRepository.findAllByGradjanin(zalbeOdbijanjeIds));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zalbeOdbijanje;
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
			// property
			zalbaOdbijanje.getPodaciOPrimaocu().getNaziv().setProperty("pred:primalacNaziv");
			if (zalbaOdbijanje.getPodaciOZaliocu().getNaziv() != null) {
				// ima naziv
				zalbaOdbijanje.getPodaciOZaliocu().getNaziv().setProperty("pred:podnosilacNaziv");
			} else {
				// ima ime i prezime
				zalbaOdbijanje.getPodaciOZaliocu().getIme().setProperty("pred:podnosilacIme");
				zalbaOdbijanje.getPodaciOZaliocu().getPrezime().setProperty("pred:podnosilacPrezime");
			}
			if (zalbaOdbijanje.getPodaciOPodnosiocuZalbe().getNaziv() != null) {
				// ima naziv
				zalbaOdbijanje.getPodaciOPodnosiocuZalbe().getNaziv().setProperty("pred:podnosilacNaziv");
			} else {
				// ima ime i prezime
				zalbaOdbijanje.getPodaciOPodnosiocuZalbe().getIme().setProperty("pred:podnosilacIme");
				zalbaOdbijanje.getPodaciOPodnosiocuZalbe().getPrezime().setProperty("pred:podnosilacPrezime");
			}
			// kada se kreira -> nije razresena
			zalbaOdbijanje.setRazresen(false);
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
