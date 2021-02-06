import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka.service';
import * as txml from 'txml';
import * as JsonToXML from 'js2xmlparser';
import { ZalbaOdlukaNaprednaPretragaDto } from 'src/app/model/zalba-odluka-napredna-pretraga-dto';
import { ZalbaDto } from 'src/app/model/zalba-dto.model';
import { ZahtevDto } from 'src/app/model/zahtev-dto.model';
import { ResenjeService } from 'src/app/services/resenje.service';
import { Router } from '@angular/router';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-zalbe-odluku-poverenik',
  templateUrl: './zalbe-odluku-poverenik.component.html',
  styleUrls: ['./zalbe-odluku-poverenik.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ZalbeOdlukuPoverenikComponent implements OnInit {

  //formе za pretragu
  obicnaForm: FormGroup;
  metaDataForm: FormGroup;

  fetchedZahtev = {
    nazivOrgana: "",
    sedisteOrgana: "",
    informacije: "",
    mesto: "",
    datum: ""
  }

  expandedElement: any | null;

  constructor(
    private zalbaOdlukaService: ZalbaOdlukaService,
    private resenjeService: ResenjeService,
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

  displayedColumns: string[] = ['naziv', 'adresa', 'organVlasti', 'broj', 'godina', 'datumZahteva', 'razlogZalbe',
                                'nazivPodnosioca', 'adresaPodnosioca', 'datumZalbe', 'mestoZalbe','razresena', 'preuzimanje', 'preuzimanjeMeta']

  ngOnInit(): void {
    this.zalbaOdlukaService.getAllZalbeOdluka()
      .subscribe(
        (response) => {
          this.listaZalbiOdluka2Prikaz(response);
        }
      )
  }

  listaZalbiOdluka2Prikaz(response) {
    let xmlResponse = response;
    let allZalbe: any = txml.parse(xmlResponse);
    let data = []
    allZalbe[1].children.map(zalba => {
      let emailPath = zalba.children[4].attributes.href.split('/');
      let zalbaPrikaz = {
        naziv: '',
        adresa: '',
        id: zalba.attributes.id.substring(22),
        userEmail: emailPath[3],
        organVlasti: zalba.children[2].children[0].children[0],
        broj: zalba.children[2].attributes.broj_odluke,
        godina: zalba.children[2].attributes.godina,
        datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
        razlogZalbe: zalba.children[3].children[0].children[0],
        nazivPodnosioca: '',
        adresaPodnosioca: '',
        datumZalbe: zalba.attributes.datum_podnosenja_zalbe,
        mestoZalbe: zalba.attributes.mesto_podnosenja_zalbe,
        razresena: zalba.attributes.razresen,
        izjasnjena: zalba.attributes.izjasnjen,
        prekinuta: zalba.attributes.prekinut,
        zahtev: zalba.attributes.href.substring(14),
        status: 'razresena'
      }
      //podaci o zaliocu
      if (zalba.children[1].children.length === 3) {
        // ima ime i prezime
        zalbaPrikaz.naziv = zalba.children[1].children[0].children[0] + ' ' + zalba.children[1].children[1].children[0];

        zalbaPrikaz.adresa = zalba.children[1].children[2].children[1].children[0] + ' ' + 
        zalba.children[1].children[2].children[2].children[0] + ', ' + 
        zalba.children[1].children[2].children[0].children[0]
      } else {
        // ima naziv
        zalbaPrikaz.naziv = zalba.children[1].children[0].children[0] ;

        zalbaPrikaz.adresa = zalba.children[1].children[1].children[1].children[0] + ' ' + 
        zalba.children[1].children[1].children[2].children[0] + ', ' + 
        zalba.children[1].children[1].children[0].children[0]
      }
       //podaci o podnosiocu zalbe
       if (zalba.children[4].children.length === 4) {
        // ima ime i prezime
        zalbaPrikaz.nazivPodnosioca = zalba.children[4].children[0].children[0] + ' ' + zalba.children[4].children[1].children[0];

        zalbaPrikaz.adresaPodnosioca = zalba.children[4].children[2].children[1].children[0] + ' ' + 
        zalba.children[4].children[2].children[2].children[0] + ', ' + 
        zalba.children[4].children[2].children[0].children[0]
      } else {
        // ima naziv
        zalbaPrikaz.nazivPodnosioca = zalba.children[4].children[0].children[0] ;

        zalbaPrikaz.adresaPodnosioca = zalba.children[4].children[1].children[1].children[0] + ' ' + 
        zalba.children[4].children[1].children[2].children[0] + ', ' + 
        zalba.children[4].children[1].children[0].children[0]
      }
      // postavljanje akcije na osnovu atributa
      if(zalbaPrikaz.prekinuta === 'false' && zalbaPrikaz.izjasnjena === 'false' && zalbaPrikaz.razresena === 'false') {
        zalbaPrikaz.status = 'posaljiNaIzjasnjenje';
      } else if(zalbaPrikaz.prekinuta === 'false' && zalbaPrikaz.izjasnjena === 'true' && zalbaPrikaz.razresena === 'false') {
        zalbaPrikaz.status = 'kreirajResenje'
      } else if(zalbaPrikaz.prekinuta === 'false' && zalbaPrikaz.izjasnjena === 'true' && zalbaPrikaz.razresena === 'true') {
        zalbaPrikaz.status = 'razresena'
      } else if(zalbaPrikaz.prekinuta === 'true') {
        zalbaPrikaz.status = 'odustao'
      }
      data.push(zalbaPrikaz);
    })
    this.dataSource = data;
  }

  posaljiNaIzjasnjenje(row: any) {
    // soap poziv salje zalbu organu vlasti
  }

  createResenje(row: any) {
    let zalbaDto: ZalbaDto = {
      id : row.id,
      fullId: 'http://zalbaodbijanje/' + row.id,
      datumPodnosenja : row.datumZalbe
    }
    let zahtevDto : ZahtevDto = {
      id: row.zahtev,
      datumPodnosenja: row.datumZahteva,
      userEmail: row.userEmail
    }
    this.resenjeService.odabraniZahtev.next(zahtevDto);
    this.resenjeService.odabranaZalba.next(zalbaDto);
    this.resenjeService.novo_resenje.next(true)
    this.router.navigate(['resenje']);
  }

  generisiPDF(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiPDF(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "pdf");
      }
    );
  }

  generisiHTML(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiHTML(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "html");
      }
    );
  }

  previewAndDownload(response: any, id: string, tip: string){
    let type = "application/"+tip;
    let blob = new Blob([response], { type: type});
    let url = window.URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = "zalba_odbijanje_"+id+"."+tip;
    link.click();
  }

  obicnaPretraga() {
    let unos = this.obicnaForm.get('sve').value;
    if(!unos) {
      this.zalbaOdlukaService.getAllZalbeOdluka().subscribe(
        (response) => {
          this.listaZalbiOdluka2Prikaz(response);
        }
      );
    } else {
      this.zalbaOdlukaService.obicnaPretraga(unos)
      .subscribe( response => {
        this.listaZalbiOdluka2Prikaz(response)
      })
    }
  }

  metapodaciPretraga() {
    let naprednaDto: ZalbaOdlukaNaprednaPretragaDto = {
      PrimalacNaziv: '?primalacNaziv',
      PodnosilacIme: '?podnosilacIme',
      PodnosilacPrezime: '?podnosilacPrezime',
      PodnosilacNaziv: '?podnosilacNaziv',
      VezanGradjanin: '?vezanGradjanin',
      Operator: 'AND'
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

    let vezanGradjanin = this.metaDataForm.get('vezaniGradjanin').value;
    if(vezanGradjanin) {
      naprednaDto.VezanGradjanin = 'http://korisnik/' + vezanGradjanin
    }

    let operator = this.metaDataForm.get('operator').value;
    if(operator) {
      naprednaDto.Operator = operator
    }

    if(!primalacNaziv && !podnosilacIme && !podnosilacPrezime && !podnosilacNaziv) {
      this.zalbaOdlukaService.getAllZalbeOdluka().subscribe(
        (response) => {
          this.listaZalbiOdluka2Prikaz(response);
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
      'ZalbaOdlukaNaprednaPretragaDto',
      naprednaDto,
      options
    );

    this.zalbaOdlukaService.naprednaPretraga(xmlDocument).subscribe(
      (response) => {
        this.listaZalbiOdluka2Prikaz(response);
      });
  }

  generisiRDF(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiRDF(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "xml");
      }
    );
  }

  generisiJSON(zalbaOdlukaId: string) {
    this.zalbaOdlukaService.generisiJSON(zalbaOdlukaId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaOdlukaId, "json");
      }
    );
  }

  fetchZahtev(zahtevId: string){
    //TODO dobaviti zahtev preko SOAP i odkomentarisati linije u html
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
  }
}
