import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-zalbe-cutanje-gradjanin',
  templateUrl: './zalbe-cutanje-gradjanin.component.html',
  styleUrls: ['./zalbe-cutanje-gradjanin.component.sass']
})
export class ZalbeCutanjeGradjaninComponent implements OnInit {

  constructor() { }
  dataSource = [
    {
      organVlasti: 'ФТН',
      razlogZalbe: 'није поступио у целости',
      datumZahteva: '2021-02-02',
      podaci: 'Захтеавне информације о положеним предметима',
      datumZalbe: '23.3.1313',
      mestoZalbe: 'Нови Сад',
      razresena: 'Да'
    }
  ];

  displayedColumns: string[] = ['organVlasti', 'razlogZalbe', 'datumZahteva', 'podaci', 'datumZalbe', 'mestoZalbe',
                                'razresena', 'preuzimanje']

  ngOnInit(): void {}

}
