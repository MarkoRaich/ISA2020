import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Dermatologist } from 'src/app/models/dermatologist';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { TimeValidator } from 'src/app/validators/time.validator';

@Component({
  selector: 'app-add-dermatologist',
  templateUrl: './add-dermatologist.component.html',
  styleUrls: ['./add-dermatologist.component.css']
})
export class AddDermatologistComponent implements OnInit {

  addDermatologistForm: FormGroup;
  derms: Dermatologist[] = [];

  constructor(private toastr: ToastrService,
              private dermatologistService: DermatologistService,
              private formBuilder: FormBuilder, 
              public dialogRef: MatDialogRef<AddDermatologistComponent> ) { }

  ngOnInit(): void {
    
    this.addDermatologistForm = this.formBuilder.group({
        dermatologist: new FormControl(null, [Validators.required]),
        workHoursFrom: new FormControl(null, [Validators.required]),
        workHoursTo: new FormControl(null, [Validators.required])
    }, {
      validator: TimeValidator('workHoursFrom', 'workHoursTo')
    });
    
    this.getOtherDermatologists();
    
  }

  getOtherDermatologists(): any {
    this.dermatologistService.getOtherDermatologists().subscribe(
      (data : Dermatologist[]) =>{
        this.derms = data;
      });
  }
  
  add(): any {
    
    if(this.addDermatologistForm.invalid){
      this.toastr.error('Unesite ispravne podatke','Dodavanje dermatologa u apoteku');
    }
    
    const derm = new Dermatologist(this.addDermatologistForm.value.dermatologist["email"],
                                   this.addDermatologistForm.value.dermatologist["firstName"],
                                   this.addDermatologistForm.value.dermatologist["lastName"],
                                   this.addDermatologistForm.value.dermatologist["phoneNumber"],
                                   this.addDermatologistForm.value.workHoursFrom,
                                   this.addDermatologistForm.value.workHoursTo,
                                   this.addDermatologistForm.value.dermatologist["id"] );

    this.dermatologistService.addDermatologistToPharmacy(derm).subscribe(
      () => {
        this.toastr.success('Uspešno Ste dodali dermatologa u apoteku','Dodavanje dermatologa u apoteku');
        this.addDermatologistForm.reset();
        this.dialogRef.close();
        this.dermatologistService.createSuccessEmitter.next(derm);
      },
      () => {
        this.toastr.error('Dermatolog radi u drugoj apoteci u odabrano vreme','Dodavanje dermatologa u apoteku');
      }
    );
  }

}
