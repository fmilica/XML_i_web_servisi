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

import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.xmldb.api.ExistManager;

@Repository
public class ObavestenjeRepository {

	private String collectionId = "/db/poverenik/Obavestenje";
	
	private static final String TARGET_NAMESPACE = "http://obavestenje";
	
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
	
	public ObavestenjeRepository() {
		try {
			ObavestenjeRepository.context = JAXBContext.newInstance(Obavestenje.class);
			ObavestenjeRepository.unmarshaller = context.createUnmarshaller();
			ObavestenjeRepository.marshaller = context.createMarshaller();
			ObavestenjeRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ObavestenjeRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public List<Obavestenje> findAll() {
		Collection allObavestenja = null;
		List<Obavestenje> obavestenja = new ArrayList<Obavestenje>();
		try {
			allObavestenja = this.existManager.loadCollection(collectionId);
			String[] resNames = allObavestenja.listResources();
			for (String name : resNames) {
				XMLResource resource = this.existManager.load(collectionId, name);
				if (resource != null) {
					obavestenja.add((Obavestenje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allObavestenja != null) {
				try {
					allObavestenja.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return obavestenja;
	}
	
	public List<Obavestenje> findAllByGradjanin(List<String> obavestenjeIds) {
		Collection allObavestenja = null;
		List<Obavestenje> obavestenja = new ArrayList<Obavestenje>();
		try {
			allObavestenja = this.existManager.loadCollection(collectionId);
			for (String id : obavestenjeIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					obavestenja.add((Obavestenje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allObavestenja != null) {
				try {
					allObavestenja.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return obavestenja;
	}
	
	public Obavestenje findById(String documentId) {
		try {
			XMLResource resource = this.existManager.load(collectionId, documentId);
			if (resource == null) {
				return null;
			} else {
				Obavestenje loaded = (Obavestenje) unmarshaller.unmarshal(resource.getContentAsDOM());
				return loaded;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String save(Obavestenje obavestenje) {
		StringWriter sw = new StringWriter();
		try {
			String documentId = UUID.randomUUID().toString();
			obavestenje.setId(TARGET_NAMESPACE + "/" + documentId);
			obavestenje.setAbout(TARGET_NAMESPACE + "/" + documentId);
			marshaller.marshal(obavestenje, sw);
			String obavestenjeString = sw.toString();
			this.existManager.storeFromText(collectionId, documentId, obavestenjeString);
			return documentId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
