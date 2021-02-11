import { HttpClient, HttpErrorResponse, HttpParams, HttpResponseBase } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { DrugWithQuantity } from "../models/drugWithQuantity";

@Injectable({
    providedIn: 'root'
})
export class DrugService {
 
   
    url = environment.baseUrl + environment.drugQ;
    
    drugsInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    drugsNotInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    searchDrugsInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    addSuccessEmitter = new Subject<DrugWithQuantity>();
    changeSuccessEmitter = new Subject<DrugWithQuantity>();


    constructor(private http: HttpClient, private router: Router) { }

    public getAllDrugsWithQuantityInPharmacy(): Observable<DrugWithQuantity[]> {
        this.http.get(this.url + "/all").subscribe(
        ( data: DrugWithQuantity[] ) => {
            this.drugsInPharmacy.next(data);
        },
            (error: HttpResponseBase) => {

            });
    return this.drugsInPharmacy.asObservable();
    }

    getDrugsNotInPharmacy() {
       this.http.get(this.url + "/other").subscribe(
           (data : DrugWithQuantity[]) => {
            this.drugsNotInPharmacy.next(data);
        },
            (error: HttpResponseBase) => {

            });
    return this.drugsNotInPharmacy.asObservable();
    }

    public deleteDrugFromPharmacy(id: number): any {
        return this.http.delete(this.url + '/' + id);
    }

    searchDrugsFromPharmacy(searchName: string): Observable<DrugWithQuantity[]> {
        let params = new HttpParams();
        params = params.append('name', searchName);

        this.http.get(this.url + "/search", {params: params} ).subscribe(
            (data: DrugWithQuantity[]) => {
                this.searchDrugsInPharmacy.next(data);
            },
            (error: HttpErrorResponse) => {

            });
        return this.searchDrugsInPharmacy.asObservable();
    }



    addDrugWithQuantityToPharmacy(drugWithQ: DrugWithQuantity) {
        return this.http.post(this.url, drugWithQ);
      }
     
    changeDrugQuantityInPharmacy(drugWithQ: DrugWithQuantity) {
        return this.http.put(this.url, drugWithQ);
      }

   
}