import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ZahtevDto } from 'src/app/model/zahtev-dto.model';
import { ZahtevService } from 'src/app/services/zahtev.service';
import * as txml from 'txml';
import * as JsonToXML from 'js2xmlparser';
import { ZahtevNaprednaPretragaDto } from 'src/app/model/zahtev-napredna-pretraga-dto';

@Component({
  selector: 'app-all-zahtevi-sluzbenik',
  templateUrl: './all-zahtevi-sluzbenik.component.html',
  styleUrls: ['./all-zahtevi-sluzbenik.component.sass']
})
export class AllZahteviSluzbenikComponent implements OnInit {

  //formе za pretragu
  obicnaForm: FormGroup;
  metaDataForm: FormGroup;

  constructor(
    private zahtevService: ZahtevService,
    private router: Router
  ) { 
    this.obicnaForm = new FormGroup({
      sve: new FormControl()
    })
    this.metaDataForm = new FormGroup({
      primalacNaziv: new FormControl(),
      podnosilacIme: new FormControl(),
      podnosilacPrezime: new FormControl(),
      podnosilacNaziv: new FormControl(),
      vezaniGradjanin: new FormControl(),
      operator: new FormControl()
    })
  }

  dataSource = [ ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'obavestenje', 'uvid', 'kopija', 'dostava', 'informacije',
                                'mesto', 'datum', 'trazilacInformacija','adresaTrazioca', 'kontaktTelefon', 'razresen',
                                'odbijen','preuzimanje', 'preuzimanjeMeta'];

  ngOnInit(): void {
    this.fetchZahtevi();
  }

  fetchZahtevi(){
    this.zahtevService.getAllZahtevi().subscribe(
      (response) => {
        this.listaZahteva2Prikaz(response);
      }
    );
  }

  listaZahteva2Prikaz(response) {
    let xmlResponse = response
        let allZahtevi: any = txml.parse(xmlResponse);
        let data = [];
        allZahtevi[1].children.map(zahtev => {
          let emailPath = zahtev.attributes.href.split('/');
          let odb = "2";
          if(zahtev.attributes.odbijen === "true"){
            odb = "1";
          }else if(zahtev.attributes.razresen === "true"){
            odb = "3"
          }
          let zahtevPrikaz = {
            id: zahtev.attributes.id.substring(14),
            gradjaninEmail: emailPath[3],
            nazivOrgana: zahtev.children[0].children[0].children[0],
            sedisteOrgana: zahtev.children[0].children[1].children[0],
            obavestenje: 'false',
            uvid: 'false',
            kopija: 'false',
            dostava: 'false',
            informacije: zahtev.children[1].children[2].children[0],
            mesto: zahtev.attributes.mesto,
            datum: zahtev.attributes.datum,
            trazilacInformacija: '',
            adresaTrazioca: '',
            kontaktTelefon: '',
            razresen: zahtev.attributes.razresen,
            odbijen: odb,
            //rascepkana adresa da je ne spajamo i ne razdvajamo stalno
            mestoTrazioc: '',
            ulicaTrazioc: '',
            brojTrazioc: '',
            //naziv i ime i prezime
            naziv: '',
            ime: '',
            prezime: ''
          }
          if(zahtev.children[2].children.length === 4) {
            //onda je ime i prezime
            zahtevPrikaz.trazilacInformacija = zahtev.children[2].children[0].children[0] + ' ' + zahtev.children[2].children[1].children[0];
            zahtevPrikaz.adresaTrazioca = zahtev.children[2].children[2].children[1].children[0] + ' ' 
            + zahtev.children[2].children[2].children[2].children[0]  + ', '  + zahtev.children[2].children[2].children[0].children[0]
            zahtevPrikaz.kontaktTelefon =  zahtev.children[2].children[3].children[0]

            zahtevPrikaz.mestoTrazioc = zahtev.children[2].children[2].children[0].children[0]
            zahtevPrikaz.ulicaTrazioc = zahtev.children[2].children[2].children[1].children[0]
            zahtevPrikaz.brojTrazioc = zahtev.children[2].children[2].children[2].children[0]

            zahtevPrikaz.ime = zahtev.children[2].children[0].children[0]
            zahtevPrikaz.prezime = zahtev.children[2].children[1].children[0]
          }else {
            //onda je naziv
            zahtevPrikaz.trazilacInformacija = zahtev.children[2].children[0].children[0]
            zahtevPrikaz.adresaTrazioca = zahtev.children[2].children[1].children[1].children[0] + ' ' 
            + zahtev.children[2].children[1].children[2].children[0]  + ', '  + zahtev.children[2].children[1].children[0].children[0]
            zahtevPrikaz.kontaktTelefon =  zahtev.children[2].children[2].children[0]

            zahtevPrikaz.mestoTrazioc = zahtev.children[2].children[1].children[0].children[0]
            zahtevPrikaz.ulicaTrazioc = zahtev.children[2].children[1].children[1].children[0]
            zahtevPrikaz.brojTrazioc = zahtev.children[2].children[1].children[2].children[0]

            zahtevPrikaz.naziv = zahtev.children[2].children[0].children[0]
          }
          for(let potrazeno of zahtev.children[1].children[1].children) {
            switch (potrazeno.tagName) {
              case 'zahtev:Obavestenje':
                zahtevPrikaz.obavestenje = 'true'
                break;
              case 'zahtev:Uvid':
                zahtevPrikaz.uvid = 'true'
                break;
              case 'zahtev:Kopija':
                zahtevPrikaz.kopija = 'true'
                break;
              case 'zahtev:Dostavljanje_kopije':
                let nacinDostave = potrazeno.children[0].children[0];
                if (nacinDostave.tagName === 'zahtev:Posebna_dostava') {
                  zahtevPrikaz.dostava = nacinDostave.children[0].children[0]
                } else {
                  switch (nacinDostave.tagName) {
                    case 'zahtev:Dostava_postom':
                      zahtevPrikaz.dostava = 'Поштом'
                      break;
                    case 'zahtev:Dostava_elektronskom_postom':
                      zahtevPrikaz.dostava = 'Електронском поштом'
                      break;
                    case 'zahtev:Dostava_faksom':
                      zahtevPrikaz.dostava = 'Факсом'
                        break;
                    
                    default:
                      break;
                  }
                }
                break;   
              default:
                break;
            }
          }
          data.push(zahtevPrikaz);
        })
        this.dataSource = data;
  }

