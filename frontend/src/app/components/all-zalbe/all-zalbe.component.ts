import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-zalbe',
  templateUrl: './all-zalbe.component.html',
  styleUrls: ['./all-zalbe.component.sass'],
})
export class AllZalbeComponent implements OnInit {
  constructor() {}

  role = 'ROLE_POVERENIK';

  dataSource = [
    {
      brojZalbe: 111,
      brojZahteva: 110,
      organ: 'ime orgaan',
      podnosilac: 'ime podnosioca',
      datumZalbe: '23.3.1313',
      razresena: 'Da',
      razresi: '*****',
    },
    {
      brojZalbe: 111,
      brojZahteva: 110,
      organ: 'ime orgaan',
      podnosilac: 'ime podnosioca',
      datumZalbe: '23.3.1313',
      razresena: 'Ne',
      razresi: '*****',
    },
    {
      brojZalbe: 111,
      brojZahteva: 110,
      organ: 'ime orgaan',
      podnosilac: 'ime podnosioca',
      datumZalbe: '23.3.1313',
      razresena: 'Da',
      razresi: '*****',
    },
  ];

  displayedColumns: string[] = this.selectDisplay();

  selectDisplay(): string[] {
    if (this.role === 'ROLE_KORISNIK')
      return [
        'brojZalbe',
        'brojZahteva',
        'organ',
        'podnosilac',
        'datumZalbe',
        'razresena',
      ];
    else
      return [
        'brojZalbe',
        'brojZahteva',
        'organ',
        'podnosilac',
        'datumZalbe',
        'razresi',
      ];
  }

  ngOnInit(): void {}
}
