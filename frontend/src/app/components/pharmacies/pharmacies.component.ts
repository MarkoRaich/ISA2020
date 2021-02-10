import { Component, OnInit } from '@angular/core';
import {Pharmacy} from '../../models/pharmacy';
import {PharmacyService} from '../../services/pharmacy.service';
import {Time} from '@angular/common';

@Component({
  selector: 'app-pharmacies',
  templateUrl: './pharmacies.component.html',
  styleUrls: ['./pharmacies.component.css']
})
export class PharmaciesComponent implements OnInit {

  constructor(private pharmacyService: PharmacyService) { }

  public pharmaties: Object;
  public pharmatists: Object = null;
  public date: string;
  public startTime: string;
  public endTime: string;

  ngOnInit(): void {
    this.pharmaties = new Set<Pharmacy>();

    this.getAllPharmaties();
  }

  private getAllPharmaties(): void {
    this.pharmacyService.getAllPharmaties().subscribe(
      response => {
        this.pharmaties = response;
      }
    )};

  public searchPharmaties(): void {
    console.log(this.date + '..' + this.startTime);
    console.log(this.date + '..' + this.endTime);

    this.pharmacyService.searchPharmaties(this.date + '..' + this.startTime, this.date + '..' + this.endTime).subscribe(
      response => {
        this.pharmaties = response;
      }
    )
  }
  public searchPharmatists(id: number): void {

    }



  public sortByName(): void {

  }

  public sortByRating(): void {

  }

  public prikaziFarmaceute(id: number): void {
    alert('Odabrana je apoteka: ' + id);

    this.pharmacyService.searchPharmatists(id, this.date + '..' + this.startTime, this.date + '..' + this.endTime).subscribe(
      response => {
        this.pharmatists = response;
      }
    )
  }

  bookAReservation(pharmacistId: number, pharmacyId: number): void {
    alert('Zakazano je savetovanje' + pharmacistId + " " + pharmacyId);
    this.pharmacyService.bookAReservation(pharmacistId, pharmacyId).subscribe();
  }


}
