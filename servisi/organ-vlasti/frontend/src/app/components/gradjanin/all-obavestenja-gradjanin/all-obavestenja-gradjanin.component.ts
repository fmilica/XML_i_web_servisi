import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-obavestenja',
  templateUrl: './all-obavestenja-gradjanin.component.html',
  styleUrls: ['./all-obavestenja-gradjanin.component.sass']
})
export class AllObavestenjaGradjaninComponent implements OnInit {

  constructor() { }

  dataSource = [
    {
      nazivOrgana: 'ФТН',
      sedisteOrgana: 'Нови Сад',
      brojPredmeta: '1',
      datum: '26.3.2020.',
      datumZahteva: '23.3.2020.',
      informacije: 'Извод оцена',
      danCasovi: '30.3.2020. у 13:00',
      vreme: '13:00-15:00',
      adresaOrgana: 'Kраља Петра 36',
      brojKancelarije: '5a'
    }
  ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'brojPredmeta', 'datum', 'datumZahteva', 'informacije','danCasovi', 'vreme', 'adresaOrgana', 'brojKancelarije', 'preuzimanje'];


  ngOnInit(): void {
  }

}
