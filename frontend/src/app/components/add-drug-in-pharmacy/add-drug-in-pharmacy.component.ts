import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DrugWithQuantity } from 'src/app/models/drugWithQuantity';
import { DrugService } from 'src/app/services/drug.service';

@Component({
  selector: 'app-add-drug-in-pharmacy',
  templateUrl: './add-drug-in-pharmacy.component.html',
  styleUrls: ['./add-drug-in-pharmacy.component.css']
})
export class AddDrugInPharmacyComponent implements OnInit {

  
  addDrugForm: FormGroup;

  drugs: DrugWithQuantity[] = [];

  constructor( private router: Router,
               private toastr: ToastrService,
               private drugService: DrugService,
               private formBuilder: FormBuilder,
               public dialogRef: MatDialogRef<AddDrugInPharmacyComponent>
               ) { }

  ngOnInit(): void {

    this.addDrugForm = this.formBuilder.group({
        drug: new FormControl(null, [Validators.required]),
        quantity: new FormControl(null, [Validators.required, Validators.min(1)])
    });
    
    this.getDrugs();
    
  }


  getDrugs() {
    this.drugService.getDrugsNotInPharmacy().subscribe(
      (data : DrugWithQuantity[]) => {
        this.drugs = data;
      });
  }


  add(){

    if(this.addDrugForm.invalid){
      this.toastr.error('Unesite ispravne podatke','Dodavanje leka u apoteku');
    }

    const drugWithQ = new DrugWithQuantity( this.addDrugForm.value.drug["name"],
                                            this.addDrugForm.value.drug["code"],
                                            this.addDrugForm.value.quantity,
                                            this.addDrugForm.value.drug["id"]   );

    this.drugService.addDrugWithQuantityToPharmacy(drugWithQ).subscribe(
      () => {
        this.toastr.success('Uspešno Ste dodali lek u apoteku','Dodavanje leka u apoteku');
        this.addDrugForm.reset();
        this.dialogRef.close();
        this.drugService.addSuccessEmitter.next(drugWithQ);
      }
    )                                  
 
  }

  
}
