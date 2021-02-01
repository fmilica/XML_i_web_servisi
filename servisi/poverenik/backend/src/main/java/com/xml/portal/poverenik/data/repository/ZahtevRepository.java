package com.xml.portal.poverenik.data.repository;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.xmldb.api.ExistManager;

@Repository
public class ZahtevRepository {

	private String collectionId = "/db/poverenik/Zahtev";
	
	private static final String TARGET_NAMESPACE = "http://zahtev";
	
	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";
	
	private static JAXBContext context;
	private static Unmarshaller unmarshaller;
	private static Marshaller marshaller;
	
	@Autowired
	private ExistManager existManager;
	
	public ZahtevRepository() {
		try {
			ZahtevRepository.context = JAXBContext.newInstance(Zahtev.class);
			ZahtevRepository.unmarshaller = context.createUnmarshaller();
			ZahtevRepository.marshaller = context.createMarshaller();
			ZahtevRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ZahtevRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public List<Zahtev> findAll() {
		Collection allZahtevi = null;
		List<Zahtev> zahtevi = new ArrayList<Zahtev>();
		try {
			allZahtevi = this.existManager.loadCollection(collectionId);
			String[] resNames = allZahtevi.listResources();
			for (String name : resNames) {
				XMLResource resource = this.existManager.load(collectionId, name);
				if (resource != null) {
					zahtevi.add((Zahtev) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZahtevi != null) {
				try {
					allZahtevi.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zahtevi;
	}
	
	public List<Zahtev> findAllByGradjanin(List<String> zahtevIds) {
		Collection allZahtevi = null;
		List<Zahtev> zahtevi = new ArrayList<Zahtev>();
		try {
			allZahtevi = this.existManager.loadCollection(collectionId);
			for (String id : zahtevIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					zahtevi.add((Zahtev) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZahtevi != null) {
				try {
					allZahtevi.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zahtevi;
	}
	
	public Zahtev findById(String documentId) {
		try {
			XMLResource resource = this.existManager.load(collectionId, documentId);
			if (resource == null) {
				return null;
			} else {
				Zahtev loaded = (Zahtev) unmarshaller.unmarshal(resource.getContentAsDOM());
				return loaded;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String save(Zahtev zahtev) {
		StringWriter sw = new StringWriter();
		try {
			String documentId = UUID.randomUUID().toString();
			zahtev.setId(TARGET_NAMESPACE + "/" + documentId);
			zahtev.setAbout(TARGET_NAMESPACE + "/" + documentId);
			marshaller.marshal(zahtev, sw);
			String zahtevString = sw.toString();
			this.existManager.storeFromText(collectionId, documentId, zahtevString);
			return documentId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean update(String zahtevId, Zahtev zahtev) {
		String xPath = "/Zahtev";
		StringWriter sw = new StringWriter();
		try {
			marshaller.marshal(zahtev, sw);
			String zahtevString = sw.toString();
			this.existManager.update(collectionId, zahtevId, xPath, zahtevString, UPDATE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
