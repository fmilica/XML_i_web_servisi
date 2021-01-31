import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllObavestenjaGradjaninComponent } from './components/gradjanin/all-obavestenja-gradjanin/all-obavestenja-gradjanin.component';
import { AllZahteviGradjaninComponent } from './components/gradjanin/all-zahtevi-gradjanin/all-zahtevi-gradjanin.component';
import { NewZahtevComponent } from './components/gradjanin/new-zahtev/new-zahtev.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { AllObavestenjaSluzbenikComponent } from './components/sluzbenik/all-obavestenja-sluzbenik/all-obavestenja-sluzbenik.component';
import { AllZahteviSluzbenikComponent } from './components/sluzbenik/all-zahtevi-sluzbenik/all-zahtevi-sluzbenik.component';
import { NewObavestenjeComponent } from './components/sluzbenik/new-obavestenje/new-obavestenje.component';

const routes: Routes = [
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
      },
      {
        path: 'registracija',
        component: RegisterComponent,
      },
    ],
  },
  {
    path: 'novi-zahtev',
    component: NewZahtevComponent
  },
  {
    path: 'zahtevi',
    component: AllZahteviGradjaninComponent
  },
  {
    path: 'obavestenja',
    component: AllObavestenjaGradjaninComponent
  },
  {
    path: 'novo-obavestenje',
    component: NewObavestenjeComponent
  },
  {
    path: 'pristigli-zahtevi',
    component: AllZahteviSluzbenikComponent
  },
  {
    path: 'kreirana-obavestenja',
    component: AllObavestenjaSluzbenikComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
