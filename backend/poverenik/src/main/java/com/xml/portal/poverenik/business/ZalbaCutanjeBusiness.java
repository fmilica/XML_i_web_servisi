package com.xml.portal.poverenik.business;

import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;
import com.xml.portal.poverenik.transformer.DokumentiTransformer;

public class ZalbaCutanjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zalba_cutanje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zalba_cutanje_fo.xsl";
	
	public ZalbaCutanje getById(String id) {
		Object ret = null;
		try {
			ret = RetrieveXML.retrieve(ZalbaCutanje.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (ZalbaCutanje)ret;
		}
		return null;
	}
	
	public ZalbaCutanje create(ZalbaCutanje zalbaCutanje) {
		Object ret = null;
		try {
			ret = StoreXML.store(zalbaCutanje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (ZalbaCutanje)ret;
		}
		return null;
	}
	
	public boolean storeMetadata(ZalbaCutanje zalbaCutanje) {
		try {
			StoreMetadata.store(zalbaCutanje);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String generateHTML(String id) throws Exception {
		DokumentiTransformer transformer = null;

		try {
			transformer = new DokumentiTransformer();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Object zahtev = RetrieveXML.retrieveRaw(ZalbaCutanje.class, id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/zalba_cutanje_" + id + ".html";

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
		
		Object zahtev = RetrieveXML.retrieveRaw(ZalbaCutanje.class, id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/zalba_cutanje_" + id + ".pdf";

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
