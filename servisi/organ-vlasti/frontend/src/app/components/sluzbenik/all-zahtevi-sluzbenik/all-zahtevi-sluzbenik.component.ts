import { Component, OnInit } from '@angular/core';
import { ZahtevService } from 'src/app/services/zahtev.service';
import * as txml from 'txml';

@Component({
  selector: 'app-all-zahtevi-sluzbenik',
  templateUrl: './all-zahtevi-sluzbenik.component.html',
  styleUrls: ['./all-zahtevi-sluzbenik.component.sass']
})
export class AllZahteviSluzbenikComponent implements OnInit {

  constructor(
    private zahtevService: ZahtevService
  ) { }

  dataSource = [ ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'obavestenje', 'uvid', 'kopija', 'dostava', 'informacije',
                                'mesto', 'datum', 'trazilacInformacija','adresaTrazioca', 'kontaktTelefon', 'preuzimanje'];

  ngOnInit(): void {
    this.zahtevService.getAllZahtevi().subscribe(
      (response) => {
        let xmlResponse = response
        let allZahtevi: any = txml.parse(xmlResponse);
        let data = [];
        allZahtevi[1].children.map(zahtev => {
          console.log(zahtev)
          let zahtevPrikaz = {
            nazivOrgana: zahtev.children[0].children[0].children[0],
            sedisteOrgana: zahtev.children[0].children[1].children[0],
            obavestenje: 'false',
            uvid: 'false',
            kopija: 'false',
            dostava: 'false',
            informacije: zahtev.children[1].children[2].children[0],
            mesto: zahtev.attributes.mesto,
            datum: zahtev.attributes.datum,
            trazilacInformacija: zahtev.children[2].children[0].children[0] + ' ' + zahtev.children[2].children[1].children[0],
            adresaTrazioca: zahtev.children[2].children[2].children[1].children[0] + ' ' 
            + zahtev.children[2].children[2].children[2].children[0]  + ', '  + zahtev.children[2].children[2].children[0].children[0],
            kontaktTelefon: zahtev.children[2].children[3].children[0]
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
    );
  }

}
