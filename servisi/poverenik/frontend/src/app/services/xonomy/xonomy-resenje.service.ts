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
        menu: [],
        attributes: {
          'xsi:schemaLocation': {
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
          broj_resenja: {
            isInvisible: true,
          },
          datum_resenja: {
            isInvisible: true,
          },
        },
      },
      'res:Opis_zalbe': {
        validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Опис жалбе је обавезно поље!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          'razlog_zalbe': {
            isInvisible: true,
          },
          'datum_zahteva': {
            isInvisible: true,
          },
        }
      },
      'res:Podnosilac_zalbe': {
        collapsed: true,
        isReadOnly: true
      },
      'res:Ime': {
        oneliner: true,
        attributes: {
          'property': {
            isInvisible: true
          }
        }
      },
      'res:Prezime': {
        oneliner: true,
        attributes: {
          'property': {
            isInvisible: true
          }
        }
      },
      'res:Organ_vlasti': {
        isReadOnly: true,
        collapsed: true
      },
      'tipovi:Naziv': {
        oneLiner: true,
        attributes: {
          'property' : {
            isInvisible: true
          }
        }
      },
      'tipovi:Sediste': {
        collapsed: true
      },
      'tipovi:Mesto': {
        oneLiner: true
      },
      'tipovi:Ulica': {
        oneLiner: true
      },
      'tipovi:Ulicni_broj': {
        oneLiner: true
      }
    }
  }
}
