import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DateTime } from 'luxon';
import { ToastrService } from 'ngx-toastr';
import { Dermatologist } from 'src/app/models/dermatologist';
import { IncomeList } from 'src/app/models/incomeList';
import { Pharmacist } from 'src/app/models/pharmacist';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { isUndefined } from 'util';

@Component({
  selector: 'app-financial-report',
  templateUrl: './financial-report.component.html',
  styleUrls: ['./financial-report.component.css']
})
export class FinancialReportComponent implements OnInit {

  pharmacyRating: Number = 0.0;
  
  dermatologistRating: Number = 0.0;
  dermatologists: Dermatologist[] = [];
  selectedDermatologist: Dermatologist;

  pharmacistRating: Number = 0.0;
  pharmacists: Pharmacist[] = [];
  selectedPharmacist: Pharmacist;
  

  constructor(private toastr: ToastrService,
              private dermatologistService: DermatologistService,
              private pharmacistService: PharmacistService,
              private pharmacyService: PharmacyService  ) { }

  ngOnInit(): void {

    this.getAllDermatologistsInPharmacy();
    this.getAllPharmacistsInPharmacy();
    this.getPharmacyRating();

  }
  
  getAllDermatologistsInPharmacy(){
    this.dermatologistService.getAllDermatologistsForAdmin().subscribe(
      (data: Dermatologist[]) => {
        this.dermatologists = data;
        this.selectedDermatologist = this.dermatologists[0];
        this.changeSelectionDerm();
      }
    )
  }

  changeSelectionDerm() {
    if (isUndefined(this.selectedDermatologist) || isUndefined(this.selectedDermatologist.rating) || this.selectedDermatologist.rating < 0 || this.selectedDermatologist.rating > 5) {
      this.dermatologistRating = 0.0;
    } else {
      this.dermatologistRating = this.selectedDermatologist.rating;
    }
  }
  
  getPharmacyRating() {
    this.pharmacyService.getPharmacyRating().subscribe(
      (data: Number) => {
        if(data < 0.0 || data > 5.0){
          this.pharmacyRating = 0.0;
        } else {
          this.pharmacyRating = data;
        }
      },
      () => {
        this.toastr.error('Ocena apoteke je nedefinisana.', 'Izveštaj o poslovanju');
      }
    )
  }
  
  getAllPharmacistsInPharmacy() {
    this.pharmacistService.getAllPharmacistsForAdmin().subscribe(
      (data: Pharmacist[]) => {
        this.pharmacists = data;
        this.selectedPharmacist = this.pharmacists[0];
        this.changeSelectionPharm();
      }
    )
  }
  changeSelectionPharm() {
    if (isUndefined(this.selectedPharmacist) || isUndefined(this.selectedPharmacist.rating) || this.selectedPharmacist.rating < 0 || this.selectedPharmacist.rating > 5) {
      this.pharmacistRating = 0.0;
    } else {
      this.pharmacistRating = this.selectedPharmacist.rating;
    }
  }



  

}
