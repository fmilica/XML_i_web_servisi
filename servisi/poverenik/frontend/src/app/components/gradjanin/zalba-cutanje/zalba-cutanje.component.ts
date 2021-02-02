import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { XonomyZalbaCutanjeService } from 'src/app/services/xonomy/xonomy-zalba-cutanje.service';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje.service';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka.service';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-cutanje',
  templateUrl: './zalba-cutanje.component.html',
  styleUrls: ['./zalba-cutanje.component.sass'],
})
export class ZalbaCutanjeComponent implements OnInit {
  constructor(
    private xonomyZalbaCutanjeService: XonomyZalbaCutanjeService,
    private zalbaCutanjeService: ZalbaCutanjeService,
    private toastr: ToastrService) {}

  ngOnInit(): void {}

  ngAfterViewInit() {
    let element = document.getElementById('zalbaCutanje');
    let specification = this.xonomyZalbaCutanjeService.zalbaCutanjeSpecification;
    let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                     <zoc:Zalba_cutanje xmlns="http://www.w3.org/ns/rdfa#"
                      xmlns:zoc="http://zalbacutanje"
                      xmlns:tipovi="http://tipovi"
                      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                      xmlns:pred="http://www.xml.com/predicate/"
                      xsi:schemaLocation="http://zalbacutanje zalbacutanje.xsd"
                      mesto="" datum_podnosenja_zalbe="${new Date().toISOString().slice(0, 10)}" datum_podnosenja_zahteva="2021-01-02"
                      >`+
                      `<zoc:Primalac_zalbe>`+
                        `<tipovi:Naziv property="pred:primalacNaziv">Повереник за информације од јавног значаја и заштиту података о личности</tipovi:Naziv>`+
                        `<tipovi:Sediste>`+
                          `<tipovi:Mesto>Београд</tipovi:Mesto>`+
                          `<tipovi:Ulica>Булевар краља Александра</tipovi:Ulica>`+
                          `<tipovi:Ulicni_broj>15</tipovi:Ulicni_broj>`+
                        `</tipovi:Sediste>`+
                      `</zoc:Primalac_zalbe>`+
                      `<zoc:Zalba>`+
                        `<zoc:Osnova_zalbe>`+
                          `<zoc:Clan>22</zoc:Clan>`+
                          `<zoc:Zakon>Закона о слободном приступу информацијама од јавног значаја</zoc:Zakon>`+
                        `</zoc:Osnova_zalbe>`+
                        `<zoc:Naziv_organa></zoc:Naziv_organa>`+
                        `<zoc:Razlog_zalbe></zoc:Razlog_zalbe>`+
                        `<zoc:Podaci_o_zahtevu></zoc:Podaci_o_zahtevu>`+
                        `<zoc:Podnosilac_zalbe>`+
                          //`<tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime>`+
                          //`<tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime>`+
                          //`<tipovi:Naziv property="pred:podnosilacNaziv"></tipovi:Naziv>`+
                          `<tipovi:Adresa>`+
                            `<tipovi:Mesto></tipovi:Mesto>`+
                            `<tipovi:Ulica></tipovi:Ulica>`+
                            `<tipovi:Ulicni_broj></tipovi:Ulicni_broj>`+
                          `</tipovi:Adresa>`+
                          `<tipovi:Kontakt_podaci></tipovi:Kontakt_podaci>`+
                        `</zoc:Podnosilac_zalbe>` +
                      `</zoc:Zalba>`+
                    `</zoc:Zalba_cutanje>`;

    Xonomy.render(xmlString, element, specification);
  }

  send() {
    let xmlDocument =  Xonomy.harvest();
    if(Xonomy.warnings.length !== 0) {
      this.toastr.error('Молимо Вас да исправно попуните форму!')
      return
    }
    this.zalbaCutanjeService.createZalbaCutanje(xmlDocument, '1ae8a7c6-e341-47aa-a5cf-945ed2b8985a')
      .subscribe((response) => {
        this.toastr.success('Успешно сте поднели жалбу због ћутања! Можете да је видите у "Жалбе на ћутање".')
      },
        err => {
          this.toastr.error('Молимо Вас да исправно попуните форму!')
      });
  }
}