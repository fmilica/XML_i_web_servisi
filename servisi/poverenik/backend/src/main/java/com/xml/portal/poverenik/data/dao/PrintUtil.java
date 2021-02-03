package com.xml.portal.poverenik.data.dao;

import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje.UkupanTrosakKopija;
import com.xml.portal.poverenik.data.dao.obavestenje.TAdresaUvida;
import com.xml.portal.poverenik.data.dao.obavestenje.TVremeMesto;
import com.xml.portal.poverenik.data.dao.obavestenje.TZakon;
import com.xml.portal.poverenik.data.dao.obavestenje.UvidUDokument;
import com.xml.portal.poverenik.data.dao.tipovi.TAdresa;
import com.xml.portal.poverenik.data.dao.tipovi.TLice;
import com.xml.portal.poverenik.data.dao.tipovi.TLiceKontakt;
import com.xml.portal.poverenik.data.dao.tipovi.TOrganVlasti;
import com.xml.portal.poverenik.data.dao.tipovi.TPoverenik;
import com.xml.portal.poverenik.data.dao.tipovi.TSluzbeniGlasnik;
import com.xml.portal.poverenik.data.dao.tipovi.TSluzbeniGlasnik.Brojevi;
import com.xml.portal.poverenik.data.dao.zahtev.NacinDostave;
import com.xml.portal.poverenik.data.dao.zahtev.TZakonskaOsnova;
import com.xml.portal.poverenik.data.dao.zahtev.TeloZahteva;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtevi;
import com.xml.portal.poverenik.data.dao.zalba_cutanje.Zalba;
import com.xml.portal.poverenik.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.TTelo;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje.PodaciOOdluci;

public class PrintUtil {

	private static String outString;
	
	public static String getOutString() {
		return outString;
	}
	
	// Obavestenje
	
	public static void printObavestenje(Obavestenje obavestenje) {
		outString = "";
		outString += ("ID dokumenta: " + obavestenje.getId()) + "\n";
		outString += "\n";
		
		outString += "Broj predmeta: " + obavestenje.getBrojPredmeta() + "\n";
		outString += "Primerak dostavljen: " + obavestenje.getDostavljeno() + "\n";
		outString += "Datum kreiranja obavestenja: " + obavestenje.getDatum() + "\n";
		
		printOrganVlasti(obavestenje.getOrganVlasti());
		printPodnosilac(obavestenje.getPodnosilac());
		printUvidUDokument(obavestenje.getUvidUDokument());
		printUkupanTrosakKopija(obavestenje.getUkupanTrosakKopija());		
	}
	
	private static void printOrganVlasti(TOrganVlasti organVlasti) {
		outString += "\n\t Podaci o nadleznom organu vlasti: " + "\n";
		outString += "\t\t Naziv: " + organVlasti.getNaziv().getValue() + "\n";
		outString += "\t\t Sediste: " + organVlasti.getSediste() + "\n";
	}
	
	private static void printPodnosilac(TLice podnosilac) {
		outString += "\n\t Podaci o podnosiocu: " + "\n";
		if (podnosilac.getIme() != null) {
			outString += "\t\t Ime: " + podnosilac.getIme() + "\n";
			outString += "\t\t Prezime: " + podnosilac.getPrezime() + "\n";
		} else {
			outString += "\t\t Naziv: " + podnosilac.getNaziv() + "\n";
		}
		printAdresa(podnosilac.getAdresa());
	}
	
	private static void printAdresa(TAdresa adresa) {
		outString += "\t\t Adresa: " + "\n";
		outString += "\t\t\t Mesto: " + adresa.getMesto() + "\n";
		outString += "\t\t\t Ulica: " + adresa.getUlica() + "\n";
		outString += "\t\t\t Broj: " + adresa.getUlicniBroj() + "\n";
	}
	
	private static void printUvidUDokument(UvidUDokument uvid) {
		printZakonskaOsnova(uvid.getZakonskaOsnova());
		outString += "\t\t Na osnovu zahteva od: " + uvid.getDatumPotrazivanja() + "\n";
		outString += "\t\t Potrazilac ce imati uvid u dokument u vezi sa: " + uvid.getOpisTrazeneInformacije() + "\n";
		printPodaciOUvidu(uvid.getPodaciOUvidu());
	}
	
