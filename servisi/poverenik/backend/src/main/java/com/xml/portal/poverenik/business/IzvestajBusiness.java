package com.xml.portal.poverenik.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.izvestaj.Izvestaj;
import com.xml.portal.poverenik.data.dao.izvestaj.ObjectFactory;
import com.xml.portal.poverenik.data.dao.izvestaj.Zahtevi;
import com.xml.portal.poverenik.data.dao.izvestaj.ZahteviStat;
import com.xml.portal.poverenik.data.repository.IzvestajRepository;
import com.xml.portal.poverenik.data.repository.ZahtevRepository;

public class IzvestajBusiness {
	
	@Autowired
	private IzvestajRepository izvestajRepository;
	
	@Autowired
	private ZahtevRepository zahtevRepository;
	
	public void generisiIzvestaj() {
		Izvestaj izvestaj = new ObjectFactory().createIzvestaj();
		ZahteviStat zs = new ZahteviStat();
		Zahtevi z = new Zahtevi();
		z.setZahteviUkupno(zs);
		izvestaj.setZahtevi(z);
		
		izvestaj.getZahtevi().getZahteviUkupno().setUkupnoZahteva((int)zahtevRepository.findAllByYear());
		
		
		System.out.println(izvestaj.getZahtevi().getZahteviUkupno().getUkupnoZahteva());
	}
}
