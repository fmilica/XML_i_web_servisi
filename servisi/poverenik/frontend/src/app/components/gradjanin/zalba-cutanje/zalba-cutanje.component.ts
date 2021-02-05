import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { XonomyZalbaCutanjeService } from 'src/app/services/xonomy/xonomy-zalba-cutanje.service';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje.service';
import * as txml from 'txml';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-cutanje',
  templateUrl: './zalba-cutanje.component.html',
  styleUrls: ['./zalba-cutanje.component.sass'],
})
export class ZalbaCutanjeComponent implements OnInit {

  form: FormGroup;
  unetId = false;
  zahtevDto;

  constructor(
    private xonomyZalbaCutanjeService: XonomyZalbaCutanjeService,
    private zalbaCutanjeService: ZalbaCutanjeService,
    private toastr: ToastrService) {
      this.unetId = false;
      this.form = new FormGroup({
        id: new FormControl('', [Validators.required])
      })
    }

  ngOnInit(): void {}

  ngAfterViewInit() { }

  podnesiZalbu() {
    let xmlResponse;
    let zahtev;
    let zahtevId = this.form.get('id').value;
    this.zalbaCutanjeService.posaljiZahtevId(zahtevId).subscribe(
      response => {
        xmlResponse = txml.parse(response);
        zahtev = xmlResponse[3].children[0].children[0]
        let zahtevDate = new Date(Number(zahtev.attributes.datum.split('-')[0]), Number(zahtev.attributes.datum.split('-')[1])-1, Number(zahtev.attributes.datum.slice(0, -1).split('-')[2]))
        let danasnjiDatum = new Date()
        danasnjiDatum.setDate(danasnjiDatum.getDate() - 15)
        if(zahtev.attributes.odbijen === 'false\\' && zahtev.attributes.razresen === 'false\\' && danasnjiDatum >= zahtevDate) {
          this.unetId = true;
        }
        else{
          this.toastr.error("Не можете да поднесете жалбу на ћутање за послат ID захтева")
          return
        }
        this.zahtevDto = {
          datumZahteva: zahtev.attributes.datum.slice(0, -1),
          organVlasti: zahtev.children[0].children[0].children[0]
        }

        let element = document.getElementById('zalbaCutanje');
        let specification = this.xonomyZalbaCutanjeService.zalbaCutanjeSpecification;
        let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                        <zoc:Zalba_cutanje xmlns="http://www.w3.org/ns/rdfa#"
                          xmlns:zoc="http://zalbacutanje"
                          xmlns:tipovi="http://tipovi"
                          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                          xmlns:pred="http://www.xml.com/predicate/"
                          xsi:schemaLocation="http://zalbacutanje zalbacutanje.xsd"
                          mesto="" datum_podnosenja_zalbe="${new Date().toISOString().slice(0, 10)}" datum_podnosenja_zahteva="` + this.zahtevDto.datumZahteva + `"
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
                            `<zoc:Naziv_organa>` + this.zahtevDto.organVlasti +`</zoc:Naziv_organa>`+
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
      },
      err => {
        this.toastr.error('Захтев са задатим Id не постоји!')
        return
      }
    )
  }

  getRequiredFieldErrorMessage(fieldName: string): string {
    if (this.form.controls[fieldName].touched) {
      return this.form.controls[fieldName].hasError('required')
        ? 'Обавезно поље'
        : '';
    }

    return '';
  }

  send() {
    let xmlDocument =  Xonomy.harvest();
    if(Xonomy.warnings.length !== 0) {
      this.toastr.error('Молимо Вас да исправно попуните форму!')
      return
    }
    this.zalbaCutanjeService.createZalbaCutanje(xmlDocument, this.form.get('id').value)
      .subscribe((response) => {
        this.toastr.success('Успешно сте поднели жалбу због ћутања! Можете да је видите у "Жалбе на ћутање".')
      },
        err => {
          this.toastr.error('Молимо Вас да исправно попуните форму!')
      });
  }
}