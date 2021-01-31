import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllZalbeCutanjeComponent } from './components/all-zalbe-cutanje/all-zalbe-cutanje.component';
import { AllZalbeOdlukaComponent } from './components/all-zalbe-odluka/all-zalbe-odluka.component';
import { AllZalbeComponent } from './components/all-zalbe/all-zalbe.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { ResenjeComponent } from './components/resenje/resenje.component';
import { ZalbaCutanjeComponent } from './components/zalba-cutanje/zalba-cutanje.component';
import { ZalbaOdlukaComponent } from './components/zalba-odluka/zalba-odluka.component';

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
        // canActivate: [LoginGuard],
      },
      {
        path: 'registracija',
        component: RegisterComponent,
      },
    ],
  },
  {
    path: 'zalbe',
    component: AllZalbeComponent,
  },
  {
    path: 'resenja',
    component: AllZalbeComponent,
  },
  {
    path: 'nova-zalba-cutanje',
    component: ZalbaCutanjeComponent,
  },
  {
    path: 'nova-zalba-odluka',
    component: ZalbaOdlukaComponent,
  },
  { 
    path: 'zalbe-cutanje',
    component: AllZalbeCutanjeComponent
  },
  {
    path: 'zalbe-odluka',
    component: AllZalbeOdlukaComponent
  },
  {
    path: 'resenje',
    component: ResenjeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
