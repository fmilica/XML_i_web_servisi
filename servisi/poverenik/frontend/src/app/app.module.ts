import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import {MatTooltipModule} from '@angular/material/tooltip';

import { ToastrModule } from 'ngx-toastr';
import { RegisterComponent } from './components/login-register/register/register.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { ZalbaCutanjeComponent } from './components/gradjanin/zalba-cutanje/zalba-cutanje.component';
import { ZalbaOdlukaComponent } from './components/gradjanin/zalba-odluka/zalba-odluka.component';
import { ResenjeComponent } from './components/poverenik/resenje/resenje.component';
import { ZalbeCutanjeGradjaninComponent } from './components/gradjanin/zalbe-cutanje-gradjanin/zalbe-cutanje-gradjanin.component';
import { ZalbeOdlukuGradjaninComponent } from './components/gradjanin/zalbe-odluku-gradjanin/zalbe-odluku-gradjanin.component';
import { ZalbeOdlukuPoverenikComponent } from './components/poverenik/zalbe-odluku-poverenik/zalbe-odluku-poverenik.component';
import { ZalbeCutanjePoverenikComponent } from './components/poverenik/zalbe-cutanje-poverenik/zalbe-cutanje-poverenik.component';
import { ResenjaPoverenikComponent } from './components/poverenik/resenja-poverenik/resenja-poverenik.component';
import { ResenjaGradjaninComponent } from './components/gradjanin/resenja-gradjanin/resenja-gradjanin.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    LoginRegisterComponent,
    ZalbaCutanjeComponent,
    ZalbaOdlukaComponent,
    ResenjeComponent,
    ZalbeCutanjeGradjaninComponent,
    ZalbeOdlukuGradjaninComponent,
    ZalbeOdlukuPoverenikComponent,
    ZalbeCutanjePoverenikComponent,
    ResenjaPoverenikComponent,
    ResenjaGradjaninComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatTooltipModule,
    ToastrModule.forRoot({
      positionClass: 'toast-custom',
      timeOut: 2500,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
