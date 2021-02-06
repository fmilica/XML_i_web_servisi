import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { IzvestajService } from 'src/app/services/izvestaj.service';
import * as txml from 'txml';

@Component({
  selector: 'app-all-izvestaji',
  templateUrl: './all-izvestaji.component.html',
  styleUrls: ['./all-izvestaji.component.sass']
})
export class AllIzvestajiComponent implements OnInit {

  dataSource = [];

  form: FormGroup;

  displayedColumns: string[] = [ 'godisnjiIzvestaj', 'datumGenerisanja', 'pregled']

  constructor(
    private izvestajService: IzvestajService,
    private router: Router,
    private toastr: ToastrService){
      this.form = new FormGroup({
        datum: new FormControl()
      })
    }

  ngOnInit(){
    this.getIzvestaji();
  }

  getIzvestaji(){
    this.izvestajService.getAllIzvestaji().subscribe(
      (response) => {
        this.lista2Izvestaji(response)
      }
    )
  }

  lista2Izvestaji(response: string) {
    let xmlResponse = response;
        let izvestaji: any = txml.parse(xmlResponse);
        let data = []
        izvestaji[1].children.map( izvestaj => {
          let datum = izvestaj.attributes.datum_podnosenja_izvestaja;
          let prikazDatum = datum.substring(8,10) + '.' + datum.substring(5,7) + '.' + datum.substring(0,4) + '.'
          let izvestajPrikaz = {
            id: izvestaj.attributes.id.substring(16),
            godisnjiIzvestaj: izvestaj.attributes.id.substring(16),
            datumGenerisanja: prikazDatum
          }
          data.push(izvestajPrikaz)
        })
        this.dataSource = data;
  }

  pregledIzvestaja(id: string){
    this.router.navigate(['izvestaj/'+id]);
  }

  obicnaPretraga() {
    let datum = this.form.get('datum').value
    if(!datum) {
      this.getIzvestaji()
    }
    this.izvestajService.obicnaPretraga(datum)
      .subscribe(
        response => {
          this.lista2Izvestaji(response)
        }
      )
  }
}
