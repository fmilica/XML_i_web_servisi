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
import zalba_odbijen.TPodnosilac;
import zalba_odbijen.TPrimalac;
import zalba_odbijen.TTelo;
import zalba_odbijen.TZalilac;
import zalba_odbijen.ZalbaOdbijenZahtev;
import zalba_odbijen.ZalbaOdbijenZahtev.PodaciOOdluci;

public class ZalbaOdbijenTest {

	public ZalbaOdbijenZahtev unmarshall(JAXBContext context) throws JAXBException, SAXException {
		Unmarshaller unmarshaller = context.createUnmarshaller();

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("./data/zalba_odbijen/zalba_odbijen.xsd"));

		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());

		ZalbaOdbijenZahtev zalba = (ZalbaOdbijenZahtev) unmarshaller
				.unmarshal(new File("./data/zalba_odbijen/zalba_odbijen1.xml"));

		printZalba(zalba);
		return zalba;

	}

	private void printZalba(ZalbaOdbijenZahtev zalba) {
		System.out.println("Mesto podnosenja zalbe: " + zalba.getMestoPodnosenjaZalbe());
		System.out.println("Datum podnosenja zalbe: " + zalba.getDatumPodnosenjaZalbe());
		System.out.println("Datum podnosenja zahteva: " + zalba.getDatumPodnosenjaZahteva());
		System.out.println();

		printPrimaoc(zalba.getPodaciOPrimaocu());
		printZalioc(zalba.getPodaciOZaliocu());
		printOdluka(zalba.getPodaciOOdluci());
		printTelo(zalba.getTeloZalbe());
		printPodnosioc(zalba.getPodaciOPodnosiocuZalbe());
	}

	private void printPodnosioc(TPodnosilac podnosilac) {
		System.out.println("\tPodaci o Podnosiocu zalbe:");

		if (podnosilac.getImeIPrezime() != null) {
			System.out.println("\t\tIme i prezime: " + podnosilac.getImeIPrezime());
		} else {
			System.out.println("\t\tNaziv: " + podnosilac.getNazivPodnosioca());
		}

		System.out.println("\t\tAdresa podnosioca: " + podnosilac.getAdresaPodnosioca());
		System.out.println("\t\tDrugi podaci za kontakt: " + podnosilac.getDrugiPodaciZaKontakt());
		System.out.println();
	}

	private void printTelo(TTelo telo) {
		System.out.println("\tTelo zalbe: ");
		System.out.println("\t\tRazlog zalbe: " + telo.getRazlogZalbe());
		System.out.println("\t\tClan: " + telo.getClan());
		System.out.println("\t\tZakon: " + telo.getZakon());
		System.out.println();
	}

	private void printOdluka(PodaciOOdluci odluka) {
		System.out.println("\tPodaci o odluci:");
		System.out.println("\t\tNaziv donosioca odluke: " + odluka.getNazivDonosiocaOdluke());
		System.out.println("\t\tBroj doluke: " + odluka.getBrojOdluke());
		System.out.println("\t\tGodina donosenja olduke: " + odluka.getGodina());
		System.out.println();
	}

	private void printZalioc(TZalilac zalilac) {
		System.out.println("\tPodaci o zaliocu:");
		if (zalilac.getImeIPrezime() != null) {
			System.out.println("\t\tIme i prezime: " + zalilac.getImeIPrezime());
		} else {
			System.out.println("\t\tNaziv: " + zalilac.getNazivZalioca());
		}
		System.out.println("\t\tAdresa zalioca: " + zalilac.getAdresaZalioca());
		System.out.println();
	}

	private void printPrimaoc(TPrimalac primalac) {
		System.out.println("\tPodaci o zaliocu:");
		System.out.println("\t\tIme i prezime: " + primalac.getNazivPrimaoca());
		System.out.println("\t\tAdresa zalioca: " + primalac.getAdresaPrimaoca());
		System.out.println();
	}

	private void marshall(JAXBContext context, ZalbaOdbijenZahtev zalba) throws JAXBException, FileNotFoundException {
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(zalba, new FileOutputStream(new File("./data/marshalled/zalba_odbijen_marsh.xml")));
	}

	public static void main(String[] args) {
		ZalbaOdbijenTest test = new ZalbaOdbijenTest();

		try {
			JAXBContext context = JAXBContext.newInstance("zalba_odbijen");
			ZalbaOdbijenZahtev zalba = test.unmarshall(context);
			test.marshall(context, zalba);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}