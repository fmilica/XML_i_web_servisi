package com.xml.portal.poverenik.data.repository;

import java.io.StringWriter;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.portal.poverenik.data.dao.odgovor.Odgovor;
import com.xml.portal.poverenik.data.xmldb.api.ExistManager;

@Repository
public class IzjasnjenjeRepository {

	private String collectionId = "/db/poverenik/Odgovor";
	
	private static final String TARGET_NAMESPACE = "http://odgovor";
	
	private static JAXBContext context;
	private static Unmarshaller unmarshaller;
	private static Marshaller marshaller;
	
	@Autowired
	private ExistManager existManager;
	
	public IzjasnjenjeRepository() {
		try {
			IzjasnjenjeRepository.context = JAXBContext.newInstance(Odgovor.class);
			IzjasnjenjeRepository.unmarshaller = context.createUnmarshaller();
			IzjasnjenjeRepository.marshaller = context.createMarshaller();
			IzjasnjenjeRepository.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			IzjasnjenjeRepository.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Odgovor findById(String zalbaId) {
		String xPath = "/odg:Odgovor[@id_zalbe='" + zalbaId + "']";
		Odgovor odgovor = null;
		try {
			ResourceSet query = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			ResourceIterator iter = query.getIterator();
			XMLResource res;
			while(iter.hasMoreResources()) {
				res = (XMLResource)iter.nextResource();
				try {
					odgovor = (Odgovor) unmarshaller.unmarshal(res.getContentAsDOM());
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
		return odgovor;
	}
	
	public String save(Odgovor odgovor) {
		StringWriter sw = new StringWriter();
		try {
			String documentId = UUID.randomUUID().toString();
			odgovor.setId(TARGET_NAMESPACE + "/" + documentId);
			marshaller.marshal(odgovor, sw);
			String odgovorString = sw.toString();
			this.existManager.storeFromText(collectionId, documentId, odgovorString);
			return documentId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