  createObavestenje(row: any) {
    let zahtevDto: ZahtevDto = {
      id: row.id,
      gradjaninEmail: row.gradjaninEmail,
      nazivOrganaVlasti: row.nazivOrgana,
      sedisteOrganaVlasti: row.sedisteOrgana,
      mesto: row.mestoTrazioc,
      ulica: row.ulicaTrazioc,
      broj: row.brojTrazioc,
      datumZahteva: row.datum,
      informacije: row.informacije
    }

    if(row.naziv) {
      zahtevDto.nazivPodnosioca = row.naziv
    } else {
      zahtevDto.imePodnosioca = row.ime
      zahtevDto.prezimePodnosioca = row.prezime
    }
    this.zahtevService.odabraniZahtev.next(zahtevDto)
    this.router.navigate(['novo-obavestenje'])
  }

  generisiPDF(zahtevId: string) {
    this.zahtevService.generisiPDF(zahtevId).subscribe(
      (response) => {
        this.previewAndDownload(response, zahtevId, "pdf");
      }
    );
  }

  generisiHTML(zahtevId: string) {
    this.zahtevService.generisiHTML(zahtevId).subscribe(
      (response) => {
        this.previewAndDownload(response, zahtevId, "html");
      }
    );
  }

  previewAndDownload(response: any, id: string, tip: string){
    let type = "application/"+tip;
    let blob = new Blob([response], { type: type});
    let url = window.URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = "zahtev_"+id+"."+tip;
    link.click();
  }

  obicnaPretraga() {
    let unos = this.obicnaForm.get('sve').value;
    if(!unos) {
      this.zahtevService.getAllZahtevi().subscribe(
        (response) => {
          this.listaZahteva2Prikaz(response);
        }
      );
    } else {
      this.zahtevService.obicnaPretraga(unos)
      .subscribe( response => {
        this.listaZahteva2Prikaz(response)
      })
    }
  }

  metapodaciPretraga() {
    let naprednaDto: ZahtevNaprednaPretragaDto = {
      VezanGradjanin: '?vezanGradjanin',
      PrimalacNaziv: '?primalacNaziv',
      PodnosilacIme: '?podnosilacIme',
      PodnosilacPrezime: '?podnosilacPrezime',
      PodnosilacNaziv: '?podnosilacNaziv',
      Operator: 'AND'
    }
    
    let vezanGradjanin = this.metaDataForm.get('vezaniGradjanin').value;
    if(vezanGradjanin) {
      naprednaDto.VezanGradjanin = 'http://korisnik/' + vezanGradjanin
    }

    let primalacNaziv = this.metaDataForm.get('primalacNaziv').value;
    if(primalacNaziv) {
      naprednaDto.PrimalacNaziv = "\"" + primalacNaziv + "\""
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

    let operator = this.metaDataForm.get('operator').value;
    if(operator) {
      naprednaDto.Operator = operator
    }

    if(!vezanGradjanin && !primalacNaziv && !podnosilacIme && !podnosilacPrezime && !podnosilacNaziv  && operator === 'OR') {
      this.zahtevService.getAllZahtevi().subscribe(
        (response) => {
          this.listaZahteva2Prikaz(response);
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
      'ZahtevNaprednaPretragaDto',
      naprednaDto,
      options
    );

    this.zahtevService.naprednaPretraga(xmlDocument).subscribe(
      (response) => {
        this.listaZahteva2Prikaz(response);
      }
    );
  }

  generisiRDF(zahtevId: string) {
    this.zahtevService.generisiRDF(zahtevId).subscribe(
      (response) => {
        this.previewAndDownload(response, zahtevId, "xml");
      }
    );
  }

  generisiJSON(zahtevId: string) {
    this.zahtevService.generisiJSON(zahtevId).subscribe(
      (response) => {
        this.previewAndDownload(response, zahtevId, "json");
      }
    );
  }

  odbijZahtev(row: any){
    console.log(row.id)
    this.zahtevService.odbijZahtev(row.id).subscribe(
      (response) => {
        this.fetchZahtevi();
    },
    (error) =>{
      this.fetchZahtevi();
    })
  }
}
