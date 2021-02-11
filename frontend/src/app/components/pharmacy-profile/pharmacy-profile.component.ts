import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { Pharmacy } from 'src/app/models/Pharmacy';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { DermatologistInPharmacyComponent } from '../dermatologist-in-pharmacy/dermatologist-in-pharmacy.component';
import { DrugInPharmacyComponent } from '../drug-in-pharmacy/drug-in-pharmacy.component';
import { ExaminationInPharmacyComponent } from '../examination-in-pharmacy/examination-in-pharmacy.component';
import { PharmacistInPharmacyComponent } from '../pharmacist-in-pharmacy/pharmacist-in-pharmacy.component';

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  pharmacy: Pharmacy;
  pharmacyExist: boolean = false;
  searchAdress: Subscription;
  pharmacyForm: FormGroup;


  

  constructor(private toastr: ToastrService,
              private route: ActivatedRoute,
              public dialog: MatDialog,
              private pharmacyService: PharmacyService
              ) { }

  ngOnInit(): void {

    this.pharmacyForm = new FormGroup({
      name: new FormControl({disabled : true}, [Validators.required, Validators.maxLength(50)]),
      address: new FormControl({disabled : true}, [Validators.required]),
      description: new FormControl({disabled : true}, [Validators.required]),
      rating: new FormControl({disabled : true}, [Validators.required])
    });

    this.route.params.subscribe(
          (params) => {
            let pharmacyId = params['id'];
            this.pharmacyService.getPharmacyById(pharmacyId).subscribe(
              (data: Pharmacy) => {
                if(!data){
                  this.pharmacyExist = false;
                  this.toastr.error("Apoteka sa ID : " + pharmacyId + " ne postoji u sistemu.", "Apoteka nije pronadjena");
                } else {
                  this.pharmacyExist=true;
                }
                this.pharmacy = data;
                this.pharmacyForm.patchValue(
                  {
                    'name': this.pharmacy.name,
                    'address': this.pharmacy.address,
                    'description': this.pharmacy.description,
                    'rating' : this.pharmacy.rating
                  }
                );
              }
            );
          }
    );

    this.searchAdress = this.pharmacyService.searchAddressPharmacyEmitter.subscribe(
      (pharmacy: Pharmacy) => {
        this.pharmacy.address = pharmacy.address;
        this.pharmacyForm.patchValue(
          {
            'address': this.pharmacy.address
          }
        );        
      }
    );
  }

  addressFocusOut() {
    const pharmacy = new Pharmacy(this.pharmacyForm.value.name, this.pharmacyForm.value.address,
      this.pharmacyForm.value.description, this.pharmacy.id);

    this.pharmacyService.editPharmacyEmitter.next(pharmacy);
  }



  openPharmacistDialog(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: this.pharmacy.id
    };
      this.dialog.open( PharmacistInPharmacyComponent, dialogConfig);
  }

  openDermatologistDialog(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: this.pharmacy.id
    };
    this.dialog.open(DermatologistInPharmacyComponent, dialogConfig);
}

  openDrugDialog(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: this.pharmacy.id
    };
    this.dialog.open(DrugInPharmacyComponent, dialogConfig);

}

  openExaminationDialog(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: this.pharmacy.id
    };
    this.dialog.open(ExaminationInPharmacyComponent, dialogConfig);
}
}
