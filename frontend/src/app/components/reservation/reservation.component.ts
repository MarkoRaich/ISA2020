import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {ReservationService} from '../../services/reservation.service';
import {Reservation} from '../../models/Reservation';
import {MatTableDataSource} from '@angular/material/table';
import {ExaminationDerm} from '../../models/ExaminationDerm';
import {environment} from '../../../environments/environment';
import {Subscription} from 'rxjs';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  examinationsDataSource: MatTableDataSource<Reservation>;

  displayedColumns: string[] = [ 'pharmacy', 'name', 'code',  'quantity', 'status', 'generatedKey', 'otkazi'];
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  cancelReservationSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(private formBuilder: FormBuilder,
              private toastr: ToastrService,
              private reservationService: ReservationService) { }

  reservations: Reservation[] = [];

  public pharmacyName: string;
  public drugName: string;
  public drugCode: string;
  public generatedKey: string;
  public quantity: number;
  public status: string;

  ngOnInit(): void {
    // this.reservations = new Set<Reservation>();

    this.getAllReservationsActive();

    this.cancelReservationSuccess =  this.reservationService.successCancelReservation.subscribe(
      () => {
        this.getAllReservationsActive();
      }
    );
  }

  private getAllReservationsActive(): void {
    this.reservationService.getAllReservationsActive().subscribe(
      (data: Reservation[]) => {
        this.reservations = data;
        this.examinationsDataSource = new MatTableDataSource(data);
        this.examinationsDataSource.sort = this.sort;
        this.examinationsDataSource.paginator = this.paginator;
      }
    ); }

  public cancelReservation(reservationId: number) {
    this.toastr.success('Otkazana je rezervacija leka ' + reservationId , 'Rezervacija');
    this.reservationService.cancelReservation(reservationId).subscribe();
  }


}
