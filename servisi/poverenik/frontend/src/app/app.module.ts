import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatRadioModule } from '@angular/material/radio';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ResenjaGradjaninComponent } from './components/gradjanin/resenja-gradjanin/resenja-gradjanin.component';
import { ZalbaCutanjeComponent } from './components/gradjanin/zalba-cutanje/zalba-cutanje.component';
import { ZalbaOdlukaComponent } from './components/gradjanin/zalba-odluka/zalba-odluka.component';
import { ZalbeCutanjeGradjaninComponent } from './components/gradjanin/zalbe-cutanje-gradjanin/zalbe-cutanje-gradjanin.component';
import { ZalbeOdlukuGradjaninComponent } from './components/gradjanin/zalbe-odluku-gradjanin/zalbe-odluku-gradjanin.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { ResenjaPoverenikComponent } from './components/poverenik/resenja-poverenik/resenja-poverenik.component';
import { ResenjeComponent } from './components/poverenik/resenje/resenje.component';
import { ZalbeCutanjePoverenikComponent } from './components/poverenik/zalbe-cutanje-poverenik/zalbe-cutanje-poverenik.component';
import { ZalbeOdlukuPoverenikComponent } from './components/poverenik/zalbe-odluku-poverenik/zalbe-odluku-poverenik.component';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';



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
    MatRadioModule,
    ToastrModule.forRoot(),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      // ako multi nije true ovo bi bio jedini interceptor i pregazio bi sve defaultne interceptore
      multi: true,
      }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
