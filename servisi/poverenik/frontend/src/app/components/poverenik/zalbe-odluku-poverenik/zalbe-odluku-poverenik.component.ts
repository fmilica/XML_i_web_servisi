import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-zalbe-odluku-poverenik',
  templateUrl: './zalbe-odluku-poverenik.component.html',
  styleUrls: ['./zalbe-odluku-poverenik.component.sass']
})
export class ZalbeOdlukuPoverenikComponent implements OnInit {

  dataSource = [
    {
      naziv: 'Пера Перић',
      adresa: 'Железничка 23, Нови Сад',
      organVlasti: 'ФТН',
      broj: '1',
      godina: '2021.',
      datumZahteva: '2021-02-02',
      razlogZalbe: 'није ми дао информације о положеним предметима',
      nazivPodnosioca: 'Пера Перић',
      adresaPodnosioca: 'Железничка 23, Нови Сад',
      datumZalbe: '23.3.1313',
      mestoZalbe: 'Нови Сад',
      razresena: 'Да'
    }
  ];

  displayedColumns: string[] = ['naziv', 'adresa', 'organVlasti', 'broj', 'godina', 'datumZahteva', 'razlogZalbe',
                                'nazivPodnosioca', 'adresaPodnosioca', 'datumZalbe', 'mestoZalbe','razresena', 'preuzimanje']

  constructor() { }

  ngOnInit(): void {
  }

}
