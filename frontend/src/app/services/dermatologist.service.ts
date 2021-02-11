import { HttpClient, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { Dermatologist } from "../models/dermatologist";

@Injectable({
    providedIn: 'root'
})
export class DermatologistService {
 
    url = environment.baseUrl + environment.dermatologist;

    dermatologistsForAdmin: BehaviorSubject<Dermatologist[]> = new BehaviorSubject<Dermatologist[]>([]);
    searchDermatologistsForAdmin: BehaviorSubject<Dermatologist[]> = new BehaviorSubject<Dermatologist[]>([]);
    createSuccessEmitter = new Subject<Dermatologist>();
    
    constructor(private http: HttpClient, private router: Router) { }

      getAllDermatologistsForAdmin() {
        this.http.get(this.url + "/all").subscribe(
            (data : Dermatologist[]) => {
                this.dermatologistsForAdmin.next(data);
            },
            (error: Dermatologist) => {}
          );
        return this.dermatologistsForAdmin.asObservable();
      }

      getAllAvailableDermatologists(startDateTime: string, endDateTime: string) {

        let params = new HttpParams();
        params = params.append('startDateTime', startDateTime);
        params = params.append('endDateTime', endDateTime);

        return this.http.get(this.url + "/available", { params: params });
      }

      getOtherDermatologists() {
        this.http.get(this.url + "/other").subscribe(
          (data : Dermatologist[]) => {
              this.dermatologistsForAdmin.next(data);
          },
          (error: Dermatologist) => {}
        );
      return this.dermatologistsForAdmin.asObservable();
      }
     
      addDermatologistToPharmacy(derm: Dermatologist) {
        return this.http.post(this.url, derm);
      }
       
      deleteDermatologist(id: number) {
        return this.http.delete(this.url + '/' + id);
      }

      searchDermatologistsForAdminRequest(firstName: string, lastName: string): Observable<Dermatologist[]> {
        let params = new HttpParams();
        params = params.append('firstName', firstName);
        params = params.append('lastName', lastName);

        this.http.get(this.url + "/search", {
        params: params
        }).subscribe((data: Dermatologist[]) => {
        this.searchDermatologistsForAdmin.next(data);
        },
        (error: HttpErrorResponse) => {

        });
        return this.searchDermatologistsForAdmin.asObservable();
      }
}