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
import { FinancialReportComponent } from './components/financial-report/financial-report.component';
import { NonAuthorizedComponent } from './components/non-authorized/non-authorized.component';
import { NonAuthenticatedComponent } from './components/non-authenticated/non-authenticated.component';
import { ErrorComponent } from './components/error/error.component';
import { PharmacyAdminGuard } from './guards/pharmacyAdmin.guard';

import {EditPatientProfileComponent} from './components/edit-patient-profile/edit-patient-profile.component';
import {ReservationComponent} from './components/reservation/reservation.component';
import {ExaminationDerm} from './models/ExaminationDerm';
import {ListExaminationsBookedComponent} from './components/list-examinations-booked/list-examinations-booked.component';
import {ListExaminationsAvailableComponent} from './components/list-examinations-available/list-examinations-available.component';
import {ListConsultationsBookedComponent} from './components/list-consultations-booked/list-consultations-booked.component';

import { PharmacyProfileComponent } from './components/pharmacy-profile/pharmacy-profile.component';

import {ListConsultationsNotDoneCanceledComponent} from './components/list-consultations-not-done-canceled/list-consultations-not-done-canceled.component';
import {ListAllDrugsComponent} from './components/list-all-drugs/list-all-drugs.component';
import {GuestDrugsComponent} from './components/guest-drugs/guest-drugs.component';
import {GuestPharmaciesComponent} from './components/guest-pharmacies/guest-pharmacies.component';
import {ListPharmaciesComponent} from './components/list-pharmacies/list-pharmacies.component';
import {ListAllComplaintsComponent} from './components/list-all-complaints/list-all-complaints.component';
import {MakeComplaintComponent} from './components/make-complaint/make-complaint.component';
import {ListDrugReservationComponent} from './components/list-drug-reservation/list-drug-reservation.component';
import {ListExaminationsDoneComponent} from './components/list-examinations-done/list-examinations-done.component';


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
  {
    path: 'guest/drugs',
    component: GuestDrugsComponent,
  },

  {
    path: 'guest/pharmacies',
    component: GuestPharmaciesComponent,
  },

  //***************** GRESKE i PRAVA PRISTUPA******************************

  {
    path: 'error/non-authenticated',
    component: NonAuthenticatedComponent,
  },
  {
    path: 'error/non-authorized',
    component: NonAuthorizedComponent
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
  {
    path: 'patient/pharmacies',
    component: PharmaciesComponent,
  },

  {
    path: 'patient/all-examinations-booked',
    component: ListExaminationsBookedComponent,
  },

  {
    path: 'patient/all-examinations-available',
    component: ListExaminationsAvailableComponent,
  },

  {
    path: 'patient/all-consultations-booked',
    component: ListConsultationsBookedComponent,
  },

  {
    path: 'patient/all-consultations-done-canceled',
    component: ListConsultationsNotDoneCanceledComponent,
  },

  {
    path: 'patient/all-drugs',
    component: ListAllDrugsComponent,
  },

  {
    path: 'patient/pharmacy-search',
    component: ListPharmaciesComponent,
  },

  {
    path: 'patient/complaints',
    component: ListAllComplaintsComponent,
  },
  {
    path: 'patient/make-complaint',
    component: MakeComplaintComponent,
  },

  {
    path: 'patient/make-drug-reservation',
    component: ListDrugReservationComponent,
  },

  {
    path: 'patient/list-examinations-done',
    component: ListExaminationsDoneComponent,
  },




  //***************** PHARMACY ADMIN *****************

  {
    path: 'pharmacy-admin/edit-pharmacy-profile',
    component: EditPharmacyProfileComponent,
    canActivate: [PharmacyAdminGuard]
  },

  {
    path: 'pharmacy-admin/edit-admin-profile',
    component: EditPharmAdminProfileComponent,
    canActivate: [PharmacyAdminGuard]
  },

  {
    path: 'pharmacy-admin/list-pharmacists',
    component: ListPharmacistsComponent,
    canActivate: [PharmacyAdminGuard]
  },

  {
    path: 'pharmacy-admin/list-dermatologists',
    component: ListDermatologistsComponent,
    canActivate: [PharmacyAdminGuard]
  },

  {
    path: 'pharmacy-admin/list-drugs',
    component: ListDrugsComponent,
    canActivate: [PharmacyAdminGuard]
  },

  {
    path: 'pharmacy-admin/examinations',
    component : ListExaminationsComponent,
    canActivate: [PharmacyAdminGuard]
  },

  {
    path: 'pharmacy-admin/financial-report',
    component: FinancialReportComponent,
    canActivate: [PharmacyAdminGuard]
  },
  //***************** PATIENT *****************

  {
    path: 'patient/pharmacies',
    component: PharmaciesComponent,
  },

  {
    path: 'patient/pharmacy-profile/:id',
    component: PharmacyProfileComponent
  }




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
