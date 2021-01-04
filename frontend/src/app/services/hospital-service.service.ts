import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Hospital } from '../models/hospital';


@Injectable({
  providedIn: 'root'
})
export class HospitalService {

  url = environment.baseUrl;
  _createNewHospital = this.url + environment.createNewHospital;
  _getAllHospitals = this.url + environment.getAllHospitals;
  _getOneHospital = this.url + environment.getOneHospital;
  
  constructor(private httpClient: HttpClient) { }

  public createNewHospital(hospital: Hospital) {
    return this.httpClient.post(this._createNewHospital, hospital);
  }

  public getAllHospitals() {
    return this.httpClient.get(this._getAllHospitals);
  }

  public getVehicleById(id: number) {
    return this.httpClient.get(this._getOneHospital + "/" + id);
  }
}
