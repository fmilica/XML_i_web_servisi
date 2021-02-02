import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { XonomyZalbaOdlukaService } from 'src/app/services/xonomy/xonomy-zalba-odluka.service';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka.service';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-odluka',
  templateUrl: './zalba-odluka.component.html',
  styleUrls: ['./zalba-odluka.component.sass']
})
export class ZalbaOdlukaComponent implements OnInit {

  constructor(
    private xonomyZalbaOdlukaService: XonomyZalbaOdlukaService,
    private zalbaOdlukaService: ZalbaOdlukaService,
    private toastr: ToastrService) { }

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
                    `<zoz:Podaci_o_primaocu>`+
                      `<tipovi:Naziv property="pred:primalacNaziv">Повереник за информације од јавног значаја и заштиту података о личности</tipovi:Naziv>`+
                      `<tipovi:Sediste>`+
                        `<tipovi:Mesto>Београд</tipovi:Mesto>`+
                        `<tipovi:Ulica>Булевар краља Александра</tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj>15</tipovi:Ulicni_broj>`+
                      `</tipovi:Sediste>`+
                    `</zoz:Podaci_o_primaocu>`+
                    `<zoz:Podaci_o_zaliocu>`+
                      //`<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>`+
                      //`<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>`+
                      `<tipovi:Adresa>`+
                        `<tipovi:Mesto></tipovi:Mesto>`+
                        `<tipovi:Ulica></tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                      `</tipovi:Adresa>`+
                    `</zoz:Podaci_o_zaliocu>`+
                    `<zoz:Podaci_o_odluci broj_odluke="" godina="">`+
                      `<zoz:Naziv_donosioca_odluke>ФТН</zoz:Naziv_donosioca_odluke>`+
                    `</zoz:Podaci_o_odluci>`+
                    `<zoz:Telo_zalbe>`+
                      `<zoz:Razlog_zalbe></zoz:Razlog_zalbe>`+
                      `<zoz:Osnova_zalbe>`+
                        `<zoz:Clan>22</zoz:Clan>`+
                        `<zoz:Stav>1</zoz:Stav>`+
                        `<zoz:Zakon>Закона о слободном приступу информацијама од јавног значаја</zoz:Zakon>`+
                      `</zoz:Osnova_zalbe>`+
                    `</zoz:Telo_zalbe>`+
                    `<zoz:Podaci_o_podnosiocu_zalbe>`+
                      //`<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>`+
                      //`<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>`+
                      `<tipovi:Adresa>`+
                        `<tipovi:Mesto></tipovi:Mesto>`+
                        `<tipovi:Ulica></tipovi:Ulica>`+
                        `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                      `</tipovi:Adresa>`+
                      `<tipovi:Kontakt_podaci></tipovi:Kontakt_podaci>`+
                    `</zoz:Podaci_o_podnosiocu_zalbe>`+
                  `</zoz:Zalba_odbijanje>`;

    Xonomy.render(xmlString, element, specification);
  }

  send() {
    let xmlDocument =  Xonomy.harvest();
    if(Xonomy.warnings.length !== 0) {
      this.toastr.error('Молимо Вас да исправно попуните форму!')
      return
    }
    this.zalbaOdlukaService.createZalbaOdluka(xmlDocument, '1')
      .subscribe((response) => {
        this.toastr.success('Успешно сте поднели жалбу на одлуку! Можете да је видите у "Жалбе на одлуку".')
      },
        err => {
          this.toastr.error('Молимо Вас да исправно попуните форму!')
      });
  }

}