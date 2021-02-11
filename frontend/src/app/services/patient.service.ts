import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {PharmacyAdmin} from '../models/PharmacyAdmin';
import {Patient} from '../models/Patient';


@Injectable({
  providedIn: 'root'
})
export class PatientService {

  url = environment.baseUrl + environment.patient;


  constructor(private httpClient: HttpClient, private router: Router) { }



  public get(id: number) {
    return this.httpClient.get(this.url + '/' + id);
  }

  public put(patient: Patient) {
    return this.httpClient.put(this.url + '/', patient);
  }


}
