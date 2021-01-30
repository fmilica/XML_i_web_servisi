import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ToastrModule } from 'ngx-toastr';
import { HeaderComponent } from './components/header/header.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { NewZahtevComponent } from './components/gradjanin/new-zahtev/new-zahtev.component';
import { AllZahteviComponent } from './components/gradjanin/all-zahtevi/all-zahtevi.component';
import { AllObavestenjaComponent } from './components/gradjanin/all-obavestenja/all-obavestenja.component';
import { NewObavestenjeComponent } from './components/sluzbenik/new-obavestenje/new-obavestenje.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginRegisterComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    NewZahtevComponent,
    AllZahteviComponent,
    AllObavestenjaComponent,
    NewObavestenjeComponent
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
    ToastrModule.forRoot({
      positionClass: 'toast-custom',
      timeOut: 2500,
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
