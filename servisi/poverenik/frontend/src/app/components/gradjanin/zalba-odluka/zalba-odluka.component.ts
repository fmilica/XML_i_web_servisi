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
                    mesto_podnosenja_zalbe="" datum_podnosenja_zalbe="${new Date().toISOString().slice(0, 10)}"
                    datum_podnosenja_zahteva="2021-01-02"
                    >`+
                    `<zoz:podaci_o_primaocu>`+
                      `<tipovi:Naziv property="pred:primalacNaziv">Поверенику за информације од јавног значаја и заштиту података о личности</tipovi:Naziv>`+
                      `<tipovi:Sediste>`+
                        `<tipovi:Mesto>Београд</tipovi:Mesto>`+
                        `<tipovi:Ulica>Булевар краља Александра</tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj>15</tipovi:Ulicni_broj>`+
                      `</tipovi:Sediste>`+
                    `</zoz:podaci_o_primaocu>`+
                    `<zoz:podaci_o_zaliocu>`+
                      //`<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>`+
                      //`<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>`+
                      `<tipovi:Adresa>`+
                        `<tipovi:Mesto></tipovi:Mesto>`+
                        `<tipovi:Ulica></tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                      `</tipovi:Adresa>`+
                    `</zoz:podaci_o_zaliocu>`+
                    `<zoz:podaci_o_odluci broj_odluke="1" godina="2021.01.02.">`+
                      `<zoz:naziv_donosioca_odluke>ФТН</zoz:naziv_donosioca_odluke>`+
                    `</zoz:podaci_o_odluci>`+
                    `<zoz:telo_zalbe>`+
                      `<zoz:razlog_zalbe></zoz:razlog_zalbe>`+
                      `<zoz:osnova_zalbe>`+
                        `<zoz:Clan>22</zoz:Clan>`+
                        `<zoz:Stav>1</zoz:Stav>`+
                        `<zoz:Zakon>Закона о слободном приступу информацијама од јавног значаја</zoz:Zakon>`+
                      `</zoz:osnova_zalbe>`+
                    `</zoz:telo_zalbe>`+
                    `<zoz:podaci_o_podnosiocu_zalbe>`+
                      //`<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>`+
                      //`<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>`+
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