import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ObavestenjeNaprednaPretragaDto } from 'src/app/model/obavestenje-napredna-pretraga-dto';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import * as txml from 'txml';
import * as JsonToXML from 'js2xmlparser';

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

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'brojPredmeta', 'datum', 'imePrezime', 'adresa', 'datumZahteva', 'informacije', 'preuzimanje', 'preuzimanjeMeta'];

  ngOnInit(): void {
    this.obavestenjeService.getAllObavestenja()
      .subscribe(
        (response) => {
          this.listaObavestenja2Prikaz(response)
        }
      )
  }

  listaObavestenja2Prikaz(response) {
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
    let unos = this.obicnaForm.get('sve').value
    if(!unos) {
      this.obavestenjeService.getAllObavestenja().subscribe(
        (response) => {
          this.listaObavestenja2Prikaz(response)
        }
      )
    } else {
      this.obavestenjeService.obicnaPretraga(unos).subscribe(
        (response) => {
          this.listaObavestenja2Prikaz(response)
        }
      )
    }
  }

  metapodaciPretraga() {
    let naprednaDto: ObavestenjeNaprednaPretragaDto = {
      IzdavacNaziv: '?izdavacNaziv',
      VezanGradjanin: '?vezanGradjanin',
      PodnosilacIme: '?podnosilacIme',
      PodnosilacPrezime: '?podnosilacPrezime',
      PodnosilacNaziv: '?podnosilacNaziv',
      VezanZahtev: '?vezanZahtev',
      Operator: 'AND'
    }

    let izdavacNaziv = this.metaDataForm.get('izdavacNaziv').value;
    if(izdavacNaziv) {
      naprednaDto.IzdavacNaziv = "\"" + izdavacNaziv + "\""
    }

    let vezanGradjanin = this.metaDataForm.get('vezaniGradjanin').value;
    if(vezanGradjanin) {
      naprednaDto.VezanGradjanin = 'http://korisnik/' + vezanGradjanin
    }

    let podnosilacIme = this.metaDataForm.get('podnosilacIme').value;
    if(podnosilacIme) {
      naprednaDto.PodnosilacIme = "\"" + podnosilacIme + "\""
    }

    let podnosilacPrezime = this.metaDataForm.get('podnosilacPrezime').value;
    if(podnosilacPrezime) {
      naprednaDto.PodnosilacPrezime = "\"" + podnosilacPrezime + "\""
    }

    let podnosilacNaziv = this.metaDataForm.get('podnosilacNaziv').value;
    if(podnosilacNaziv) {
      naprednaDto.PodnosilacNaziv = "\"" + podnosilacNaziv + "\""
    }

    let vezanZahtev = this.metaDataForm.get('vezaniZahtev').value;
    if(vezanZahtev) {
      naprednaDto.VezanZahtev = "\"" + vezanZahtev + "\""
    }

    let operator = this.metaDataForm.get('operator').value;
    if(operator) {
      naprednaDto.Operator = operator
    }

    if(!izdavacNaziv && !vezanGradjanin && !podnosilacIme && !podnosilacPrezime && !podnosilacNaziv  && !vezanZahtev) {
      this.obavestenjeService.getAllObavestenja().subscribe(
        (response) => {
          this.listaObavestenja2Prikaz(response);
        }
      );
      return
    }

    const options = {
      declaration: {
        include: false,
      },
    };

    let xmlDocument: string = JsonToXML.parse(
      'ObavestenjeNaprednaPretragaDto',
      naprednaDto,
      options
    );

    this.obavestenjeService.naprednaPretraga(xmlDocument).subscribe(
      (response) => {
        this.listaObavestenja2Prikaz(response);
      })
  }
      
        
  generisiRDF(zahtevId: string) {
    this.obavestenjeService.generisiRDF(zahtevId).subscribe(
      (response) => {
        this.previewAndDownload(response, zahtevId, "xml");
      }
    );
  }

  generisiJSON(zahtevId: string) {
    this.obavestenjeService.generisiJSON(zahtevId).subscribe(
      (response) => {
        this.previewAndDownload(response, zahtevId, "json");
      }
    );
  }

}
