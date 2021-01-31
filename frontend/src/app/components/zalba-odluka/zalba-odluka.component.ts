import { Component, OnInit } from '@angular/core';
import { XonomyZalbaOdlukaService } from 'src/app/services/xonomy/xonomy-zalba-odluka.service';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-odluka',
  templateUrl: './zalba-odluka.component.html',
  styleUrls: ['./zalba-odluka.component.sass']
})
export class ZalbaOdlukaComponent implements OnInit {

  constructor(private xonomyZalbaOdlukaService: XonomyZalbaOdlukaService) { }

  ngOnInit(): void {}

  ngAfterViewInit(){
    let element = document.getElementById('zalbaOdluka');
    let specification = this.xonomyZalbaOdlukaService.zalbaOdlukaSpecification;
    let xmlString = `<?xml version="1.0" encoding="UTF-8"?>`+
                    `<zoz:Zalba_odbijanje xmlns="http://www.w3.org/ns/rdfa#"
                    xmlns:zoz="http://zalbaodbijanje"
                    xmlns:tipovi="http://tipovi"
                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns:pred="http://www.xml.com/predicate/"
                    xsi:schemaLocation="http://zalbaodbijen zalba_odbijen.xsd"
                    mesto_podnosenja_zalbe="Sabac" datum_podnosenja_zalbe="2020-01-01" datum_podnosenja_zahteva="2020-01-01" id="ID1"
                    
                    vocab="http://www.xml.com/predicate/"
                    about="http://zalbaodbijen/ID1"
                    rel="pred:vezanZahtev"
                    href="http://zahtev/ID1">`+
                    `<zoz:podaci_o_primaocu>`+
                      `<tipovi:Naziv property="pred:primalacNaziv"></tipovi:Naziv>`+
                      `<tipovi:Sediste>`+
                        `<tipovi:Mesto></tipovi:Mesto>`+
                        `<tipovi:Ulica></tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                      `</tipovi:Sediste>`+
                    `</zoz:podaci_o_primaocu>`+
                    `<zoz:podaci_o_zaliocu>`+
                      `<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>`+
                      `<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>`+
                      `<tipovi:Adresa>`+
                        `<tipovi:Mesto></tipovi:Mesto>`+
                        `<tipovi:Ulica></tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                      `</tipovi:Adresa>`+
                    `</zoz:podaci_o_zaliocu>`+
                    `<zoz:podaci_o_odluci broj_odluke="0" godina="0">`+
                      `<zoz:naziv_donosioca_odluke></zoz:naziv_donosioca_odluke>`+
                    `</zoz:podaci_o_odluci>`+
                    `<zoz:telo_zalbe>`+
                      `<zoz:razlog_zalbe></zoz:razlog_zalbe>`+
                      `<zoz:osnova_zalbe>`+
                        `<zoz:Clan></zoz:Clan>`+
                        `<zoz:Stav></zoz:Stav>`+
                        `<zoz:Zakon></zoz:Zakon>`+
                      `</zoz:osnova_zalbe>`+
                    `</zoz:telo_zalbe>`+
                    `<zoz:podaci_o_podnosiocu_zalbe>`+
                      `<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>`+
                      `<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>`+
                      `<tipovi:Adresa>`+
                        `<tipovi:Mesto></tipovi:Mesto>`+
                        `<tipovi:Ulica></tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                      `</tipovi:Adresa>`+
                      `<tipovi:Kontakt_podaci></tipovi:Kontakt_podaci>`+
                    `</zoz:podaci_o_podnosiocu_zalbe>`+
                  `</zoz:Zalba_odbijanje>`;

    Xonomy.render(xmlString, element, specification);
  }

}
