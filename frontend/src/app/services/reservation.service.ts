import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {Subject} from 'rxjs';
import {Reservation} from '../models/Reservation';
import {ExaminationDerm} from '../models/ExaminationDerm';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  // url = environment.baseUrl + environment.pharmacy;
  urlPatient = environment.baseUrl + '/api/patient';

  allReservations = new Subject<Reservation>();

  successCancelReservation = new Subject<Reservation>();

  constructor(private http: HttpClient, private router: Router) {}

  public getAllReservationsActive() {
    return this.http.get(this.urlPatient + '/getAllReservations');
  }

  public cancelReservation(reservationId: number) {
    console.log(reservationId);
    let params = new HttpParams().set("reservationId", reservationId.toString());
    return this.http.put<any>(this.urlPatient + '/cancelDrugReservation', {}, {params: params});

  }


}
