import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IzvestajService } from 'src/app/services/izvestaj.service';
import * as txml from 'txml';

@Component({
  selector: 'app-izvestaji',
  templateUrl: './izvestaji.component.html',
  styleUrls: ['./izvestaji.component.sass']
})
export class IzvestajiComponent implements OnInit {

  dataSourceZahtevi = [];
  dataSourceZalbe = [];
  izvestajId = "";

  displayedColumnsZahtevi: string[] = [ 'redniBroj', 'trazilac', 'brojPodnetihZahteva', 'brojUsvojenihZahteva', 
                                        'brojOdbijenihZahteva','brojNerazresenihZahteva']
  
  displayedColumnsZalbe: string[] = [ 'redniBroj', 'trazilac', 'brojPodnetihZalbi', 'brojNepostupanjaZalbi', 
                                        'brojOdbijenihZalbi']
  constructor(
    private izvestajService: IzvestajService,
    private router: Router) { 
      const siteUrl = this.router.url.split('/');
      this.izvestajId = siteUrl[siteUrl.length - 1];
    }

  ngOnInit(): void {
    this.izvestajService.getIzvestaj(this.izvestajId).subscribe(
      (response) => {
        let xmlResponse = response;
        let izvestaj: any = txml.parse(xmlResponse);
        let dataZahtevi = []
        let dataZalbe = []
        izvestaj[1].children[0].children[0].children[0].children.map( i => {
          let gradjanin = izvestaj[1].children[0].children[0]
          let izvestajGradjaninPrikaz = {
            redniBroj: "1.",
            trazilac: "Грађанин",
            brojPodnetihZahteva: gradjanin.children[0].children[0],
            brojUsvojenihZahteva: gradjanin.children[1].children[0],
            brojOdbijenihZahteva: gradjanin.children[2].children[0],
            brojNerazresenihZahteva: gradjanin.children[3].children[0]
          }
          dataZahtevi.push(izvestajGradjaninPrikaz)
          let organizacija = izvestaj[1].children[0].children[1]
          let izvestajOrganizacijaPrikaz = {
            redniBroj: "2.",
            trazilac: "Организација",
            brojPodnetihZahteva: organizacija.children[0].children[0],
            brojUsvojenihZahteva: organizacija.children[1].children[0],
            brojOdbijenihZahteva: organizacija.children[2].children[0],
            brojNerazresenihZahteva: organizacija.children[3].children[0]
          }
          dataZahtevi.push(izvestajOrganizacijaPrikaz)
          let ukupno = izvestaj[1].children[0].children[2]
          let izvestajUkupnoPrikaz = {
            redniBroj: "3.",
            trazilac: "Укупно",
            brojPodnetihZahteva: ukupno.children[0].children[0],
            brojUsvojenihZahteva: ukupno.children[1].children[0],
            brojOdbijenihZahteva: ukupno.children[2].children[0],
            brojNerazresenihZahteva: ukupno.children[3].children[0]
          }
          dataZahtevi.push(izvestajUkupnoPrikaz)
        })
        this.dataSourceZahtevi = dataZahtevi;
        izvestaj[1].children[1].children[0].children[0].children.map( i => {
          let gradjanin = izvestaj[1].children[1].children[0]
          let izvestajGradjaninPrikaz = {
            redniBroj: "1.",
            trazilac: "Грађанин",
            brojPodnetihZalbi: gradjanin.children[0].children[0],
            brojNepostupanjaZalbi: gradjanin.children[1].children[0],
            brojOdbijenihZalbi: gradjanin.children[2].children[0]
          }
          dataZalbe.push(izvestajGradjaninPrikaz)
          let organizacija = izvestaj[1].children[1].children[1]
          let izvestajOrganizacijaPrikaz = {
            redniBroj: "2.",
            trazilac: "Организација",
            brojPodnetihZalbi: organizacija.children[0].children[0],
            brojNepostupanjaZalbi: organizacija.children[1].children[0],
            brojOdbijenihZalbi: organizacija.children[2].children[0]
          }
          dataZalbe.push(izvestajOrganizacijaPrikaz)
          let ukupno = izvestaj[1].children[1].children[2]
          let izvestajUkupnoPrikaz = {
            redniBroj: "3.",
            trazilac: "Укупно",
            brojPodnetihZalbi: ukupno.children[0].children[0],
            brojNepostupanjaZalbi: ukupno.children[1].children[0],
            brojOdbijenihZalbi: ukupno.children[2].children[0]
          }
          dataZalbe.push(izvestajUkupnoPrikaz)
        })
        this.dataSourceZalbe = dataZalbe;
      }
    )
  }

}
