package com.xml.portal.poverenik.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xml.portal.poverenik.data.dao.resenje.DOMParser;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;
import com.xml.portal.poverenik.transformer.DokumentiTransformer;

public class ResenjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/resenje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/resenje_fo.xsl";
	
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
	
	public String generateHTML(String id) throws Exception {
		DokumentiTransformer transformer = null;

		try {
			transformer = new DokumentiTransformer();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Object zahtev = RetrieveXML.retrieveRaw(null, id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/resenje_" + id + ".html";

		try {
			ok = transformer.generateHTML(zahtev.toString(), htmlPath, XSL_FILE);
			if (ok.length()>0)
				return htmlPath;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String generatePDF(String id) throws Exception {
		DokumentiTransformer transformer = null;

		try {
			transformer = new DokumentiTransformer();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Object zahtev = RetrieveXML.retrieveRaw(null, id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/resenje_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(zahtev.toString(), pdfPath, XSL_FO_FILE);
			if (ok.length()>0)
				return pdfPath;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
