import { Component, OnInit } from '@angular/core';
import { XonomyZahtevService } from 'src/app/services/xonomy/xonomy-zahtev.service';

declare const Xonomy: any;
@Component({
  selector: 'app-new-zahtev',
  templateUrl: './new-zahtev.component.html',
  styleUrls: ['./new-zahtev.component.sass']
})
export class NewZahtevComponent implements OnInit {

  constructor(
    private xonomyZahtevService: XonomyZahtevService
  ) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    let element = document.getElementById('zahtev');
    let specification = this.xonomyZahtevService.zahtevSpecification
    let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
    <zahtev:Zahtev xmlns="http://www.w3.org/ns/rdfa#"
        xmlns:zahtev="http://zahtev"
        xmlns:tipovi="http://tipovi"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:pred="http://www.xml.com/predicate/"
        xsi:schemaLocation="http://zahtev zahtev.xsd"
        datum="${new Date().toISOString().slice(0, 10)}"
        mesto=""
        id="ID1"
        
        vocab="http://www.xml.com/predicate/"
        about="http://zahtev/ID1">` +
          `<zahtev:Organ_vlasti>` +
            `<tipovi:Naziv property="pred:primalacNaziv">` + 
            `</tipovi:Naziv><tipovi:Sediste></tipovi:Sediste>` +
          `</zahtev:Organ_vlasti>` +
          `<zahtev:Telo_zahteva>` +
              `<zahtev:Zakonska_osnova>` +
                `<zahtev:Clan>15</zahtev:Clan>` + 
                `<zahtev:Stav>1</zahtev:Stav>` +
                `<zahtev:Zakon>Захтев о слободном приступу информацијама од јавног значаја</zahtev:Zakon>` +
                `<zahtev:Sluzbeni_glasnik>` +
                  `<tipovi:Naziv>"Службени гласник РС"</tipovi:Naziv>` +
                  `<tipovi:Brojevi>` + 
                    `<tipovi:Broj>120/04</tipovi:Broj>` +
                    `<tipovi:Broj>54/07</tipovi:Broj>` +
                    `<tipovi:Broj>104/09</tipovi:Broj>` +
                    `<tipovi:Broj>36/10</tipovi:Broj>` +
                  `</tipovi:Brojevi>` +
                `</zahtev:Sluzbeni_glasnik>` +
              `</zahtev:Zakonska_osnova>` +
              `<zahtev:Zahtevi>` + 
              `</zahtev:Zahtevi>` +
              `<zahtev:Zahtevane_informacije>` +
              `</zahtev:Zahtevane_informacije>` +
          `</zahtev:Telo_zahteva>` +
          `<zahtev:Trazilac>` +
           /* `<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>` +
            `<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>` +*/
            `<tipovi:Adresa>` +
              `<tipovi:Mesto></tipovi:Mesto>` +
              `<tipovi:Ulica></tipovi:Ulica>` + 
              `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>` +
            `</tipovi:Adresa>` +
            `<tipovi:Kontakt_podaci></tipovi:Kontakt_podaci>` + 
          `</zahtev:Trazilac>` +
        `</zahtev:Zahtev>`
    Xonomy.render(xmlString, element, specification);
  }

}
