import { Component, OnInit, ɵɵi18nAttributes } from '@angular/core';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje.service';
import * as txml from 'txml';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-zalbe-cutanje-gradjanin',
  templateUrl: './zalbe-cutanje-gradjanin.component.html',
  styleUrls: ['./zalbe-cutanje-gradjanin.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ZalbeCutanjeGradjaninComponent implements OnInit {

  constructor(
    private zalbaCutanjeService: ZalbaCutanjeService,
    private zahtevService: ZahtevService
  ) { }
  dataSource = [ ];

  fetchedZahtev = {
    nazivOrgana: "",
    sedisteOrgana: "",
    informacije: "",
    mesto: "",
    datum: ""
  }

  expandedElement: any | null;

  displayedColumns: string[] = ['organVlasti', 'razlogZalbe', 'datumZahteva', 'podaci', 'datumZalbe', 'mestoZalbe',
                                'razresena', 'preuzimanje', 'preuzimanjeMeta']

  ngOnInit(): void {
    this.zalbaCutanjeService.getAllGradjaninZalbeCutanje()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allZalbe: any = txml.parse(xmlResponse);
          let data = []
          allZalbe[1].children.map(zalba => {
            let zalbaPrikaz = {
              id: zalba.attributes.id.substring(20),
              organVlasti: zalba.children[1].children[1].children[0],
              razlogZalbe: zalba.children[1].children[2].children[0],
              datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
              podaci: zalba.children[1].children[3].children[0],
              datumZalbe: zalba.attributes.datum_podnosenja_zalbe,
              mestoZalbe: zalba.attributes.mesto,
              razresena: zalba.attributes.razresen,
              zahtev: zalba.attributes.href.substring(14)
            }
            data.push(zalbaPrikaz);
          })
          this.dataSource = data;
        }
      )
  }

  generisiPDF(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiPDF(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "pdf");
      }
    );
  }

  generisiHTML(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiHTML(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "html");
      }
    );
  }

  previewAndDownload(response: any, id: string, tip: string){
    let type = "application/"+tip;
    let blob = new Blob([response], { type: type});
    let url = window.URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = "zalba_cutanje_"+id+"."+tip;
    link.click();
  }

  generisiRDF(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiRDF(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "xml");
      }
    );
  }

  generisiJSON(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiJSON(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "json");
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
