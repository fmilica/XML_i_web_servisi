import { Component, OnInit } from '@angular/core';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka.service';
import * as txml from 'txml';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-zalbe-odluku-gradjanin',
  templateUrl: './zalbe-odluku-gradjanin.component.html',
  styleUrls: ['./zalbe-odluku-gradjanin.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ZalbeOdlukuGradjaninComponent implements OnInit {

  constructor(
    private zalbaOdlukaService: ZalbaOdlukaService,
    private zahtevService: ZahtevService
  ) { }

  dataSource = [
    /*{
      naziv: 'Пера Перић',
      adresa: 'Железничка 23, Нови Сад',
      organVlasti: 'ФТН',
      broj: '1',
      godina: '2021.',
      datumZahteva: '2021-02-02',
      razlogZalbe: 'није ми дао информације о положеним предметима',
      datumZalbe: '23.3.1313',
      mestoZalbe: 'Нови Сад',
      razresena: 'Да'
    }*/
  ];

  fetchedZahtev = {
    nazivOrgana: "",
    sedisteOrgana: "",
    informacije: "",
    mesto: "",
    datum: ""
  }

  expandedElement: any | null;

  displayedColumns: string[] = ['naziv', 'adresa', 'organVlasti', 'broj', 'godina', 'datumZahteva', 'razlogZalbe',
                                'datumZalbe', 'mestoZalbe','razresena', 'preuzimanje', 'preuzimanjeMeta']

  ngOnInit(): void {
    this.zalbaOdlukaService.getAllGradjaninZalbeOdluka()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allZalbe: any = txml.parse(xmlResponse);
          let data = []
          allZalbe[1].children.map(zalba => {
            let zalbaPrikaz = {
              naziv: '',
              adresa: '',
              id: zalba.attributes.id.substring(22),
              organVlasti: zalba.children[2].children[0].children[0],
              broj: zalba.children[2].attributes.broj_odluke,
              godina: zalba.children[2].attributes.godina,
              datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
              razlogZalbe: zalba.children[3].children[0].children[0],
              datumZalbe: zalba.attributes.datum_podnosenja_zalbe,
              mestoZalbe: zalba.attributes.mesto_podnosenja_zalbe,
              razresena: zalba.attributes.razresen,
              zahtev: zalba.attributes.href.substring(14)
            }
            //podaci o zaliocu
            if (zalba.children[1].children.length === 3) {
              // ima ime i prezime
              zalbaPrikaz.naziv = zalba.children[1].children[0].children[0] + ' ' + zalba.children[1].children[1].children[0];

              zalbaPrikaz.adresa = zalba.children[1].children[2].children[1].children[0] + ' ' + 
              zalba.children[1].children[2].children[2].children[0] + ', ' + 
              zalba.children[1].children[2].children[0].children[0]
            } else {
              // ima naziv
              zalbaPrikaz.naziv = zalba.children[1].children[0].children[0] ;

              zalbaPrikaz.adresa = zalba.children[1].children[1].children[1].children[0] + ' ' + 
              zalba.children[1].children[1].children[2].children[0] + ', ' + 
              zalba.children[1].children[1].children[0].children[0]
            }
            data.push(zalbaPrikaz);
          })
          this.dataSource = data;
        }
      )
  } 

  generisiPDF(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiPDF(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "pdf");
      }
    );
  }

  generisiHTML(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiHTML(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "html");
      }
    );
  }

  previewAndDownload(response: any, id: string, tip: string){
    let type = "application/"+tip;
    let blob = new Blob([response], { type: type});
    let url = window.URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = "zalba_odbijanje_"+id+"."+tip;
    link.click();
  }

  generisiRDF(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiRDF(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "xml");
      }
    );
  }

  generisiJSON(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiJSON(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "json");
      }
    );
  }

  fetchZahtev(zahtevId: string){
    //TODO dobaviti zahtev preko SOAP i odkomentarisati linije u html
    this.zahtevService.getZahtevById(zahtevId).subscribe(
      (response) => {
        let xmlResponse = response;
        let zahtev: any =  txml.parse(xmlResponse);
        zahtev.map( z => {
          let zahtevPrikaz = {
            nazivOrgana: zahtev[1].children[0].children[0].children[0],
            sedisteOrgana: zahtev[1].children[0].children[1].children[0],
            dostava: 'false',
            informacije: zahtev[1].children[1].children[2].children[0],
            mesto: zahtev[1].attributes.mesto,
            datum: zahtev[1].attributes.datum
          }
          this.fetchedZahtev = zahtevPrikaz
        })
      }
    )
  }
}
