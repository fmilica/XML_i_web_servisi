import { Component, OnInit } from '@angular/core';
import { XonomyZalbaCutanjeService } from 'src/app/services/xonomy/xonomy-zalba-cutanje.service';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-cutanje',
  templateUrl: './zalba-cutanje.component.html',
  styleUrls: ['./zalba-cutanje.component.sass'],
})
export class ZalbaCutanjeComponent implements OnInit {
  constructor(private xonomyZalbaCutanjeService: XonomyZalbaCutanjeService) {}

  ngOnInit(): void {}

  ngAfterViewInit() {
    let element = document.getElementById('zalba');
    let specification = this.xonomyZalbaCutanjeService.fakultetSpecification;
    let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                     <zoc:Zalba_cutanje xmlns="http://www.w3.org/ns/rdfa#"
                      xmlns:zoc="http://zalbacutanje"
                      xmlns:tipovi="http://tipovi"
                      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                      xmlns:pred="http://www.xml.com/predicate/"
                      xsi:schemaLocation="http://zalbacutanje zalbacutanje.xsd"
                      mesto="Novi Sad" datum="2020-12-07" id="ID1"
                      
                      vocab="http://www.xml.com/predicate/"
                      about="http://zalbacutanje/ID1"
                      rel="pred:vezanZahtev"
                      href="http://zahtev/ID1"><zoc:Primalac_zalbe><tipovi:Naziv property="pred:primalacNaziv">Поверенику за информације од јавног значаја и заштиту података о личности</tipovi:Naziv><tipovi:Sediste><tipovi:Mesto>Београд</tipovi:Mesto><tipovi:Ulica>Булевар краља Александра</tipovi:Ulica><tipovi:Ulicni_broj>15</tipovi:Ulicni_broj></tipovi:Sediste></zoc:Primalac_zalbe><zoc:Zalba><zoc:Osnova_zalbe><zoc:Clan>22.</zoc:Clan><zoc:Zakon>Закона о слободном приступу информацијама од јавног ѕначаја</zoc:Zakon></zoc:Osnova_zalbe><zoc:Naziv_organa></zoc:Naziv_organa><zoc:Razlog_zalbe></zoc:Razlog_zalbe><zoc:Datum>${new Date().toISOString().slice(0, 10)}</zoc:Datum><zoc:Podaci_o_zahtevu></zoc:Podaci_o_zahtevu></zoc:Zalba><zoc:Podnosilac_zalbe><tipovi:Ime property="pred:podnosilacIme"></tipovi:Ime><tipovi:Prezime property="pred:podnosilacPrezime"></tipovi:Prezime><tipovi:Adresa><tipovi:Mesto></tipovi:Mesto><tipovi:Ulica></tipovi:Ulica><tipovi:Ulicni_broj></tipovi:Ulicni_broj></tipovi:Adresa><tipovi:Kontakt_podaci></tipovi:Kontakt_podaci></zoc:Podnosilac_zalbe></zoc:Zalba_cutanje>`;

    Xonomy.render(xmlString, element, specification);
  }
}
