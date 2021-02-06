import { Component, OnInit } from '@angular/core';
import { ZahtevService } from 'src/app/services/zahtev.service';
import * as txml from 'txml';

@Component({
  selector: 'app-all-zahtevi',
  templateUrl: './all-zahtevi-gradjanin.component.html',
  styleUrls: ['./all-zahtevi-gradjanin.component.sass']
})
export class AllZahteviGradjaninComponent implements OnInit {

  constructor(
    private zahtevService: ZahtevService
  ) { }

  dataSource = [
    /*{
      nazivOrgana: 'ФТН',
      sedisteOrgana: 'Нови Сад',
      obavestenje: 'true',
      uvid: 'false',
      kopija: 'true',
      dostava: 'Голубом писмоношом',
      informacije: 'Извод оцена',
      mesto: 'Нови Сад',
      datum: '23.3.2020.'
    }*/
  ];

  displayedColumns: string[] = [ 'id', 'nazivOrgana', 'sedisteOrgana', 'obavestenje', 'uvid', 'kopija', 'dostava', 'informacije', 
                                'mesto', 'datum', 'status', 'preuzimanje', 'preuzimanjeMeta'];

  ngOnInit(): void {
    this.zahtevService.getAllGradjaninZahtevi().subscribe(
      (response) => {
        let xmlResponse = response
        let allZahtevi: any = txml.parse(xmlResponse);
        let data = [];
        allZahtevi[1].children.map(zahtev => {
          let zahtevPrikaz = {
            id: zahtev.attributes.id.substring(14),
            nazivOrgana: zahtev.children[0].children[0].children[0],
            sedisteOrgana: zahtev.children[0].children[1].children[0],
            obavestenje: 'false',
            uvid: 'false',
            kopija: 'false',
            dostava: 'false',
            informacije: zahtev.children[1].children[2].children[0],
            mesto: zahtev.attributes.mesto,
            datum: zahtev.attributes.datum,
            status: 'Послат'
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
          if(zahtev.attributes.odbijen === 'true' && zahtev.attributes.razresen === 'true') {
            zahtevPrikaz.status = 'Одбијен'
          } else if( zahtev.attributes.odbijen === 'false' && zahtev.attributes.razresen === 'true' ) {
            zahtevPrikaz.status = 'Прихваћен'
          } else {
            zahtevPrikaz.status = 'Послат'
          }
          data.push(zahtevPrikaz);
        })
        this.dataSource = data;
      }
    );
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

}
