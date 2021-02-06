import { Component, OnInit } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import * as txml from 'txml';
import { ResenjeService } from 'src/app/services/resenje.service';
import { ZahtevService } from 'src/app/services/zahtev.service';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje.service';
import { ZalbaOdlukaComponent } from '../../gradjanin/zalba-odluka/zalba-odluka.component';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka.service';

@Component({
  selector: 'app-resenja-gradjanin',
  templateUrl: './resenja-gradjanin.component.html',
  styleUrls: ['./resenja-gradjanin.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ResenjaGradjaninComponent implements OnInit {

  constructor(
    private resenjeService: ResenjeService,
    private zahtevService: ZahtevService,
    private zalbaCutanjeService: ZalbaCutanjeService,
    private zalbaOdlukaService: ZalbaOdlukaService
  ) { }

  dataSource = [
   ];

  displayedColumns: string[] = ['zalilac', 'organVlasti', 'ishod', 'emailZalioca', 'datumResenja', 'preuzimanje', 'preuzimanjeMeta'];

  fetchedZahtev = {
    nazivOrgana: "",
    sedisteOrgana: "",
    informacije: "",
    mesto: "",
    datum: ""
  }

  fetchedZalba = {
    tip: "",
    datumZalbe: "",
    razresena: ""
  }

  expandedElement: any | null;

  ngOnInit(): void {
    this.resenjeService.getGradjaninResenja().subscribe(
      (response) => {
        let xmlResponse = response;
        let allResenja: any =  txml.parse(xmlResponse);
        let data = []
        allResenja[0].children.map( resenje => {
          let vrstaZalbe = "zalbacutanje";
          let idZalbe = resenje.children[3].children[0].substring(20)
          if(resenje.children[3].children[0].substring(7,19) !== "zalbacutanje"){
            vrstaZalbe = "zalbaodbijanje";
            idZalbe = resenje.children[3].children[0].substring(22);
          }
          let resenjePrikaz = {
            id: resenje.children[0].children[0],
            zahtevId: resenje.children[2].children[0],
            zalbaId: idZalbe,
            vrstaZalbe: vrstaZalbe,
            zalilac: resenje.children[5].children[0],
            organVlasti: resenje.children[6].children[0],
            ishod: resenje.children[7].children[0],
            emailZalioca: resenje.children[4].children[0],
            datumResenja: resenje.children[1].children[0],
          }
          data.push(resenjePrikaz);
        })
        this.dataSource = data;
      }
    )
  }

  fetchDokumente(zahtevId: string, zalbaId: string, vrstaZalbe: string){
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
    if(vrstaZalbe === "zalbacutanje"){
      this.zalbaCutanjeService.getById(zalbaId).subscribe(
        (response) => {
          let xmlResponse = response;
          let zalba: any =  txml.parse(xmlResponse);
          let zalbaPrikaz;
          zalba.map( z => {
            let razresen = "Разрешена";
            if(zalba[1].attributes.razresen === "false"){
              razresen = "Неразрешена"
            }
            zalbaPrikaz = {
              tip: "Жалба на ћутање",
              datumZalbe: zalba[1].attributes.datum_podnosenja_zalbe,
              razresena: razresen
            }
          })
          this.fetchedZalba = zalbaPrikaz
        }
      )
    }else{
      this.zalbaOdlukaService.getById(zalbaId).subscribe(
        (response) => {
          let xmlResponse = response;
          let zalba: any =  txml.parse(xmlResponse);
          let zalbaPrikaz;
          zalba.map( z => {
            let razresen = "Разрешена";
            if(zalba[1].attributes.razresen === "false"){
              razresen = "Неразрешена"
            }
            zalbaPrikaz = {
              tip: "Жалба на одбијање",
              datumZalbe: zalba[1].attributes.datum_podnosenja_zalbe,
              razresena: razresen
            }
          })
          this.fetchedZalba = zalbaPrikaz
        }
      )
    }
  }

  generisiPDF(resenjeId: string) {
    this.resenjeService.generisiPDF(resenjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, resenjeId, "pdf");
      }
    );
  }

  generisiHTML(resenjeId: string) {
    this.resenjeService.generisiHTML(resenjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, resenjeId, "html");
      }
    );
  }

  previewAndDownload(response: any, id: string, tip: string){
    let type = "application/"+tip;
    let blob = new Blob([response], { type: type});
    let url = window.URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = "resenje_"+id+"."+tip;
    link.click();
  }

  generisiRDF(resenjeId: string) {
    this.resenjeService.generisiRDF(resenjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, resenjeId, "xml");
      }
    );
  }

  generisiJSON(resenjeId: string) {
    this.resenjeService.generisiJSON(resenjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, resenjeId, "json");
      }
    );
  }

}
