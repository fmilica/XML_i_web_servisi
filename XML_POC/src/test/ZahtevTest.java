package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import util.MyValidationEventHandler;
import zahtev.NacinDostave;
import zahtev.TOrganVlasti;
import zahtev.TTrazilac;
import zahtev.TZakonskaOsnova;
import zahtev.TZakonskaOsnova.SluzbeniGlasnik;
import zahtev.TZakonskaOsnova.SluzbeniGlasnik.Brojevi;
import zahtev.TeloZahteva;
import zahtev.ZahtevZaPristupInformacijama;
import zahtev.Zahtevi;

public class ZahtevTest {
	
	public ZahtevZaPristupInformacijama unmarshall(JAXBContext context) throws JAXBException, SAXException {
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("./data/zahtev/zahtev.xsd"));
        
		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());
		
		ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) 
				unmarshaller.unmarshal(new File("./data/zahtev/zahtev_gen1.xml"));
		
		printZahtev(zahtev);
		return zahtev;
		
		
	}
	
	private void marshall(JAXBContext context, ZahtevZaPristupInformacijama zahtev) throws JAXBException, FileNotFoundException {
		Marshaller marshaller = context.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.marshal(zahtev, new FileOutputStream(new File("./data/marshalled/zahtev_marsh.xml")));
	}
	
	private void printZahtev(ZahtevZaPristupInformacijama zahtev) {
		
		System.out.println("-Mesto zahteva: " + zahtev.getMesto());
		System.out.println("-Datum zahteva: " + zahtev.getDatum());
		
		printOrganVlasti(zahtev.getOrganVlasti());
		printTeloZahteva(zahtev.getTeloZahteva());
		printTrazilac(zahtev.getTrazilac());
		
	}
	
	private void printOrganVlasti(TOrganVlasti organVlasti) {
		System.out.println("\t Podaci o organu kom se upucuje zahtev: ");
		System.out.println("\t\t- Naziv organa: " + organVlasti.getNazivOrgana() + ", sediste organa: " + organVlasti.getSedisteOrgana());
		
	}
	
	private void printTeloZahteva(TeloZahteva teloZahteva) {
		printZakonskaOsnova(teloZahteva.getZakonskaOsnova());
		printZahtevi(teloZahteva.getZahtevi());
		System.out.println("\t -Zahtevane informacije: " + teloZahteva.getZahtevaneInformacije());
	}
	
	private void printZakonskaOsnova(TZakonskaOsnova zakonskaOsnova) {
		System.out.println("\t -Na osnovu clana " + zakonskaOsnova.getClan() + ". st. " + zakonskaOsnova.getStav() + 
				". " + zakonskaOsnova.getZakon());
		printSluzbeniGlasnik(zakonskaOsnova.getSluzbeniGlasnik());
	}
	
	private void printSluzbeniGlasnik(SluzbeniGlasnik sluzbeniGlasnik) {
		System.out.println("\t -Naziv sluzbenog glasnika: " + sluzbeniGlasnik.getNaziv());
		printBrojevi(sluzbeniGlasnik.getBrojevi());
	}
	
	private void printBrojevi(Brojevi brojevi) {
		for (String broj : brojevi.getBroj()) {
			System.out.println("\t\t -Broj: " + broj);
		}
	}
	
	private void printZahtevi(Zahtevi zahtevi) {
		System.out.println("\t Od gore navedenog organa zahtevam: ");
		if (zahtevi.getObavestenje() != null) {
			System.out.println("\t\t -Obavestenje da li poseduje trazenu informaciju");
		}
		if (zahtevi.getUvid() != null) {
			System.out.println("\t\t -Uvid u dokument koji sadrzi trazenu informaciju");
		}
		if (zahtevi.getKopija() != null) {
			System.out.println("\t\t -Kopiju dokumenta koji sadrzi trazenu informaciju");
		}
		printDostavljanjeKopije(zahtevi.getDostavljanjeKopije().getNacinDostave());
	}
	
	public void printDostavljanjeKopije(NacinDostave nacinDostave) {
		String nacin = null;
		if (nacinDostave.getDostavaPostom() != null) {
			nacin = "postom";
		}
		if (nacinDostave.getDostavaElektronskomPostom() != null) {
			nacin = "elektronskom postom";
		}
		if (nacinDostave.getDostavaFaksom() != null) {
			nacin = "faksom";
		}
		if (nacinDostave.getPosebnaDostava() != null) {
			nacin = nacinDostave.getPosebnaDostava().getNacinPosebneDostave();
		}
		System.out.println("\t\t -Dostavljanje kopije dokumenta koji sadrzi trazenu informaciju: " + nacin);
	}
	
	public void printTrazilac(TTrazilac trazilac) {
		System.out.println("\t Informacije o traziocu: ");
		System.out.println("\t\t -Ime: " + trazilac.getIme());
		System.out.println("\t\t -Prezime: " + trazilac.getPrezime());
		System.out.println("\t\t -Adresa: " + trazilac.getAdresa());
		System.out.println("\t\t -Kontakt podaci: " +trazilac.getKontaktPodaci());
	}

	public static void main(String[] args) {
		ZahtevTest test = new ZahtevTest();
		
		try {
			JAXBContext context = JAXBContext.newInstance("zahtev");
			ZahtevZaPristupInformacijama zahtev = test.unmarshall(context);
			test.marshall(context, zahtev);
		} catch (Exception e) {
		}
		

	}

}
