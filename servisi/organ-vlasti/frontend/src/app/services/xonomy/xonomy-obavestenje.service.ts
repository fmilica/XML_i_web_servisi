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
      'obv:Obavestenje': {
        menu: [],
        attributes: {
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          dostavljeno: {
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
          rel: {
            isInvisible: true,
          },
          href: {
            isInvisible: true,
          },
        },
      },
      'obv:Organ_vlasti': {
        isReadOnly: true,
        oneliner: true,
        collapsed: true,
      },
      'tipovi:Naziv': {
        // collapsed: true,
        //   oneliner: true,
        attributes: {
          property: {
            isInvisible: true,
          },
        },
      },
      // 'tipovi:Sediste': {
      //   oneliner: true,
      // },
      'obv:Broj_predmeta': {
        //   isReadOnly: true
        oneliner: true,
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
      'obv:Zakonska_osnova': {
        isReadOnly: true,
        collapsed: true,
      },
      'obv:Clan': {
        oneliner: true,
      },
      'obv:Zakon': {
        oneliner: true,
      },
      'obv:Datum_potrazivanja': {
        oneliner: true,
      },
      'obv:Opis_trazene_informacije': {
        oneliner: true,
      },
      'obv:Datum': {
        //   isReadOnly: true,
        oneliner: true,
      },
      'obv:Vreme': {
        oneliner: true,
      },
      'obv:Pocetno_vreme': {
        oneliner: true,
      },
      'obv:Krajnje_vreme': {
        oneliner: true,
      },
      'obv:Kancelarijski_broj': {
        oneliner: true,
      },
      'obv:Ukupan_trosak_kopija': {
        oneliner: true,
        collapsed: true,
        isReadOnly: true
      },
    },
  };
}
