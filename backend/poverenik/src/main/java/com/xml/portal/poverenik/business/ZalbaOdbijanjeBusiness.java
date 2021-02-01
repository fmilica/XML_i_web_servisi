package com.xml.portal.poverenik.business;

import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;
import com.xml.portal.poverenik.transformer.DokumentiTransformer;

public class ZalbaOdbijanjeBusiness {
	
	public static final String XSL_FILE = "src/main/resources/data/xsl/zalba_odbijanje.xsl";
	
	public static final String XSL_FO_FILE = "src/main/resources/data/xsl/zalba_odbijanje_fo.xsl";
	
	public ZalbaOdbijanje getById(String id) {
		Object ret = null;
		try {
			ret = RetrieveXML.retrieve(ZalbaOdbijanje.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (ZalbaOdbijanje)ret;
		}
		return null;
	}
	
	public ZalbaOdbijanje create(ZalbaOdbijanje zalbaOdbijanje) {
		Object ret = null;
		try {
			ret = StoreXML.store(zalbaOdbijanje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (ZalbaOdbijanje)ret;
		}
		return null;
	}
	
	public boolean storeMetadata(ZalbaOdbijanje zalbaOdbijanje) {
		try {
			StoreMetadata.store(zalbaOdbijanje);
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
		
		Object zahtev = RetrieveXML.retrieveRaw(ZalbaOdbijanje.class, id);

		String ok = "";
		String htmlPath = "src/main/resources/data/html/zalba_odbijanje_" + id + ".html";

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
		
		Object zahtev = RetrieveXML.retrieveRaw(ZalbaOdbijanje.class, id);

		String ok = "";
		String pdfPath = "src/main/resources/data/pdf/zalba_odbijanje_" + id + ".pdf";

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
