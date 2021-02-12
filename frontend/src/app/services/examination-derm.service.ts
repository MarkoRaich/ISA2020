import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {AvailableExamination} from '../models/availableExamination';
import {HttpClient, HttpResponseBase} from '@angular/common/http';
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
    console.log("usao je u servis");
    return this.http.get(this.urlPatient + '/getAllExaminationsSortedByPriceBooked');
  }

  public getAllExaminationsAvailable() {
    console.log("usao je u servis");
    return this.http.get(this.urlPatient + '/getAllExaminationsAvailabe');
  }


  public cancelExamination(id: number) {
    console.log(id);
    return this.http.put<any>(this.urlPatient + '/cancelExaminationReservation/' + id, {}, {});
  }

  public bookExamination(id: number) {
    console.log(id);
    return this.http.put<any>(this.urlPatient + '/makeExaminationReservation/' + id, {}, {});
  }

}
