import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {DrugWithQuantity} from '../../models/drugWithQuantity';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {DrugService} from '../../services/drug.service';
import {MatDialogRef} from '@angular/material/dialog';
import {DrugReservation} from '../../models/DrugReservation';
import {element} from 'protractor';

@Component({
  selector: 'app-drug-reservation',
  templateUrl: './drug-reservation.component.html',
  styleUrls: ['./drug-reservation.component.css']
})
export class DrugReservationComponent implements OnInit {

  addQuantityEndTime: FormGroup;

  drugs: DrugReservation[] = [];

  constructor( private router: Router,
               private toastr: ToastrService,
               private drugService: DrugService,
               private formBuilder: FormBuilder
  ) { }

  //public drugs: Object;
  public date: string;
  public endTime: string;
  public quantity: number;

  ngOnInit(): void {

    this.addQuantityEndTime = this.formBuilder.group({
      quantity: new FormControl(null, [Validators.required]),
      endTime: new FormControl(null, [Validators.required])
    });
  }


  add(){

    if(this.addQuantityEndTime.invalid){
      this.toastr.error('Unesite ispravne podatke','Dodavanje rezervaije leka');
    }

    // const drugWithQ = new DrugReservation( this.addQuantityEndTime.value.endTime,
    //   // this.addQuantityEndTime.value.drug["code"], ///??????
    //   this.addQuantityEndTime.value.quantity,

    // this.drugService.makeReservationWithQuantity(element.drugId, element.pharmacyId, drugWithQ.quantity, drugWithQ.endTime).subscribe(
    //   () => {
    //     this.toastr.success('Uspešno Ste napravili rezervaciju leka','Rezervacija leka');
    //     this.addQuantityEndTime.reset();
    //     this.dialogRef.close();
    //     this.drugService.addSuccessEmitter.next(drugWithQ);
    //   }
    // );

  }

}
