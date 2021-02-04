import { Component, OnInit } from '@angular/core';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka.service';
import * as txml from 'txml';

@Component({
  selector: 'app-zalbe-odluku-poverenik',
  templateUrl: './zalbe-odluku-poverenik.component.html',
  styleUrls: ['./zalbe-odluku-poverenik.component.sass']
})
export class ZalbeOdlukuPoverenikComponent implements OnInit {

  constructor(
    private zalbaOdlukaService: ZalbaOdlukaService
  ) { }

  dataSource = [
    {
      naziv: 'Пера Перић',
      adresa: 'Железничка 23, Нови Сад',
      organVlasti: 'ФТН',
      broj: '1',
      godina: '2021.',
      datumZahteva: '2021-02-02',
      razlogZalbe: 'није ми дао информације о положеним предметима',
      nazivPodnosioca: 'Пера Перић',
      adresaPodnosioca: 'Железничка 23, Нови Сад',
      datumZalbe: '23.3.1313',
      mestoZalbe: 'Нови Сад',
      razresena: 'Да'
    }
  ];

  displayedColumns: string[] = ['naziv', 'adresa', 'organVlasti', 'broj', 'godina', 'datumZahteva', 'razlogZalbe',
                                'nazivPodnosioca', 'adresaPodnosioca', 'datumZalbe', 'mestoZalbe','razresena', 'preuzimanje', 'preuzimanjeMeta']

  ngOnInit(): void {
    this.zalbaOdlukaService.getAllZalbeOdluka()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allZalbe: any = txml.parse(xmlResponse);
          let data = []
          allZalbe[1].children.map(zalba => {
            let zalbaPrikaz = {
              naziv: '',
              adresa: '',
              id: zalba.attributes.id.substring(22),
              organVlasti: zalba.children[2].children[0].children[0],
              broj: zalba.children[2].attributes.broj_odluke,
              godina: zalba.children[2].attributes.godina,
              datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
              razlogZalbe: zalba.children[3].children[0].children[0],
              nazivPodnosioca: '',
              adresaPodnosioca: '',
              datumZalbe: zalba.attributes.datum_podnosenja_zalbe,
              mestoZalbe: zalba.attributes.mesto_podnosenja_zalbe,
              razresena: 'Да'
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
            data.push(zalbaPrikaz);
          })
          this.dataSource = data;
        }
      )
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
}
