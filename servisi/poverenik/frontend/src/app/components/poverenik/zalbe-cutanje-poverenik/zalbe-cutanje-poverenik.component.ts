import { Component, OnInit } from '@angular/core';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-zalbe-cutanje-poverenik',
  templateUrl: './zalbe-cutanje-poverenik.component.html',
  styleUrls: ['./zalbe-cutanje-poverenik.component.sass']
})
export class ZalbeCutanjePoverenikComponent implements OnInit {

  dataSource = [ ];

  displayedColumns: string[] = ['organVlasti', 'razlogZalbe', 'datumZahteva', 'podaci', 'zalilac','adresa', 'kontaktTelefon', 
                                'datumZalbe', 'mestoZalbe', 'razresena', 'preuzimanje', 'preuzimanjeMeta']

  constructor(
    private zalbaCutanjeService: ZalbaCutanjeService
  ) { }

  ngOnInit(): void {
    this.zalbaCutanjeService.getAllGradjaninZalbeCutanje()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allZalbe: any = txml.parse(xmlResponse);
          let data = []
          allZalbe[1].children.map(zalba => {
            let zalbaPrikaz = {
              id: zalba.attributes.id.substring(20),
              organVlasti: zalba.children[1].children[1].children[0],
              razlogZalbe: zalba.children[1].children[2].children[0],
              datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
              podaci: zalba.children[1].children[3].children[0],
              datumZalbe: zalba.attributes.datum_podnosenja_zalbe,
              mestoZalbe: zalba.attributes.mesto,
              razresena: 'Да',
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
              prezime: ''
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
            data.push(zalbaPrikaz);
          })
          this.dataSource = data;
        }
      )
  }

  generisiPDF(obavestenjeId: string) {
    this.zalbaCutanjeService.generisiPDF(obavestenjeId).subscribe(
      (response) => {
        this.previewAndDownload(response, obavestenjeId, "pdf");
      }
    );
  }

  generisiHTML(obavestenjeId: string) {
    this.zalbaCutanjeService.generisiHTML(obavestenjeId).subscribe(
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
    link.download = "zalba_cutanje_"+id+"."+tip;
    link.click();
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
}
