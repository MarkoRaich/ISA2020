import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { Pharmacy } from "../models/Pharmacy";

@Injectable({
    providedIn: 'root'
})
export class PharmacyService {
   
 
    url = environment.baseUrl + environment.pharmacy;

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
    

}