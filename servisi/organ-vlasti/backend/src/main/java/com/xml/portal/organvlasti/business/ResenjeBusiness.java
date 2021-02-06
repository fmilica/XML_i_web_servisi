package com.xml.portal.organvlasti.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xml.portal.organvlasti.data.dao.resenje.DOMParser;
import com.xml.portal.organvlasti.data.metadatadb.api.QueryMetadata;
import com.xml.portal.organvlasti.data.metadatadb.api.StoreMetadata;
import com.xml.portal.organvlasti.data.repository.ObavestenjeRepository;
import com.xml.portal.organvlasti.data.repository.ResenjeRepository;
import com.xml.portal.organvlasti.dto.ResenjePrikazDTO;
import com.xml.portal.organvlasti.dto.pretraga.ResenjePretraga;
import com.xml.portal.organvlasti.transformator.DokumentiTransformator;

import resenje.Resenje;

public class ResenjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/resenje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/resenje_fo.xsl";
	
	private final String KORISNIK_NAMESPACE = "http://korisnik/";
	
	private final String GRAPH_URI = "/organvlasti/Resenje";
	private final String QUERY_PATH = "src/main/resources/data/sparql/napredna/resenje/";
	
	private static JAXBContext context;
	private static Marshaller marshaller;

	@Autowired
	private ResenjeRepository resenjeRepository;
	
	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	public ResenjeBusiness() {
		try {
			ResenjeBusiness.context = JAXBContext.newInstance(ResenjePrikazDTO.class);
			ResenjeBusiness.marshaller = context.createMarshaller();
			ResenjeBusiness.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ResenjeBusiness.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Object getAll() {
		List<Object> resenja = resenjeRepository.findAll();
		String allResenja = toResenjePrikazList(resenja);
		return allResenja;
	}
	
	public Object getAllByGradjanin(String userEmail) {
		List<String> resenjaIds;
		String allResenja = "";
		try {
			List<String> userQuery = new ArrayList<String>();
			userQuery.add(this.KORISNIK_NAMESPACE + userEmail);
			resenjaIds = QueryMetadata.query(
					"/organvlasti/Resenje", 
					"src/main/resources/data/sparql/korisnikResenja.rq", 
					userQuery);
			List<Object> resenja = resenjeRepository.findAllByGradjanin(resenjaIds);
			allResenja = toResenjePrikazList(resenja);
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
			return loaded;
		}
		return null;
	}
	
	public Object getAllByContent(String content) {
		List<Object> resenja = resenjeRepository.findAllByContent(content);
		String allResenja = toResenjePrikazList(resenja);
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
			allResenja = toResenjePrikazList(resenja);
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
		
		String resenje = this.resenjeRepository.findByIdRaw(id);
		//Object zahtev = RetrieveXML.retrieveRaw(null, id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/resenje_" + id + ".html";

		try {
			ok = transformer.generateHTML(resenje, htmlPath, XSL_FILE);
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
		
		String resenje = this.resenjeRepository.findByIdRaw(id);
		//Object zahtev = RetrieveXML.retrieveRaw(null, id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/resenje_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(resenje, pdfPath, XSL_FO_FILE);
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
	
//	private String toResenjaList(List<Object> resenja) {
//		String allResenja = "<ListaResenja>\n";
//		for (Object o : resenja) {
//			allResenja += (String)o + '\n';
//		}
//		allResenja += "</ListaResenja>";
//		return allResenja;
//	}
	
	private String toResenjePrikazList(List<Object> resenja) {
		String allResenja = "<ListaResenja>\n";
		for (Object resenjeString : resenja) {
			ResenjePrikazDTO resenjePrikaz = new ResenjePrikazDTO();
			
			Document resenjeDoc = stringToDocument((String)resenjeString);
			
			NodeList list = resenjeDoc.getElementsByTagNameNS("*", "Resenje");
			Element resenje = (Element)list.item(0);
			
			String resenjeIdUri = resenje.getAttribute("id");
			String[] resenjeIdUriList = resenjeIdUri.split("/");
			String resenjeId = resenjeIdUriList[resenjeIdUriList.length - 1];
			resenjePrikaz.setResenjeId(resenjeId);
			
			String resenjeDatum = resenje.getAttribute("datum_resenja");
			resenjePrikaz.setResenjeDatum(resenjeDatum);
			
			String tipOdluke = resenje.getAttribute("tip_odluke");
			resenjePrikaz.setIshodResenja(tipOdluke);
			
			String userEmailUri = resenje.getAttribute("href");
			String[] userEmailUiList = userEmailUri.split("/");
			String userEmail = userEmailUiList[userEmailUiList.length - 1];
			resenjePrikaz.setUserEmail(userEmail);
			
			list = resenje.getElementsByTagNameNS("*", "Naziv_organa_vlasti");
			Element nazivOrganaVlastiElem = (Element)list.item(0);
			String nazivOrganaVlasti = nazivOrganaVlastiElem.getTextContent();
			resenjePrikaz.setOptuzeniOrganVlasti(nazivOrganaVlasti);
			
			list = resenje.getElementsByTagNameNS("*", "Podnosenje_zalbe");
			Element podnosenjeZalbe = (Element)list.item(0);
			String zalbaIdUri = podnosenjeZalbe.getAttribute("href");
			String[] zalbaIdUriList = zalbaIdUri.split("/");
			String zalbaId = zalbaIdUriList[zalbaIdUriList.length - 1];
			resenjePrikaz.setZalbaId(zalbaIdUri);
			
			list = resenje.getElementsByTagNameNS("*", "Podnosenje_zahteva");
			Element podnosenjeZahteva = (Element)list.item(0);
			String zahtevIdUri = podnosenjeZahteva.getAttribute("href");
			String[] zahtevIdUriList = zahtevIdUri.split("/");
			String zahtevId = zahtevIdUriList[zahtevIdUriList.length - 1];
			resenjePrikaz.setZahtevId(zahtevId);
			
			String zalilac = "";
			list = resenje.getElementsByTagNameNS("*", "Ime_zalilac");
			Element imeZalilacElem = (Element)list.item(0);
			if (imeZalilacElem != null) {
				String imeZalilac = imeZalilacElem.getTextContent();
				zalilac += imeZalilac + " ";
				//resenjePrikaz.setZalilacIme(imeZalilac);	
			}

			list = resenje.getElementsByTagNameNS("*", "Prezime_zalilac");
			Element prezimeZalilacElem = (Element)list.item(0);
			if (prezimeZalilacElem != null) {
				String prezimeZalilac = prezimeZalilacElem.getTextContent();
				zalilac += prezimeZalilac;
				//resenjePrikaz.setZalilacPrezime(prezimeZalilac);	
			}

			list = resenje.getElementsByTagNameNS("*", "Naziv_zalilac");
			Element nazivZalilacElem = (Element)list.item(0);
			if (nazivZalilacElem != null) {
				String nazivZalilac = nazivZalilacElem.getTextContent();
				zalilac += nazivZalilac;
				//resenjePrikaz.setZalilacNaziv(nazivZalilac);	
			}
			resenjePrikaz.setZalilacNaziv(zalilac);	
			
			if(obavestenjeRepository.findByZahtevId(zahtevId)) {
				resenjePrikaz.setImaObavestenje("true");
			}else {
				resenjePrikaz.setImaObavestenje("false");
			}
			
			StringWriter sw = new StringWriter();
			try {
				ResenjeBusiness.marshaller.marshal(resenjePrikaz, sw);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			String resenjePrikazString = sw.toString();

			allResenja += resenjePrikazString + '\n';
		}
		allResenja += "</ListaResenja>";
		return allResenja;
	}

	public String saveToDB(Resenje resenje) {
//		String documentId = resenjeRepository.save(resenje);
//		return documentId;
		return "";
	}
}
