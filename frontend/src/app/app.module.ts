import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ObavestenjeComponent } from './components/obavestenje/obavestenje.component';
import { ViewObavestenjeComponent } from './components/obavestenje/view-obavestenje/view-obavestenje.component';

@NgModule({
  declarations: [AppComponent, ObavestenjeComponent, ViewObavestenjeComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, RouterModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
