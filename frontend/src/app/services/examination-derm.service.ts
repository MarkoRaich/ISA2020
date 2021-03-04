import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {AvailableExamination} from '../models/availableExamination';
import {HttpClient, HttpParams, HttpResponseBase} from '@angular/common/http';
import {Router} from '@angular/router';
import {ExaminationDerm} from '../models/ExaminationDerm';
import {Reservation} from '../models/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ExaminationDermService {


  //url = environment.baseUrl + environment.patient;

  urlPatient = environment.baseUrl + '/api/patient';

  allExaminationsBooked = new Subject<ExaminationDerm>();

  successCancelExamination = new Subject<ExaminationDerm>();

  successBookExamination = new Subject<ExaminationDerm>();

  constructor(private http: HttpClient, private router: Router) {}

  public getAllExaminationsBooked() {
    return this.http.get(this.urlPatient + '/getAllExaminationsSortedByPriceBooked');
  }

  public getAllExaminationsAvailable() {
    return this.http.get(this.urlPatient + '/getAllExaminationsAvailabe');
  }

  public getAllExaminationsDone() {
    return this.http.get(this.urlPatient + '/getAllExaminationsSortedByPriceDone');
  }


  public cancelExamination(id: number) {
    console.log(id);
    return this.http.put<any>(this.urlPatient + '/cancelExaminationReservation/' + id, {}, {});
  }

  public bookExamination(id: number) {
    console.log(id);
    return this.http.put<any>(this.urlPatient + '/makeExaminationReservation/' + id, {}, {});
  }

  public setGrade(examinationId: number, grade: number) {
    let params = new HttpParams().set("examinationId", examinationId.toString()).set("grade", grade.toString());

    return this.http.put<any>(this.urlPatient + '/setDermatologistGrade/', {}, {params: params});
  }

}
