import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { AddHospitalComponent } from './components/add-hospital/add-hospital.component';


const routes: Routes = [
  {
    path: '',
    component: ProductListComponent
  },

  {
    path: 'hospital',
    component: AddHospitalComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
