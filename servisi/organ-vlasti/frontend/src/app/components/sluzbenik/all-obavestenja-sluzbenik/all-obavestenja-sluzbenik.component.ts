import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-obavestenja-sluzbenik',
  templateUrl: './all-obavestenja-sluzbenik.component.html',
  styleUrls: ['./all-obavestenja-sluzbenik.component.sass']
})
export class AllObavestenjaSluzbenikComponent implements OnInit {

  constructor() { }

  
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
  }

}
