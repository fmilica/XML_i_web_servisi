import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-zahtevi-sluzbenik',
  templateUrl: './all-zahtevi-sluzbenik.component.html',
  styleUrls: ['./all-zahtevi-sluzbenik.component.sass']
})
export class AllZahteviSluzbenikComponent implements OnInit {

  constructor() { }

  dataSource = [
    {
      nazivOrgana: 'ФТН',
      sedisteOrgana: 'Нови Сад',
      obavestenje: 'труе',
      uvid: 'фалсе',
      kopija: 'труе',
      informacije: 'Извод оцена',
      dostava: 'Голубом писмоношом',
      mesto: 'Нови Сад',
      datum: '23.3.2020.',
      trazilacInformacija: 'Властислав Јаковљевић',
      kontaktTelefon: '0632258394'
    }
  ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'obavestenje', 'uvid', 'kopija', 'dostava', 
                                'informacije', 'mesto', 'datum', 'trazilacInformacija','kontaktTelefon', 'preuzimanje'];

  ngOnInit(): void {
  }

}
