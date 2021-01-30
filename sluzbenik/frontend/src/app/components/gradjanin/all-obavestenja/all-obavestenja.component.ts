import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-obavestenja',
  templateUrl: './all-obavestenja.component.html',
  styleUrls: ['./all-obavestenja.component.sass']
})
export class AllObavestenjaComponent implements OnInit {

  constructor() { }

  dataSource = [
    {
      nazivOrgana: 'ФТН',
      sedisteOrgana: 'Нови Сад',
      brojPredmeta: '1',
      datum: '26.3.2020.',
      //imePrezime: 'Властислав Јаковљевић',
      //adresa: 'Железничка 23, Нови Сад',
      datumZahteva: '23.3.2020.',
      informacije: 'Извод оцена',
      danCasovi: '30.3.2020. у 13:00',
      vreme: '13:00-15:00',
      adresaOrgana: 'Kраља Петра 36',
      brojKancelarije: '5a'
    }
  ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'brojPredmeta', 'datum'/*, 'imePrezime', 'adresa',*/, 'datumZahteva', 'informacije','danCasovi', 'vreme', 'adresaOrgana', 'brojKancelarije'];


  ngOnInit(): void {
  }

}
