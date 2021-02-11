import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {Subject} from 'rxjs';
import {ExaminationDerm} from '../models/ExaminationDerm';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {Consultation} from '../models/Consultation';

@Injectable({
  providedIn: 'root'
})
export class ConsultationService {


  //url = environment.baseUrl + environment.patient;

  urlPatient = environment.baseUrl + '/api/patient';

  allConsultationsBooked = new Subject<Consultation>();

  constructor(private http: HttpClient, private router: Router) {}

  public getAllConsultationsBooked() {
    console.log("usao je u servis");
    return this.http.get(this.urlPatient + '/getAllConsultationsBooked');
  }

  // public getAllExaminationsAvailable() {
  //   console.log("usao je u servis");
  //   return this.http.get(this.urlPatient + '/getAllExaminationsAvailabe');
  // }


  public cancelConsultation(reservationId: number) {
    console.log(reservationId);
    let params = new HttpParams().set("reservationId", reservationId.toString());
    return this.http.put<any>(this.urlPatient + '/cancelConsultationReservation', {}, {params: params});
  }

  // public bookExamination(id: number) {
  //   console.log(id);
  //   return this.http.put<any>(this.urlPatient + '/makeExaminationReservation/' + id, {}, {});
  // }

}
