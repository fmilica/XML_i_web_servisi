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
        },
      },
      'res:Opis_zalbe': {
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
            caption: "Обмотај тагом <res:ImeZalilac>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:ImeZalilac xmlns:res="http://resenje" xmlns:pred="http://www.xml.com/predicate/"  property="pred:zalilacIme">$</res:ImeZalilac>', placeholder: "$"},
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("res:ImeZalilac") || jsElement.hasChildElement("res:NazivZalilac"))
            }
          }, 
          {
            caption: "Обмотај тагом <res:PrezimeZalilac>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:PrezimeZalilac xmlns:res="http://resenje" xmlns:pred="http://www.xml.com/predicate/"  property="pred:zalilacPrezime">$</res:PrezimeZalilac>', placeholder: "$"},
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("res:PrezimeZalilac") || jsElement.hasChildElement("res:NazivZalilac"))
            },
          },
          {
            caption: "Обмотај тагом <res:NazivZalilac>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:NazivZalilac xmlns:res="http://resenje" xmlns:pred="http://www.xml.com/predicate/"  property="pred:zalilacNaziv">$</res:NazivZalilac>', placeholder: "$"},
            hideIf: function (jsElement) {
              return (jsElement.hasChildElement("res:ImeZalilac") || jsElement.hasChildElement("res:NazivZalilac"))
            }
          },
          {
            caption: "Обмотај тагом <res:NazivOrganaVlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:NazivOrganaVlasti xmlns:res="http://resenje">$</res:NazivOrganaVlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:NazivOrganaVlasti")
            },
          },
          {
            caption: "Обмотај тагом <res:MestoOrganaVlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:MestoOrganaVlasti xmlns:res="http://resenje">$</res:MestoOrganaVlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:MestoOrganaVlasti")
            },
          },
          {
            caption: "Обмотај тагом <res:UlicaOrganaVlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:UlicaOrganaVlasti xmlns:res="http://resenje">$</res:UlicaOrganaVlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:UlicaOrganaVlasti")
            },
          },
          {
            caption: "Обмотај тагом <res:Ulicni_brojOrganaVlasti>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:Ulicni_brojOrganaVlasti xmlns:res="http://resenje">$</res:Ulicni_brojOrganaVlasti>', placeholder: "$"},
            hideIf: function (jsElement) {
              return jsElement.hasChildElement("res:Ulicni_brojOrganaVlasti")
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
            caption: "Обмотај тагом <res:NazivSluzbenogGlasnika>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:NazivSluzbenogGlasnika xmlns:res="http://resenje">$</res:NazivSluzbenogGlasnika>', placeholder: "$"},
          },
          {
            caption: "Обмотај тагом <res:BrojSluzbenogGlasnika>",
            action: Xonomy.wrap,
            actionParameter: {template: '<res:BrojSluzbenogGlasnika xmlns:res="http://resenje">$</res:BrojSluzbenogGlasnika>', placeholder: "$"},
          },
        ]
      },
      //Tagovi unutar opis zalbe
      'res:ImeZalilac': {
        oneliner: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        hasText: true,
        menu: [{
          caption: "Уклони таг <ImeZalilac>",
          action: Xonomy.unwrap
        }]

      },
      'res:PrezimeZalilac': {
        oneliner: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        hasText: true,
        menu: [{
          caption: "Уклони таг <PrezimeZalilac>",
          action: Xonomy.unwrap
        }]
      },
      'res:NazivZalilac': {
        oneliner: true,
        attributes: {
          property: {
            isInvisible: true
          }
        },
        hasText: true,
        menu: [{
          caption: "Уклони таг <NazivZalilac>",
          action: Xonomy.unwrap
        }]
      },
      'res:NazivOrganaVlasti': {
        oneliner: true,
        hasText: true,
        menu: [{
          caption: "Уклони таг <NazivOrganaVlasti>",
          action: Xonomy.unwrap
        }]
      },
      'res:MestoOrganaVlasti': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <MestoOrganaVlasti>",
          action: Xonomy.unwrap
        }]
      },
      'res:UlicaOrganaVlasti': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <UlicaOrganaVlasti>",
          action: Xonomy.unwrap
        }]
      },
      'res:Ulicni_brojOrganaVlasti': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Ulicni_brojOrganaVlasti>",
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
      'res:NazivSluzbenogGlasnika': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <NazivSluzbenogGlasnika>",
          action: Xonomy.unwrap
        }]
      },
      'res:BrojSluzbenogGlasnika': {
        oneliner: true,
        menu: [{
          caption: "Уклони таг <BrojSluzbenogGlasnika>",
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
          }
        ]
      },
      'res:Nalog': {
        attributes: {
          'rok_izvrsenja': {
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

      //Obrazlozenje
      //Postupak zalioca
      //Podnosenje zalbe
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
      //Odgovor na zalbu
      'res:Odgovor_na_zalbu': {
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
              return jsElement.hasChildElement("res:Detaljan_opis_odluke>")
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
      //Obrazlozenje odluke
      'res:Detaljan_opis_odluke': {
        hasText: true,
        oneliner: true,
        menu: [{
          caption: "Уклони таг <Detaljan_opis_odluke>",
          action: Xonomy.unwrap
        }]
      },
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
