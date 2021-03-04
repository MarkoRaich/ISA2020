import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { VacationDermService } from 'src/app/services/vacationDerm.service';
import { VacationPharmService } from 'src/app/services/vacationPharm.service';

@Component({
  selector: 'app-denial-reason',
  templateUrl: './denial-reason.component.html',
  styleUrls: ['./denial-reason.component.css']
})
export class DenialReasonComponent implements OnInit {
  
  worker: any;
  workerType:string;

  reasonForm: FormGroup;

  constructor(public toastr: ToastrService,
              private formBuilder: FormBuilder,
              private vacationPharmService: VacationPharmService,
              private vacationDermService: VacationDermService,
              private dialogRef: MatDialogRef<DenialReasonComponent>,
              @Inject(MAT_DIALOG_DATA) data) {

      this.worker = data.id;
      this.workerType=data.worker;
     }

  ngOnInit() {

      this.reasonForm = this.formBuilder.group({
        reason: new FormControl(null, [Validators.required]),
      });
  }

  deny(){

    if(this.reasonForm.invalid){
      this.toastr.error("Unesite ispravne podatke.", 'Odbijanje zahteva');
      return;
    }
              
    if(this.workerType == "pharmacist"){

      this.vacationPharmService.deny(this.worker["id"], this.reasonForm.value.reason).subscribe(
          () => {
            this.reasonForm.reset();
            this.dialogRef.close();
            this.toastr.success("Uspešno odbijen zahtev", 'Odbijanje zahteva');
            this.vacationPharmService.changeSuccessEmitter.next(this.worker);
          },
          () => {
            this.toastr.error("Došlo je do greške", 'Odbijanje zahteva');
          } 
        )

    } else if(this.workerType == "dermatologist") {

        this.vacationDermService.deny(this.worker["id"], this.reasonForm.value.reason).subscribe(
          () => {
            this.reasonForm.reset();
            this.dialogRef.close();
            this.toastr.success("Uspešno odbijen zahtev", 'Odbijanje zahteva');
            this.vacationDermService.changeSuccessEmitter.next(this.worker);
          },
          () => {
            this.toastr.error("Došlo je do greške", 'Odbijanje zahteva');
          }
        )
    }
  }


}
