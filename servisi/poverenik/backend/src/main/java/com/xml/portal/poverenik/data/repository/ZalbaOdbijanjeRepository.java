package com.xml.portal.poverenik.data.repository;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;
import com.xml.portal.poverenik.data.xmldb.api.ExistManager;

@Repository
public class ZalbaOdbijanjeRepository {
	
	private String collectionId = "/db/poverenik/ZalbaOdbijanje";
	
	private static final String TARGET_NAMESPACE = "http://zalbaodbijanje";
	
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
	
	public ZalbaOdbijanjeRepository() {
		try {
			ZalbaOdbijanjeRepository.context = JAXBContext.newInstance(ZalbaOdbijanje.class);
			ZalbaOdbijanjeRepository.unmarshaller = context.createUnmarshaller();
			ZalbaOdbijanjeRepository.marshaller = context.createMarshaller();
			ZalbaOdbijanjeRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ZalbaOdbijanjeRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public List<ZalbaOdbijanje> findAll() {
		Collection allZalbeOdbijanje = null;
		List<ZalbaOdbijanje> zalbeOdbijanje = new ArrayList<ZalbaOdbijanje>();
		try {
			allZalbeOdbijanje = this.existManager.loadCollection(collectionId);
			String[] resNames = allZalbeOdbijanje.listResources();
			for (String name : resNames) {
				XMLResource resource = this.existManager.load(collectionId, name);
				if (resource != null) {
					zalbeOdbijanje.add((ZalbaOdbijanje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZalbeOdbijanje != null) {
				try {
					allZalbeOdbijanje.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zalbeOdbijanje;
	}
	
	public List<ZalbaOdbijanje> findAllByGradjanin(List<String> zalbeCutanjaIds) {
		Collection allZalbeOdbijanje = null;
		List<ZalbaOdbijanje> zalbeOdbijanje = new ArrayList<ZalbaOdbijanje>();
		try {
			allZalbeOdbijanje = this.existManager.loadCollection(collectionId);
			for (String id : zalbeCutanjaIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					zalbeOdbijanje.add((ZalbaOdbijanje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZalbeOdbijanje != null) {
				try {
					allZalbeOdbijanje.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zalbeOdbijanje;
	}
	
	public ZalbaOdbijanje findById(String documentId) {
		try {
			XMLResource resource = this.existManager.load(collectionId, documentId);
			if (resource == null) {
				return null;
			} else {
				ZalbaOdbijanje loaded = (ZalbaOdbijanje) unmarshaller.unmarshal(resource.getContentAsDOM());
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
	
	public List<ZalbaOdbijanje> findAllByContent(String content) {
		String xPath = "//*[contains(., '" + content + "')]";
		List<ZalbaOdbijanje> pronadjeneZalbe = new ArrayList<ZalbaOdbijanje>();
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			ResourceIterator iter = query.getIterator();
			XMLResource res;
			while(iter.hasMoreResources()) {
				res = (XMLResource)iter.nextResource();
				try {
					pronadjeneZalbe.add((ZalbaOdbijanje) unmarshaller.unmarshal(res.getContentAsDOM()));
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
		return pronadjeneZalbe;
	}
	
	public List<ZalbaOdbijanje> findAllNapredna(List<String> zalbeOdbijanjeIds) {
		Collection allZalbeOdbijanje = null;
		List<ZalbaOdbijanje> zalbeOdbijanje = new ArrayList<ZalbaOdbijanje>();
		try {
			allZalbeOdbijanje = this.existManager.loadCollection(collectionId);
			for (String id : zalbeOdbijanjeIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					zalbeOdbijanje.add((ZalbaOdbijanje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZalbeOdbijanje != null) {
				try {
					allZalbeOdbijanje.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zalbeOdbijanje;
	}
	
	public String save(ZalbaOdbijanje zalbaOdbijanje) {
		StringWriter sw = new StringWriter();
		try {
			String documentId = UUID.randomUUID().toString();
			zalbaOdbijanje.setId(TARGET_NAMESPACE + "/" + documentId);
			zalbaOdbijanje.setAbout(TARGET_NAMESPACE + "/" + documentId);
			zalbaOdbijanje.getPodaciOPodnosiocuZalbe().setAbout(TARGET_NAMESPACE + "/" + documentId);
			marshaller.marshal(zalbaOdbijanje, sw);
			String zalbaOdbijanjeString = sw.toString();
			this.existManager.storeFromText(collectionId, documentId, zalbaOdbijanjeString);
			return documentId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean update(String zalbaOdbijanjeId, ZalbaOdbijanje zalbaOdbijanje) {
		String xPath = "/Zalba_odbijanje/@razresen";
		try {
			this.existManager.update(collectionId, zalbaOdbijanjeId, xPath, "true", UPDATE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//zabla na odbijanje po godini
	public long findAllByYear() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/Zalba_odbijanje[contains(@datum_podnosenja_zalbe,'" + year + "')]";
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//zalba na odbijanje po godini

	
	//zalba na odbijanje po godini gradjanin
	public long findAllByYearGradjanin() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zalbaodbijanje:Zalba_odbijanje[boolean(/zalbaodbijanje:Zalba_odbijanje/zalbaodbijanje:Podaci_o_zaliocu/tipovi:Ime) "
				+ "and contains(@datum_podnosenja_zalbe,'"+ year +"')]";
		ArrayList<String> a = new ArrayList<String>();
		a.add(TARGET_NAMESPACE);
		a.add("http://tipovi");
		try {
			ResourceSet query = this.existManager.retrievePokusaj(collectionId, xPath, a);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//zalba na odbijanje po godini gradjanin
	
	
	//zalba na odbijanje po godini gradjanin
	public long findAllByYearOrganizacija() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zalbaodbijanje:Zalba_odbijanje[boolean(/zalbaodbijanje:Zalba_odbijanje/zalbaodbijanje:Podaci_o_zaliocu/tipovi:Naziv) "
				+ "and contains(@datum_podnosenja_zalbe,'"+ year +"')]";
		ArrayList<String> a = new ArrayList<String>();
		a.add(TARGET_NAMESPACE);
		a.add("http://tipovi");
		try {
			ResourceSet query = this.existManager.retrievePokusaj(collectionId, xPath, a);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//zalba na odbijanje po godini gradjanin
		
}
