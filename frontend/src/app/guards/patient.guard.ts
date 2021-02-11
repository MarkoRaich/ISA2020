import { LoggedInUser } from './../models/loggedInUser';
import { UserService } from './../services/user.service';
import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class PatientGuard implements CanActivate {

    loggedInUser: LoggedInUser;

    constructor(
        private router: Router,
        private userService: UserService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        this.loggedInUser = this.userService.getLoggedInUser();

        if (this.loggedInUser) {
            if (this.loggedInUser.role === "PATIENT") {
                return true;
            } else {
                this.router.navigate(['/error/non-authorized']);
                return false;
            }
        }

        this.router.navigate(['/error/non-authenticated']);
        return false;
    }
}