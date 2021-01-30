import { Injectable } from '@angular/core';

declare const Xonomy: any;

const zoc = `xmlns:zoc="http://zalbacutanje"`;
const tipovi = `xmlns:tipovi="http://tipovi"`;
@Injectable({
  providedIn: 'root',
})
export class XonomyZalbaCutanjeService {
  constructor() {}

  public fakultetSpecification = {
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
      'zoc:Zalba_cutanje': {
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
          rel: {
            isInvisible: true,
          },
          href: {
            isInvisible: true,
          },
        },
      },
      'zoc:Primalac_zalbe': {
        menu: [],
        //   mustBeBefore:["zoc:Zalba"]
        isReadOnly: true,
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
      
      'zoc:Osnova_zalbe': {
        isReadOnly: true,
        collapsed: true,
      },
      'zoc:Clan': {
        oneliner: true,
      },
      'zoc:Zakon': {
        oneliner: true,
      },
      'zoc:Datum':{
        isReadOnly:true,
        oneliner:true
      },
      'zoc:Naziv_organa':{
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Назив органа је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      'zoc:Razlog_zalbe': {
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
      'zoc:Podaci_o_zahtevu':
      {
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
    },
  };
}