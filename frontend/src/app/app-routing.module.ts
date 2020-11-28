import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { AddHospitalComponent } from './components/add-hospital/add-hospital.component';


const routes: Routes = [
  {
    path: '',
    component: AppComponent
  },

  {
    path: 'hopsital',
    component: AddHospitalComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
