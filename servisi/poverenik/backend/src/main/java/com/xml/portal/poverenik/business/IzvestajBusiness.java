package com.xml.portal.poverenik.business;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.izvestaj.Izvestaj;
import com.xml.portal.poverenik.data.dao.izvestaj.ListaIzvestaj;
import com.xml.portal.poverenik.data.dao.izvestaj.ObjectFactory;
import com.xml.portal.poverenik.data.dao.izvestaj.Zahtevi;
import com.xml.portal.poverenik.data.dao.izvestaj.ZahteviStat;
import com.xml.portal.poverenik.data.dao.izvestaj.Zalbe;
import com.xml.portal.poverenik.data.dao.izvestaj.ZalbeStat;
import com.xml.portal.poverenik.data.dao.obavestenje.ListaObavestenja;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.repository.IzvestajRepository;
import com.xml.portal.poverenik.data.repository.ZahtevRepository;
import com.xml.portal.poverenik.data.repository.ZalbaCutanjeRepository;
import com.xml.portal.poverenik.data.repository.ZalbaOdbijanjeRepository;

public class IzvestajBusiness {
	
	@Autowired
	private IzvestajRepository izvestajRepository;
	
	@Autowired
	private ZahtevRepository zahtevRepository;
	
	@Autowired
	private ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	@Autowired
	private ZalbaOdbijanjeRepository zalbaOdbijanjeRepository;
	
	public Izvestaj generisiIzvestaj() {
		Izvestaj izvestaj = new ObjectFactory().createIzvestaj();
		ZahteviStat zs = new ZahteviStat();
		Zahtevi z = new Zahtevi();
		z.setZahteviUkupno(new ZahteviStat());
		z.setGradjaninZahtevi(new ZahteviStat());
		z.setOrganizacijaUkupno(new ZahteviStat());
		izvestaj.setZahtevi(z);
		Zalbe za = new Zalbe();
		za.setGradjaninZalbe(new ZalbeStat());
		za.setOrganizacijaZalbe(new ZalbeStat());
		za.setZalbeUkupno(new ZalbeStat());
		izvestaj.setZalbe(za);
		
		izvestaj.getZahtevi().getZahteviUkupno().setUkupnoZahteva((int)zahtevRepository.findAllByYear());
		izvestaj.getZahtevi().getZahteviUkupno().setUkupnoNerazresenihZahteva((int)zahtevRepository.findAllByYearAndNeazreseni());
		izvestaj.getZahtevi().getZahteviUkupno().setUkupnoOdbijenihZahtevi((int)zahtevRepository.findAllByYearAndOdbijeni());
		izvestaj.getZahtevi().getZahteviUkupno().setUkupnoPrihvacenihZahteva((int)zahtevRepository.findAllByYearAndPrihvaceni());
		
		izvestaj.getZahtevi().getGradjaninZahtevi().setUkupnoZahteva((int)zahtevRepository.findAllByYearGradjanin());
		izvestaj.getZahtevi().getGradjaninZahtevi().setUkupnoNerazresenihZahteva((int)zahtevRepository.findAllByYearGradjaninNerazreseni());
		izvestaj.getZahtevi().getGradjaninZahtevi().setUkupnoPrihvacenihZahteva((int)zahtevRepository.findAllByYearGradjaninPrihvaceni());
		izvestaj.getZahtevi().getGradjaninZahtevi().setUkupnoOdbijenihZahtevi((int)zahtevRepository.findAllByYearGradjaninOdbijeni());
		
		izvestaj.getZahtevi().getOrganizacijaUkupno().setUkupnoZahteva((int)zahtevRepository.findAllByYearOrganizacija());
		izvestaj.getZahtevi().getOrganizacijaUkupno().setUkupnoNerazresenihZahteva((int)zahtevRepository.findAllByYearOrganizacijaNerazreseni());
		izvestaj.getZahtevi().getOrganizacijaUkupno().setUkupnoPrihvacenihZahteva((int)zahtevRepository.findAllByYearOrganizacijaPrihvaceni());
		izvestaj.getZahtevi().getOrganizacijaUkupno().setUkupnoOdbijenihZahtevi((int)zahtevRepository.findAllByYearOrganizacijaOdbijeni());
		
		int ukupnoZalbi = (int)zalbaCutanjeRepository.findAllByYear() + (int)zalbaOdbijanjeRepository.findAllByYear();
		izvestaj.getZalbe().getZalbeUkupno().setUkupnoZalbi(ukupnoZalbi);
		izvestaj.getZalbe().getZalbeUkupno().setUkupnoZbogNepostupanja((int)zalbaCutanjeRepository.findAllByYear());
		izvestaj.getZalbe().getZalbeUkupno().setUkupnoZbogOdbijanja((int)zalbaOdbijanjeRepository.findAllByYear());
		
		int ukupnoZalbiGradjanin = (int)zalbaCutanjeRepository.findAllByYearGradjanin() + (int)zalbaOdbijanjeRepository.findAllByYearGradjanin();
		izvestaj.getZalbe().getGradjaninZalbe().setUkupnoZalbi(ukupnoZalbiGradjanin);
		izvestaj.getZalbe().getGradjaninZalbe().setUkupnoZbogNepostupanja((int)zalbaCutanjeRepository.findAllByYearGradjanin());
		izvestaj.getZalbe().getGradjaninZalbe().setUkupnoZbogOdbijanja((int)zalbaOdbijanjeRepository.findAllByYearGradjanin());
		
		int ukupnoZalbiOrganizacija = (int)zalbaCutanjeRepository.findAllByYearOrganizacija() + (int)zalbaOdbijanjeRepository.findAllByYearOrganizacija();
		izvestaj.getZalbe().getOrganizacijaZalbe().setUkupnoZalbi(ukupnoZalbiOrganizacija);
		izvestaj.getZalbe().getOrganizacijaZalbe().setUkupnoZbogNepostupanja((int)zalbaCutanjeRepository.findAllByYearOrganizacija());
		izvestaj.getZalbe().getOrganizacijaZalbe().setUkupnoZbogOdbijanja((int)zalbaOdbijanjeRepository.findAllByYearOrganizacija());
		
		try{
            Calendar createDate = Calendar.getInstance();
            Date cDate = createDate.getTime();
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(cDate);
            XMLGregorianCalendar currentDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            izvestaj.setDatumPodnosenjaIzvestaja(currentDate);
         }catch(Exception e){
            System.out.println("nista");
         }
		
		this.izvestajRepository.save(izvestaj);
		
		//System.out.println(izvestaj);
		return izvestaj;
	}
	
	public ListaIzvestaj getAll() {
		ListaIzvestaj izvestaji = new ListaIzvestaj();
		izvestaji.setIzvestaj(izvestajRepository.findAll());;
		return izvestaji;
	}
	
	public Izvestaj getById(String id) {
		Izvestaj loaded = null;
		try {
			loaded = izvestajRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loaded;
	}

	public String saveToDB(Izvestaj izvestaj) {
		String documentId = izvestajRepository.save(izvestaj);
		return documentId;
	}
	
	public ListaIzvestaj getAllByContent(String content) {
		ListaIzvestaj filtriraniIzvestaji = new ListaIzvestaj();
		filtriraniIzvestaji.setIzvestaj(izvestajRepository.findAllByDatumContaining(content));
		return filtriraniIzvestaji;
	}
}
