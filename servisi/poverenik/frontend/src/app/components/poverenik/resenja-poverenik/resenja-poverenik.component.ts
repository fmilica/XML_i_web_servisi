import { Component, OnInit } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import * as txml from 'txml';

@Component({
  selector: 'app-resenja-poverenik',
  templateUrl: './resenja-poverenik.component.html',
  styleUrls: ['./resenja-poverenik.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ResenjaPoverenikComponent implements OnInit {

  constructor() { }

  dataSource = [
    {
      zalilac: "Mika Mikic",
      organVlasti: "Fakultet tehnickih nauka",
      ishod: "Neki ishod",
      emailZalioca: "user@email.com",
      datumResenja: "23.5.2020."
    }
   ];

  displayedColumns: string[] = ['zalilac', 'organVlasti', 'ishod', 'emailZalioca', 'datumResenja'];

  fetchedZahtev = {
    nazivOrgana: "",
    sedisteOrgana: "",
    informacije: "",
    mesto: "",
    datum: ""
  }

  fetchedZalba = {
    nazivOrgana: "",
    sedisteOrgana: "",
    informacije: "",
    mesto: "",
    datum: ""
  }

  expandedElement: any | null;

  ngOnInit(): void {
  }

  fetchDokumente(zahtevId: string, zalbaId: string){
    //TODO dobavljanje zahteva preko SOAP
    /*this.zahtevService.getZahtevById(zahtevId).subscribe(
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
    )*/
    //TODO dobavljanje zalbe preko SOAP
    //mora da se proveri koja je tacno zalba pa da se tako prikaze
  }
}
