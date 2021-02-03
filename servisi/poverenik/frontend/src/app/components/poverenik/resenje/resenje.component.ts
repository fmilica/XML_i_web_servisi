import { Component, OnInit } from '@angular/core';
import { XonomyResenjeService } from 'src/app/services/xonomy/xonomy-resenje.service';

declare const Xonomy: any;

@Component({
  selector: 'app-resenje',
  templateUrl: './resenje.component.html',
  styleUrls: ['./resenje.component.sass']
})
export class ResenjeComponent implements OnInit {

  constructor(
    private xonomyResenjeService: XonomyResenjeService
  ) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    let elemet = document.getElementById('resenje');
    let specification = this.xonomyResenjeService.resenjeSpecificaion;
    let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                     <res:Resenje xmlns="http://www.w3.org/ns/rdfa#"
                        xmlns:res="http://resenje"
                        xmlns:tipovi="http://tipovi"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:pred="http://www.xml.com/predicate/"
                        xsi:schemaLocation="http://resenje resenje.xsd" 
                        broj_resenja="000-00-0000/0000-00" 
                        datum_resenja="2006-05-04">` +
                        `<res:Opis_zalbe razlog_zalbe="nepostupanje" datum_zahteva="2006-05-04">` +
                        `</res:Opis_zalbe>` +
                        `<res:Odluka>` +
                        `</res:Odluka>` +
                        `<res:Obrazlozenje>` +
                          `<res:Postupak_zalioca prilozene_kopije="true">` +
                            `<res:Podnosenje_zalbe datum_zalbe="2020-05-07">` +
                            `</res:Podnosenje_zalbe>` +
                            `<res:Podnosenje_zahteva datum_zahteva="2020-04-16">` +
                            `</res:Podnosenje_zahteva>` +
                          `</res:Postupak_zalioca>` +
                          `<res:Prosledjivanje_zalbe datum_prosledjivanja="2020-05-11">` +
                          `</res:Prosledjivanje_zalbe>` +
                          `<res:Odgovor_na_zalbu rok_za_odgovor="8">` +
                          `</res:Odgovor_na_zalbu>` +
                          `<res:Razlozi_odluke>` +
                          `</res:Razlozi_odluke>` +
                          `<res:Zalba_na_resenje rok_za_tuzbu="30" taksa_tuzbe="3.9E2" zakon="Закон о управним споровима" sud="Управни суд у Београду">` +
                          `</res:Zalba_na_resenje>` +
                          `<res:Poverenik>` +
                            `<res:Ime property="pred:izdavacIme"></res:Ime>` +
                            `<res:Prezime property="pred:izdavacPrezime"></res:Prezime>` +
                          `</res:Poverenik>` +
                        `</res:Obrazlozenje>` +
                    `</res:Resenje>`
    Xonomy.render(xmlString, elemet, specification);
  }

}
