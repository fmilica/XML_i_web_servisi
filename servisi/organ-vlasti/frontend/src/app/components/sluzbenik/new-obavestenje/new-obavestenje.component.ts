import { Component, OnInit } from '@angular/core';
import { XonomyObavestenjeService } from 'src/app/services/xonomy/xonomy-obavestenje.service';

declare const Xonomy: any;

@Component({
  selector: 'app-new-obavestenje',
  templateUrl: './new-obavestenje.component.html',
  styleUrls: ['./new-obavestenje.component.sass']
})
export class NewObavestenjeComponent implements OnInit {

  constructor(private xonomyObavestenjeService: XonomyObavestenjeService) {}

  ngOnInit(): void {}

  ngAfterViewInit() {
    let element = document.getElementById('obavestenje');
    let specification = this.xonomyObavestenjeService.obavestenjeSpecification;
    let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                    <obv:Obavestenje xmlns="http://www.w3.org/ns/rdfa#"
                        xmlns:obv="http://obavestenje"
                        xmlns:tipovi="http://tipovi"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:pred="http://www.xml.com/predicate/"
                        xsi:schemaLocation="http://obavestenje obavestenje.xsd" 
                        dostavljeno="imenovanom" 
                        datum="${new Date().toISOString().slice(0, 10)}"
                        id="ID1"
                        
                        vocab="http://www.xml.com/predicate/"
                        about="http://obavestenje/ID1"
                        rel="pred:vezanZahtev"
                        href="http://zahtev/ID1">`+
                      `<obv:Organ_vlasti>`+
                        `<tipovi:Naziv property="pred:izdavacNaziv">Факултет техничких наука</tipovi:Naziv>`+
                        `<tipovi:Sediste>Нови Сад</tipovi:Sediste>`+
                      `</obv:Organ_vlasti>`+
                      `<obv:Broj_predmeta></obv:Broj_predmeta>`+
                      `<obv:Podnosilac>`+
                        `<tipovi:Naziv>ПМФ</tipovi:Naziv>` +
                        /*`<tipovi:Ime property="pred:podnosilacIme">Пера</tipovi:Ime>`+
                        `<tipovi:Prezime property="pred:podnosilacPrezime">Перић</tipovi:Prezime>`+*/
                        `<tipovi:Adresa>`+
                          `<tipovi:Mesto>Нови Сад</tipovi:Mesto>`+
                          `<tipovi:Ulica>Железничка</tipovi:Ulica>`+
                          `<tipovi:Ulicni_broj>23</tipovi:Ulicni_broj>`+
                        `</tipovi:Adresa>`+
                      `</obv:Podnosilac>`+
                      `<obv:Uvid_u_dokument>`+
                        `<obv:Zakonska_osnova>`+
                          `<obv:Clan>16</obv:Clan>`+
                          `<obv:Stav>1</obv:Stav>`+
                          `<obv:Zakon>Закон о слободном приступу информацијама од јавног значаја</obv:Zakon>`+
                        `</obv:Zakonska_osnova>`+
                        `<obv:Datum_potrazivanja>2021-01-31</obv:Datum_potrazivanja>`+
                        `<obv:Opis_trazene_informacije>Информације о положеним предметима</obv:Opis_trazene_informacije>`+
                        `<obv:Podaci_o_uvidu>`+
                          `<obv:Datum></obv:Datum>`+
                          `<obv:Vreme></obv:Vreme>`+
                          `<obv:Pocetno_vreme></obv:Pocetno_vreme>`+
                          `<obv:Krajnje_vreme></obv:Krajnje_vreme>`+
                          `<obv:Mesto_uvida>`+
                            `<tipovi:Mesto></tipovi:Mesto>`+
                            `<tipovi:Ulica></tipovi:Ulica>`+
                            `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                            `<obv:Kancelarijski_broj></obv:Kancelarijski_broj>`+
                          `</obv:Mesto_uvida>`+
                        `</obv:Podaci_o_uvidu>`+
                      `</obv:Uvid_u_dokument>`+
                    `<obv:Ukupan_trosak_kopija valuta="RSD" ziro_racun="840-742328-843-30" poziv_na_broj="97">30</obv:Ukupan_trosak_kopija>`+
                  `</obv:Obavestenje>`;
                
    Xonomy.render(xmlString, element, specification);
  }
}
