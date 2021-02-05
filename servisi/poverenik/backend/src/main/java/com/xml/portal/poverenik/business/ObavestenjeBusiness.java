package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.obavestenje.ListaObavestenja;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ObavestenjeRepository;
import com.xml.portal.poverenik.dto.pretraga.ObavestenjePretraga;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ObavestenjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/obavestenje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/obavestenje_fo.xsl";
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	private final String GRAPH_URI = "/poverenik/Obavestenje";
	private final String QUERY_PATH = "src/main/resources/data/sparql/napredna/obavestenje/";
	
	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	public ListaObavestenja getAll() {
		ListaObavestenja obavestenja = new ListaObavestenja();
		obavestenja.setObavestenje(obavestenjeRepository.findAll());
		return obavestenja;
	}
	
	public ListaObavestenja getAllByGradjanin(String userEmail) {
		List<String> obavestenjeIds;
		ListaObavestenja obavestenja = null;
		try {
			List<String> userQuery = new ArrayList<String>();
			userQuery.add(this.KORISNIK_NAMESPACE + userEmail);
			obavestenjeIds = QueryMetadata.query(
					"/poverenik/Obavestenje", 
					"src/main/resources/data/sparql/korisnikObavestenja.rq", 
					userQuery);
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
	
	public ListaObavestenja getAllByContent(String content) {
		ListaObavestenja filtriranaObavestenja = new ListaObavestenja();
		filtriranaObavestenja.setObavestenje(obavestenjeRepository.findAllByContent(content));
		return filtriranaObavestenja;
	}
	
	public ListaObavestenja getAllNapredna(ObavestenjePretraga params) {
		List<String> obavestenjeIds;
		ListaObavestenja obavestenja = null;
		
		String vezanGradjanin = params.getVezanGradjanin();
		if (!vezanGradjanin.equals("?vezanGradjanin")) {
			// dodajemo <> okolo
			vezanGradjanin = "<" + vezanGradjanin + ">";
			params.setVezanGradjanin(vezanGradjanin);
		}
		String vezanZahtev = params.getVezanZahtev();
		if (!vezanZahtev.equals("?vezanZahtev")) {
			// dodajemo <> okolo
			vezanGradjanin = "<" + vezanGradjanin + ">";
			params.setVezanGradjanin(vezanGradjanin);
		}
		String izdavacNaziv = params.getIzdavacNaziv();
		if (!izdavacNaziv.equals("?izdavacNaziv")) {
			izdavacNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
			params.setIzdavacNaziv(izdavacNaziv);
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
				// ako unese kombinaciju primalac:
				// ime+naziv || prezime+naziv || ime+prezime+naziv
				// -> prazna lista
				if ((!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) 
					&& ((!params.getPodnosilacIme().equals("?podnosilacIme"))
							|| (!params.getPodnosilacPrezime().equals("?podnosilacPrezime")))) {
					obavestenjeIds = new ArrayList<String>();
				} else {
					// validna AND pretraga
					if (!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) {
						obavestenjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaObavestenjeNaziv.rq", 
								params.createNazivArray());
					} else if ((!params.getPodnosilacIme().equals("?podnosilacIme")) || (!params.getPodnosilacPrezime().equals("?podnosilacPrezime"))) {
						obavestenjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaObavestenjeImePrezime.rq", 
								params.createImePrezimeArray());
					} else {
						// samo po zajednickim parametrima
						obavestenjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaObavestenje.rq", 
								params.createCommonArray());
					}
				}
			} else {
				vezanGradjanin = params.getVezanGradjanin();
				if (vezanGradjanin.equals("?vezanGradjanin")) {
					// dodajemo <> okolo
					vezanGradjanin = "<>";
					params.setVezanGradjanin(vezanGradjanin);
				}
				vezanZahtev = params.getVezanZahtev();
				if (vezanZahtev.equals("?vezanZahtev")) {
					// dodajemo <> okolo
					vezanZahtev = "<>";
					params.setVezanGradjanin(vezanZahtev);
				}
				izdavacNaziv = params.getIzdavacNaziv();
				if (izdavacNaziv.equals("?izdavacNaziv")) {
					izdavacNaziv = "\"\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.setIzdavacNaziv(izdavacNaziv);
				}
				// naziv
				podnosilacNaziv = params.getPodnosilacNaziv();
				if (podnosilacNaziv.equals("?podnosilacNaziv")) {
					podnosilacNaziv = "\"\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.setPodnosilacNaziv(podnosilacNaziv);
				}
				// ime i prezime
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
					obavestenjeIds = QueryMetadata.query(
							GRAPH_URI, 
							QUERY_PATH + "naprednaObavestenjeORSve.rq", 
							params.createAllArray());
				} else {
					if (!params.getPodnosilacNaziv().equals("?podnosilacNaziv")) {
						// naziv
						obavestenjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaObavestenjeORNaziv.rq", 
								params.createNazivArray());
					} else {
						// ime i prezime
						obavestenjeIds = QueryMetadata.query(
								GRAPH_URI, 
								QUERY_PATH + "naprednaObavestenjeORImePrezime.rq", 
								params.createImePrezimeArray());
					}
				}
			}
			obavestenja = new ListaObavestenja();
			obavestenja.setObavestenje(obavestenjeRepository.findAllByGradjanin(obavestenjeIds));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obavestenja;
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
			// vezivanje ostalih metapodataka
			obavestenje.getOrganVlasti().getNaziv().setProperty("pred:izdavacNaziv");
			if (obavestenje.getPodnosilac().getNaziv() != null) {
				// ima naziv
				obavestenje.getPodnosilac().getNaziv().setProperty("pred:podnosilacNaziv");
			} else {
				// ima ime i prezime
				obavestenje.getPodnosilac().getIme().setProperty("pred:podnosilacIme");
				obavestenje.getPodnosilac().getPrezime().setProperty("pred:podnosilacPrezime");
			}
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
