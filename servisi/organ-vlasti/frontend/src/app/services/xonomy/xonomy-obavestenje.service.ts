import { Injectable } from '@angular/core';

declare const Xonomy: any;
@Injectable({
  providedIn: 'root',
})
export class XonomyObavestenjeService {
  constructor() {}

  public obavestenjeSpecification = {
    validate: function (jsElement) {
      //Validate the element:
      let elementSpec = this.elements[jsElement.name];
      if (elementSpec.validate) elementSpec.validate(jsElement);
      //Cycle through the element's attributes:
      for (let i = 0; i < jsElement.attributes.length; i++) {
        let jsAttribute = jsElement.attributes[i];
        let attributeSpec = elementSpec.attributes[jsAttribute.name];
        if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
      }
      //Cycle through the element's children:
      for (let i = 0; i < jsElement.children.length; i++) {
        let jsChild = jsElement.children[i];
        if (jsChild.type == 'element') {
          //if element
          this.validate(jsChild); //recursion
        }
      }
    },
    elements: {
      //Obavestenje
      'obv:Obavestenje': {
        title: 'Обевештење о стављању на увид документа који садржи тражену информацију и о изради копије',
        attributes: {
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          dostavljeno: {
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
          rel: {
            isInvisible: true,
          },
          href: {
            isInvisible: true,
          },
        },
      },
      //Podaci o organu vlasti
      'obv:Organ_vlasti': {
        isInvisible: true
      },
      //Broj predmeta
      'obv:Broj_predmeta': {
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Број предмета је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      //Podaci o podnosiocu zahteva
      'obv:Podnosilac': {
        title: 'Подаци о подносиоцу захтева'
      },
      
      'tipovi:Ime': {
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Име је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          property: {
            isInvisible: true,
          },
        },
      },
      'tipovi:Prezime': {
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Презиме је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          property: {
            isInvisible: true,
          },
        },
      },
      'tipovi:Mesto': {
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Место је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      'tipovi:Ulica': {
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Улица је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      'tipovi:Ulicni_broj': {
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Улични број је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      //Zakonska osnova
      'obv:Zakonska_osnova': {
        isInvisible: true,
      },
      //Datum potrazivanja
      'obv:Datum_potrazivanja': {
        isReadOnly: true,
        oneliner: true,
      },
      //Opis trazene informacije
      'obv:Opis_trazene_informacije': {
        isReadOnly: true,
        oneliner: true,
      },
      //Podaci o uvidu
      'obv:Podaci_o_uvidu': {
        title: 'Подаци о увиду у документ у ком је садржана тражена информација'
      },
      'obv:Datum': {
        title: 'Датум увида',
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Датум увида је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'obv:Vreme': {
        title: 'Време увида',
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Време увида је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'obv:Pocetno_vreme': {
        title: 'Почетно време увида',
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Почетно време увида је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'obv:Krajnje_vreme': {
        title: 'Крајње време увида',
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Крајње време увида је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'obv:Kancelarijski_broj': {
        title: 'Број канцеларије у којој се може извршити увид у документ',
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Канцеларијски број је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      'obv:Mesto_uvida': {
        title: 'Подаци о месту на ком је могуће извршити увид у документ'
      },
      'obv:Ukupan_trosak_kopija': {
        oneliner: true,
        collapsed: true,
        isReadOnly: true
      },
    },
  };
}
