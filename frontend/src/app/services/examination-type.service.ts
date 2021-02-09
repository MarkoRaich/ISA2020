import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { ExaminationType } from "../models/examinationType";

@Injectable({
    providedIn: 'root'
  })
  export class ExaminationTypeService {

    url = environment.baseUrl + environment.examinationType;

    examinationTypesForAdmin: BehaviorSubject<ExaminationType[]> = new BehaviorSubject<ExaminationType[]>([]);

    constructor(private http :HttpClient, private router : Router){}

    getExaminationTypesForAdmin(): Observable<ExaminationType[]> {
        this.http.get(this.url + "/all").subscribe((data: ExaminationType[]) => {
            this.examinationTypesForAdmin.next(data);
          },
            (error: HttpErrorResponse) => {
      
            });
          return this.examinationTypesForAdmin.asObservable();
  }
}