import { Component, OnInit } from '@angular/core';
import {Pharmacy} from '../../models/pharmacy';
import {PharmacyService} from '../../services/pharmacy.service';
import {Time} from '@angular/common';
import {FormBuilder} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pharmacies',
  templateUrl: './pharmacies.component.html',
  styleUrls: ['./pharmacies.component.css']
})
export class PharmaciesComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private toastr: ToastrService,
              private pharmacyService: PharmacyService,
              private router: Router) { }

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

  showPharmacyProfile(pharmId: number){
      this.router.navigate(['patient/pharmacy-profile', pharmId ]);
  }

  public prikaziFarmaceute(id: number): void {
    this.toastr.success('Odabrana je apoteka: ' + id, 'Apoteka');

    this.pharmacyService.searchPharmatists(id, this.date + '..' + this.startTime, this.date + '..' + this.endTime).subscribe(
      response => {
        this.pharmatists = response;
      }
    )
  }

  bookAReservation(pharmacistId: number, pharmacyId: number): void {
    this.toastr.success('Zakazano je savetovanje' + pharmacistId + " " + pharmacyId, 'Savetovanje');
    this.pharmacyService.bookAReservation(pharmacistId, pharmacyId).subscribe();
  }

  public prikaziInformacije(id: number): void {

  }


}
