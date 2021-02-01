import { Injectable } from '@angular/core';

declare const Xonomy: any;

const zahtev = `xmlns:zoc="http://zahtev"`;
const tipovi = `xmlns:tipovi="http://tipovi"`;
@Injectable({
  providedIn: 'root'
})
export class XonomyZahtevService {

  constructor() { }

  public zahtevSpecification = {
    validate: function (jsElement) {
      //Validate the element:
      let elementSpec = this.elements[jsElement.name];
      if (elementSpec.validate) elementSpec.validate(jsElement);
      //Cycle through the element's attributes:
      for (let i = 0; i < jsElement.attributes.length; i++) {
        let jsAttribute = jsElement.attributes[i];
        let attributeSpec = elementSpec.attributes[jsAttribute.name];
        if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
      };
      //Cycle through the element's children:
      for (let i = 0; i < jsElement.children.length; i++) {
        let jsChild = jsElement.children[i];
        if (jsChild.type == "element") { //if element
          this.validate(jsChild); //recursion
        }
      }
    },
    elements: {
      //ZAHTEV
      'zahtev:Zahtev': {
        attributes: {
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          mesto: {
            title: 'Место подношења захтева',
            validate: function(jsAttribute){
              //Make sure item/@mesto is not an empty string:
              if(jsAttribute.value=="") {
                Xonomy.warnings.push({
                htmlID: jsAttribute.htmlID,
                text: "Место је обавезан атрибут."}
              );
            }   
          },        
            asker: Xonomy.askString,
          },
          id: {
            isInvisible: true,
          },
          vocab: {
            isInvisible: true,
          },
          about: {
            isInvisible: true,
          },
          
        },
        title: 'Захтев за приступ информацији од јавног значаја',
      },
      //Organ vlasti i njegovi elementi
      'zahtev:Organ_vlasti': {
        title: 'Орган власти коме се упућује захтев'
      },
      'tipovi:Naziv' : {
        mustBeBefore: ['tipovi:Prezime','tipovi:Adresa', 'tipovi:Kontakt_podaci'],
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Назив је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        title: 'Назив органа'
      },
      'tipovi:Sediste' : {
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Седиште је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        title: 'Седиште органа'
      },
      //Telo zahteva
      'zahtev:Telo_zahteva': {
        title: 'Садржај захтева'
      },
      //Zakonska osnova i njeni elementi - ne prikazuju se 
      'zahtev:Zakonska_osnova': {
        isInvisible: true,
      },
      //Zahtev i njegovi elementi
      'zahtev:Zahtevi': {
        validate: function (jsElement:any) {
          if (jsElement.children.length == 0) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Морате додати макар један захтев!"
            }
            );
          }
        },
        title: 'Кликните да бисте додали захтев/е',
        menu: [
        {
          caption: 'од горе наведеног органа захтевам обавештење да ли поседује тражену информацију',
          action: Xonomy.newElementChild,
          actionParameter: '<zahtev:Obavestenje xmlns:zahtev="http://zahtev"></zahtev:Obavestenje>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("zahtev:Obavestenje")
          }
        },
        {
          caption: 'од горе наведеног органа захтевам увид у документ који садржи тражену информацију',
          action: Xonomy.newElementChild,
          actionParameter: '<zahtev:Uvid xmlns:zahtev="http://zahtev"></zahtev:Uvid>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("zahtev:Uvid")
          }
        },
        {
          caption: 'од горе наведеног органа захтевам копију документа који садржи тражену информацију',
          action: Xonomy.newElementChild,
          actionParameter: '<zahtev:Kopija xmlns:zahtev="http://zahtev"></zahtev:Kopija>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("zahtev:Kopija")
          }
        },
        {
          caption: 'од горе наведеног органа захтевам достављање копије документа који садржи тражену информацију',
          action: Xonomy.newElementChild,
          actionParameter: '<zahtev:Dostavljanje_kopije xmlns:zahtev="http://zahtev"></zahtev:Dostavljanje_kopije>',
          hideIf: function (jsElement) {
            return jsElement.hasChildElement("zahtev:Dostavljanje_kopije")
          }
        }
      ]
      },
      'zahtev:Obavestenje': {
        mustBeBefore: ['zahtev:Uvid', 'zahtev:Kopija','zahtev:Dostavljanje_kopije'],
        hasText: false,
        oneliner: true,
        menu: [
          {
            caption: 'Обришите Обавештење',
            action: Xonomy.deleteElement
          }
        ]
      },
      'zahtev:Uvid': {
        mustBeBefore: ['zahtev:Kopija','zahtev:Dostavljanje_kopije'],
        hasText: false,
        oneliner: true,
        menu: [
          {
            caption: 'Обришите Увид',
            action: Xonomy.deleteElement
          }
        ]
      },
      'zahtev:Kopija': {
        mustBeBefore: ['zahtev:Dostavljanje_kopije'],
        hasText: false,
        oneliner: true,
        menu: [
          {
            caption: 'Обришите Копију',
            action: Xonomy.deleteElement
          }
        ]
      },
      //Dostavljanje kopije
      'zahtev:Dostavljanje_kopije': {
        validate: function (jsElement:any) {
          if (jsElement.children.length == 0) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Морате додати начин достављања копије!"
            }
            );
          }
        },
        title: 'Кликните да бисте одабрали начин достављања копије',
        hasText: false,
        menu: [
          {
            caption: 'Обришите Достављање копије',
            action: Xonomy.deleteElement
          },
          {
            caption: 'поштом',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Dostava_postom xmlns:zahtev="http://zahtev"></zahtev:Dostava_postom>',
            hideIf: function (jsElement) { 
              return (jsElement.hasChildElement("zahtev:Dostava_postom") || jsElement.hasChildElement("zahtev:Dostava_elektronskom_postom") ||
              jsElement.hasChildElement("zahtev:Dostava_faksom") || jsElement.hasChildElement("zahtev:Posebna_dostava"))
            }
          },
          {
            caption: 'електронском поштом',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Dostava_elektronskom_postom xmlns:zahtev="http://zahtev"></zahtev:Dostava_elektronskom_postom>',
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("zahtev:Dostava_postom") || jsElement.hasChildElement("zahtev:Dostava_elektronskom_postom") ||
              jsElement.hasChildElement("zahtev:Dostava_faksom") || jsElement.hasChildElement("zahtev:Posebna_dostava"))
            }
          },
          {
            caption: 'факсом',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Dostava_faksom xmlns:zahtev="http://zahtev"></zahtev:Dostava_faksom>',
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("zahtev:Dostava_postom") || jsElement.hasChildElement("zahtev:Dostava_elektronskom_postom") ||
              jsElement.hasChildElement("zahtev:Dostava_faksom") || jsElement.hasChildElement("zahtev:Posebna_dostava"))
            }
          },
          {
            caption: 'на други начин',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Posebna_dostava xmlns:zahtev="http://zahtev"><zahtev:Nacin_posebne_dostave></zahtev:Nacin_posebne_dostave></zahtev:Posebna_dostava>',
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("zahtev:Dostava_postom") || jsElement.hasChildElement("zahtev:Dostava_elektronskom_postom") ||
              jsElement.hasChildElement("zahtev:Dostava_faksom") || jsElement.hasChildElement("zahtev:Posebna_dostava"))
            }
          }
        ]
      },
      'zahtev:Dostava_postom': {
        mustBeBefore: ['zahtev:Dostava_elektronskom_postom', 'zahtev:Dostava_faksom','zahtev:Posebna_dostava'],
        hasText: false,
        menu: [
          {
            caption: 'Обришите Достава поштом',
            action: Xonomy.deleteElement
          }
        ],
      },
      'zahtev:Dostava_elektronskom_postom': {
        mustBeBefore: ['zahtev:Dostava_faksom','zahtev:Posebna_dostava'],
        hasText: false,
        menu: [
          {
            caption: 'Обришите Достава електронском поштом',
            action: Xonomy.deleteElement
          }
        ]
      },
      'zahtev:Dostava_faksom': {
        mustBeBefore: ['zahtev:Posebna_dostava'],
        hasText: false,
        menu: [
          {
            caption: 'Обришите Достава факсом',
            action: Xonomy.deleteElement
          }
        ]
      },
      'zahtev:Posebna_dostava': {
        hasText: false,
        menu: [
          {
            caption: 'Обришите Посебна достава',
            action: Xonomy.deleteElement
          },
        ],
      },
      'zahtev:Nacin_posebne_dostave': {
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Начин посебне доставе је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      //Zahtevane informacije
      'zahtev:Zahtevane_informacije' : {
        title: 'Навести што прецизнији опис информације која се тражи као и друге податке који олакшавају проналажење тражене информације',
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Захтеване информације је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      //Trazilac zahteva
      'zahtev:Trazilac': {
        title: 'Информације о тражиоцу захтева',
        validate: function (jsElement:any) {
          if (jsElement.children.length == 2) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Тражилац информације/Име и презиме је обавезно поље!"
            }
            );
          }
        },
        menu: [
          {
            caption: 'Назив тражиоца информације',
            action: Xonomy.newElementChild,
            actionParameter: '<tipovi:Naziv xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacNaziv"></tipovi:Naziv>',
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("tipovi:Naziv") || jsElement.hasChildElement("tipovi:Ime"))
            }
          },
          {
            caption: 'Име и презиме тражиоца информације',
            action: insertImePrezime,
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("tipovi:Naziv") || jsElement.hasChildElement("tipovi:Ime"))
            }
          }
        ]
      },
      'tipovi:Ime' : {
        mustBeBefore: ['tipovi:Prezime','tipovi:Adresa', 'tipovi:Kontakt_podaci'],
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Име је обавезно поље!"
            }
            );
          }
        },
        attributes: {
          property: {
            isInvisible: true,
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'tipovi:Prezime' : {
        mustBeBefore: ['tipovi:Adresa', 'tipovi:Kontakt_podaci'],
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Презиме је обавезно поље!"
            }
            );
          }
        },
        attributes: {
          property: {
            isInvisible: true,
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'tipovi:Mesto' : {
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Место је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'tipovi:Ulica' : {
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Улица је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'tipovi:Ulicni_broj' : {
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Улични број је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'tipovi:Kontakt_podaci' : {
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Контакт подаци је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString
      }
    },
  }
}

function insertImePrezime(htmlID) {
  Xonomy.newElementChild(htmlID, '<tipovi:Ime xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacIme"></tipovi:Ime>')
  Xonomy.newElementChild(htmlID, '<tipovi:Prezime xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacPrezime"></tipovi:Prezime>')
}

