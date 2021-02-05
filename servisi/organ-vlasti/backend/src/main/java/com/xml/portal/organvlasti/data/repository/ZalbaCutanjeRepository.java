package com.xml.portal.organvlasti.data.repository;

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

import com.xml.portal.organvlasti.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.organvlasti.data.xmldb.api.ExistManager;

@Repository
public class ZalbaCutanjeRepository {
	
	private String collectionId = "/db/organvlasti/ZalbaCutanje";
	
	private static final String TARGET_NAMESPACE = "http://zalbacutanje";
	
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
	
	public ZalbaCutanjeRepository() {
		try {
			ZalbaCutanjeRepository.context = JAXBContext.newInstance(ZalbaCutanje.class);
			ZalbaCutanjeRepository.unmarshaller = context.createUnmarshaller();
			ZalbaCutanjeRepository.marshaller = context.createMarshaller();
			ZalbaCutanjeRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ZalbaCutanjeRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public List<ZalbaCutanje> findAll() {
		Collection allZalbeCutanja = null;
		List<ZalbaCutanje> zalbeCutanja = new ArrayList<ZalbaCutanje>();
		try {
			allZalbeCutanja = this.existManager.loadCollection(collectionId);
			String[] resNames = allZalbeCutanja.listResources();
			for (String name : resNames) {
				XMLResource resource = this.existManager.load(collectionId, name);
				if (resource != null) {
					zalbeCutanja.add((ZalbaCutanje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZalbeCutanja != null) {
				try {
					allZalbeCutanja.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zalbeCutanja;
	}
	
	public List<ZalbaCutanje> findAllByGradjanin(List<String> zalbeCutanjaIds) {
		Collection allZalbeCutanja = null;
		List<ZalbaCutanje> zalbeCutanja = new ArrayList<ZalbaCutanje>();
		try {
			allZalbeCutanja = this.existManager.loadCollection(collectionId);
			for (String id : zalbeCutanjaIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					zalbeCutanja.add((ZalbaCutanje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZalbeCutanja != null) {
				try {
					allZalbeCutanja.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zalbeCutanja;
	}
	
	public ZalbaCutanje findById(String documentId) {
		try {
			XMLResource resource = this.existManager.load(collectionId, documentId);
			if (resource == null) {
				return null;
			} else {
				ZalbaCutanje loaded = (ZalbaCutanje) unmarshaller.unmarshal(resource.getContentAsDOM());
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
	
	public List<ZalbaCutanje> findAllByContent(String content) {
		String xPath = "//*[contains(., '" + content + "')]";
		List<ZalbaCutanje> pronadjeneZalbe = new ArrayList<ZalbaCutanje>();
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			ResourceIterator iter = query.getIterator();
			XMLResource res;
			while(iter.hasMoreResources()) {
				res = (XMLResource)iter.nextResource();
				try {
					pronadjeneZalbe.add((ZalbaCutanje) unmarshaller.unmarshal(res.getContentAsDOM()));
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
	
	public List<ZalbaCutanje> findAllNapredna(List<String> zalbeCutanjeIds) {
		Collection allZalbeCutanje = null;
		List<ZalbaCutanje> zalbeCutanje = new ArrayList<ZalbaCutanje>();
		try {
			allZalbeCutanje = this.existManager.loadCollection(collectionId);
			for (String id : zalbeCutanjeIds) {
				XMLResource resource = this.existManager.load(collectionId, id);
				if (resource != null) {
					zalbeCutanje.add((ZalbaCutanje) unmarshaller.unmarshal(resource.getContentAsDOM()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allZalbeCutanje != null) {
				try {
					allZalbeCutanje.close();
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zalbeCutanje;
	}
	
	public String save(ZalbaCutanje zalbaCutanje) {
		StringWriter sw = new StringWriter();
		try {
			String documentId = UUID.randomUUID().toString();
			zalbaCutanje.setId(TARGET_NAMESPACE + "/" + documentId);
			zalbaCutanje.setAbout(TARGET_NAMESPACE + "/" + documentId);
			zalbaCutanje.getZalba().getPodnosilacZalbe().setAbout(TARGET_NAMESPACE + "/" + documentId);
			marshaller.marshal(zalbaCutanje, sw);
			String zalbaCutanjeString = sw.toString();
			this.existManager.storeFromText(collectionId, documentId, zalbaCutanjeString);
			return documentId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//zalbe na cutanje po godini
	public long findAllByYear() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		//String xPath = "/Zahtev/contains(@datum, '2020')"; -> vraca true false
		// -> zato je uvek vracao sve, vracao je dve false vrednosti
		String xPath = "/Zalba_cutanje[contains(@datum_podnosenja_zalbe,'" + year + "')]";
		// vraca zahtev ceo ako zadovoljava uslov u uglastim
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//zalbe na cutanje po godini
	
	
	//zalbe na cutanje po godini gradjanin
	public long findAllByYearGradjanin() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zalbacutanje:Zalba_cutanje[boolean(/zalbacutanje:Zalba_cutanje/zalbacutanje:Zalba/zalbacutanje:Podnosilac_zalbe/tipovi:Ime) "
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
	//zalbe na cutanje po godini gradjanin
	
	
	//zalbe na cutanje po godini organizacija
	public long findAllByYearOrganizacija() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zalbacutanje:Zalba_cutanje[boolean(/zalbacutanje:Zalba_cutanje/zalbacutanje:Zalba/zalbacutanje:Podnosilac_zalbe/tipovi:Naziv) "
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
	//zalbe na cutanje po godini organizacija
}
