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

import com.ximpleware.extended.xpath.sym;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.xmldb.api.ExistManager;

@Repository
public class ZahtevRepository {

	private String collectionId = "/db/poverenik/Zahtev";
	
	private static final String TARGET_PREFIX = "zahtev";
	private static final String TARGET_NAMESPACE = "http://zahtev";
	
	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns:=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns:"+TARGET_PREFIX+"=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
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
	
	
	//ukupni zahtevi za godinu
	public long findAllByYearAndPrihvaceni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		//String xPath = "/Zahtev/contains(@datum, '2020')"; -> vraca true false
		// -> zato je uvek vracao sve, vracao je dve false vrednosti
		String xPath = "/Zahtev[contains(@datum,'" + year + "') and @razresen='true' and @odbijen='false']";
		// vraca zahtev ceo ako zadovoljava uslov u uglastim
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public long findAllByYearAndOdbijeni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		//String xPath = "/Zahtev/contains(@datum, '2020')"; -> vraca true false
		// -> zato je uvek vracao sve, vracao je dve false vrednosti
		String xPath = "/Zahtev[contains(@datum,'" + year + "') and @razresen='true' and @odbijen='true']";
		// vraca zahtev ceo ako zadovoljava uslov u uglastim
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public long findAllByYearAndNeazreseni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		//String xPath = "/Zahtev/contains(@datum, '2020')"; -> vraca true false
		// -> zato je uvek vracao sve, vracao je dve false vrednosti
		String xPath = "/Zahtev[contains(@datum,'" + year + "') and @razresen='false']";
		// vraca zahtev ceo ako zadovoljava uslov u uglastim
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Zahtev> findAllByContent(String content) {
		String xPath = "//*[contains(., '" + content + "')]";
		List<Zahtev> pronadjeniZahtevi = new ArrayList<Zahtev>();
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			ResourceIterator iter = query.getIterator();
			XMLResource res;
			while(iter.hasMoreResources()) {
				res = (XMLResource)iter.nextResource();
				try {
					pronadjeniZahtevi.add((Zahtev) unmarshaller.unmarshal(res.getContentAsDOM()));
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
		return pronadjeniZahtevi;
	}
	
	public List<Zahtev> findAllNapredna(List<String> zahtevIds) {
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

	public long findAllByYear() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/Zahtev[contains(@datum,'" + year + "')]";
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return query.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//ukupni zahtevi za godinu
	
	
	//ukupni zahtevi za gradjanina
	public long findAllByYearGradjanin() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Ime) and contains(@datum,'"+ year +"')]";
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
	
	public long findAllByYearGradjaninNerazreseni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Ime) "
				+ "and @razresen='false'"
				+ "and contains(@datum,'"+ year +"')]";
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
	
	public long findAllByYearGradjaninPrihvaceni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Ime) "
				+ "and @razresen='true'"
				+ "and @odbijen='false'"
				+ "and contains(@datum,'"+ year +"')]";
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
	
	public long findAllByYearGradjaninOdbijeni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Ime) "
				+ "and @razresen='true'"
				+ "and @odbijen='true'"
				+ "and contains(@datum,'"+ year +"')]";
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
	//ukupni zahtevi za gradjanina
	
	
	//ukupni zahtevi za organizaciju
	public long findAllByYearOrganizacija() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Naziv) and contains(@datum,'"+ year +"')]";
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
	
	public long findAllByYearOrganizacijaNerazreseni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Naziv) "
				+ "and @razresen='false'"
				+ "and contains(@datum,'"+ year +"')]";
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
	
	public long findAllByYearOrganizacijaPrihvaceni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Naziv) "
				+ "and @razresen='true'"
				+ "and @odbijen='false'"
				+ "and contains(@datum,'"+ year +"')]";
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
	
	public long findAllByYearOrganizacijaOdbijeni() {
		String year = (Calendar.getInstance().get(Calendar.YEAR))+"";
		String xPath = "/zahtev:Zahtev[boolean(/zahtev:Zahtev/zahtev:Trazilac/tipovi:Naziv) "
				+ "and @razresen='true'"
				+ "and @odbijen='true'"
				+ "and contains(@datum,'"+ year +"')]";
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
	//ukupni zahtevi za organizaciju

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
		String xPath = "/zahtev:Zahtev/@razresen";
		try {
			this.existManager.update(collectionId, zahtevId, xPath, "true", UPDATE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
