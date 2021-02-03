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
import { RoleGuard } from './guards/role-guard.service';
import { LoginGuard } from './guards/login-guard.service';
import { IzvestajComponent } from './components/poverenik/izvestaj/izvestaj.component';
import { AllIzvestajiComponent } from './components/poverenik/all-izvestaji/all-izvestaji.component';

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
        canActivate: [LoginGuard]
      },
    ],
  },
  //Gradjanin putanje
  {
    path: 'nova-zalba-cutanje',
    component: ZalbaCutanjeComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'nova-zalba-odluka',
    component: ZalbaOdlukaComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'moje-zalbe-odluka',
    component: ZalbeOdlukuGradjaninComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'moje-zalbe-cutanje',
    component: ZalbeCutanjeGradjaninComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'moja-resenja',
    component: ResenjaGradjaninComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  //Poverenik putanje
  {
    path: 'resenje',
    component: ResenjeComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'zalbe-odluka',
    component: ZalbeOdlukuPoverenikComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'zalbe-cutanje',
    component: ZalbeCutanjePoverenikComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'resenja',
    component: ResenjaPoverenikComponent,
    canActivate: [RoleGuard],
    data: { expectedRoles: 'ROLE_SLUZBENIK' }
  },
  {
    path: 'izvestaj/:id',
    component: IzvestajComponent,
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
  exports: [RouterModule],
})
export class AppRoutingModule {}
