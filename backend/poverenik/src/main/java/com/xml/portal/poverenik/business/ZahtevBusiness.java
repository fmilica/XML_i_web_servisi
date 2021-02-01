package com.xml.portal.poverenik.business;

import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;
import com.xml.portal.poverenik.transformer.DokumentiTransformer;

public class ZahtevBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zahtev.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zahtev_fo.xsl";

	public Zahtev getById(String id) {
		Object ret = null;
		try {
			ret = RetrieveXML.retrieve(Zahtev.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (Zahtev)ret;
		}
		return null;
	}
	
	public Zahtev create(Zahtev zahtev) {
		Object ret = null;
		try {
			ret = StoreXML.store(zahtev);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (Zahtev)ret;
		}
		return null;
	}
	
	public boolean storeMetadata(Zahtev zahtev) {
		try {
			StoreMetadata.store(zahtev);
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
		
		Object zahtev = RetrieveXML.retrieveRaw(Zahtev.class, id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/zahtev_" + id + ".html";

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
		
		Object zahtev = RetrieveXML.retrieveRaw(Zahtev.class, id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/zahtev_" + id + ".pdf";

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
