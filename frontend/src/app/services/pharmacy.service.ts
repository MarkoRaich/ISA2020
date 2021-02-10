import {HttpClient, HttpParams} from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { Pharmacy } from "../models/Pharmacy";

@Injectable({
    providedIn: 'root'
})
export class PharmacyService {


    url = environment.baseUrl + environment.pharmacy;

    urlPatient = environment.baseUrl + '/api/auth/patient';
    searchAddressPharmacyEmitter = new Subject<Pharmacy>();
    editPharmacyEmitter= new Subject<Pharmacy>();
    addPharmacyAdressEmiter = new Subject<Pharmacy>();
    addSearchAddresPharmacyEmitter = new Subject<Pharmacy>();


    constructor(private http: HttpClient, private router: Router) {}



    public getPharmacyInWhichPharmacyAdminWorks() {
     return this.http.get(this.url + "/pharmacy-of-admin");
    }

    public edit(pharmacy: Pharmacy) {
        return this.http.put(this.url, pharmacy);
      }

    public get(quaery: string) {
        return this.http.get('https://nominatim.openstreetmap.org/search?q=' + quaery + '&format=json');
      }
   public getAllPharmaties() {
     return this.http.get(this.url + '/getAll');
   }

   public searchPharmaties(start: string, end: string) {
     //return this.http.get(this.url + '/getAllPharmaciesWithPharmacistForTime?startTime=' + start + '&endTime=' + end);
     //headers.append('startTime', start);
     //headers.append('endTime', end);
     //const params = new URLSearchParams();
     //params.append('startTime', start);
     //params.append('endTime', end);

     let params = new HttpParams().set("startTime", start).set("endTime", end); //Create new HttpParams

     return this.http.get(this.urlPatient + '/getAllPharmaciesWithPharmacistForTime', {params: params})
   }

   public searchPharmatists(id: number, start: string, end: string) {
     let params = new HttpParams().set("pharmacyId", ""+id).set("startTime", start).set("endTime", end); //Create new HttpParams

     return this.http.get(this.urlPatient + '/getAllPharmacistFreeForPharmacy', {params: params})
   }

   public bookAReservation(pharmacistId: number, pharmacyId: number){
      console.log(pharmacyId + pharmacistId);
     let params = new HttpParams().set("pharmacistId", pharmacistId.toString()).set("pharmacyId", pharmacyId.toString()); //Create new HttpParams
     return this.http.put(this.urlPatient + '/makeConsultationReservation',{}, {params: params})
   }


}
