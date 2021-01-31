package com.xml.portal.poverenik.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xml.portal.poverenik.data.dao.resenje.DOMParser;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;

public class ResenjeBusiness {
	
	public Document getById(String id) {
		Object ret = null;
		try {
			ret = RetrieveXML.retrieve(null, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return stringToDocument((String)ret);
		}
		return null;
	}
	
	public Document create(String inputXML) {
		
		Document resenje = stringToDocument(inputXML);
		
		Object ret = null;
		try {
			ret = StoreXML.store(resenje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (Document)ret;
		}
		return null;
	}
	
	public String storeMetadata(Document resenje) {
		try {
			StoreMetadata.store(resenje);
			// dobavljanje id-a
			NodeList nodeList = resenje.getChildNodes();
			Element resenjeElem = (Element)nodeList.item(0);
			String id = resenjeElem.getAttribute("id");
			return id;
		} catch (Exception e) {
			return null;
		}
	}
	
	// Helper method
	private Document stringToDocument(String xmlString) {
		FileOutputStream outResenjeTemp = null;
		try {
			outResenjeTemp = new FileOutputStream(new File("src/main/resources/data/schema/resenje/temp_resenje.xml"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			outResenjeTemp.write(xmlString.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		DOMParser parser = new DOMParser();
		parser.buildDocument("src/main/resources/data/schema/resenje/temp_resenje.xml");
		Document resenje = parser.getDocument();
		
		return resenje;
	}
}
