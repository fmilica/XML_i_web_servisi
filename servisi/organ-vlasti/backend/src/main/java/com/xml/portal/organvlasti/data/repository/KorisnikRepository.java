package com.xml.portal.organvlasti.data.repository;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;

import com.xml.portal.organvlasti.data.dao.korisnik.Korisnik;
import com.xml.portal.organvlasti.data.dao.korisnik.ListaKorisnika;
import com.xml.portal.organvlasti.data.xmldb.api.ExistManager;

@Repository
public class KorisnikRepository {

	private String collectionId = "/db/organvlasti/Korisnik";
	private String documentId = "korisnici1.xml";
	
	private static final String TARGET_NAMESPACE = "http://korisnik";
	
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
	
	public KorisnikRepository() {
		try {
			KorisnikRepository.context = JAXBContext.newInstance(ListaKorisnika.class);
			KorisnikRepository.unmarshaller = context.createUnmarshaller();
			KorisnikRepository.marshaller = context.createMarshaller();
			KorisnikRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			KorisnikRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Korisnik findByEmail(String email) {
		try {
			XMLResource resource = this.existManager.load(collectionId, documentId);
			ListaKorisnika loaded = (ListaKorisnika) unmarshaller.unmarshal(resource.getContentAsDOM());
			for (Korisnik k : loaded.getKorisnik()) {
				if (k.getEmail().equals(email)) {
					return k;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean save(Korisnik korisnik) {
		StringWriter sw = new StringWriter();
		try {
			marshaller.marshal(korisnik, sw);
			String korisnikString = sw.toString();
			this.existManager.append(collectionId, documentId, "/Lista_korisnika", korisnikString, APPEND);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
