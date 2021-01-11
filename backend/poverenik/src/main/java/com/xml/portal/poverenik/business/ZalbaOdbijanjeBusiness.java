package com.xml.portal.poverenik.business;

import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;

public class ZalbaOdbijanjeBusiness {
	
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
	
}
