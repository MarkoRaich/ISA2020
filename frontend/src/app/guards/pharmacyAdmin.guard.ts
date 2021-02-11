import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { LoggedInUser } from "../models/loggedInUser";
import { UserService } from "../services/user.service";


@Injectable({ providedIn: 'root' })
export class PharmacyAdminGuard implements CanActivate{
    
    loggedInUser: LoggedInUser;

    constructor(private router: Router,
                private userService: UserService) { }
    
    
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
       
        this.loggedInUser = this.userService.getLoggedInUser();

        if (this.loggedInUser) {
            if (this.loggedInUser.role === "PHARMACY_ADMIN") {
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