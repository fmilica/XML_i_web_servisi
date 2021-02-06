package com.xml.portal.poverenik.data.repository;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.portal.poverenik.data.dao.izvestaj.Izvestaj;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.xmldb.api.ExistManager;

@Repository
public class IzvestajRepository {

	private String collectionId = "/db/poverenik/Izvestaj";
	
	private static final String TARGET_NAMESPACE = "http://izvestaj";
	
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
	
	public IzvestajRepository() {
		try {
			IzvestajRepository.context = JAXBContext.newInstance(Izvestaj.class);
			IzvestajRepository.unmarshaller = context.createUnmarshaller();
			IzvestajRepository.marshaller = context.createMarshaller();
			IzvestajRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			IzvestajRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public List<Izvestaj> findAll() {
		Collection allIzvestaji = null;
		List<Izvestaj> izvestaji = new ArrayList<Izvestaj>();
		try {
			allIzvestaji = this.existManager.loadCollection(collectionId);
			String[] resNames = allIzvestaji.listResources();
			for (String name : resNames) {
				XMLResource resource = this.existManager.load(collectionId, name);
				if (resource != null) {
					izvestaji.add((Izvestaj) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allIzvestaji != null) {
				try {
					allIzvestaji.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return izvestaji;
	}
	
	public String save(Izvestaj izvestaj) {
		StringWriter sw = new StringWriter();
		try {
			String documentId = UUID.randomUUID().toString();
			izvestaj.setId(TARGET_NAMESPACE + "/" + documentId);
			marshaller.marshal(izvestaj, sw);
			String obavestenjeString = sw.toString();
			this.existManager.storeFromText(collectionId, documentId, obavestenjeString);
			return documentId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Izvestaj findById(String documentId) {
		try {
			XMLResource resource = this.existManager.load(collectionId, documentId);
			if (resource == null) {
				return null;
			} else {
				Izvestaj loaded = (Izvestaj) unmarshaller.unmarshal(resource.getContentAsDOM());
				return loaded;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Izvestaj> findAllByDatumContaining(String content) {
		String xPath = "/Izvestaj[contains(@datum_podnosenja_izvestaja,'" + content + "')]";
		List<Izvestaj> pronadjeniIzvestaji = new ArrayList<Izvestaj>();
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			ResourceIterator iter = query.getIterator();
			XMLResource res;
			while(iter.hasMoreResources()) {
				res = (XMLResource)iter.nextResource();
				try {
					pronadjeniIzvestaji.add((Izvestaj) unmarshaller.unmarshal(res.getContentAsDOM()));
				} catch (ClassCastException | UnmarshalException e) {
					// elementi ispod zahteva koji zadovoljavaju xpath
					continue;
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pronadjeniIzvestaji;
	}
}
