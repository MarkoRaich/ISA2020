import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DrugInfo } from 'src/app/models/DrugInfo';
import { DrugWithPrice } from 'src/app/models/drugWithPrice';
import { DrugWithQuantity } from 'src/app/models/drugWithQuantity';
import { DrugService } from 'src/app/services/drug.service';

@Component({
  selector: 'app-change-drug-price',
  templateUrl: './change-drug-price.component.html',
  styleUrls: ['./change-drug-price.component.css']
})
export class ChangeDrugPriceComponent implements OnInit {

  changeDrugForm: FormGroup;
  
  drug: DrugWithPrice;

  constructor(private router: Router,
              private toastr: ToastrService,
              private drugService: DrugService,
              private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<ChangeDrugPriceComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.changeDrugForm = this.formBuilder.group({
      price: new FormControl(null, [Validators.required, Validators.min(1)])
    })
    this.drug = this.data.drug
  }

  change() {

    if(this.changeDrugForm.invalid){
      this.toastr.error('Unesite ispravne podatke','Menjanje cene leka u apoteci');
    }

    this.drug.price = this.changeDrugForm.value.price;

    this.drugService.changeDrugPriceInPharmacy(this.drug).subscribe(
      () => {
        this.toastr.success('Uspešno Ste izmenili cenu leka u apoteku', 'Menjanje cene leka u apoteci');
        this.changeDrugForm.reset();
        this.dialogRef.close();
        this.drugService.changeSuccessEmitter.next();
      },
      () => {
        this.toastr.error('Došlo je do greške', 'Menjanje cene leka u apoteci');
      }
    )                                  
 
  }

  
}
