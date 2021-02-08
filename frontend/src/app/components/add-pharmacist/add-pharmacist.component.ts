import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Pharmacist } from 'src/app/models/pharmacist';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { TimeValidator } from 'src/app/validators/time.validator';

@Component({
  selector: 'app-add-pharmacist',
  templateUrl: './add-pharmacist.component.html',
  styleUrls: ['./add-pharmacist.component.css']
})
export class AddPharmacistComponent implements OnInit {

  addPharmacistForm: FormGroup;

  constructor(private toastr: ToastrService,
              private pharmacistService: PharmacistService,
              private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<AddPharmacistComponent>          
              ) { }

  ngOnInit() {

      this.addPharmacistForm = this.formBuilder.group({
        email: new FormControl(null, [Validators.required, Validators.email]),
        firstName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
        lastName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
        phoneNumber: new FormControl(null, [Validators.required, Validators.minLength(9), Validators.maxLength(10), Validators.pattern("[0-9]+")]),
        workHoursFrom: new FormControl(null, [Validators.required]),
        workHoursTo: new FormControl(null, [Validators.required]),
      }, {
        validator: TimeValidator('workHoursFrom', 'workHoursTo')
      });

  }

  add() {

    if(this.addPharmacistForm.invalid){
      this.toastr.error("Unesite ispravne podatke.", 'Kreiraj farmaceuta');
      return;
    }

    const pharmacist = new Pharmacist(this.addPharmacistForm.value.email, 
                                      this.addPharmacistForm.value.firstName,
                                      this.addPharmacistForm.value.lastName, 
                                      this.addPharmacistForm.value.phoneNumber,
                                      this.addPharmacistForm.value.workHoursFrom,
                                      this.addPharmacistForm.value.workHoursTo  
                                      );
                            
    this.pharmacistService.create(pharmacist).subscribe(
      () => {
        this.addPharmacistForm.reset();
        this.dialogRef.close();
        this.toastr.success("Kreiran farmaceut. Inicijalna lozinka je : lozinka", "Kreiraj farmaceuta");
        this.pharmacistService.createSuccessEmitter.next(pharmacist);
      },
      () => {
        this.toastr.error("Farmaceut sa ovim emailom već postoji", 'Kreiraj farmaceuta');
      }
    )
  }

}
