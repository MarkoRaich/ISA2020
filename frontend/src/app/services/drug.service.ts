import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams, HttpResponseBase } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { DrugWithQuantity } from "../models/drugWithQuantity";
import {DrugInfo} from '../models/DrugInfo';
import {ExaminationDerm} from '../models/ExaminationDerm';
import { DrugWithPrice } from "../models/drugWithPrice";

@Injectable({
    providedIn: 'root'
})
export class DrugService {

    url = environment.baseUrl + environment.drugQ;

    urlDrug = environment.baseUrl + '/api/drug';

    urlPatient = environment.baseUrl + '/api/patient';

    urlDrugPrice = environment.baseUrl + '/api/drugPrice';

    drugsInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    drugsNotInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    searchDrugsInPharmacy: BehaviorSubject<DrugWithQuantity[]> = new BehaviorSubject<DrugWithQuantity[]>([]);
    addSuccessEmitter = new Subject<DrugWithQuantity>();
    changeSuccessEmitter = new Subject<DrugWithQuantity>();
    
    succesCreatedPricelist = new Subject<DrugWithPrice>();
    
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
   
    return this.http.get(this.urlDrug + '/getAll');
  }

  public getAllDrugQuantities() {
    
    return this.http.get(this.urlPatient + '/getAllDrugQuantities');
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

    getAllDrugPricesForPharmacy() {
     return this.http.get(this.urlDrugPrice + "/getAll");
    }

    addDrugWithQuantityToPharmacy(drugWithQ: DrugWithQuantity) {
        return this.http.post(this.url, drugWithQ);
      }

    changeDrugQuantityInPharmacy(drugWithQ: DrugWithQuantity) {
        return this.http.put(this.url, drugWithQ);
      }

    changeDrugPriceInPharmacy(drugWithPrice: DrugWithPrice) {
      return this.http.put(this.urlDrugPrice, drugWithPrice);
    }

  public addAlergie(drugId: number) {
    console.log(drugId);
    return this.http.put<any>(this.urlPatient + '/addAlergie/' + drugId, {}, {});
  }

  public makeReservationWithQuantity(drugId: number, pharmacyId: number, quantity: number, endTime: string) {
    console.log(drugId);
    let params = new HttpParams().set("drugId", drugId.toString()).set("pharmacyId", pharmacyId.toString()).set("quantity", quantity.toString()).set("endTime", endTime); //Create new HttpParams

    return this.http.put<any>(this.urlPatient + '/makeDrugReservation/', {}, {params: params});
  }

  public setGrade(drugId: number, grade: number) {
    let params = new HttpParams().set("drugId", drugId.toString()).set("grade", grade.toString());

    return this.http.put<any>(this.urlPatient + '/setDrugGrade/', {}, {params: params});
  }

  createPriceList(drugsWithPrice: DrugWithPrice[]) {
    return this.http.post(this.urlDrugPrice + '/create-pricelist', JSON.stringify({"pricelist" : drugsWithPrice}), {headers: new HttpHeaders().set('Content-Type', 'application/json')});   
}

}
