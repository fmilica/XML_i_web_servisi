import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-zahtevi',
  templateUrl: './all-zahtevi-gradjanin.component.html',
  styleUrls: ['./all-zahtevi-gradjanin.component.sass']
})
export class AllZahteviGradjaninComponent implements OnInit {

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
      datum: '23.3.2020.'
    }
  ];

  displayedColumns: string[] = ['nazivOrgana', 'sedisteOrgana', 'obavestenje', 'uvid', 'kopija', 'dostava', 'informacije', 'mesto', 'datum'];

  ngOnInit(): void {
  }

}
