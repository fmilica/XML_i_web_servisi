import { Injectable } from '@angular/core';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root',
})
export class XonomyZalbaCutanjeService {
  constructor() {}

  public zalbaCutanjeSpecification = {
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
        title: 'Жалба када орган власти није поступио/није поступио у целости/по захтеву тражиоца у законском року (ћутање управе)',
        attributes: {
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          mesto: {
            title: 'Место подношења жалбе',
            validate: function(jsAttribute){
              //Make sure item/@mesto is not an empty string:
              if(jsAttribute.value=="") {
                Xonomy.warnings.push({
                htmlID: jsAttribute.htmlID,
                text: "Место је обавезан атрибут."}
              );
              }
            },
             hasText: true,
             asker: Xonomy.askString
          },
        }
      },
      'zoc:Primalac_zalbe': {
        title: 'Назив и адреса органа ком се упућује жалба',
        isReadOnly: true,
        collapsed: true
      },
      'tipovi:Naziv': {
        mustBeBefore: ['tipovi:Adresa', 'tipovi:Kontakt_podaci'],
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
        attributes:{
          property: {
            isInvisible: true,
          },
        },
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
        title: 'Назив места',
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
        title: ''
      },   
      'zoc:Osnova_zalbe': {
        isInvisible: true,
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
        title: 'Назив органа власти'
      },
      'zoc:Razlog_zalbe': {
        validate: function (jsElement:any) {
          if (jsElement.getText() === '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Морате додати разлог жалбе"
            }
            );
          }
        },
        title: 'Кликните у поље за унос да бисте додали разлог подношења жалбе',
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ["није поступио", "није поступио у целости", "није поступио у законском року"],
      },
      'zoc:Datum':{
        title: 'Датум подношења захтева',
        isReadOnly:true,
        oneliner:true
      },
      'zoc:Podaci_o_zahtevu':
      {
        title: 'Навести податке о захтеву и информацији/ама',
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Подаци о захтеву је обавезно поље!"
            }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      //Podnosilac zalbe
      'zoc:Podnosilac_zalbe': {
        title: 'Подаци у подносиоцу жалбе/Лични подаци',
        validate: function (jsElement:any) {
          if (jsElement.children.length == 2) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Подносилац жалбе/Име и презиме је обавезно поље!"
            }
            );
          }
        },
        menu: [
          {
            caption: 'Назив подносиоца жалбе',
            action: Xonomy.newElementChild,
            actionParameter: '<tipovi:Naziv xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacNaziv"></tipovi:Naziv>',
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("tipovi:Naziv") || jsElement.hasChildElement("tipovi:Ime"))
            }
          },
          {
            caption: 'Име и презиме подносиоца жалбе',
            action: insertImePrezime,
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("tipovi:Naziv") || jsElement.hasChildElement("tipovi:Ime"))
            }
          }
        ]
      },
      'tipovi:Ime':{
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
        hasText: true,
        asker: Xonomy.askString,
        title: 'Име подносиоца жалбе',
        attributes:{
          property: {
          isInvisible: true,
        },}
      },
      'tipovi:Prezime':{
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
        hasText: true,
        title: 'Презиме подносиоца жалбе',
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

function insertImePrezime(htmlID) {
  Xonomy.newElementChild(htmlID, '<tipovi:Ime xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacIme"></tipovi:Ime>')
  Xonomy.newElementChild(htmlID, '<tipovi:Prezime xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacPrezime"></tipovi:Prezime>')
}