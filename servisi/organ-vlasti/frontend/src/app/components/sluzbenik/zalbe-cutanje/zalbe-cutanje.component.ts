import { Component, OnInit } from '@angular/core';
import * as txml from 'txml';
import * as JsonToXML from 'js2xmlparser';
import { FormGroup, FormControl } from '@angular/forms';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje-service';
import { ZalbaCutanjeNaprednaPretragaDto } from 'src/app/model/zalba-cutanje-napredna-pretraga-dto';
import { ZahtevService } from 'src/app/services/zahtev.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-zalbe-cutanje',
  templateUrl: './zalbe-cutanje.component.html',
  styleUrls: ['./zalbe-cutanje.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ZalbeCutanjeComponent implements OnInit {

  dataSource = [ ];

  displayedColumns: string[] = ['organVlasti', 'razlogZalbe', 'datumZahteva', 'podaci', 'zalilac','adresa', 'kontaktTelefon', 
                                'datumZalbe', 'mestoZalbe', 'status', 'preuzimanje', 'preuzimanjeMeta']


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
    private zalbaCutanjeService: ZalbaCutanjeService,
    private zahtevService: ZahtevService,
    private izjasnjenjeDialog: MatDialog,
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

  ngOnInit(): void {
    this.zalbaCutanjeService.getAllZalbeCutanje()
      .subscribe(
        (response) => {
          this.listaZalbiCutanje2Prikaz(response)
        }
      )
  }

  listaZalbiCutanje2Prikaz(response) {
    let xmlResponse = response;
    let allZalbe: any = txml.parse(xmlResponse);
    let data = []
    allZalbe[1].children.map(zalba => {
      let zalbaPrikaz = {
        id: zalba.attributes.id.substring(20),
        tipZalbe: 'zalbacutanje',
        organVlasti: zalba.children[1].children[1].children[0],
        razlogZalbe: zalba.children[1].children[2].children[0],
        datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
        podaci: zalba.children[1].children[3].children[0],
        datumZalbe: zalba.attributes.datum_podnosenja_zalbe,
        mestoZalbe: zalba.attributes.mesto,
        razresena: zalba.attributes.razresen,
        izjasnjena: zalba.attributes.izjasnjen,
        prekinuta: zalba.attributes.prekinut,
        ceka: zalba.attributes.ceka,
        status: '',
        zalilac: '',
        adresa: '',
        kontaktTelefon: '',
        //rascepkana adresa da je ne spajamo i ne razdvajamo stalno
        mestoTrazioc: '',
        ulicaTrazioc: '',
        brojTrazioc: '',
        //naziv i ime i prezime
        naziv: '',
        ime: '',
        prezime: '',
        zahtev: zalba.attributes.href.substring(14)
      }
      if (zalba.children[1].children[4].children.length === 4) {
        // ima ime i prezime
        zalbaPrikaz.zalilac = zalba.children[1].children[4].children[0].children[0] + ' ' + zalba.children[1].children[4].children[1].children[0];
        zalbaPrikaz.ime = zalba.children[1].children[4].children[0].children[0];
        zalbaPrikaz.prezime = zalba.children[1].children[4].children[1].children[0];

        zalbaPrikaz.adresa = zalba.children[1].children[4].children[2].children[1].children[0] + ' ' + 
          zalba.children[1].children[4].children[2].children[2].children[0] + ', ' + 
          zalba.children[1].children[4].children[2].children[0].children[0]
        zalbaPrikaz.mestoTrazioc = zalba.children[1].children[4].children[2].children[0].children[0]
        zalbaPrikaz.ulicaTrazioc = zalba.children[1].children[4].children[2].children[1].children[0]
        zalbaPrikaz.brojTrazioc = zalba.children[1].children[4].children[2].children[2].children[0]
      
        zalbaPrikaz.kontaktTelefon = zalba.children[1].children[4].children[3].children[0]
      } else {
        // ima naziv
        zalbaPrikaz.zalilac = zalba.children[1].children[4].children[0].children[0];
        zalbaPrikaz.naziv = zalba.children[1].children[4].children[0].children[0];

        zalbaPrikaz.adresa = zalba.children[1].children[4].children[1].children[1].children[0] + ' ' + 
          zalba.children[1].children[4].children[1].children[2].children[0] + ', ' + 
          zalba.children[1].children[4].children[1].children[0].children[0]
        zalbaPrikaz.mestoTrazioc = zalba.children[1].children[4].children[1].children[0].children[0]
        zalbaPrikaz.ulicaTrazioc = zalba.children[1].children[4].children[1].children[1].children[0]
        zalbaPrikaz.brojTrazioc = zalba.children[1].children[4].children[1].children[2].children[0]
      
        zalbaPrikaz.kontaktTelefon = zalba.children[1].children[4].children[2].children[0]
      }
      if(zalbaPrikaz.razresena === 'true') {
        zalbaPrikaz.status = 'razresena';
      } else if (zalbaPrikaz.izjasnjena === 'false') {
        zalbaPrikaz.status = 'izjasniSe'
      } else if(zalbaPrikaz.izjasnjena === 'true') {
        zalbaPrikaz.status = 'izjasnjen'
      }
      data.push(zalbaPrikaz);
    })
    this.dataSource = data;
  }

  generisiPDF(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiPDF(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "pdf");
      }
    );
  }

  generisiHTML(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiHTML(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "html");
      }
    );
  }

  previewAndDownload(response: any, id: string, tip: string){
    let type = "application/"+tip;
    let blob = new Blob([response], { type: type});
    let url = window.URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = "zalba_cutanje_"+id+"."+tip;
    link.click();
  }

  obicnaPretraga() {
    let unos = this.obicnaForm.get('sve').value;
    if(!unos) {
      this.zalbaCutanjeService.getAllZalbeCutanje().subscribe(
        (response) => {
          this.listaZalbiCutanje2Prikaz(response);
        }
      );
    } else {
      this.zalbaCutanjeService.obicnaPretraga(unos)
      .subscribe( response => {
        this.listaZalbiCutanje2Prikaz(response)
      })
    }
  }

  metapodaciPretraga() {
    let naprednaDto: ZalbaCutanjeNaprednaPretragaDto = {
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
      this.zalbaCutanjeService.getAllZalbeCutanje().subscribe(
        (response) => {
          this.listaZalbiCutanje2Prikaz(response);
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
      'ZalbaCutanjeNaprednaPretragaDto',
      naprednaDto,
      options
    );

    this.zalbaCutanjeService.naprednaPretraga(xmlDocument).subscribe(
      (response) => {
        this.listaZalbiCutanje2Prikaz(response);
      }
    );
  }

  generisiRDF(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiRDF(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "xml");
      }
    );
  }

  generisiJSON(zalbaCutanjeId: string) {
    this.zalbaCutanjeService.generisiJSON(zalbaCutanjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, zalbaCutanjeId, "json");
      }
    );
  }

  fetchZahtev(zahtevId: string){
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
  openDialog(row: any): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = { zalbaId: row.id, tipZalbe: row.tipZalbe };
    dialogConfig.width = '900px';
    const dialogRef = this.izjasnjenjeDialog.open(DialogComponent, dialogConfig);
  
    dialogRef.afterClosed().subscribe(value => {
      this.zalbaCutanjeService.getAllZalbeCutanje()
      .subscribe(
        (response) => {
          this.listaZalbiCutanje2Prikaz(response)
        }
      )
    });
  }

}
