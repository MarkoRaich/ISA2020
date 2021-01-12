import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoggedInUser } from '../models/loggedInUser';
import { UserLoginRequest } from '../models/userLoginRequest';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class UserService {

    access_token = null;
  
    loggedInUserSubject: BehaviorSubject<LoggedInUser>;
    loggedInUser: Observable<LoggedInUser>;




    constructor(private http: HttpClient, private router: Router) {
        this.loggedInUserSubject = new BehaviorSubject<LoggedInUser>(JSON.parse(localStorage.getItem('LoggedInUser')));
        this.loggedInUser = this.loggedInUserSubject.asObservable();
    }

    login(user: UserLoginRequest) {
        return this.http.post(environment.baseUrl + environment.login, user).pipe(map((res: LoggedInUser) => {
          this.access_token = res.userTokenState.accessToken;
          localStorage.setItem('LoggedInUser', JSON.stringify(res));
          this.loggedInUserSubject.next(res);
        }));
    }

    logout() {
        this.access_token = null;
        localStorage.removeItem('LoggedInUser');
        this.router.navigate(['']);
    }

    
    getLoggedInUser(): LoggedInUser {
        return this.loggedInUserSubject.value;
      }


    isLoggedIn() {
        return localStorage.getItem('LoggedInUser') !== null;
    }

    isNormalUser() {
        if (this.isLoggedIn()) {
          return this.loggedInUserSubject.value.role === "ROLE_NORMAL_USER";
        }
    }

    isPatient() {
        if (this.isLoggedIn()) {
          return this.loggedInUserSubject.value.role === "PATIENT";
        }
      }

    isPharmacist(){
        if (this.isLoggedIn()) {
            return this.loggedInUserSubject.value.role === "PHARMACIST";
          }
    }

    isDermatologist(){
        if (this.isLoggedIn()) {
            return this.loggedInUserSubject.value.role === "DERMATOLOGIST";
          }
    }

    isPharmacyAdmin(){
        if (this.isLoggedIn()) {
            return this.loggedInUserSubject.value.role === "PHARMACY_ADMIN";
          }
    }

    isSystemAdmin(){
        if (this.isLoggedIn()) {
            return this.loggedInUserSubject.value.role === "SYSTEM_ADMIN";
          }
    }

    isSupplier(){
        if (this.isLoggedIn()) {
            return this.loggedInUserSubject.value.role === "SUPPLIER";
          }
    }
}