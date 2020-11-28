import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ISA-project';

  hospitals: any;

  constructor(private http: HttpClient) {}

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
