import { Component, OnInit, ɵɵi18nAttributes } from '@angular/core';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-zalbe-cutanje-gradjanin',
  templateUrl: './zalbe-cutanje-gradjanin.component.html',
  styleUrls: ['./zalbe-cutanje-gradjanin.component.sass']
})
export class ZalbeCutanjeGradjaninComponent implements OnInit {

  constructor(
    private zalbaCutanjeService: ZalbaCutanjeService
  ) { }
  dataSource = [ ];

  displayedColumns: string[] = ['organVlasti', 'razlogZalbe', 'datumZahteva', 'podaci', 'datumZalbe', 'mestoZalbe',
                                'razresena', 'preuzimanje']

  ngOnInit(): void {
    this.zalbaCutanjeService.getAllGradjaninZalbeCutanje()
      .subscribe(
        (response) => {
          let xmlResponse = response;
          let allZalbe: any = txml.parse(xmlResponse);
          let data = []
          allZalbe[1].children.map(zalba => {
            let zalbaPrikaz = {
              organVlasti: zalba.children[1].children[1].children[0],
              razlogZalbe: zalba.children[1].children[2].children[0],
              datumZahteva: zalba.attributes.datum_podnosenja_zahteva,
              podaci: zalba.children[1].children[3].children[0],
              datumZalbe: zalba.attributes.datum_podnosenja_zalbe,
              mestoZalbe: zalba.attributes.mesto,
              razresena: 'Да'
            }
            data.push(zalbaPrikaz);
          })
          this.dataSource = data;
        }
      )
  }

}
