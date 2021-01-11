import { HttpEvent, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { LoggedInUser } from '../models/loggedInUser';
import { UserTokenState } from '../models/userTokenState';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs';

//PRESRECE SVAKI ZAHTEV KOJI SE SALJE NA SERVER I DODAJE MU TOKEN U HEADER!
@Injectable()
export class AuthInterceptor implements HttpInterceptor {


  loggedInUser: LoggedInUser;
    userTokenState: UserTokenState;

    constructor(public userService: UserService) { }
    
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        this.loggedInUser = JSON.parse(localStorage.getItem("LoggedInUser"));

        if (this.loggedInUser) {
            this.userTokenState = this.loggedInUser.userTokenState;
            if (this.userTokenState) {
                if (this.userTokenState.accessToken) {
                    request = request.clone({
                        setHeaders: {
                            Authorization: `Bearer ${this.userTokenState.accessToken}`
                        }
                    });
                }
            }
        }

        return next.handle(request);
    }

  }