import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { map, shareReplay } from 'rxjs/operators';
import { Router, NavigationEnd } from '@angular/router';


import { LoggedInUser } from './models/loggedInUser';
import { UserService } from './services/user.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  

  title = 'ISA-project';
  hospitals: any;
  user: LoggedInUser;

  constructor(private router: Router,
              private http: HttpClient, 
              private userService: UserService, 
              private breakpointObserver: BreakpointObserver) { }


  //radi otvaranje i zatvaranje side navigation u zavisnosti od velicine prozora itd...
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  
  
  
  

  ngOnInit() {
    this.getHospitals();
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        if (this.isLoggedIn()) {
          this.user = this.userService.getLoggedInUser();
        }
      }
    });
  }

  isLoggedIn() {
    return this.userService.isLoggedIn();
  }

  onLogout() {
    this.userService.logout();
    this.router.navigate(['/user/login']);
  }

  getHospitals() {
    this.http.get('http:localhost:8080/api/auth/hospital/getAll').subscribe(response => {
      this.hospitals = response; 
    }, error => {
      console.log(error);
    })
  }

  isPatient(){
    return this.userService.isPatient();
  }

  isPharmacyAdmin(){
    return this.userService.isPharmacyAdmin();
  }

  isSystemAdmin(){
    return this.userService.isSystemAdmin();
  }



}
