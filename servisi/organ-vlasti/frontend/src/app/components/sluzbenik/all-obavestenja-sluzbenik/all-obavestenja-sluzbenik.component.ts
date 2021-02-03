import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-all-obavestenja-sluzbenik',
  templateUrl: './all-obavestenja-sluzbenik.component.html',
  styleUrls: ['./all-obavestenja-sluzbenik.component.sass']
})
export class AllObavestenjaSluzbenikComponent implements OnInit {

  //formе za pretragu
  obicnaForm: FormGroup;
  metaDataForm: FormGroup;

  constructor(
    private obavestenjeService: ObavestenjeService
  ) { 
    this.obicnaForm = new FormGroup({
      sve: new FormControl()
    })

    this.metaDataForm = new FormGroup({
      izdavacNaziv: new FormControl(),
      vezaniGradjanin: new FormControl(),
      podnosilacIme: new FormControl(),
      podnosilacPrezime: new FormControl(),
      podnosilacNaziv: new FormControl(),
      vezaniZahtev: new FormControl(),
      operator: new FormControl()
    })
  }

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
            //console.log(obavestenje)
            let obavestenjePrikaz = {
              id: obavestenje.attributes.id.substring(19),
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

  obicnaPretraga() {
    console.log(this.obicnaForm.value)
  }

  metapodaciPretraga() {
    console.log(this.obicnaForm.value)
  }

}
