import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Subject} from 'rxjs';
import {Reservation} from '../models/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  // url = environment.baseUrl + environment.pharmacy;
  urlPatient = environment.baseUrl + '/api/patient';

  allReservations = new Subject<Reservation>();

  constructor(private http: HttpClient, private router: Router) {}

  public getAllReservationsActive() {
    return this.http.get(this.urlPatient + '/getAllReservations');
  }


}
