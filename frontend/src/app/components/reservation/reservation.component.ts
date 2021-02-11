import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {ReservationService} from '../../services/reservation.service';
import {Reservation} from '../../models/Reservation';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

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
  }

  private getAllReservationsActive(): void {
    this.reservationService.getAllReservationsActive().subscribe(
      (data: Reservation[]) => {
        this.reservations = data;
      }
    ); }

}
