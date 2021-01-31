import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-zalbe-odluka',
  templateUrl: './all-zalbe-odluka.component.html',
  styleUrls: ['./all-zalbe-odluka.component.sass'],
})
export class AllZalbeOdlukaComponent implements OnInit {
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

  role = 'ROLE_KORISNIK';
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

  constructor() {}

  ngOnInit(): void {}
}