	private static void printZakonskaOsnova(TZakon zakon) {
		outString += "\n\tNa osnovu clana " + zakon.getClan() + ". " + 
							"stava " + zakon.getStav() + ". " + 
							"zakona " + zakon.getZakon() + ": " + "\n";
	}
	
	private static void printPodaciOUvidu(TVremeMesto vremeMestoUvida) {
		outString += "\t\t\t Uvid ce se odrzati dana: " + vremeMestoUvida.getDatum() + "\n";
		outString += "\t\t\t U: " + vremeMestoUvida.getVreme() + "\n";
		outString += "\t\t\t Odnosno, u vremenu od: " + vremeMestoUvida.getPocetnoVreme() + "\n";
		outString += "\t\t\t Do: " + vremeMestoUvida.getKrajnjeVreme() + "\n";
		printMestoUvida(vremeMestoUvida.getMestoUvida());
	}
	
	private static void printMestoUvida(TAdresaUvida mestoUvida) {
		outString += "\t\t\t U prostorijama organa u: " + mestoUvida.getMesto() + "\n";
		outString += "\t\t\t Ulica: " + mestoUvida.getUlica() + "\n";
		outString += "\t\t\t Broj:" + mestoUvida.getUlicniBroj() + "\n";
		outString += "\t\t\t Broj kancelarije: " + mestoUvida.getKancelarijskiBroj() + "\n";
	}
	
	private static void printUkupanTrosakKopija(UkupanTrosakKopija trosak) {
		outString += "\n\t Ukupan trosak kopija: " + trosak.getValue() + " " + trosak.getValuta() + "\n";
		outString += "\t\t Podaci o uplati: " + "\n";
		outString += "\t\t\t Broj ziro racuna: " + trosak.getZiroRacun() + "\n";
		outString += "\t\t\t Poziv na broj: " + trosak.getPozivNaBroj() + "\n";
	}
	
	// Zahtev
	
	public static void printZahtev(Zahtev zahtev) {
		outString = "";
		outString += "ID dokumenta: " + zahtev.getId() + "\n";
		outString += "\n";
		
		outString += "Mesto zahteva: " + zahtev.getMesto() + "\n";
		outString += "Datum zahteva: " + zahtev.getDatum() + "\n";
		outString += "\n";
		
		printOrganVlastiZahtev(zahtev.getOrganVlasti());
		printTeloZahteva(zahtev.getTeloZahteva());
		printTrazilac(zahtev.getTrazilac());
	}
	
	private static void printOrganVlastiZahtev(TOrganVlasti organVlasti) {
		outString += "\t Podaci o organu kom se upucuje zahtev: " + "\n";
		outString += "\t\t Naziv: " + organVlasti.getNaziv().getValue() + "\n";
		outString += "\t\t Sediste: " + organVlasti.getSediste() + "\n";
		outString += "\n";
	}
	
	private static void printTeloZahteva(TeloZahteva teloZahteva) {
		printZakonskaOsnova(teloZahteva.getZakonskaOsnova());
		printZahtevi(teloZahteva.getZahtevi());
		outString += "\t Zahtevane informacije: " + teloZahteva.getZahtevaneInformacije() + "\n";
		outString += "\n";
	}
	
	private static void printZakonskaOsnova(TZakonskaOsnova zakonskaOsnova) {
		outString += "\t Na osnovu clana " + zakonskaOsnova.getClan() + ". st. " + zakonskaOsnova.getStav() + 
				". " + zakonskaOsnova.getZakon();
		printSluzbeniGlasnik(zakonskaOsnova.getSluzbeniGlasnik());
	}
	
	private static void printSluzbeniGlasnik(TSluzbeniGlasnik sluzbeniGlasnik) {
		outString += "\t Naziv sluzbenog glasnika: " + sluzbeniGlasnik.getNaziv() + "\n";
		printBrojevi(sluzbeniGlasnik.getBrojevi());
	}
	
