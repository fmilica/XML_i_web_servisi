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
      'zahtev:Zahtev': {
        menu: [],
        attributes: {
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          mesto: {
            isInvisible: true,
          },
          datum: {
            isInvisible: true,
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
      },
      'tipovi:Naziv' : {
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
        }
      },
      'tipovi:Sediste' : {
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
        }
      },
      'zahtev:Zakonska_osnova': {
        isReadOnly: true,
        collapsed: true
      },
      'tipovi:Brojevi': {
        oneliner: true,
        collapsed: true
      },
      'zahtev:Clan': {
        oneliner: true
      },
      'zahtev:Stav': {
        oneliner: true
      },
      'zahtev:Zakon': {
        oneliner: true
      },
      'tipovi:Broj': {
        oneliner: true
      },
      'zahtev:Zahtevi': {
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
            caption: 'Обришите Увод',
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
      'zahtev:Dostavljanje_kopije': {
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
              return jsElement.hasChildElement("zahtev:Dostava_postom")
            }
          },
          {
            caption: 'електронском поштом',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Dostava_elektronskom_postom xmlns:zahtev="http://zahtev"></zahtev:Dostava_elektronskom_postom>',
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("zahtev:Dostava_elektronskom_postom")
            }
          },
          {
            caption: 'факсом',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Dostava_faksom xmlns:zahtev="http://zahtev"></zahtev:Dostava_faksom>',
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("zahtev:Dostava_faksom")
            }
          },
          {
            caption: 'на други начин',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Posebna_dostava xmlns:zahtev="http://zahtev"></zahtev:Posebna_dostava>',
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("zahtev:Posebna_dostava")
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
        ]
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
          {
            caption: 'Начин посебне доставе',
            action: Xonomy.newElementChild,
            actionParameter: '<zahtev:Nacin_posebne_dostave xmlns:zahtev="http://zahtev"></zahtev:Nacin_posebne_dostave>',
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("zahtev:Nacin_posebne_dostave")
            }
          }
        ]
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
      'zahtev:Zahtevane_informacije' : {
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
      'tipovi:Ime' : {
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
