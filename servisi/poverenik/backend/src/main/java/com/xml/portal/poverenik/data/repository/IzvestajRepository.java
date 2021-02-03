package com.xml.portal.poverenik.data.repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
			IzvestajRepository.context = JAXBContext.newInstance(Obavestenje.class);
			IzvestajRepository.unmarshaller = context.createUnmarshaller();
			IzvestajRepository.marshaller = context.createMarshaller();
			IzvestajRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			IzvestajRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
