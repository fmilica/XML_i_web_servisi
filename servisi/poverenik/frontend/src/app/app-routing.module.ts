import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { ResenjeComponent } from './components/poverenik/resenje/resenje.component';
import { ZalbaCutanjeComponent } from './components/gradjanin/zalba-cutanje/zalba-cutanje.component';
import { ZalbaOdlukaComponent } from './components/gradjanin/zalba-odluka/zalba-odluka.component';
import { ZalbeCutanjeGradjaninComponent } from './components/gradjanin/zalbe-cutanje-gradjanin/zalbe-cutanje-gradjanin.component';
import { ZalbeOdlukuGradjaninComponent } from './components/gradjanin/zalbe-odluku-gradjanin/zalbe-odluku-gradjanin.component';
import { ResenjaGradjaninComponent } from './components/gradjanin/resenja-gradjanin/resenja-gradjanin.component';
import { ZalbeOdlukuPoverenikComponent } from './components/poverenik/zalbe-odluku-poverenik/zalbe-odluku-poverenik.component';
import { ZalbeCutanjePoverenikComponent } from './components/poverenik/zalbe-cutanje-poverenik/zalbe-cutanje-poverenik.component';
import { ResenjaPoverenikComponent } from './components/poverenik/resenja-poverenik/resenja-poverenik.component';

const routes: Routes = [
  //Putanje kojima mogu svi da pristupe
  { path: '', redirectTo: '/prijava-registracija/prijava', pathMatch: 'full' },
  {
    path: 'prijava-registracija',
    component: LoginRegisterComponent,
    children: [
      {
        path: '',
        redirectTo: 'prijava',
        pathMatch: 'full',
      },
      {
        path: 'prijava',
        component: LoginComponent,
        // canActivate: [LoginGuard],
      },
      {
        path: 'registracija',
        component: RegisterComponent,
      },
    ],
  },
  //Gradjanin putanje
  {
    path: 'nova-zalba-cutanje',
    component: ZalbaCutanjeComponent,
  },
  {
    path: 'nova-zalba-odluka',
    component: ZalbaOdlukaComponent,
  },
  {
    path: 'moje-zalbe-odluka',
    component: ZalbeOdlukuGradjaninComponent
  },
  {
    path: 'moje-zalbe-cutanje',
    component: ZalbeCutanjeGradjaninComponent
  },
  {
    path: 'resenja',
    component: ResenjaGradjaninComponent
  },
  //Poverenik putanje
  {
    path: 'resenje',
    component: ResenjeComponent
  },
  {
    path: 'zalbe-odluka',
    component: ZalbeOdlukuPoverenikComponent
  },
  {
    path: 'zalbe-cutanje',
    component: ZalbeCutanjePoverenikComponent
  },
  {
    path: 'moja-resenja',
    component: ResenjaPoverenikComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
