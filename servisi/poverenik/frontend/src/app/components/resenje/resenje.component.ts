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
                        datum_resenja="2006-05-04" 
                        id="ID1"
                        
                        vocab="http://www.xml.com/predicate/"
                        about="http://resenje/ID1">` +
                        `<res:Opis_zalbe razlog_zalbe="nepostupanje" datum_zahteva="2006-05-04">` +
                          `<res:Podnosilac_zalbe>` +
                            `<res:Ime property="pred:zalilacIme">Михајло</res:Ime>` +
                            `<res:Prezime property="pred:zalilacPrezime">Грујић</res:Prezime>` +
                          `</res:Podnosilac_zalbe>` +
                          `<res:Organ_vlasti>` +
                          `<tipovi:Naziv property="pred:optuzeniNaziv">Учитељски факултет у Призрену</tipovi:Naziv>` +
                          `<tipovi:Sediste>` +
                            `<tipovi:Mesto>Лепосавић</tipovi:Mesto>` +
                            `<tipovi:Ulica>Немањина</tipovi:Ulica>` +
                            `<tipovi:Ulicni_broj>23</tipovi:Ulicni_broj>` +
                          `</tipovi:Sediste>` +
                          `</res:Organ_vlasti>` +
                          `<res:Zakonska_osnova_resenja>` +
                            `<res:Zakonska_osnova>` +
                              `<res:Clan>35</res:Clan>` +
                              `<res:Stav>1</res:Stav>` +
                              `<res:Tacka>5</res:Tacka>` +
                              `<res:Zakon>Закона о слободном приступу информацијама од јавног значаја</res:Zakon>` +
                            `</res:Zakonska_osnova>` +
                          `</res:Zakonska_osnova_resenja>` +
                        `</res:Opis_zalbe>` +
                    `</res:Resenje>`
    Xonomy.render(xmlString, elemet, specification);
  }

}
