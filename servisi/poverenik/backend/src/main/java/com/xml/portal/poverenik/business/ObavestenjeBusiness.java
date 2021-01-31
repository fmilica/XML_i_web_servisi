package com.xml.portal.poverenik.business;

import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.metadatadb.api.StoreMetadata;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;

public class ObavestenjeBusiness {
	
	public Obavestenje getById(String id) {
		Object ret = null;
		try {
			ret = RetrieveXML.retrieve(Obavestenje.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (Obavestenje)ret;
		}
		return null;
	}
	
	public Obavestenje create(Obavestenje obavestenje) {
		Object ret = null;
		try {
			ret = StoreXML.store(obavestenje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret != null) {
			return (Obavestenje)ret;
		}
		return null;
	}
	
	public boolean storeMetadata(Obavestenje obavestenje) {
		try {
			StoreMetadata.store(obavestenje);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
