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
                        datum="2006-05-04"
                        id="ID1"
                        
                        vocab="http://www.xml.com/predicate/"
                        about="http://obavestenje/ID1"
                        rel="pred:vezanZahtev"
                        href="http://zahtev/ID1">`+
                      `<obv:Organ_vlasti>`+
                        `<tipovi:Naziv property="pred:izdavacNaziv">Fakultet tehnickih nauka</tipovi:Naziv>`+
                        `<tipovi:Sediste>Novi Sad</tipovi:Sediste>`+
                      `</obv:Organ_vlasti>`+
                      `<obv:Broj_predmeta>SIIT-1</obv:Broj_predmeta>`+
                      `<obv:Podnosilac>`+
                        `<tipovi:Ime property="pred:podnosilacIme">Vlastislav</tipovi:Ime>`+
                        `<tipovi:Prezime property="pred:podnosilacPrezime">Jakovljevic</tipovi:Prezime>`+
                        `<tipovi:Adresa>`+
                          `<tipovi:Mesto>Novi Sad</tipovi:Mesto>`+
                          `<tipovi:Ulica>Zeleznicka</tipovi:Ulica>`+
                          `<tipovi:Ulicni_broj>23</tipovi:Ulicni_broj>`+
                        `</tipovi:Adresa>`+
                      `</obv:Podnosilac>`+
                      `<obv:Uvid_u_dokument>`+
                        `<obv:Zakonska_osnova>`+
                          `<obv:Clan>16</obv:Clan>`+
                          `<obv:Stav>1</obv:Stav>`+
                          `<obv:Zakon>Zakon o slobodnom pristupu informacijama od javnog značaja</obv:Zakon>`+
                        `</obv:Zakonska_osnova>`+
                        `<obv:Datum_potrazivanja>2006-05-04</obv:Datum_potrazivanja>`+
                        `<obv:Opis_trazene_informacije>Informacije o polozenim predmetima</obv:Opis_trazene_informacije>`+
                        `<obv:Podaci_o_uvidu>`+
                          `<obv:Datum>2006-05-04</obv:Datum>`+
                          `<obv:Vreme>15:30:00.000</obv:Vreme>`+
                          `<obv:Pocetno_vreme>12</obv:Pocetno_vreme>`+
                          `<obv:Krajnje_vreme>17</obv:Krajnje_vreme>`+
                          `<obv:Mesto_uvida>`+
                            `<tipovi:Mesto>Novi Sad</tipovi:Mesto>`+
                            `<tipovi:Ulica>Trg Dositeja Obradovica</tipovi:Ulica>`+
                            `<tipovi:Ulicni_broj>4</tipovi:Ulicni_broj>`+
                            `<obv:Kancelarijski_broj>F-13</obv:Kancelarijski_broj>`+
                          `</obv:Mesto_uvida>`+
                        `</obv:Podaci_o_uvidu>`+
                      `</obv:Uvid_u_dokument>`+
                    `<obv:Ukupan_trosak_kopija valuta="RSD" ziro_racun="840-742328-843-30" poziv_na_broj="97">30</obv:Ukupan_trosak_kopija>`+
                  `</obv:Obavestenje>`;
                
    Xonomy.render(xmlString, element, specification);
  }
}
