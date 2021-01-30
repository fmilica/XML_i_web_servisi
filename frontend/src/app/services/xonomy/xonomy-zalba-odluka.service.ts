import { Injectable } from '@angular/core';

declare const Xonomy: any;

// const zoz = `xmlns:zoz="http://zalbaodluka"`;
// const tipovi = `xmlns:tipovi="http://tipovi"`;
@Injectable({
  providedIn: 'root',
})
export class XonomyZalbaOdlukaService {
  constructor() {}

  public zalbaOdlukaSpecification = {
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
      'zoz:Zalba_odbijanje': {
        menu:[],
        attributes:{
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          mesto_podnosenja_zalbe: {
            isInvisible: true,
          },
          datum_podnosenja_zalbe: {
            isInvisible: true,
          },
          datum_podnosenja_zahteva:{
            isInvisible: true
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
        }
      },
      'zoz:podaci_o_primaocu':{
        menu:[],
        // isReadOnly: true
      },
      'tipovi:Naziv': {
        // collapsed: true,
        oneliner: true,
        attributes:{
          property: {
            isInvisible: true,
          },
        }
      },
      'tipovi:Sediste': {
        collapsed: true,
      },
      'tipovi:Mesto': {
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
        asker: Xonomy.askString,
      },
      'tipovi:Ulica': {
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
        asker: Xonomy.askString,
      },
      'tipovi:Ulicni_broj': {
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
        asker: Xonomy.askString,
      },
      'tipovi:Ime':{
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Име је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes:{
          property: {
          isInvisible: true,
        },}
      },
      'tipovi:Prezime':{
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Презиме је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes:{
          property: {
          isInvisible: true,
        },}
      },
      'zoz:podaci_o_odluci':{
        attributes:{
          broj_odluke:{
            isInvisible: true
          },
          godina:{
            isInvisible:true
          }
        }
      },
      'zoz:naziv_donosioca_odluke':{
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Назив доносиоца одлуке је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      'zoz:razlog_zalbe': {
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Разлог жалбе је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ["није поступио", "није поступио у целости", "није поступио у законском року"],
      },
      'zoz:Osnova_zalbe': {
        isReadOnly: true,
        collapsed: true,
      },
      'zoz:Clan': {
        oneliner: true,
      },
      'zoz:Zakon': {
        oneliner: true,
      },
      'zoz:Datum':{
        isReadOnly:true,
        oneliner:true
      },
      'tipovi:Kontakt_podaci':{
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
        asker: Xonomy.askString,
      }
    }
  };
}
