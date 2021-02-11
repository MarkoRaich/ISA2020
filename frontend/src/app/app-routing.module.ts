import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { PatientProfileComponent } from './components/patient-profile/patient-profile.component';

import { EditPharmacyProfileComponent } from './components/edit-pharmacy-profile/edit-pharmacy-profile.component';
import { EditPharmAdminProfileComponent } from './components/edit-pharm-admin-profile/edit-pharm-admin-profile.component';
import { ListPharmacistsComponent } from './components/list-pharmacists/list-pharmacists.component';
import { ListDrugsComponent } from './components/list-drugs/list-drugs.component';
import { UserChangePasswordComponent } from './components/user-change-password/user-change-password.component';
import { ListDermatologistsComponent } from './components/list-dermatologists/list-dermatologists.component';
import { ListExaminationsComponent } from './components/list-examinations/list-examinations.component';
import {PharmaciesComponent} from './components/pharmacies/pharmacies.component';
import {EditPatientProfileComponent} from './components/edit-patient-profile/edit-patient-profile.component';
import {ReservationComponent} from './components/reservation/reservation.component';

const routes: Routes = [


  //***************** GOST - NEAUTENTIFIKOVANI KORISNIK *****************

  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'register',
    component: RegisterComponent,
    pathMatch: 'full'
  },

  //***************** SVI KORISNICI*****************************

  {
    path: 'user/change-password',
    component: UserChangePasswordComponent
  },

  //***************** PATIENT *****************

  {
    path: 'patient',
    component: PatientProfileComponent,
    pathMatch: 'full'
  },
  {
    path: 'patient/edit-patient-profile',
    component: EditPatientProfileComponent
  },
  {
    path: 'patient/get-all-reservations-active',
    component: ReservationComponent
  },

  //***************** PHARMACY ADMIN *****************

  {
    path: 'pharmacy-admin/edit-pharmacy-profile',
    component: EditPharmacyProfileComponent
  },

  {
    path: 'pharmacy-admin/edit-admin-profile',
    component: EditPharmAdminProfileComponent
  },

  {
    path: 'pharmacy-admin/list-pharmacists',
    component: ListPharmacistsComponent
  },

  {
    path: 'pharmacy-admin/list-dermatologists',
    component: ListDermatologistsComponent
  },

  {
    path: 'pharmacy-admin/list-drugs',
    component: ListDrugsComponent
  },

  {
    path: 'pharmacy-admin/examinations',
    component : ListExaminationsComponent
  },
  //***************** PATIENT *****************

  {
    path: 'patient/pharmacies',
    component: PharmaciesComponent,
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
