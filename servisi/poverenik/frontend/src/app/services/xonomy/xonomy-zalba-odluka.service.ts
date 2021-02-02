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
      //Zalba odbijanje
      'zoz:Zalba_odbijanje': {
        title: 'Жалба против одлуке органа власти којом је одбијен или одбачен захтев за приступ информацији',
        attributes:{
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          mesto_podnosenja_zalbe: {
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
          },
          datum_podnosenja_zalbe: {
            title: 'Датум подношења жалбе',            
          },
          datum_podnosenja_zahteva:{
            title: 'Датум подношења захтева',
          },
        }
      },
      //Podaci o primaocu
      'zoz:podaci_o_primaocu':{
        title: 'Подаци о примаоцу жалбе',
        isReadOnly: true
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
      //Podaci o zaliocu
      'zoz:podaci_o_zaliocu':{
        title: 'Подаци у подносиоцу жалбе/Лични подаци',
        validate: function (jsElement:any) {
          if (jsElement.children.length == 1) {
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
        asker: Xonomy.askString,
        attributes:{
          property: {
          isInvisible: true,
        },}
      },
      //Podaci o odluci
      'zoz:podaci_o_odluci':{
        title: 'Подаци о одлуци',
      },
      'zoz:naziv_donosioca_odluke':{
        isReadOnly: true,
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
        asker: Xonomy.askString,
      },
      'zoz:osnova_zalbe': {
        isInvisible: true,
      },

      //Podaci o podnosiocu zalbe
      'zoz:podaci_o_podnosiocu_zalbe':{
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

function insertImePrezime(htmlID) {
  Xonomy.newElementChild(htmlID, '<tipovi:Ime xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacIme"></tipovi:Ime>')
  Xonomy.newElementChild(htmlID, '<tipovi:Prezime xmlns:tipovi="http://tipovi"  xmlns:pred="http://www.xml.com/predicate/" property="pred:podnosilacPrezime"></tipovi:Prezime>')
}