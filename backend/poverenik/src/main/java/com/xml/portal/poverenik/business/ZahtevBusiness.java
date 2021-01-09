package com.xml.portal.poverenik.business;

import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.xmldb.api.RetrieveXML;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;

public class ZahtevBusiness {

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
	
}
