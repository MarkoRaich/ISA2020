import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { min } from 'rxjs/operators';
import { Promotion } from 'src/app/models/promotion';
import { PromotionService } from 'src/app/services/promotion.service';
import { TimeValidator } from 'src/app/validators/time.validator';

@Component({
  selector: 'app-create-promotion',
  templateUrl: './create-promotion.component.html',
  styleUrls: ['./create-promotion.component.css']
})
export class CreatePromotionComponent implements OnInit {

  createpromotionForm: FormGroup;
  minDate = new Date();
  timeError = false;

  constructor(private toastr : ToastrService, 
              private formBuilder : FormBuilder,
              private promotionService: PromotionService
              ) { }

  ngOnInit(): void {

    this.minDate.setDate(this.minDate.getDate() + 1);

    this.createpromotionForm = this.formBuilder.group({
      dateFrom: new FormControl(null, [Validators.required]),
      dateTo: new FormControl(null, [Validators.required]),
      content: new  FormControl(null, [Validators.required])
    },{
      validator: TimeValidator('dateFrom', 'dateTo')
    }
    )

  }


  createPromotion(): void {

    if(this.createpromotionForm.invalid){
      this.toastr.error('Unesite ispravne podatke.','Kreiranje promocije');
      return;
    }

    const dateFrom = formatDate(this.createpromotionForm.value.dateFrom, 'yyyy-MM-dd', 'en-US');
    const dateTo = formatDate(this.createpromotionForm.value.dateTo, 'yyyy-MM-dd', 'en-US');

    const startDateTime = dateFrom + ' ' + "00:01";
    const endDateTime = dateTo + ' ' + "00:01";

    const promotion = new Promotion(this.createpromotionForm.value.content, startDateTime, endDateTime);

    this.promotionService.createPromotion(promotion).subscribe(
      (data) => {
        this.createpromotionForm.reset();
        this.toastr.success('Uspešno Ste kreirali promociju.', 'Kreiranje promocije');
      }
    )
  }
}
