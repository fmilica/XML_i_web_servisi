import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllZalbeComponent } from './components/all-zalbe/all-zalbe.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { RegisterComponent } from './components/login-register/register/register.component';

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
    component: AllZalbeComponent,
  },
  {
    path: 'nova-zalba-odluka',
    component: AllZalbeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
