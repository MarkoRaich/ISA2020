import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';


import { MatFormFieldModule } from "@angular/material/form-field";
import { HomeComponent } from './components/home/home.component';
import { AngularMaterialModule } from './angular-material/angular-material.module';
import { ToastrModule } from 'ngx-toastr';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { PatientProfileComponent } from './components/patient-profile/patient-profile.component';
import { PharmacyAdminProfileComponent } from './components/pharmacy-admin-profile/pharmacy-admin-profile.component';
import { SystemAdminProfileComponent } from './components/system-admin-profile/system-admin-profile.component';
import { EditPharmacyProfileComponent } from './components/edit-pharmacy-profile/edit-pharmacy-profile.component';
import { EditPharmAdminProfileComponent } from './components/edit-pharm-admin-profile/edit-pharm-admin-profile.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { MapComponent } from './components/map/map.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatNativeDateModule } from '@angular/material/core';
import { ListPharmacistsComponent } from './components/list-pharmacists/list-pharmacists.component';
import { AddPharmacistComponent } from './components/add-pharmacist/add-pharmacist.component';
import { ListDrugsComponent } from './components/list-drugs/list-drugs.component';
import { AddDrugInPharmacyComponent } from './components/add-drug-in-pharmacy/add-drug-in-pharmacy.component';
import { ChangeDrugQuantityComponent } from './components/change-drug-quantity/change-drug-quantity.component';
import { UserChangePasswordComponent } from './components/user-change-password/user-change-password.component';
import { ListDermatologistsComponent } from './components/list-dermatologists/list-dermatologists.component';
import { AddDermatologistComponent } from './components/add-dermatologist/add-dermatologist.component';
import { ListExaminationsComponent } from './components/list-examinations/list-examinations.component';
import { AddExaminationComponent } from './components/add-examination/add-examination.component';
import { PharmaciesComponent } from './components/pharmacies/pharmacies.component';
import { FinancialReportComponent } from './components/financial-report/financial-report.component';
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { PharmacyAdminGuard } from './guards/pharmacyAdmin.guard';
import { PatientGuard } from './guards/patient.guard';
import { NonAuthorizedComponent } from './components/non-authorized/non-authorized.component';
import { NonAuthenticatedComponent } from './components/non-authenticated/non-authenticated.component';
import { ErrorComponent } from './components/error/error.component';
import { EditPatientProfileComponent } from './components/edit-patient-profile/edit-patient-profile.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { ListExaminationsBookedComponent } from './components/list-examinations-booked/list-examinations-booked.component';
import { ListExaminationsAvailableComponent } from './components/list-examinations-available/list-examinations-available.component';
import { ListConsultationsBookedComponent } from './components/list-consultations-booked/list-consultations-booked.component';
import { PharmacyProfileComponent } from './components/pharmacy-profile/pharmacy-profile.component';
import { PharmacistInPharmacyComponent } from './components/pharmacist-in-pharmacy/pharmacist-in-pharmacy.component';
import { DermatologistInPharmacyComponent } from './components/dermatologist-in-pharmacy/dermatologist-in-pharmacy.component';
import { DrugInPharmacyComponent } from './components/drug-in-pharmacy/drug-in-pharmacy.component';
import { ExaminationInPharmacyComponent } from './components/examination-in-pharmacy/examination-in-pharmacy.component';
//import { ErrorInterceptor } from './interceptors/error.interceptor';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    PatientProfileComponent,
    PharmacyAdminProfileComponent,
    SystemAdminProfileComponent,
    EditPharmacyProfileComponent,
    EditPharmAdminProfileComponent,
    MapComponent,
    ListPharmacistsComponent,
    AddPharmacistComponent,
    ListDrugsComponent,
    AddDrugInPharmacyComponent,
    ChangeDrugQuantityComponent,
    UserChangePasswordComponent,
    ListDermatologistsComponent,
    AddDermatologistComponent,
    ListExaminationsComponent,
    AddExaminationComponent,
    PharmaciesComponent,
    FinancialReportComponent,
    NonAuthorizedComponent,
    NonAuthenticatedComponent,
    ErrorComponent,
    EditPatientProfileComponent,
    ReservationComponent,
    ListExaminationsBookedComponent,
    ListExaminationsAvailableComponent,
    ListConsultationsBookedComponent,
    PharmacyProfileComponent,
    PharmacistInPharmacyComponent,
    DermatologistInPharmacyComponent,
    DrugInPharmacyComponent,
    ExaminationInPharmacyComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    LayoutModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 2000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
    AngularMaterialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    PharmacyAdminGuard,
    PatientGuard
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  title = "ISA 2020 app";
 }
