import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { ZahtevDto } from 'src/app/model/zahtev-dto.model';
import { EpostaService } from 'src/app/services/eposta.service';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import { XonomyObavestenjeService } from 'src/app/services/xonomy/xonomy-obavestenje.service';
import { ZahtevService } from 'src/app/services/zahtev.service';
import * as txml from 'txml';

import * as JsonToXML from 'js2xmlparser';
import { arrayBufferToBase64 } from 'src/app/util/util';

declare const Xonomy: any;

@Component({
  selector: 'app-new-obavestenje',
  templateUrl: './new-obavestenje.component.html',
  styleUrls: ['./new-obavestenje.component.sass']
})
export class NewObavestenjeComponent implements OnInit {

  zahtevDto: ZahtevDto;
  subscription: Subscription;

  constructor(
    private xonomyObavestenjeService: XonomyObavestenjeService,
    private zahtevService: ZahtevService,
    private toastr: ToastrService,
    private obavestenjeService: ObavestenjeService,
    private router: Router,
    private epostaService: EpostaService) {}

  ngOnInit(

  ): void {
    this.subscription = this.zahtevService.odabraniZahtev
      .subscribe(zahtevDto => {
        this.zahtevDto = zahtevDto;
      })
  }

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
                        >`+
                      `<obv:Organ_vlasti>`+
                        `<tipovi:Naziv property="pred:izdavacNaziv">` + this.zahtevDto.nazivOrganaVlasti + `</tipovi:Naziv>`+
                        `<tipovi:Sediste>` + this.zahtevDto.sedisteOrganaVlasti + `</tipovi:Sediste>`+
                      `</obv:Organ_vlasti>`+
                      `<obv:Broj_predmeta></obv:Broj_predmeta>`+
                      `<obv:Podnosilac>`;
                      if(this.zahtevDto.nazivPodnosioca) {
                        xmlString += `<tipovi:Naziv>` + this.zahtevDto.nazivPodnosioca + `</tipovi:Naziv>`
                      }else {
                        xmlString += 
                        `<tipovi:Ime property="pred:podnosilacIme">` + this.zahtevDto.imePodnosioca + `</tipovi:Ime>`+
                        `<tipovi:Prezime property="pred:podnosilacPrezime">` + this.zahtevDto.prezimePodnosioca + `</tipovi:Prezime>`
                      }
                      xmlString += 
                        `<tipovi:Adresa>`+
                          `<tipovi:Mesto>` + this.zahtevDto.mesto + `</tipovi:Mesto>`+
                          `<tipovi:Ulica>` + this.zahtevDto.ulica + `</tipovi:Ulica>`+
                          `<tipovi:Ulicni_broj>` + this.zahtevDto.broj + `</tipovi:Ulicni_broj>`+
                        `</tipovi:Adresa>`+
                      `</obv:Podnosilac>`+
                      `<obv:Uvid_u_dokument>`+
                        `<obv:Zakonska_osnova>`+
                          `<obv:Clan>16</obv:Clan>`+
                          `<obv:Stav>1</obv:Stav>`+
                          `<obv:Zakon>Закон о слободном приступу информацијама од јавног значаја</obv:Zakon>`+
                        `</obv:Zakonska_osnova>`+
                        `<obv:Datum_potrazivanja>` + this.zahtevDto.datumZahteva + `</obv:Datum_potrazivanja>`+
                        `<obv:Opis_trazene_informacije>` + this.zahtevDto.informacije + `</obv:Opis_trazene_informacije>`+
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

  ngOnDestroy() {
    this.subscription.unsubscribe()
  }

  posalji(obavestenjeId: string){
    this.obavestenjeService.generisiPDF(obavestenjeId).subscribe((response)=>{
      let bajtovi = response;
      let base64Bajtovi = arrayBufferToBase64(bajtovi)
      let obj = {
        "@": {
          "tipPriloga": "pdf",
          "xmlns":"http://pismo"
      },
  
      "primalac": this.zahtevDto.gradjaninEmail,
      "naslov": "Обавештење",
      "sadrzaj": "Креирано је обавештење",
      "prilog": base64Bajtovi
      }
  
      this.epostaService.posalji(JsonToXML.parse("pismo", obj)).subscribe((resp) => {console.log("Proslo")});
    })
    
  }

  send() {
    let xmlDocument =  Xonomy.harvest();
    if(Xonomy.warnings.length !== 0) {
      this.toastr.error('Молимо Вас да исправно попуните форму!')
      return
    }
    this.obavestenjeService.createObavestenje(xmlDocument, this.zahtevDto.id, this.zahtevDto.gradjaninEmail)
      .subscribe((response) => {
        this.toastr.success('Успешно сте креирали обавештење!')
        let xmlResponse = response;
        let obavestenje: any = txml.parse(xmlResponse);
        this.posalji(obavestenje[1].attributes.id);
        // this.epostaService.posalji(JsonToXML.parse("pismo", obj)).subscribe((resp) => {console.log("Proslo")});
        this.router.navigate(['kreirana-obavestenja'])
        this.zahtevService.resiZahtev(this.zahtevDto.id)
          .subscribe()
        this.obavestenjeService.novo_obavestenje.next(false)
      },
        err => {
          this.toastr.error('Молимо Вас да исправно попуните форму!')
        });
  }
}
