import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-zalbe-odluku-gradjanin',
  templateUrl: './zalbe-odluku-gradjanin.component.html',
  styleUrls: ['./zalbe-odluku-gradjanin.component.sass']
})
export class ZalbeOdlukuGradjaninComponent implements OnInit {

  constructor() { }

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

  ngOnInit(): void {} 

}
