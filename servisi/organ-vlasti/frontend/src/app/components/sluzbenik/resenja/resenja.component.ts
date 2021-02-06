import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ResenjeNaprednaPretragaDto } from 'src/app/model/resenje-napredna-pretraga-dto';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import { ResenjeService } from 'src/app/services/resenje.service';
import { ZahtevService } from 'src/app/services/zahtev.service';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje-service';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka-service';
import * as txml from 'txml';
import * as JsonToXML from 'js2xmlparser';
import { ZahtevDto } from 'src/app/model/zahtev-dto.model';
import { Router } from '@angular/router';
import { ZahtevObavestenjeDto } from 'src/app/model/zahtev-obavestenje-dto';

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

  //formе za pretragu
  obicnaForm: FormGroup;
  metaDataForm: FormGroup;

  constructor(
    private obavestenjeService: ObavestenjeService,
    private zahtevService: ZahtevService,
    private resenjeService: ResenjeService,
    private zalbaCutanjeService: ZalbaCutanjeService,
    private zalbaOdlukaService: ZalbaOdlukaService,
    private router: Router
  ) { 
    this.obicnaForm = new FormGroup({
      sve: new FormControl()
    })

    this.metaDataForm = new FormGroup({
      optuzeniNaziv: new FormControl(),
      vezaniGradjanin: new FormControl(),
      operator: new FormControl()
    })
  }

  dataSource = [ ];

  displayedColumns: string[] = ['zalilac', 'organVlasti', 'ishod', 'emailZalioca', 'datumResenja', 'akcija', 'preuzimanje', 'preuzimanjeMeta'];

  fetchedZahtev = {
    nazivOrgana: "",
    sedisteOrgana: "",
    informacije: "",
    mesto: "",
    datum: ""
  }

  zahtevObavestenje: ZahtevObavestenjeDto;

  fetchedZalba = {
    tip: "",
    datumZalbe: "",
    razresena: ""
  }

  expandedElement: any | null;

  ngOnInit(): void {
    this.resenjeService.getAllResenja().subscribe(
      (response) => {
        this.listaResenja2Prikaz(response);
      }
    )
  }

  kreirajObavestenje(zahtevId: string) {
    let zahtevDto: ZahtevDto = {
      id: zahtevId,
      gradjaninEmail: this.zahtevObavestenje.gradjaninEmail,
      nazivOrganaVlasti: this.zahtevObavestenje.nazivOrgana,
      sedisteOrganaVlasti: this.zahtevObavestenje.sedisteOrgana,
      mesto: this.zahtevObavestenje.mesto,
      ulica: this.zahtevObavestenje.ulicaTrazioc,
      broj: this.zahtevObavestenje.brojTrazioc,
      datumZahteva: this.zahtevObavestenje.datum,
      informacije: this.zahtevObavestenje.informacije
    }

    if(this.zahtevObavestenje.naziv) {
      zahtevDto.nazivPodnosioca = this.zahtevObavestenje.naziv
    } else {
      zahtevDto.imePodnosioca = this.zahtevObavestenje.ime
      zahtevDto.prezimePodnosioca = this.zahtevObavestenje.prezime
    }
    this.zahtevService.odabraniZahtev.next(zahtevDto)
    this.obavestenjeService.novo_obavestenje.next(true)
    this.router.navigate(['novo-obavestenje'])
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
          this.zahtevObavestenje = zahtevPrikaz
          let emailPath = zahtev[1].attributes.href.split('/');
          this.zahtevObavestenje.gradjaninEmail = emailPath[3]
          if(zahtev[1].children[2].children.length === 4) {
            // ime i prezime
            this.zahtevObavestenje.ulicaTrazioc = zahtev[1].children[2].children[2].children[1].children[0]
            this.zahtevObavestenje.brojTrazioc = zahtev[1].children[2].children[2].children[2].children[0]
            this.zahtevObavestenje.ime = zahtev[1].children[2].children[0].children[0]
            this.zahtevObavestenje.prezime = zahtev[1].children[2].children[1].children[0]
          } else {
            // naziv
            this.zahtevObavestenje.ulicaTrazioc = zahtev[1].children[2].children[1].children[1].children[0]
            this.zahtevObavestenje.brojTrazioc = zahtev[1].children[2].children[1].children[2].children[0]
            this.zahtevObavestenje.naziv = zahtev[1].children[2].children[0].children[0]
          }
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

  obicnaPretraga() {
    let unos = this.obicnaForm.get('sve').value;
    if(!unos) {
      this.resenjeService.getAllResenja().subscribe(
        (response) => {
          this.listaResenja2Prikaz(response);
        }
      );
    } else {
      this.resenjeService.obicnaPretraga(unos)
      .subscribe( response => {
        this.listaResenja2Prikaz(response);
      })
    }
  }

  metapodaciPretraga() {
    let naprednaDto: ResenjeNaprednaPretragaDto = {
      VezanGradjanin: '?vezanGradjanin',
      OptuzeniNaziv: '?optuzeniNaziv',
      Operator: 'AND'
    }

    let optuzeniNaziv = this.metaDataForm.get('optuzeniNaziv').value;
    if(optuzeniNaziv) {
      naprednaDto.OptuzeniNaziv = "\"" + optuzeniNaziv + "\"";
    }

    let vezanGradjanin = this.metaDataForm.get('vezaniGradjanin').value;
    if(vezanGradjanin) {
      naprednaDto.VezanGradjanin = 'http://korisnik/' + vezanGradjanin
    }

    let operator = this.metaDataForm.get('operator').value;
    if(operator) {
      naprednaDto.Operator = operator
    }

    if(!optuzeniNaziv && !vezanGradjanin) {
      this.resenjeService.getAllResenja().subscribe(
        (response) => {
          this.listaResenja2Prikaz(response);
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
      'ResenjeNaprednaPretragaDto',
      naprednaDto,
      options
    );

    this.resenjeService.naprednaPretraga(xmlDocument).subscribe(
      (response) => {
        this.listaResenja2Prikaz(response);
      }
    );
  }

  listaResenja2Prikaz(response) {
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