	private static void printBrojevi(Brojevi brojevi) {
		for (String broj : brojevi.getBroj()) {
			outString += "\t\t Broj: " + broj + "\n";
		}
		outString += "\n";
	}
	
	private static void printZahtevi(Zahtevi zahtevi) {
		outString += "\t Od gore navedenog organa zahtevam: " + "\n";
		if (zahtevi.getObavestenje() != null) {
			outString += "\t\t Obavestenje da li poseduje trazenu informaciju" + "\n";
		}
		if (zahtevi.getUvid() != null) {
			outString += "\t\t Uvid u dokument koji sadrzi trazenu informaciju" + "\n";
		}
		if (zahtevi.getKopija() != null) {
			outString += "\t\t Kopiju dokumenta koji sadrzi trazenu informaciju" + "\n";
		}
		printDostavljanjeKopije(zahtevi.getDostavljanjeKopije().getNacinDostave());
	}
	
	private static void printDostavljanjeKopije(NacinDostave nacinDostave) {
		String nacin = null;
		if (nacinDostave.getDostavaPostom() != null) {
			nacin = "postom" + "\n";
		}
		if (nacinDostave.getDostavaElektronskomPostom() != null) {
			nacin = "elektronskom postom" + "\n";
		}
		if (nacinDostave.getDostavaFaksom() != null) {
			nacin = "faksom" + "\n";
		}
		if (nacinDostave.getPosebnaDostava() != null) {
			nacin = nacinDostave.getPosebnaDostava().getNacinPosebneDostave() + "\n";
		}
		outString += "\t\t Dostavljanje kopije dokumenta koji sadrzi trazenu informaciju: " + nacin + "\n";
	}
	
	private static void printTrazilac(TLiceKontakt trazilac) {
		outString += "\t Informacije o traziocu: " + "\n";
		outString += "\t\t Ime: " + trazilac.getIme().getValue() + "\n";
		outString += "\t\t Prezime: " + trazilac.getPrezime().getValue() + "\n";
		printAdresa(trazilac.getAdresa());
		outString += "\t\t Kontakt podaci: " +trazilac.getKontaktPodaci() + "\n";
	}
	
	// Zalba cutanje
	
	public static void printZalbaCutanje(ZalbaCutanje zalbaCutanje) {
		outString = "";
		outString += "ID dokumenta: " + zalbaCutanje.getId() + "\n";
		outString += "\n";
		
		outString += "Mesto zahteva: " + zalbaCutanje.getMesto() + "\n";
		outString += "Datum zahteva: " + zalbaCutanje.getDatumPodnosenjaZahteva() + "\n";
		outString += "\n";
		
		printPoverenik(zalbaCutanje.getPrimalacZalbe());
		
		printZalba(zalbaCutanje.getZalba());
	}
	
	private static void printPoverenik(TPoverenik primalacZalbe) {
		outString += "\t Podaci o povereniku koji prima zalbu: " + "\n";
		outString += "\t\t Naziv: " + primalacZalbe.getNaziv() + "\n";
		printAdresaZalba(primalacZalbe.getSediste());
	}
	
	private static void printAdresaZalba(TAdresa adresa) {
		outString += "\t\t Adresa:" + "\n";
		outString += "\t\t\t Mesto:" + adresa.getMesto() + "\n";
		outString += "\t\t\t Ulica:" + adresa.getUlica() + "\n";
	}
	
	private static void printZalba(Zalba zalba) {
		outString += "\t Zalba: " + "\n";
		outString += "\t\t Clan: " + zalba.getOsnovaZalbe().getClan() + "\n";
		outString += "\t\t Zakon: " + zalba.getOsnovaZalbe().getZakon() + "\n";
		outString += "\t\t Naziv organa: " + zalba.getNazivOrgana() + "\n";
		outString += "\t\t Razlog zalbe: " + zalba.getRazlogZalbe() + "\n";
		outString += "\t\t Podaci o zahtevu: " + zalba.getPodaciOZahtevu() + "\n";
		printPodnosilacZalba(zalba.getPodnosilacZalbe());
	}
	
