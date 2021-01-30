import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PharmacyAdmin } from '../models/PharmacyAdmin';

@Injectable({
  providedIn: 'root'
})
export class PharmacyAdminService {

  url= environment.baseUrl + environment.pharmacyAdmin;

  pharmacyAdmins: BehaviorSubject<PharmacyAdmin[]> = new BehaviorSubject<PharmacyAdmin[]>([]);
  addSuccessEmitter = new Subject<PharmacyAdmin>();

  constructor(private httpClient : HttpClient, private router : Router) { }



  public get(id: number) {
    return this.httpClient.get(this.url + '/' + id);
  }

  public put(pharmacyAdmin: PharmacyAdmin) {
    return this.httpClient.put(this.url, pharmacyAdmin);
  }
 
}
