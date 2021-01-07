import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';


import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';

import { map, shareReplay } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ISA-project';

  //radi otvaranje i zatvaranje side navigation u zavisnosti od velicine prozora itd...
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  hospitals: any;
  
  constructor(private http: HttpClient, private breakpointObserver: BreakpointObserver) { }
  

  ngOnInit() {
    this.getHospitals();
  }
  

  getHospitals() {
    this.http.get('http:localhost:8080/api/auth/hospital/getAll').subscribe(response => {
      this.hospitals = response; 
    }, error => {
      console.log(error);
    })
  }
}
