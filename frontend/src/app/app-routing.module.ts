import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewObavestenjeComponent } from './components/obavestenje/view-obavestenje/view-obavestenje.component';

const routes: Routes = [
  {
    path: 'obavestenje',
    component: ViewObavestenjeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
