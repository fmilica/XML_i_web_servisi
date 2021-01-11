package com.xml.portal.poverenik.business;

import com.xml.portal.poverenik.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;

public class ZalbaCutanjeBusiness {
	
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
}
