import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {DrugWithQuantity} from '../models/drugWithQuantity';
import {DrugInfo} from '../models/DrugInfo';
import {HttpClient, HttpErrorResponse, HttpParams, HttpResponseBase} from '@angular/common/http';
import {Router} from '@angular/router';
import {Patient} from '../models/Patient';
import {Complaint} from '../models/Complaint';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  url = environment.baseUrl + '/api/complaint';

  drugsAll: BehaviorSubject<DrugInfo[]> = new BehaviorSubject<DrugInfo[]>([]);


  constructor(private httpClient: HttpClient, private router: Router) {
  }

  public getAllComplaints() {
    console.log("usao je u servis");
    return this.httpClient.get(this.url + '/getAll');
  }


  public put(complaint: Complaint): Observable<any> {
    console.log(complaint);
    return this.httpClient.post<any>(this.url + '/create', complaint);
  }


  // public addAlergie(drugId: number) {
  //   console.log(drugId);
  //   return this.http.put<any>(this.urlPatient + '/addAlergie/' + drugId, {}, {});
  // }
}
