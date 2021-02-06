package com.xml.portal.organvlasti.data.repository;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.portal.organvlasti.data.xmldb.api.ExistManager;

@Repository
public class ResenjeRepository {
	
	private String collectionId = "/db/organvlasti/Resenje";
	
	private static final String TARGET_NAMESPACE = "http://resenje";
	
	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";
	
	private static TransformerFactory transformerFactory;
	private static Transformer transformer;
	
	
	@Autowired
	private ExistManager existManager;
	
	public ResenjeRepository() {
		try {
			ResenjeRepository.transformerFactory = TransformerFactory.newInstance();
			// Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
			ResenjeRepository.transformer = transformerFactory.newTransformer();
			ResenjeRepository.transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			ResenjeRepository.transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public List<Object> findAll() {
		Collection allResenja = null;
		List<Object> resenja = new ArrayList<Object>();
		try {
			allResenja = this.existManager.loadCollection(collectionId);
			String[] resNames = allResenja.listResources();
			for (String name : resNames) {
				XMLResource resource = this.existManager.load(collectionId, name);
				if (resource != null) {
					resenja.add(resource.getContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allResenja != null) {
				try {
					allResenja.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return resenja;
	}
	
	public List<Object> findAllByGradjanin(List<String> resenjaIds) {
		Collection allResenja = null;
		List<Object> resenja = new ArrayList<Object>();
		try {
			allResenja = this.existManager.loadCollection(collectionId);
			for (String id : resenjaIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					resenja.add(resource.getContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allResenja != null) {
				try {
					allResenja.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return resenja;
	}
	
	public Object findById(String documentId) {
		try {
			XMLResource resource = this.existManager.load(collectionId, documentId);
			if (resource == null) {
				return null;
			} else {
				Object loaded = resource.getContent();
				return loaded;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String findByIdRaw(String documentId) {
		try {
			XMLResource resource = this.existManager.loadRaw(collectionId, documentId);
			if (resource == null) {
				return null;
			} else {
				return resource.getContent().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Object> findAllByContent(String content) {
		String xPath = "//*[contains(., '" + content + "')]";
		List<Object> pronadjenaResenja = new ArrayList<Object>();
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			ResourceIterator iter = query.getIterator();
			XMLResource res;
			while(iter.hasMoreResources()) {
				res = (XMLResource)iter.nextResource();
				try {
					Element e = (Element)res.getContentAsDOM();
					if (e.getTagName() == "res:Resenje") {
						pronadjenaResenja.add(res.getContent());
					}
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pronadjenaResenja;
	}
	
	public List<Node> findAllNapredna(List<String> resenjaIds) {
		Collection allResenja = null;
		List<Node> resenja = new ArrayList<Node>();
		try {
			allResenja = this.existManager.loadCollection(collectionId);
			for (String id : resenjaIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					resenja.add(resource.getContentAsDOM());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allResenja != null) {
				try {
					allResenja.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return resenja;
	}
	
	public String save(Document resenjeDoc, String zahtevId, String zalbaId, String userEmail) {
		StringWriter sw = new StringWriter();
		try {
			String documentId = UUID.randomUUID().toString();
			NodeList list = resenjeDoc.getElementsByTagNameNS("*", "Resenje");
			Element resenje = (Element)list.item(0);
			resenje.setAttribute("id", TARGET_NAMESPACE + "/" + documentId);
			// vezivanje resenja i gradjanina
			resenje.setAttribute("vocab", "http://www.xml.com/predicate/");
			resenje.setAttribute("about", TARGET_NAMESPACE + "/" + documentId);
			resenje.setAttribute("rel", "pred:vezanGradjanin");
			resenje.setAttribute("href", "http://korisnik/" + userEmail);
			// vezivanje resenja i zahteva
			list = resenje.getElementsByTagNameNS("*", "Podnosenje_zahteva");
			Element podnosenjeZahteva = (Element)list.item(0);
			podnosenjeZahteva.setAttribute("vocab", "http://www.xml.com/predicate/");
			podnosenjeZahteva.setAttribute("about", TARGET_NAMESPACE + "/" + documentId);
			podnosenjeZahteva.setAttribute("rel", "pred:vezanZahtev");
			podnosenjeZahteva.setAttribute("href", "http://zahtev/" + zahtevId);
			// vezivanje resenja i zalbe
			list = resenje.getElementsByTagNameNS("*", "Podnosenje_zalbe");
			Element podnosenjeZalbe = (Element)list.item(0);
			podnosenjeZalbe.setAttribute("vocab", "http://www.xml.com/predicate/");
			podnosenjeZalbe.setAttribute("about", TARGET_NAMESPACE + "/" + documentId);
			podnosenjeZalbe.setAttribute("rel", "pred:vezanaZalba");
			podnosenjeZalbe.setAttribute("href", zalbaId);
			// vezivanje ostalih metapodataka
			// izdavac resenja ime i prezime
			list = resenje.getElementsByTagNameNS("*", "Ime");
			Element poverenikIme = (Element)list.item(0);
			poverenikIme.setAttribute("property", "pred:izdavacIme");
			list = resenje.getElementsByTagNameNS("*", "Prezime");
			Element poverenikPrezime = (Element)list.item(0);
			poverenikPrezime.setAttribute("property", "pred:izdavacPrezime");
			// zalilac ime i prezime
			list = resenje.getElementsByTagNameNS("*", "Ime_zalilac");
			Element zalilacIme = (Element)list.item(0);
			if (zalilacIme != null) {
				zalilacIme.setAttribute("property", "pred:zalilacIme");
			}
			list = resenje.getElementsByTagNameNS("*", "Prezime_zalilac");
			Element zalilacPrezime = (Element)list.item(0);
			if (zalilacPrezime != null) {
				zalilacPrezime.setAttribute("property", "pred:zalilacPrezime");
			}
			list = resenje.getElementsByTagNameNS("*", "Naziv_zalilac");
			Element zalilacNaziv = (Element)list.item(0);
			if (zalilacNaziv != null) {
				zalilacNaziv.setAttribute("property", "pred:zalilacNaziv");
			}
			// optuzeni organ vlasti naziv
			list = resenje.getElementsByTagNameNS("*", "Naziv_organa_vlasti");
			Element organVlastiNaziv = (Element)list.item(0);
			if (organVlastiNaziv != null) {
				organVlastiNaziv.setAttribute("property", "pred:optuzeniNaziv");	
			}

			DOMSource source = new DOMSource(resenjeDoc);
			StreamResult result = new StreamResult(sw);
			transformer.transform(source, result);
			
			String resenjeString = sw.toString();
			this.existManager.storeFromText(collectionId, documentId, resenjeString);
			return resenjeString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
