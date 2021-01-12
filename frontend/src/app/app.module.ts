import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddHospitalComponent } from './components/add-hospital/add-hospital.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { AngularMaterialModule } from './angular-material/angular-material.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { PatientProfileComponent } from './components/patient-profile/patient-profile.component';
import { PharmacyAdminProfileComponent } from './components/pharmacy-admin-profile/pharmacy-admin-profile.component';
import { SystemAdminProfileComponent } from './components/system-admin-profile/system-admin-profile.component';
//import { JwtInterceptor } from './interceptors/jwt.interceptor';
//import { ErrorInterceptor } from './interceptors/error.interceptor';



@NgModule({
  declarations: [
    AppComponent,
    AddHospitalComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    PatientProfileComponent,
    PharmacyAdminProfileComponent,
    SystemAdminProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FlexLayoutModule
  
  ],
  providers: [
    //{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    //{ provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  title = "ISA 2020 app";
 }
