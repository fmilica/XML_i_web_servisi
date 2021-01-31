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
      obavestenje: 'true',
      uvid: 'false',
      kopija: 'true',
      dostava: 'Голубом писмоношом',
      informacije: 'Извод оцена',
      mesto: 'Нови Сад',
      datum: '23.3.2020.',
      trazilacInformacija: 'Властислав Јаковљевић',
      adresaTrazioca: 'Железничка 23, Нови Сад',
      kontaktTelefon: '0632258394'
    }
  ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'obavestenje', 'uvid', 'kopija', 'dostava', 'informacije',
                                'mesto', 'datum', 'trazilacInformacija','adresaTrazioca', 'kontaktTelefon', 'preuzimanje'];

  ngOnInit(): void {
  }

}
