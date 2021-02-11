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

  public deletePharmacist(id: number) {
    return this.http.delete(this.url + '/' + id);
  }
}