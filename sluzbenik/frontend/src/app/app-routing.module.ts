import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllObavestenjaComponent } from './components/gradjanin/all-obavestenja/all-obavestenja.component';
import { AllZahteviComponent } from './components/gradjanin/all-zahtevi/all-zahtevi.component';
import { NewZahtevComponent } from './components/gradjanin/new-zahtev/new-zahtev.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
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
    component: AllZahteviComponent
  },
  {
    path: 'obavestenja',
    component: AllObavestenjaComponent
  },
  {
    path: 'novo-obavestenje',
    component: NewObavestenjeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
