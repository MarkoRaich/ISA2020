import { HttpClient, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { Pharmacist } from "../models/pharmacist";

@Injectable({
    providedIn: 'root'
})
export class PharmacistService {
 
 
  url = environment.baseUrl + environment.pharmacist;

  pharmacistsForAdmin: BehaviorSubject<Pharmacist[]> = new BehaviorSubject<Pharmacist[]>([]);
  searchPharmacistsForAdmin: BehaviorSubject<Pharmacist[]> = new BehaviorSubject<Pharmacist[]>([]);
  searchPharmacistsInPharm: BehaviorSubject<Pharmacist[]> = new BehaviorSubject<Pharmacist[]>([]);
  createSuccessEmitter = new Subject<Pharmacist>();
  
  constructor(private http: HttpClient, private router: Router) { }

  public create(pharmacist: Pharmacist): any {
      return this.http.post(this.url, pharmacist);
  }

  public getAllPharmacistsForAdmin() {
   this.http.get(this.url + "/all").subscribe(
        (data : Pharmacist[]) => {
            this.pharmacistsForAdmin.next(data);
        },
        (error: HttpErrorResponse) => {}
      );
    return this.pharmacistsForAdmin.asObservable();
  }

  getPharmacistsInPharmacy(pharmId: string) {
    let params = new HttpParams();
    params = params.append('pharmId', pharmId );

    return this.http.get(this.url + "/inPharmacy", { params: params })
  }
    
  public searchPharmacistsForAdminRequest(firstName: string, lastName: string): Observable<Pharmacist[]> {
    let params = new HttpParams();
    params = params.append('firstName', firstName);
    params = params.append('lastName', lastName);

    this.http.get(this.url + "/search", {
      params: params
    }).subscribe((data: Pharmacist[]) => {
      this.searchPharmacistsForAdmin.next(data);
    },
      (error: HttpErrorResponse) => {

      });
    return this.searchPharmacistsForAdmin.asObservable();
  }

  searchPharmacistsInPharmacy(firstName: string, lastName: string, pharmId: any): Observable<Pharmacist[]> {
    let params = new HttpParams();
    params = params.append('firstName', firstName);
    params = params.append('lastName', lastName);
    params = params.append('pharmId', pharmId);

    this.http.get(this.url + "/searchInPharmacy", {
      params: params
    }).subscribe((data: Pharmacist[]) => {
      this.searchPharmacistsInPharm.next(data);
    },
      (error: HttpErrorResponse) => {

      });
    return this.searchPharmacistsInPharm.asObservable();
  }
  

  public deletePharmacist(id: number) {
    return this.http.delete(this.url + '/' + id);
  }
}