import { HttpClient, HttpErrorResponse, HttpParams, HttpResponseBase } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { DrugWithQuantity } from "../models/drugWithQuantity";
import {DrugInfo} from '../models/DrugInfo';
import {ExaminationDerm} from '../models/ExaminationDerm';

@Injectable({
    providedIn: 'root'
})
export class DrugService {

   
 
   


    url = environment.baseUrl + environment.drugQ;

    urlDrug = environment.baseUrl + '/api/drug';

    urlPatient = environment.baseUrl + '/api/patient';

    drugsInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    drugsNotInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    searchDrugsInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    addSuccessEmitter = new Subject<DrugWithQuantity>();
    changeSuccessEmitter = new Subject<DrugWithQuantity>();

    drugsAll: BehaviorSubject<DrugInfo[]> = new BehaviorSubject<DrugInfo[]>([]);

    successAddAlergie = new Subject<DrugInfo>();

    constructor(private http: HttpClient, private router: Router) { }

    public getAllDrugsWithQuantityInPharmacy(): Observable<DrugWithQuantity[]> {
        this.http.get(this.url+ "/all").subscribe(
        ( data: DrugWithQuantity[] ) => {
            this.drugsInPharmacy.next(data);
        },
            (error: HttpResponseBase) => {

            });
    return this.drugsInPharmacy.asObservable();
    }


    getDrugsWithQuantityInPharmacy(pharmId: string){
        let params = new HttpParams();
        params = params.append('pharmId', pharmId );

        return this.http.get(this.url + "/inPharmacy", { params: params })
      }


  public getAllDrugs() {
    console.log("usao je u servis");
    return this.http.get(this.urlDrug + '/getAll');
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

  public addAlergie(drugId: number) {
    console.log(drugId);
    return this.http.put<any>(this.urlPatient + '/addAlergie/' + drugId, {}, {});
  }


}
