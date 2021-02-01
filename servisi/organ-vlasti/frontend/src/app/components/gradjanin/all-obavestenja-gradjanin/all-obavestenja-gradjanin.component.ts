import { Component, OnInit } from '@angular/core';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-all-obavestenja',
  templateUrl: './all-obavestenja-gradjanin.component.html',
  styleUrls: ['./all-obavestenja-gradjanin.component.sass']
})
export class AllObavestenjaGradjaninComponent implements OnInit {

  constructor(
    private obavestenjeService: ObavestenjeService
  ) { }

  dataSource = [ ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'brojPredmeta', 'datum', 'datumZahteva', 'informacije','danCasovi', 'vreme', 'adresaOrgana', 'brojKancelarije', 'preuzimanje'];


  ngOnInit(): void {
    this.obavestenjeService.getAllGradjaninObavestenja()
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
              datumZahteva: obavestenje.children[3].children[1].children[0], 
              informacije: obavestenje.children[3].children[2].children[0],
              danCasovi: obavestenje.children[3].children[3].children[0].children[0] + ' у ' + obavestenje.children[3].children[3].children[1].children[0],
              vreme: obavestenje.children[3].children[3].children[2].children[0] + '-' + obavestenje.children[3].children[3].children[3].children[0],
              adresaOrgana: obavestenje.children[3].children[3].children[4].children[1].children[0] + ' ' +
               obavestenje.children[3].children[3].children[4].children[2].children[0] + ', ' + 
               obavestenje.children[3].children[3].children[4].children[0].children[0],
              brojKancelarije: obavestenje.children[3].children[3].children[4].children[3].children[0]
            }
            data.push(obavestenjePrikaz);
          })
          this.dataSource = data;
        }
      )
  }

}
