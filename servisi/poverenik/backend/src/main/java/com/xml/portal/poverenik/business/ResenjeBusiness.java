package com.xml.portal.poverenik.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xml.portal.poverenik.data.dao.resenje.DOMParser;
import com.xml.portal.poverenik.data.metadatadb.api.QueryMetadata;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.repository.ResenjeRepository;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.dto.pretraga.ResenjePretraga;
import com.xml.portal.poverenik.transformator.DokumentiTransformator;

public class ResenjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/resenje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/resenje_fo.xsl";
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	private final String GRAPH_URI = "/poverenik/Resenje";
	private final String QUERY_PATH = "src/main/resources/data/sparql/napredna/resenje/";
	
	@Autowired
	private ResenjeRepository resenjeRepository;
	
	public Object getAll() {
		List<Object> resenja = resenjeRepository.findAll();
		String allResenja = toResenjaList(resenja);
		return allResenja;
	}
	
	public Object getAllByGradjanin(String userEmail) {
		List<String> resenjaIds;
		String allResenja = "";
		try {
			List<String> userQuery = new ArrayList<String>();
			userQuery.add(this.KORISNIK_NAMESPACE + userEmail);
			resenjaIds = QueryMetadata.query(
					"/poverenik/Resenje", 
					"src/main/resources/data/sparql/korisnikResenja.rq", 
					userQuery);
			List<Object> resenja = resenjeRepository.findAllByGradjanin(resenjaIds);
			allResenja = toResenjaList(resenja);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allResenja;
	}
	
	public Object getById(String id) {
		Object loaded = null;
		try {
			loaded = resenjeRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (loaded != null) {
			System.out.println(loaded);
			return loaded;
		}
		return null;
	}
	
	public Object getAllByContent(String content) {
		List<Object> resenja = resenjeRepository.findAllByContent(content);
		String allResenja = toResenjaList(resenja);
		return allResenja;
	}
	
	public Object getAllNapredna(ResenjePretraga params) {
		List<String> resenjaIds;
		String allResenja = "";
		
		String vezanGradjanin = params.getVezanGradjanin();
		if (!vezanGradjanin.equals("?vezanGradjanin")) {
			// dodajemo <> okolo
			vezanGradjanin = "<" + vezanGradjanin + ">";
			params.setVezanGradjanin(vezanGradjanin);
		}
		
		String optuzeniNaziv = params.getOptuzeniNaziv();
		if (!optuzeniNaziv.equals("?optuzeniNaziv")) {
			optuzeniNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
			params.setOptuzeniNaziv(optuzeniNaziv);
		}
		
		try {
			if (params.getOperator().equals("AND")) {
				resenjaIds = QueryMetadata.query(
						GRAPH_URI, 
						QUERY_PATH + "naprednaResenjeAND.rq", 
						params.createAllArray());
			} else {
				vezanGradjanin = params.getVezanGradjanin();
				if (vezanGradjanin.equals("?vezanGradjanin")) {
					// dodajemo <> okolo
					vezanGradjanin = "<>";
					params.setVezanGradjanin(vezanGradjanin);
				}
				optuzeniNaziv = params.getOptuzeniNaziv();
				if (!optuzeniNaziv.equals("?optuzeniNaziv")) {
					optuzeniNaziv += "^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>";
					params.setOptuzeniNaziv(optuzeniNaziv);
				}
				resenjaIds = QueryMetadata.query(
						GRAPH_URI, 
						QUERY_PATH + "naprednaResenjeOR.rq", 
						params.createAllArray());
			}
			List<Object> resenja = resenjeRepository.findAllByGradjanin(resenjaIds);
			allResenja = toResenjaList(resenja);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allResenja;
	}
	
	public String create(String resenje, String zahtevId, String zalbaId, String userEmail) {
		
		Document resenjeDoc = stringToDocument(resenje);
		
		String documentId = resenjeRepository.save(resenjeDoc, zahtevId, zalbaId, userEmail);
		
		return documentId;
	}
	
	public String storeMetadata(String resenjeString) {
		try {
			Document resenje = stringToDocument(resenjeString);
			StoreMetadata.store(resenje);
			NodeList list = resenje.getElementsByTagNameNS("*", "Resenje");
			Element resenjeElem = (Element)list.item(0);
			String resenjeId = resenjeElem.getAttribute("id");
			return resenjeId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
		
		Object zahtev = RetrieveXML.retrieveRaw(null, id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/resenje_" + id + ".html";

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
		
		Object zahtev = RetrieveXML.retrieveRaw(null, id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/resenje_" + id + ".pdf";

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
	
	// Helper method
	private Document stringToDocument(String xmlString) {
		FileOutputStream outResenjeTemp = null;
		try {
			outResenjeTemp = new FileOutputStream(new File("src/main/resources/data/schema/resenje/temp_resenje.xml"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			outResenjeTemp.write(xmlString.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		DOMParser parser = new DOMParser();
		parser.buildDocument("src/main/resources/data/schema/resenje/temp_resenje.xml");
		Document resenje = parser.getDocument();
		
		return resenje;
	}
	
	private String toResenjaList(List<Object> resenja) {
		String allResenja = "<ListaResenja>\n";
		for (Object o : resenja) {
			allResenja += (String)o + '\n';
		}
		allResenja += "</ListaResenja>";
		return allResenja;
	}
}
