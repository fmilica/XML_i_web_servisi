import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-zalbe-cutanje',
  templateUrl: './all-zalbe-cutanje.component.html',
  styleUrls: ['./all-zalbe-cutanje.component.sass'],
})
export class AllZalbeCutanjeComponent implements OnInit {
  constructor() {}
  role = 'ROLE_POVERENIK';
  dataSource = [
    {
      brojZalbe: 111,
      brojZahteva: 110,
      organ: 'ime orgaan',
      podnosilac: 'ime podnosioca',
      datumZalbe: '23.3.1313',
      razresi: '****',
    },
    {
      brojZalbe: 111,
      brojZahteva: 110,
      organ: 'ime orgaan',
      podnosilac: 'ime podnosioca',
      datumZalbe: '23.3.1313',
      razresi: '****',
    },
    {
      brojZalbe: 111,
      brojZahteva: 110,
      organ: 'ime orgaan',
      podnosilac: 'ime podnosioca',
      datumZalbe: '23.3.1313',
      razresi: '****',
    },
  ];

  displayedColumns: string[] = this.selectDisplay();

  selectDisplay(): string[] {
    if (this.role == 'ROLE_KORISNIK')
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
