import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.sass']
})
export class DialogComponent {

  form: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { zalbaId: string }
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
