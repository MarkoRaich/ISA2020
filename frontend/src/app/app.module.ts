import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
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
import { RatingModule } from 'ng-starrating';
import { ChartsModule } from 'ng2-charts';
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
import { ListConsultationsNotDoneCanceledComponent } from './components/list-consultations-not-done-canceled/list-consultations-not-done-canceled.component';
import { ListAllDrugsComponent } from './components/list-all-drugs/list-all-drugs.component';
import { GuestDrugsComponent } from './components/guest-drugs/guest-drugs.component';
import { GuestPharmaciesComponent } from './components/guest-pharmacies/guest-pharmacies.component';
import { PatientPharmaciesComponent } from './components/patient-pharmacies/patient-pharmacies.component';
import { CreatePromotionComponent } from './components/create-promotion/create-promotion.component';
import { ListPurchaseOrdersComponent } from './components/list-purchase-orders/list-purchase-orders.component';
import { AddPurchaseOrderComponent } from './components/add-purchase-order/add-purchase-order.component';
import { ListOrderItemsComponent } from './components/list-order-items/list-order-items.component';
import { ListPharmaciesComponent } from './components/list-pharmacies/list-pharmacies.component';
import { ListAllComplaintsComponent } from './components/list-all-complaints/list-all-complaints.component';
import { MakeComplaintComponent } from './components/make-complaint/make-complaint.component';
import { DrugReservationComponent } from './components/drug-reservation/drug-reservation.component';
import { ListDrugReservationComponent } from './components/list-drug-reservation/list-drug-reservation.component';
import { ListExaminationsDoneComponent } from './components/list-examinations-done/list-examinations-done.component';
import { PriceListComponent } from './components/price-list/price-list.component';
import { AddOrderItemComponent } from './components/add-order-item/add-order-item.component';
import { ListOffersComponent } from './components/list-offers/list-offers.component';
import { MonthlyStatisticComponent } from './components/charts/monthly-statistic/monthly-statistic.component';
import { QuartalStatisticComponent } from './components/charts/quartal-statistic/quartal-statistic.component';
import { YearStatisticComponent } from './components/charts/year-statistic/year-statistic.component';
import { MonthStatDrugsComponent } from './components/charts/month-stat-drugs/month-stat-drugs.component';
import { QuartalStatDrugsComponent } from './components/charts/quartal-stat-drugs/quartal-stat-drugs.component';
import { YearStatDrugsComponent } from './components/charts/year-stat-drugs/year-stat-drugs.component';
import { IncomeStatComponent } from './components/charts/income-stat/income-stat.component';
import { ListVacationsComponent } from './components/list-vacations/list-vacations.component';
import { DenialReasonComponent } from './components/denial-reason/denial-reason.component';
import { AddPricelistComponent } from './components/add-pricelist/add-pricelist.component';
import { ChangeDrugPriceComponent } from './components/change-drug-price/change-drug-price.component';





@NgModule({
  declarations: [
    AppComponent,
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
    ListConsultationsNotDoneCanceledComponent,
    ListAllDrugsComponent,
    GuestDrugsComponent,
    GuestPharmaciesComponent,
    PatientPharmaciesComponent,
    CreatePromotionComponent,
    ListPurchaseOrdersComponent,
    AddPurchaseOrderComponent,
    ListOrderItemsComponent,
    ListPharmaciesComponent,
    ListAllComplaintsComponent,
    MakeComplaintComponent,
    DrugReservationComponent,
    ListDrugReservationComponent,
    ListExaminationsDoneComponent,
    PriceListComponent,
    AddOrderItemComponent,
    ListOffersComponent,
    MonthlyStatisticComponent,
    QuartalStatisticComponent,
    YearStatisticComponent,
    MonthStatDrugsComponent,
    QuartalStatDrugsComponent,
    YearStatDrugsComponent,
    IncomeStatComponent,
    ListVacationsComponent,
    DenialReasonComponent,
    AddPricelistComponent,
    ChangeDrugPriceComponent

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
    RatingModule,
    ChartsModule,
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
