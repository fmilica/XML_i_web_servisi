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

import obavestenje.Obavestenje;
import obavestenje.Obavestenje.UkupanTrosakKopija;
import obavestenje.TAdresa;
import obavestenje.TAdresaUvida;
import obavestenje.TLice;
import obavestenje.TOrganVlasti;
import obavestenje.TVremeMesto;
import obavestenje.TZakon;
import obavestenje.UvidUDokument;
import util.MyValidationEventHandler;


public class ObavestenjeTest {
	
	public Obavestenje unmarshall(JAXBContext context) throws JAXBException, SAXException {
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("./data/obavestenje/obavestenje.xsd"));
        
		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());
		
		Obavestenje obavestenje = (Obavestenje) 
				unmarshaller.unmarshal(new File("./data/obavestenje/obavestenje_gen1.xml"));
		
		return obavestenje;
	}
	
	private void marshall(JAXBContext context, Obavestenje obavestenje) throws JAXBException, FileNotFoundException {
		Marshaller marshaller = context.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.marshal(obavestenje, new FileOutputStream(new File("./data/marshalled/obavestenje_marsh.xml")));
	}
	
	private void printObavestenje(Obavestenje obavestenje) {
		
		System.out.println("Broj predmeta: " + obavestenje.getBrojPredmeta());
		System.out.println("Primerak dostavljen: " + obavestenje.getDostavljeno());
		System.out.println("Datum kreiranja obavestenja: " + obavestenje.getDatum());
		
		printOrganVlasti(obavestenje.getOrganVlasti());
		printPodnosilac(obavestenje.getPodnosilac());
		printUvidUDokument(obavestenje.getUvidUDokument());
		printUkupanTrosakKopija(obavestenje.getUkupanTrosakKopija());		
	}
	
	private void printOrganVlasti(TOrganVlasti organVlasti) {
		System.out.println("\n\t Podaci o nadleznom organu vlasti: ");
		System.out.println("\t\t Naziv: " + organVlasti.getNaziv());
		System.out.println("\t\t Sediste: " + organVlasti.getSediste());
	}
	
	private void printPodnosilac(TLice podnosilac) {
		System.out.println("\n\t Podaci o podnosiocu: ");
		if (podnosilac.getIme() != null) {
			System.out.println("\t\t Ime: " + podnosilac.getIme());
			System.out.println("\t\t Prezime: " + podnosilac.getPrezime());
		} else {
			System.out.println("\t\t Naziv: " + podnosilac.getNaziv());
		}
		printAdresa(podnosilac.getAdresa());
	}
	
	private void printAdresa(TAdresa adresa) {
		System.out.println("\t\t Adresa: ");
		System.out.println("\t\t\t Mesto: " + adresa.getMesto());
		System.out.println("\t\t\t Ulica: " + adresa.getUlica());
		System.out.println("\t\t\t Broj: " + adresa.getUlicniBroj());
	}
	
	private void printUvidUDokument(UvidUDokument uvid) {
		printZakonskaOsnova(uvid.getZakonskaOsnova());
		System.out.println("\t\t Na osnovu zahteva od: " + uvid.getDatumPotrazivanja());
		System.out.println("\t\t Potrazilac ce imati uvid u dokument u vezi sa: " + uvid.getOpisTrazeneInformacije());
		printPodaciOUvidu(uvid.getPodaciOUvidu());
	}
	
	private void printZakonskaOsnova(TZakon zakon) {
		System.out.println("\n\tNa osnovu clana " + zakon.getClan() + ". " + 
							"stava " + zakon.getStav() + ". " + 
							"zakona " + zakon.getZakon() + ": ");
	}
	
	private void printPodaciOUvidu(TVremeMesto vremeMestoUvida) {
		System.out.println("\t\t\t Uvid ce se odrzati dana: " + vremeMestoUvida.getDatum());
		System.out.println("\t\t\t U: " + vremeMestoUvida.getVreme());
		System.out.println("\t\t\t Odnosno, u vremenu od: " + vremeMestoUvida.getPocetnoVreme());
		System.out.println("\t\t\t Do: " + vremeMestoUvida.getKrajnjeVreme());
		printMestoUvida(vremeMestoUvida.getMestoUvida());
	}
	
	private void printMestoUvida(TAdresaUvida mestoUvida) {
		System.out.println("\t\t\t U prostorijama organa u: " + mestoUvida.getMesto());
		System.out.println("\t\t\t Ulica: " + mestoUvida.getUlica());
		System.out.println("\t\t\t Broj:" + mestoUvida.getUlicniBroj());
		System.out.println("\t\t\t Broj kancelarije: " + mestoUvida.getKancelarijskiBroj());

	}
	
	private void printUkupanTrosakKopija(UkupanTrosakKopija trosak) {
		System.out.println("\n\t Ukupan trosak kopija: " + trosak.getValue() + " " + trosak.getValuta());
		System.out.println("\t\t Podaci o uplati: ");
		System.out.println("\t\t\t Broj ziro racuna: " + trosak.getZiroRacun());
		System.out.println("\t\t\t Poziv na broj: " + trosak.getPozivNaBroj());
	}

	public static void main(String[] args) {
		ObavestenjeTest test = new ObavestenjeTest();
		
		try {
			JAXBContext context = JAXBContext.newInstance("obavestenje");
			Obavestenje obavestenje = test.unmarshall(context);
			test.printObavestenje(obavestenje);
			test.marshall(context, obavestenje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
