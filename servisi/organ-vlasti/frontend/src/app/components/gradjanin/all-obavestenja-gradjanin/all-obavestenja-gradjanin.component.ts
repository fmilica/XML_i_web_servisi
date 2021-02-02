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
              id: obavestenje.attributes.id.substring(19),
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

  generisiPDF(obavestenjeId: string) {
    this.obavestenjeService.generisiPDF(obavestenjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, obavestenjeId, "pdf");
      }
    );
  }

  generisiHTML(obavestenjeId: string) {
    this.obavestenjeService.generisiHTML(obavestenjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, obavestenjeId, "html");
      }
    );
  }

  previewAndDownload(response: any, id: string, tip: string){
    let type = "application/"+tip;
    let blob = new Blob([response], { type: type});
    let url = window.URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = "obavestenje_"+id+"."+tip;
    link.click();
  }
}
