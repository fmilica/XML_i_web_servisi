import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import { ResenjeService } from 'src/app/services/resenje.service';
import { ZahtevService } from 'src/app/services/zahtev.service';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje-service';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka-service';
import * as txml from 'txml';

@Component({
  selector: 'app-resenja',
  templateUrl: './resenja.component.html',
  styleUrls: ['./resenja.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ResenjaComponent implements OnInit {

  constructor(
    private obavestenjeService: ObavestenjeService,
    private zahtevService: ZahtevService,
    private resenjeService: ResenjeService,
    private zalbaCutanjeService: ZalbaCutanjeService,
    private zalbaOdlukaService: ZalbaOdlukaService
  ) { }

  dataSource = [
    /*{
      zalilac: "Mika Mikic",
      organVlasti: "Fakultet tehnickih nauka",
      ishod: "Neki ishod",
      emailZalioca: "user@email.com",
      datumResenja: "23.5.2020."
    }*/
   ];

  displayedColumns: string[] = ['zalilac', 'organVlasti', 'ishod', 'emailZalioca', 'datumResenja', 'akcija'];

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
    this.resenjeService.getAllResenja().subscribe(
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
          let kreiranjeObavestenja = "1" // crtica
          if(resenje.children[7].children[0] == "основана"){
            if(resenje.children[8].children[0] ===  "true"){
              kreiranjeObavestenja = "3"; // 'obavesten'
            }else{
              kreiranjeObavestenja = "2"; // dugme
            }
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
            akcija: kreiranjeObavestenja
          }
          data.push(resenjePrikaz);
        })
        this.dataSource = data;
      }
    )
  }

  kreirajObavestenje(zahtevId: string) {

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

}
