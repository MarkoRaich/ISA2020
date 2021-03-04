import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DrugWithQuantity } from 'src/app/models/drugWithQuantity';
import { DrugService } from 'src/app/services/drug.service';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-change-drug-quantity',
  templateUrl: './change-drug-quantity.component.html',
  styleUrls: ['./change-drug-quantity.component.css']
})
export class ChangeDrugQuantityComponent implements OnInit {

  changeDrugForm: FormGroup;
  drug: DrugWithQuantity;

  constructor(private router: Router,
              private toastr: ToastrService,
              private drugService: DrugService,
              private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<ChangeDrugQuantityComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.changeDrugForm = this.formBuilder.group({
      quantity: new FormControl(null, [Validators.required, Validators.min(1)])
    })
    this.drug = this.data;
  }

  change() {

    if(this.changeDrugForm.invalid){
      this.toastr.error('Unesite ispravne podatke','Menjanje kolicine leka u apoteci');
    }

    const drugWithQ = new DrugWithQuantity( this.data.name,
                                            this.data.code,
                                            this.changeDrugForm.value.quantity,
                                            this.data.id   );

    this.drugService.changeDrugQuantityInPharmacy(drugWithQ).subscribe(
      () => {
        this.toastr.success('Uspešno Ste dodali lek u apoteku','Dodavanje leka u apoteku');
        this.changeDrugForm.reset();
        this.dialogRef.close();
        this.drugService.changeSuccessEmitter.next(drugWithQ);
      }
    )                                  
 
  }

  
}