	private static void printPodnosilacZalba(TLiceKontakt podnosilac) {
		outString += "\t\t Podaci o podnosiocu zalbe: " + "\n";
		outString += "\t\t\t Ime: " + podnosilac.getIme() + "\n";
		outString += "\t\t\t Prezime: " + podnosilac.getPrezime() + "\n";
		printAdresaPodnosioca(podnosilac.getAdresa());
		outString += "\t\t\t Drugi podaci:\n\t\t\t\t " + podnosilac.getKontaktPodaci() + "\n";
	}
	
	private static void printAdresaPodnosioca(TAdresa adresa) {
		outString += "\t\t\t Adresa:" + "\n";
		outString += "\t\t\t\t Mesto:" + adresa.getMesto() + "\n";
		outString += "\t\t\t\t Ulica:" + adresa.getUlica() + "\n";
	}
	
	// Zalba odbijanje
	
	public static void printZalba(ZalbaOdbijanje zalba) {
		
		outString += "ID dokumenta: " + zalba.getId() + "\n";
		outString += "\n";
		
		outString += "Mesto podnosenja zalbe: " + zalba.getMestoPodnosenjaZalbe() + "\n";
		outString += "Datum podnosenja zalbe: " + zalba.getDatumPodnosenjaZalbe() + "\n";
		outString += "Datum podnosenja zahteva: " + zalba.getDatumPodnosenjaZahteva() + "\n";
		outString += "\n";

		printPrimaoc(zalba.getPodaciOPrimaocu());
		printZalioc(zalba.getPodaciOZaliocu());
		printOdluka(zalba.getPodaciOOdluci());
		printTelo(zalba.getTeloZalbe());
		printPodnosioc(zalba.getPodaciOPodnosiocuZalbe());
	}

	private static void printPodnosioc(TLiceKontakt podnosilac) {
		outString += "\tPodaci o Podnosiocu zalbe:" + "\n";

		if (podnosilac.getIme() != null) {
			outString += "\t\tIme i prezime: " + podnosilac.getIme() + " " + podnosilac.getPrezime() + "\n";
		} else {
			outString += "\t\tNaziv: " + podnosilac.getNaziv() + "\n";
		}

		printAdresaZalba(podnosilac.getAdresa());
		outString += "\t\tDrugi podaci za kontakt: " + podnosilac.getKontaktPodaci() + "\n";
		outString += "\n";
	}

	private static void printTelo(TTelo telo) {
		outString += "\tTelo zalbe: " + "\n";
		outString += "\t\tRazlog zalbe: " + telo.getRazlogZalbe() + "\n";
		outString += "\t\tClan: " + telo.getOsnovaZalbe().getClan() + "\n";
		outString += "\t\tZakon: " + telo.getOsnovaZalbe().getZakon() + "\n";
		outString += "\n";
	}

	private static void printOdluka(PodaciOOdluci odluka) {
		outString += "\tPodaci o odluci:" + "\n";
		outString += "\t\tNaziv donosioca odluke: " + odluka.getNazivDonosiocaOdluke() + "\n";
		outString += "\t\tBroj doluke: " + odluka.getBrojOdluke() + "\n";
		outString += "\t\tGodina donosenja olduke: " + odluka.getGodina() + "\n";
		outString += "\n";
	}

	private static void printZalioc(TLice zalilac) {
		outString += "\tPodaci o zaliocu:" + "\n";
		if (zalilac.getIme() != null) {
			outString += "\t\tIme i prezime: " + zalilac.getIme() + " " + zalilac.getPrezime() + "\n";
		} else {
			outString += "\t\tNaziv: " + zalilac.getNaziv() + "\n";
		}
		printAdresaZalba(zalilac.getAdresa());
		outString += "\n";
	}

	private static void printPrimaoc(TPoverenik primalac) {
		outString += "\tPodaci o primaocu:" + "\n";
		outString += "\t\tNaziv primaoca: " + primalac.getNaziv() + "\n";
		outString += "\t\tAdresa primaoca: " + primalac.getSediste() + "\n";
		outString += "\n";
	}
}
