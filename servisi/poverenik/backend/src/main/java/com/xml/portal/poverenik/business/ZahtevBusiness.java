package com.xml.portal.poverenik.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.zahtev.ListaZahteva;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ZahtevRepository;
import com.xml.portal.poverenik.dto.pretraga.NaprednaPretragaDTO;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ZahtevBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zahtev.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zahtev_fo.xsl";

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
			List<String> userQuery = new ArrayList<String>();
			userQuery.add(this.KORISNIK_NAMESPACE + userEmail);
			zahtevIds = QueryMetadata.query(
					"/poverenik/Zahtev", 
					"src/main/resources/data/sparql/korisnikZahtevi.rq", 
					userQuery);
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
	
	public ListaZahteva getAllByContent(String content) {
		ListaZahteva filtriraniZahtevi = new ListaZahteva();
		filtriraniZahtevi.setZahtev(zahtevRepository.findAllByContent(content));
		return filtriraniZahtevi;
	}
	
	public ListaZahteva getAllNapredna(NaprednaPretragaDTO params) {
		List<String> zahtevIds;
		ListaZahteva zahtevi = null;
		try {
			String vezanGradjanin = params.getParametri().getParametar().get(0);
			if (!vezanGradjanin.equals("?vezanGradjanin")) {
				// dodajemo <> okolo
				vezanGradjanin = "<" + vezanGradjanin + ">";
				params.getParametri().getParametar().set(0, vezanGradjanin);
			}
			// za sve ostale dodajemo
			// ^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>
			// nakon vrednosti
			// ako se razlikuju od default-ne
			String primalacNaziv = params.getParametri().getParametar().get(1);
			if (!primalacNaziv.equals("?primalacNaziv")) {
				primalacNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
				params.getParametri().getParametar().set(1, primalacNaziv);
			}
			if (params.getParametri().getParametar().size() == 4) {
				// ime i prezime
				String podnosilacIme = params.getParametri().getParametar().get(2);
				if (!podnosilacIme.equals("?podnosilacIme")) {
					podnosilacIme += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.getParametri().getParametar().set(2, podnosilacIme);
				}
				String podnosilacPrezime = params.getParametri().getParametar().get(3);
				if (!podnosilacPrezime.equals("?podnosilacPrezime")) {
					podnosilacPrezime += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.getParametri().getParametar().set(3, podnosilacPrezime);
				}
				
				// provera logickog operatora
				if (params.getOperator().equals("AND")) {
					zahtevIds = QueryMetadata.query(
							"/poverenik/Zahtev", 
							"src/main/resources/data/sparql/napredna/naprednaZahtevImePrezime.rq", 
							params.getParametri().getParametar());
				} else {
					zahtevIds = QueryMetadata.query(
							"/poverenik/Zahtev", 
							"src/main/resources/data/sparql/napredna/naprednaZahtevORImePrezime.rq", 
							params.getParametri().getParametar());
				}
			} else if (params.getParametri().getParametar().size() == 3) {
				// naziv
				String podnosilacNaziv = params.getParametri().getParametar().get(2);
				if (!podnosilacNaziv.equals("?podnosilacNaziv")) {
					podnosilacNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.getParametri().getParametar().set(2, podnosilacNaziv);
				}
				
				// provera logickog operatora
				if (params.getOperator().equals("AND")) {
					zahtevIds = QueryMetadata.query(
							"/poverenik/Zahtev", 
							"src/main/resources/data/sparql/napredna/naprednaZahtevNaziv.rq", 
							params.getParametri().getParametar());
				} else {
					zahtevIds = QueryMetadata.query(
							"/poverenik/Zahtev", 
							"src/main/resources/data/sparql/napredna/naprednaZahtevORNaziv.rq", 
							params.getParametri().getParametar());
				}
			} else {
				// i naziv i ime i prezime
				String podnosilacIme = params.getParametri().getParametar().get(2);
				if (!podnosilacIme.equals("?podnosilacIme")) {
					podnosilacIme += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.getParametri().getParametar().set(2, podnosilacIme);
				}
				String podnosilacPrezime = params.getParametri().getParametar().get(3);
				if (!podnosilacPrezime.equals("?podnosilacPrezime")) {
					podnosilacPrezime += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.getParametri().getParametar().set(3, podnosilacPrezime);
				}
				String podnosilacNaziv = params.getParametri().getParametar().get(4);
				if (!podnosilacNaziv.equals("?podnosilacNaziv")) {
					podnosilacNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.getParametri().getParametar().set(2, podnosilacNaziv);
				}
				// provera logickog operatora
				if (params.getOperator().equals("AND")) {
					zahtevIds = new ArrayList<String>();
				} else {
					zahtevIds = QueryMetadata.query(
							"/poverenik/Zahtev", 
							"src/main/resources/data/sparql/napredna/naprednaZahtevORSve.rq", 
							params.getParametri().getParametar());
				}
			}
			zahtevi = new ListaZahteva();
			zahtevi.setZahtev(zahtevRepository.findAllNapredna(zahtevIds));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zahtevi;
	}
	
	public boolean update(String zahtevId, Zahtev zahtev) {
		zahtev.setRazresen(true);
		return zahtevRepository.update(zahtevId, zahtev);
	}
	
	public String create(Zahtev zahtev, String userEmail) {
		String documentId = null;
		try {
			// vezivanje zahteva i korisnika
			zahtev.setVocab("http://www.xml.com/predicate/");
	    	zahtev.setRel("pred:vezanGradjanin");
			zahtev.setHref("http://korisnik/" + userEmail);
			// vezivanje ostalih metapodataka
			zahtev.getOrganVlasti().getNaziv().setProperty("pred:primalacNaziv");
			if (zahtev.getTrazilac().getNaziv() != null) {
				// ima naziv
				zahtev.getTrazilac().getNaziv().setProperty("pred:podnosilacNaziv");
			} else {
				// ima ime i prezime
				zahtev.getTrazilac().getIme().setProperty("pred:podnosilacIme");
				zahtev.getTrazilac().getPrezime().setProperty("pred:podnosilacPrezime");
			}
			// kada se kreira -> nije razresen
			zahtev.setRazresen(false);
			// kada se kreira -> nije odbijen
			zahtev.setOdbijen(false);
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
	
	public String generateHTML(String id) throws Exception {
		DokumentiTransformator transformer = null;

		try {
			transformer = new DokumentiTransformator();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		String zahtev = zahtevRepository.findByIdRaw(id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/zahtev_" + id + ".html";

		try {
			ok = transformer.generateHTML(zahtev.toString(), htmlPath, XSL_FILE);
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
		
		String zahtev = zahtevRepository.findByIdRaw(id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/zahtev_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(zahtev.toString(), pdfPath, XSL_FO_FILE);
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
