import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ToastrModule } from 'ngx-toastr';
import { HeaderComponent } from './components/header/header.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { NewZahtevComponent } from './components/gradjanin/new-zahtev/new-zahtev.component';
import { AllZahteviGradjaninComponent } from './components/gradjanin/all-zahtevi-gradjanin/all-zahtevi-gradjanin.component';
import { AllObavestenjaGradjaninComponent } from './components/gradjanin/all-obavestenja-gradjanin/all-obavestenja-gradjanin.component';
import { NewObavestenjeComponent } from './components/sluzbenik/new-obavestenje/new-obavestenje.component';
import { AllZahteviSluzbenikComponent } from './components/sluzbenik/all-zahtevi-sluzbenik/all-zahtevi-sluzbenik.component';
import { AllObavestenjaSluzbenikComponent } from './components/sluzbenik/all-obavestenja-sluzbenik/all-obavestenja-sluzbenik.component';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { ZalbeCutanjeComponent } from './components/sluzbenik/zalbe-cutanje/zalbe-cutanje.component';
import { ZalbeOdlukaComponent } from './components/sluzbenik/zalbe-odluka/zalbe-odluka.component';
import { ResenjaComponent } from './components/sluzbenik/resenja/resenja.component';
import { DialogComponent } from './components/sluzbenik/dialog/dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginRegisterComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    NewZahtevComponent,
    AllZahteviGradjaninComponent,
    AllObavestenjaGradjaninComponent,
    NewObavestenjeComponent,
    AllZahteviSluzbenikComponent,
    AllObavestenjaSluzbenikComponent,
    ZalbeCutanjeComponent,
    ZalbeOdlukaComponent,
    ResenjaComponent,
    DialogComponent
  ],
  imports: [
    AppRoutingModule, 
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    MatTabsModule,
    MatTooltipModule,
    MatRadioModule,
    MatButtonModule,
    MatDialogModule,
    ToastrModule.forRoot()
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptorService,
    // ako multi nije true ovo bi bio jedini interceptor i pregazio bi sve defaultne interceptore
    multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
