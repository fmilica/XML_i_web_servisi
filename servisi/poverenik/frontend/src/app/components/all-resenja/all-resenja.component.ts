import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-resenja',
  templateUrl: './all-resenja.component.html',
  styleUrls: ['./all-resenja.component.sass']
})
export class AllResenjaComponent implements OnInit {

  constructor() { }

  displayedColumns: string[] = [
    'brojResenja',
    'podnosilacZalbe',
    'organ',
    'datumZahteva',
    'datumResenja',
    'razlogZalbe',
    'preuzimanje'
  ];

  dataSource = [
    {
      brojResenja: 111,
      podnosilacZalbe: 110,
      organ: 'ime orgaan',
      datumZahteva: '23.3.1313',
      datumResenja: '23.3.1222',
      razlogZalbe: 'nepostupanje'
    },
    {
      brojResenja: 111,
      podnosilacZalbe: 110,
      organ: 'ime orgaan',
      datumZahteva: '23.3.1313',
      datumResenja: '23.3.1222',
      razlogZalbe: 'nepostupanje'
    },
    {
      brojResenja: 111,
      podnosilacZalbe: 110,
      organ: 'ime orgaan',
      datumZahteva: '23.3.1313',
      datumResenja: '23.3.1222',
      razlogZalbe: 'nepostupanje'
    },
  ];

  ngOnInit(): void {
  }

}
