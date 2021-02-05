import { Injectable } from '@angular/core';

declare const Xonomy: any;
@Injectable({
  providedIn: 'root'
})
export class XonomyResenjeService {

  constructor() { }

  public resenjeSpecificaion = {
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
      'res:Resenje': {
        attributes: {
          'xsi:schemaLocation': {
            isInvisible: true,
          },
          broj_resenja: {
            title: 'Број решења',
            validate: function(jsAttribute){
              //Make sure item/@mesto is not an empty string:
              if(jsAttribute.value=="") {
                Xonomy.warnings.push({
                htmlID: jsAttribute.htmlID,
                text: "Број решења је обавезан атрибут."}
              );
              }
            },
            hasText: true,
            asker: Xonomy.askString,
          },
          'tip_odluke': {
            asker: Xonomy.askPicklist,
            askerParameter: ["основана", "неоснована", "поништена"],
            validate: function(jsAttribute){
              //Make sure item/@razlog_odbijanja is not an empty string:
              if(jsAttribute.value=="") {
                Xonomy.warnings.push({
                htmlID: jsAttribute.htmlID,
                text: "Тип одлуке је обавезан атрибут."}
              );
              }
            },
          }
        },
      },
      // Opis zalbe
      'res:Opis_zalbe': {
        attributes: {
          razlog_zalbe: {
            title: 'Разлог жалбе',
            asker: Xonomy.askPicklist,
            askerParameter: ["непоступање", "непоступање у целости", "непоступање у законском року", "одбијање/одбацивање"],
            validate: function(jsAttribute){
              //Make sure item/@mesto is not an empty string:
              if(jsAttribute.value=="") {
                Xonomy.warnings.push({
                htmlID: jsAttribute.htmlID,
                text: "Разлог жалбе је обавезан атрибут."}
              );
              }
            },
          }
        },
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Опис жалбе је обавезно поље!"
            }
            );
          }
        },
        inlineMenu: [
          {
            caption: "Обмотај тагом <res:Ime_zalilac>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Ime_zalilac xmlns:res="http://resenje" xmlns:pred="http://www.xml.com/predicate/"  property="pred:zalilacIme">$</res:Ime_zalilac>', placeholder: "$"},
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("res:Ime_zalilac") || jsElement.hasChildElement("res:Naziv_zalilac"))
            }
          }, 
          {
            caption: "Обмотај тагом <res:Prezime_zalilac>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Prezime_zalilac xmlns:res="http://resenje" xmlns:pred="http://www.xml.com/predicate/"  property="pred:zalilacPrezime">$</res:Prezime_zalilac>', placeholder: "$"},
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("res:Prezime_zalilac") || jsElement.hasChildElement("res:Naziv_zalilac"))
            },
          },
          {
            caption: "Обмотај тагом <res:Naziv_zalilac>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Naziv_zalilac xmlns:res="http://resenje" xmlns:pred="http://www.xml.com/predicate/"  property="pred:zalilacNaziv">$</res:Naziv_zalilac>', placeholder: "$"},
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("res:Ime_zalilac") 
              || jsElement.hasChildElement("res:Prezime_zalilac") 
              || jsElement.hasChildElement("res:Naziv_zalilac"))
            }
          },
          {
            caption: "Обмотај тагом <res:Naziv_organa_vlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Naziv_organa_vlasti xmlns:res="http://resenje">$</res:Naziv_organa_vlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Naziv_organa_vlasti")
            },
          },
          {
            caption: "Обмотај тагом <res:Mesto_organa_vlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Mesto_organa_vlasti xmlns:res="http://resenje">$</res:Mesto_organa_vlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Mesto_organa_vlasti")
            },
          },
          {
            caption: "Обмотај тагом <res:Ulica_organa_vlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Ulica_organa_vlasti xmlns:res="http://resenje">$</res:Ulica_organa_vlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Ulica_organa_vlasti")
            },
          },
          {
            caption: "Обмотај тагом <res:Ulicni_broj_organa_vlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Ulicni_broj_organa_vlasti xmlns:res="http://resenje">$</res:Ulicni_broj_organa_vlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Ulicni_broj_organa_vlasti")
            },
          },
          {
            caption: "Обмотај тагом <res:Clan>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Clan xmlns:res="http://resenje">$</res:Clan>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Stav>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Stav xmlns:res="http://resenje">$</res:Stav>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Tacka>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Tacka xmlns:res="http://resenje">$</res:Tacka>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Zakon>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Zakon xmlns:res="http://resenje">$</res:Zakon>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Naziv_sluzbenog_glasnika>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Naziv_sluzbenog_glasnika xmlns:res="http://resenje">$</res:Naziv_sluzbenog_glasnika>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Broj_sluzbenog_glasnika>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Broj_sluzbenog_glasnika xmlns:res="http://resenje">$</res:Broj_sluzbenog_glasnika>', placeholder: "$"},
          },
        ]
      },
      //Tagovi unutar opis zalbe
      'res:Ime_zalilac': {
        oneliner: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        hasText: true,
        menu: [{
          caption: "Уклони таг <Ime_zalilac>",
          action: Xonomy.unwrap
        }]

      },
      'res:Prezime_zalilac': {
        oneliner: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        hasText: true,
        menu: [{
          caption: "Уклони таг <Prezime_zalilac>",
          action: Xonomy.unwrap
        }]
      },
      'res:Naziv_zalilac': {
        oneliner: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        hasText: true,
        menu: [{
          caption: "Уклони таг <Naziv_zalilac>",
          action: Xonomy.unwrap
        }]
      },
      'res:Naziv_organa_vlasti': {
        oneliner: true,
        hasText: true,
        menu: [{
          caption: "Уклони таг <Naziv_organa_vlasti>",
          action: Xonomy.unwrap
        }]
      },
      'res:Mesto_organa_vlasti': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Mesto_organa_vlasti>",
          action: Xonomy.unwrap
        }]
      },
      'res:Ulica_organa_vlasti': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Ulica_organa_vlasti>",
          action: Xonomy.unwrap
        }]
      },
      'res:Ulicni_broj_organa_vlasti': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Ulicni_broj_organa_vlasti>",
          action: Xonomy.unwrap
        }]
      },
      //Zakon tagovi
      'res:Clan': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Clan>",
          action: Xonomy.unwrap
        }]
      },
      'res:Stav': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Stav>",
          action: Xonomy.unwrap
        }]
      },
      'res:Tacka': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Tacka>",
          action: Xonomy.unwrap
        }]
      },
      'res:Zakon': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Zakon>",
          action: Xonomy.unwrap
        }]
      },
      //Sluzbeni glasnik tagovi
      'res:Naziv_sluzbenog_glasnika': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Naziv_sluzbenog_glasnika>",
          action: Xonomy.unwrap
        }]
      },
      'res:Broj_sluzbenog_glasnika': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Broj_sluzbenog_glasnika>",
          action: Xonomy.unwrap
        }]
      },
      //Odluka
      'res:Odluka': {
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Одлука је обавезно поље!"
            }
            );
          }
        },
        inlineMenu: 
        [
          {
            caption: "Обмотај тагом <res:Nalog>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Nalog rok_izvrsenja="" xmlns:res="http://resenje">$</res:Nalog>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Odbijanje")
            },
          },
          {
            caption: "Обмотај тагом <res:Odbijanje>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Odbijanje razlog_odbijanja="" xmlns:res="http://resenje">$</res:Odbijanje>', placeholder: "$"},
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("res:Nalog") || jsElement.hasChildElement("res:Odbijanje"))
            },
          }
        ]
      },
      'res:Nalog': {
        attributes: {
          'rok_izvrsenja': {
            hasText: true,
            asker: Xonomy.askString,
            validate: function(jsAttribute){
              //Make sure item/@rok_izvrsenja is not an empty string:
              if(jsAttribute.value=="") {
                Xonomy.warnings.push({
                htmlID: jsAttribute.htmlID,
                text: "Рок извршења је обавезан атрибут."}
              );
              }
            },
          }
        },
        menu: [{
          caption: "Уклони таг <Nalog>",
          action: Xonomy.unwrap
        }]
      },
      'res:Odbijanje': {
        menu: [{
          caption: "Уклони таг <Odbijanje>",
          action: Xonomy.unwrap
        }]
      },

      //Obrazlozenje
        //Postupak zalioca
          //Podnosenje zalbe
          //Podnosenje zahteva
      'res:Podnosenje_zalbe': {
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Подношење жалбе је обавезно поље!"
            }
            );
          }
        },
      },
      'res:Podnosenje_zahteva': {
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Подношење захтева је обавезно поље!"
            }
            );
          }
        },
      },
        //Prosledjivanje zalbe
      'res:Prosledjivanje_zalbe': {
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Прослеђивање жалбе је обавезно поље!"
            }
            );
          }
        },
        inlineMenu:
        [
          {
            caption: "Обмотај тагом <res:Clan>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Clan xmlns:res="http://resenje">$</res:Clan>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Stav>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Stav xmlns:res="http://resenje">$</res:Stav>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Tacka>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Tacka xmlns:res="http://resenje">$</res:Tacka>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Zakon>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Zakon xmlns:res="http://resenje">$</res:Zakon>', placeholder: "$"},
          },
        ]
      },
        //Izjasnjenje o zalbi
      'res:Izjasnjenje_o_zalbi': {
        attributes: {
          'datum_izjasnjenja': {
            hasText: true,
            asker: Xonomy.askString,
            validate: function(jsAttribute){
              //Make sure item/@datum_izjasnjenja is not an empty string:
              if(jsAttribute.value=="") {
                Xonomy.warnings.push({
                htmlID: jsAttribute.htmlID,
                text: "Датум изјашњења је обавезан атрибут."}
              );
              }
            },
          }
        },
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Одговор на жалбу је обавезно поље!"
            }
            );
          }
        }
      },
      //Razlozi odluke
      'res:Razlozi_odluke': {
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Разлози одлуке је обавезно поље!"
            }
            );
          }
        },
        inlineMenu:
        [
          {
            caption: "Обмотај тагом <res:Detaljan_opis_podnetog_zahteva>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Detaljan_opis_podnetog_zahteva xmlns:res="http://resenje">$</res:Detaljan_opis_podnetog_zahteva>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Detaljan_opis_podnetog_zahteva")
            }
          },
          {
            caption: "Обмотај тагом <res:Detaljan_opis_odgovora_na_zahtev>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Detaljan_opis_odgovora_na_zahtev xmlns:res="http://resenje">$</res:Detaljan_opis_odgovora_na_zahtev>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Detaljan_opis_odgovora_na_zahtev")
            }
          },
          {
            caption: "Обмотај тагом <res:Detaljan_opis_odluke>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Detaljan_opis_odluke xmlns:res="http://resenje">$</res:Detaljan_opis_odluke>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Detaljan_opis_odluke")
            }
          },
          {
            caption: "Обмотај тагом <res:Clan>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Clan xmlns:res="http://resenje">$</res:Clan>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Stav>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Stav xmlns:res="http://resenje">$</res:Stav>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Tacka>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Tacka xmlns:res="http://resenje">$</res:Tacka>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:Zakon>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Zakon xmlns:res="http://resenje">$</res:Zakon>', placeholder: "$"},
          },
        ]
      },
      //Razlozi odluke unutrasnji
      'res:Detaljan_opis_podnetog_zahteva': {
        hasText: true,
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Detaljan_opis_podnetog_zahteva>",
          action: Xonomy.unwrap
        }]
      },
      'res:Detaljan_opis_odgovora_na_zahtev': {
        hasText: true,
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Detaljan_opis_odgovora_na_zahtev>",
          action: Xonomy.unwrap
        }]
      },
      'res:Detaljan_opis_odluke': {
        hasText: true,
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Detaljan_opis_odluke>",
          action: Xonomy.unwrap
        }]
      },
      // Zalba na resenje
      'res:Zalba_na_resenje': {
        hasText: true,
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Жалба на решење је обавезно поље!"
            }
            );
          }
        }
      },
      'res:Ime': {
        hasText: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Име издавача решења је обавезно поље!"
            }
            );
          }
        }
      },
      'res:Prezime': {
        hasText: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        validate: function (jsElement:any) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Име издавача решења је обавезно поље!"
            }
            );
          }
        }
      }
    }
  }
}
