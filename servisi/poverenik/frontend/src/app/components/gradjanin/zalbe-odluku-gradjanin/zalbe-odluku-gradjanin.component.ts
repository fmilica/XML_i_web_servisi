import { Component, OnInit } from '@angular/core';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka.service';
import * as txml from 'txml';

@Component({
  selector: 'app-zalbe-odluku-gradjanin',
  templateUrl: './zalbe-odluku-gradjanin.component.html',
  styleUrls: ['./zalbe-odluku-gradjanin.component.sass']
})
export class ZalbeOdlukuGradjaninComponent implements OnInit {

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
      datumZalbe: '23.3.1313',
      mestoZalbe: 'Нови Сад',
      razresena: 'Да'
    }
  ];

  displayedColumns: string[] = ['naziv', 'adresa', 'organVlasti', 'broj', 'godina', 'datumZahteva', 'razlogZalbe',
                                'datumZalbe', 'mestoZalbe','razresena', 'preuzimanje']

  ngOnInit(): void {
    this.zalbaOdlukaService.getAllGradjaninZalbeOdluka()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allZalbe: any = txml.parse(xmlResponse);
          let data = []
          allZalbe[1].children.map(zalba => {
            let zalbaPrikaz = {
              naziv: '',
              adresa: '',
              organVlasti: zalba.children[2].children[0].children[0],
              broj: zalba.children[2].attributes.broj_odluke,
              godina: zalba.children[2].attributes.godina,
              datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
              razlogZalbe: zalba.children[3].children[0].children[0],
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
            data.push(zalbaPrikaz);
          })
          this.dataSource = data;
        }
      )
  } 

}
