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

import { ToastrModule } from 'ngx-toastr';
import { RegisterComponent } from './components/login-register/register/register.component';
import { HeaderComponent } from './components/header/header.component';
import { AllZalbeComponent } from './components/all-zalbe/all-zalbe.component';
import { AllZalbeCutanjeComponent } from './components/all-zalbe-cutanje/all-zalbe-cutanje.component';
import { AllZalbeOdlukaComponent } from './components/all-zalbe-odluka/all-zalbe-odluka.component';
import { ViewZalbaCutanjeComponent } from './components/view-zalba-cutanje/view-zalba-cutanje.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { ZalbaCutanjeComponent } from './components/zalba-cutanje/zalba-cutanje.component';
import { ZalbaOdlukaComponent } from './components/zalba-odluka/zalba-odluka.component';
import { ObavestenjeComponent } from './components/obavestenje/obavestenje.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    AllZalbeComponent,
    AllZalbeCutanjeComponent,
    AllZalbeOdlukaComponent,
    ViewZalbaCutanjeComponent,
    LoginRegisterComponent,
    ZalbaCutanjeComponent,
    ZalbaOdlukaComponent,
    ObavestenjeComponent,
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
    ToastrModule.forRoot({
      positionClass: 'toast-custom',
      timeOut: 2500,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
