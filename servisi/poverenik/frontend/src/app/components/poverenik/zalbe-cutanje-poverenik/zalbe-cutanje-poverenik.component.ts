import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-zalbe-cutanje-poverenik',
  templateUrl: './zalbe-cutanje-poverenik.component.html',
  styleUrls: ['./zalbe-cutanje-poverenik.component.sass']
})
export class ZalbeCutanjePoverenikComponent implements OnInit {

  dataSource = [
    {
      organVlasti: 'ФТН',
      razlogZalbe: 'није поступио у целости',
      datumZahteva: '2021-02-02',
      podaci: 'Захтеавне информације о положеним предметима',
      naziv: 'Пера Перић',
      adresa: 'Железничка 23, Нови Сад',
      kontaktTelefon: '063552297',
      datumZalbe: '23.3.1313',
      mestoZalbe: 'Нови Сад',
      razresena: 'Да'
    }
  ];

  displayedColumns: string[] = ['organVlasti', 'razlogZalbe', 'datumZahteva', 'podaci', 'naziv','adresa', 'kontaktTelefon', 
                                'datumZalbe', 'mestoZalbe', 'razresena', 'preuzimanje']

  constructor() { }

  ngOnInit(): void {
  }

}
