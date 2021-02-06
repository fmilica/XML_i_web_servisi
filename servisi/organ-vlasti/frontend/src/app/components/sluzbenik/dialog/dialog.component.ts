import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import * as JsonToXML from 'js2xmlparser';
import { IzjasnjenjeDto } from 'src/app/model/izjasnjenje-dto.model';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje-service';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka-service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.sass']
})
export class DialogComponent {

  form: FormGroup;

  constructor(
    public zalbaCutanjeService: ZalbaCutanjeService,
    public zalbaOdlukaService: ZalbaOdlukaService,
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { zalbaId: string, tipZalbe: string }
  ) { 
    this.form = new FormGroup({
      text: new FormControl('', [Validators.required])
    });
  }

  close(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    if (this.form.invalid) {
        return;
    }
    console.log(this.data)
    
    let izjasnjenjeDto :IzjasnjenjeDto = {
      id_zalbe : this.data.zalbaId,
      izjasnjenje: this.form.get('text').value
    }

    const options = {
      declaration: {
        include: false,
      },
    };

    let xmlDocument: string = JsonToXML.parse(
      'OdgovorDTO',
      izjasnjenjeDto,
      options
    );

    if(this.data.tipZalbe==='zalbacutanje'){
      this.zalbaCutanjeService.posaljiOdgovor(xmlDocument).subscribe(()=>{console.log("poslao")});
    }else{
      this.zalbaOdlukaService.posaljiOdgovor(xmlDocument).subscribe(()=>{console.log("poslao")});
    }

    //TO-DO TREBA DA POSALJE MEJL ILI XML DOKUMENT ILI STA GOD OVDE
    this.dialogRef.close();
  }

  getTextErrorMessage(): string {
    if (this.form.controls.text.touched) {
        if ( this.form.controls.text.hasError('required')) {
        return 'Обавезно поље';
        }
    }
    return '';
  }

}
