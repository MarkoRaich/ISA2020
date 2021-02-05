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
import { ListPharmacistsComponent } from './list-pharmacists/list-pharmacists.component';
import { AddPharmacistComponent } from './components/add-pharmacist/add-pharmacist.component';

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
    AngularMaterialModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    //{ provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  title = "ISA 2020 app";
 }
