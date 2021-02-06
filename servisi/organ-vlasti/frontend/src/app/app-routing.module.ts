import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllObavestenjaGradjaninComponent } from './components/gradjanin/all-obavestenja-gradjanin/all-obavestenja-gradjanin.component';
import { AllZahteviGradjaninComponent } from './components/gradjanin/all-zahtevi-gradjanin/all-zahtevi-gradjanin.component';
import { NewZahtevComponent } from './components/gradjanin/new-zahtev/new-zahtev.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { AllIzvestajiComponent } from './components/sluzbenik/all-izvestaji/all-izvestaji.component';
import { AllObavestenjaSluzbenikComponent } from './components/sluzbenik/all-obavestenja-sluzbenik/all-obavestenja-sluzbenik.component';
import { AllZahteviSluzbenikComponent } from './components/sluzbenik/all-zahtevi-sluzbenik/all-zahtevi-sluzbenik.component';
import { IzvestajiComponent } from './components/sluzbenik/izvestaji/izvestaji.component';
import { NewObavestenjeComponent } from './components/sluzbenik/new-obavestenje/new-obavestenje.component';
import { ResenjaComponent } from './components/sluzbenik/resenja/resenja.component';
import { ZalbeCutanjeComponent } from './components/sluzbenik/zalbe-cutanje/zalbe-cutanje.component';
import { ZalbeOdlukaComponent } from './components/sluzbenik/zalbe-odluka/zalbe-odluka.component';
import { LoginGuard } from './guards/login-guard.service';
import { ObavestenjeGuard } from './guards/obavestenje-guard.service';
import { RoleGuard } from './guards/role-guard.service';

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
        //canActivate: [LoginGuard]
      },
      {
        path: 'registracija',
        component: RegisterComponent,
        canActivate: [LoginGuard]
      },
    ],
  },
  {
    path: 'novi-zahtev',
    component: NewZahtevComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'zahtevi',
    component: AllZahteviGradjaninComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'obavestenja',
    component: AllObavestenjaGradjaninComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'novo-obavestenje',
    component: NewObavestenjeComponent,
    canActivate: [RoleGuard, ObavestenjeGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'pristigli-zahtevi',
    component: AllZahteviSluzbenikComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'kreirana-obavestenja',
    component: AllObavestenjaSluzbenikComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'zalbe-cutanje',
    component: ZalbeCutanjeComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'zalbe-odluka',
    component: ZalbeOdlukaComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'resenje',
    component: ResenjaComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'izvestaj/:id',
    component: IzvestajiComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'izvestaji',
    component: AllIzvestajiComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
