import { Component, OnInit } from '@angular/core';
import { ObavestenjeService } from 'src/app/services/obavestenje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-all-obavestenja-sluzbenik',
  templateUrl: './all-obavestenja-sluzbenik.component.html',
  styleUrls: ['./all-obavestenja-sluzbenik.component.sass']
})
export class AllObavestenjaSluzbenikComponent implements OnInit {

  constructor(
    private obavestenjeService: ObavestenjeService
  ) { }

  
  dataSource = [
    {
      nazivOrgana: 'ФТН',
      sedisteOrgana: 'Нови Сад',
      brojPredmeta: '1',
      datum: '26.3.2020.',
      imePrezime: 'Властислав Јаковљевић',
      adresa: 'Железничка 23, Нови Сад',
      datumZahteva: '23.3.2020.',
      informacije: 'Извод оцена'
    }
  ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'brojPredmeta', 'datum', 'imePrezime', 'adresa', 'datumZahteva', 'informacije', 'preuzimanje'];

  ngOnInit(): void {
    this.obavestenjeService.getAllObavestenja()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allObavestenja: any =  txml.parse(xmlResponse);
          let data = []
          allObavestenja[1].children.map(obavestenje => {
            console.log(obavestenje)
            let zahtevPrikaz = {
              nazivOrgana: 'ФТН',
              sedisteOrgana: 'Нови Сад',
              brojPredmeta: '1',
              datum: '26.3.2020.',
              imePrezime: 'Властислав Јаковљевић',
              adresa: 'Железничка 23, Нови Сад',
              datumZahteva: '23.3.2020.',
              informacije: 'Извод оцена'
            }
          })
        }
      )
  }

}
