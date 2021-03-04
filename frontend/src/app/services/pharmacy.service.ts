import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import { environment } from "src/environments/environment";
import { Pharmacy } from "../models/Pharmacy";
import {Dermatologist} from '../models/dermatologist';

@Injectable({
    providedIn: 'root'
})
export class PharmacyService {
 
  
 
    url = environment.baseUrl + environment.pharmacy;

    urlPatient = environment.baseUrl + '/api/patient';
    searchAddressPharmacyEmitter = new Subject<Pharmacy>();
    editPharmacyEmitter= new Subject<Pharmacy>();
    addPharmacyAdressEmiter = new Subject<Pharmacy>();
    addSearchAddresPharmacyEmitter = new Subject<Pharmacy>();

  searchPharmacyName: BehaviorSubject<Pharmacy[]> = new BehaviorSubject<Pharmacy[]>([]);
  searchPharmacyAddress: BehaviorSubject<Pharmacy[]> = new BehaviorSubject<Pharmacy[]>([]);


  constructor(private http: HttpClient, private router: Router) {}

    getPharmacyRating() {
      return this.http.get(this.url + '/pharmacy-rating');
    }

    public getPharmacyInWhichPharmacyAdminWorks() {
     return this.http.get(this.url + "/pharmacy-of-admin");
    }

    getPharmacyById(pharmacyId: any) {
     return this.http.get("http://localhost:8080/api/pharmacy/"  + pharmacyId);
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




   public searchPharmacyByName(pharmacyName: string) {
     let params = new HttpParams().set("pharmacyName", pharmacyName); //Create new HttpParams

     return this.http.get(this.url + "/noAuth/getAllPharmaciesSortedByNameForName", {
       params: params});
   }


   public searchPharmacyByAddress(pharmacyAddress: string) {
     let params = new HttpParams().set("pharmacyAddress", pharmacyAddress); //Create new HttpParams

     return this.http.get(this.url + "/noAuth/getAllPharmaciesSortedByAddressForAddress", {
       params: params});
   }

   getMothlyStatistic() {
    return this.http.get(this.url + '/monthly-statistic'); 
  }
 
   getQuartalStatistic() {
     return this.http.get(this.url + '/quartal-statistic');
   }

   getYearStatistic() {
    return this.http.get(this.url + '/year-statistic');
   }

   getMothlyStatisticDrugs() {
    return this.http.get(this.url + '/monthly-statistic-drugs'); 
  }
 
  getQuartalStatisticDrugs() {
    return this.http.get(this.url + '/quartal-statistic-drugs');
  }

  getYearStatisticDrugs() {
   return this.http.get(this.url + '/year-statistic-drugs');
  }


  getPharmacyIncomeForPeriod(startDate: any, endDate: any) {
    let params = new HttpParams();
    params = params.append('startDate', startDate);
    params = params.append('endDate', endDate);

    return this.http.get(this.url + '/income', {
      params: params
    });
  }

}
