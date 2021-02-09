import { HttpClient, HttpResponseBase } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { AvailableExamination } from "../models/availableExamination";
import { Examination } from "../models/examination";

@Injectable({
    providedIn: 'root'
})
export class ExaminationService {
 

    url = environment.baseUrl + environment.examination;

    examinationsForAdmin: BehaviorSubject<AvailableExamination[]> = new BehaviorSubject<AvailableExamination[]>([]);
    
    successCreatedExamination = new Subject<AvailableExamination>();
    
    constructor(private http: HttpClient, private router: Router) { }

  getAllAvailableExaminationsInPharmacy() : Observable<AvailableExamination[]>{
        this.http.get(this.url + "/forAdmin").subscribe(
        (data : AvailableExamination[]) => {
            this.examinationsForAdmin.next(data);
        },
        (error : HttpResponseBase) => {} 
        );
    return this.examinationsForAdmin.asObservable();
  }


  createAvailableExamination(availableExamination: AvailableExamination): any {
    return this.http.post(this.url + "/create-examination", availableExamination);
  }


}