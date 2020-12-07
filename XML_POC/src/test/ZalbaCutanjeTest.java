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
import zalbacutanje.Adresa;
import zalbacutanje.PodnosilacZalbe;
import zalbacutanje.PrimalacZalbe;
import zalbacutanje.Zalba;
import zalbacutanje.ZalbaCutanje;

public class ZalbaCutanjeTest {

	public ZalbaCutanje unmarshall(JAXBContext context) throws JAXBException, SAXException {
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("./data/zalba_cutanje/zalbacutanje.xsd"));
        
		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());
		
		ZalbaCutanje zalbaCutanje = (ZalbaCutanje) 
				unmarshaller.unmarshal(new File("./data/zalba_cutanje/zalbacutanje_gen1.xml"));
		
		return zalbaCutanje;
	}
	
	private void marshall(JAXBContext context, ZalbaCutanje zalbaCutanje) throws JAXBException, FileNotFoundException {
		Marshaller marshaller = context.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.marshal(zalbaCutanje, new FileOutputStream(new File("./data/marshalled/zalbacutanje_marsh.xml")));
	}
	
	
	private void printZalbaCutanje(ZalbaCutanje zalbaCutanje) {
		
		System.out.println("ID dokumenta: " + zalbaCutanje.getId());
		System.out.println();
		
		System.out.println("Mesto zahteva: " + zalbaCutanje.getMesto());
		System.out.println("Datum zahteva: " + zalbaCutanje.getDatum());
		System.out.println();
		
		printPoverenik(zalbaCutanje.getPrimalacZalbe());
		
		printZalba(zalbaCutanje.getZalba());
	}
	
	private void printPoverenik(PrimalacZalbe primalacZalbe) {
		System.out.println("\t Podaci o povereniku koji prima zalbu: ");
		System.out.println("\t\t Naziv: " + primalacZalbe.getNazivPrimaoca());
		printAdresa(primalacZalbe.getAdresa());
	}
	
	private void printAdresa(Adresa adresa) {
		System.out.println("\t\t Adresa:");
		System.out.println("\t\t\t Mesto:" + adresa.getMesto());
		System.out.println("\t\t\t Postanski broj:" + adresa.getPostanskiBroj());
		System.out.println("\t\t\t Ulica:" + adresa.getUlica());
		System.out.println("\t\t\t Broj:" + adresa.getBroj());
	}
	
	private void printZalba(Zalba zalba) {
		System.out.println("\t Zalba: ");
		System.out.println("\t\t Clan: " + zalba.getClan());
		System.out.println("\t\t Zakon: " + zalba.getZakon());
		System.out.println("\t\t Naziv organa: " + zalba.getNazivOrgana());
		System.out.println("\t\t Razlog zalbe: " + zalba.getRazlogZalbe());
		System.out.println("\t\t Datum: " + zalba.getDatum());
		System.out.println("\t\t Podaci o zahtevu: " + zalba.getPodaciOZahtevu());
		printPodnosilac(zalba.getPodnosilacZalbe());
	}
	
	private void printPodnosilac(PodnosilacZalbe podnosilac) {
		System.out.println("\t\t Podaci o podnosiocu zalbe: ");
		System.out.println("\t\t\t Ime: " + podnosilac.getIme());
		System.out.println("\t\t\t Prezime: " + podnosilac.getPrezime());
		printAdresaPodnosioca(podnosilac.getAdresa());
		System.out.println("\t\t\t Drugi podaci:\n\t\t\t\t " + podnosilac.getDrugiPodaci());
	}
	
	private void printAdresaPodnosioca(Adresa adresa) {
		System.out.println("\t\t\t Adresa:");
		System.out.println("\t\t\t\t Mesto:" + adresa.getMesto());
		System.out.println("\t\t\t\t Postanski broj:" + adresa.getPostanskiBroj());
		System.out.println("\t\t\t\t Ulica:" + adresa.getUlica());
		System.out.println("\t\t\t\t Broj:" + adresa.getBroj());
	}
	
	public static void main(String[] args) {
		ZalbaCutanjeTest test = new ZalbaCutanjeTest();
		
		try {
			JAXBContext context = JAXBContext.newInstance("zalbacutanje");
			ZalbaCutanje zalbaCutanje = test.unmarshall(context);
			test.printZalbaCutanje(zalbaCutanje);
			test.marshall(context, zalbaCutanje);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
