import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { ResenjeDto } from 'src/app/model/resenje-dto.model';
import { ZahtevDto } from 'src/app/model/zahtev-dto.model';
import { ZalbaDto } from 'src/app/model/zalba-dto.model';
import { ResenjeService } from 'src/app/services/resenje.service';
import { XonomyResenjeService } from 'src/app/services/xonomy/xonomy-resenje.service';

declare const Xonomy: any;

@Component({
  selector: 'app-resenje',
  templateUrl: './resenje.component.html',
  styleUrls: ['./resenje.component.sass']
})
export class ResenjeComponent implements OnInit, OnDestroy {

  zahtevDto: ZahtevDto;
  zahtevSub: Subscription;

  zalbaDto: ZalbaDto;
  zalbaSub: Subscription;


  constructor(
    private xonomyResenjeService: XonomyResenjeService,
    private resenjeService: ResenjeService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.zahtevSub = this.resenjeService.odabraniZahtev
      .subscribe(zahtevDto => {
        this.zahtevDto = zahtevDto;
      })
    this.zalbaSub = this.resenjeService.odabranaZalba
      .subscribe(zalbaDto => {
        this.zalbaDto = zalbaDto;
      })
  }

  ngAfterViewInit() {
    let elemet = document.getElementById('resenje');
    let specification = this.xonomyResenjeService.resenjeSpecificaion;
    let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                      <res:Resenje xmlns="http://www.w3.org/ns/rdfa#"
                        xmlns:res="http://resenje"
                        xmlns:tipovi="http://tipovi"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:pred="http://www.xml.com/predicate/"
                        xsi:schemaLocation="http://resenje resenje.xsd" 
                        broj_resenja="" 
                        datum_resenja="${new Date().toISOString().slice(0, 10)}"
                        tip_odluke="">` +
                        `<res:Opis_zalbe razlog_zalbe="">` +
                        `</res:Opis_zalbe>` +
                        `<res:Odluka>` +
                        `</res:Odluka>` +
                        `<res:Obrazlozenje>` +
                          `<res:Postupak_zalioca>` +
                            `<res:Podnosenje_zalbe datum_zalbe="` + this.zalbaDto.datumPodnosenja + `">` +
                            `</res:Podnosenje_zalbe>` +
                            `<res:Podnosenje_zahteva datum_zahteva="` + this.zahtevDto.datumPodnosenja + `">` +
                            `</res:Podnosenje_zahteva>` +
                          `</res:Postupak_zalioca>` +
                          `<res:Prosledjivanje_zalbe datum_prosledjivanja="">` +
                          `</res:Prosledjivanje_zalbe>` +
                          `<res:Izjasnjenje_o_zalbi datum_izjasnjenja="">` +
                          `</res:Izjasnjenje_o_zalbi>` +
                          `<res:Razlozi_odluke>` +
                          `</res:Razlozi_odluke>` +
                          `<res:Zalba_na_resenje rok_za_tuzbu="30" taksa_tuzbe="390" zakon="Закон о управним споровима" sud="Управни суд у Београду">` +
                          `</res:Zalba_na_resenje>` +
                        `</res:Obrazlozenje>` +
                        `<res:Poverenik>` +
                          `<res:Ime property="pred:izdavacIme"></res:Ime>` +
                          `<res:Prezime property="pred:izdavacPrezime"></res:Prezime>` +
                        `</res:Poverenik>` +
                      `</res:Resenje>`
    Xonomy.render(xmlString, elemet, specification);
  }

  send() {
    let xmlDocument =  Xonomy.harvest();
    if(Xonomy.warnings.length !== 0) {
      this.toastr.error('Молимо Вас да исправно попуните форму!')
      return
    }

    let resenjeDto : ResenjeDto = {
      sadrzaj: xmlDocument,
      zahtevId: this.zahtevDto.id,
      zalbaId: this.zalbaDto.id,
      userEmail: this.zahtevDto.userEmail
    }

    this.resenjeService.createResenje(resenjeDto)
      .subscribe((response) => {
        this.toastr.success('Успешно сте креирали решење! Можете да је видите у "Решења".')
        this.router.navigate(['/resenje'])
      },
        err => {
          this.toastr.error('Молимо Вас да исправно попуните форму!')
      });
  }

  ngOnDestroy() {
    this.zalbaSub.unsubscribe();
    this.zahtevSub.unsubscribe();
  }
}
