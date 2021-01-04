import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Hospital } from 'src/app/models/hospital';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HospitalService } from 'src/app/services/hospital-service.service';

@Component({
  selector: 'app-add-hospital',
  templateUrl: './add-hospital.component.html',
  styleUrls: ['./add-hospital.component.css']
})
export class AddHospitalComponent implements OnInit {

  public createNewHospitalForm: FormGroup;

  constructor(
    private _hospitalService: HospitalService,
    private _formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createNewHospitalForm = this._formBuilder.group({
      name: new FormControl(null, [Validators.required]),
      city: new FormControl(null, [Validators.required]),
      address: new FormControl(null, [Validators.required])
      
    })
  }

  /*getHospitals(): Observable<Hospital[]> {
    return this.http.get<Hospital[]>('http://localhost:8080/api/auth/hospita/getAll')
  }

  getOneHospital(): Observable<Hospital> {
    return this.http.get<Hospital>('http://localhost:8080/api/auth/hospital/')
  } */


  addNewHospital() : void {
    const hospital = new Hospital(
      this.createNewHospitalForm.value.name,
      this.createNewHospitalForm.value.city,
      this.createNewHospitalForm.value.address
    );
    this._hospitalService.createNewHospital(hospital).subscribe(
      () => {
        this.createNewHospitalForm.reset();
      }
    );
  }
  
}
