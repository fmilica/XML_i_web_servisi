import { Component, OnInit } from '@angular/core';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-all-obavestenja-sluzbenik',
  templateUrl: './all-obavestenja-sluzbenik.component.html',
  styleUrls: ['./all-obavestenja-sluzbenik.component.sass']
})
export class AllObavestenjaSluzbenikComponent implements OnInit {

  constructor(
    private obavestenjeService: ObavestenjeService
  ) { }

  
  dataSource = [ ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'brojPredmeta', 'datum', 'imePrezime', 'adresa', 'datumZahteva', 'informacije', 'preuzimanje'];

  ngOnInit(): void {
    this.obavestenjeService.getAllObavestenja()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allObavestenja: any =  txml.parse(xmlResponse);
          let data = []
          allObavestenja[1].children.map(obavestenje => {
            console.log(obavestenje)
            let obavestenjePrikaz = {
              nazivOrgana: obavestenje.children[0].children[0].children[0],
              sedisteOrgana: obavestenje.children[0].children[1].children[0],
              brojPredmeta: obavestenje.children[1].children[0],
              datum: obavestenje.attributes.datum,
              imePrezime: obavestenje.children[2].children[0].children[0] + ' ' + obavestenje.children[2].children[1].children[0],
              adresa: obavestenje.children[2].children[2].children[1].children[0] + ', ' + 
              obavestenje.children[2].children[2].children[2].children[0] + ' ' + obavestenje.children[2].children[2].children[0].children[0],
              datumZahteva: obavestenje.children[3].children[1].children[0],
              informacije: obavestenje.children[3].children[2].children[0]
            }
            data.push(obavestenjePrikaz);
          })
          this.dataSource = data;
        }
      )
  }

}
