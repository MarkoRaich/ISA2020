import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { VacationDerm } from "../models/vacationDerm";

@Injectable({
    providedIn: 'root'
})
export class VacationDermService {
 
   
    url = environment.baseUrl + environment.vacationDerm;
    changeSuccessEmitter = new Subject<VacationDerm>();
    
    constructor(private http: HttpClient, private router: Router) { }

    getAllRequestsInPharmacy() {
        return this.http.get(this.url + "/all");
    }

    approveRequest(id: number) {
        return this.http.get(this.url + '/approve/' + id);
    }
    
    deny(id: string, reason: string[]) {
        return this.http.post(this.url + '/deny/' + id, JSON.stringify({"reason" : reason}), {headers: new HttpHeaders().set('Content-Type', 'application/json')});
       
    }
}