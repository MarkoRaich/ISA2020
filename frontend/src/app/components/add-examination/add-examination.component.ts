import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Dermatologist } from 'src/app/models/dermatologist';
import {ExaminationType} from 'src/app/models/examinationType';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { ExaminationTypeService } from 'src/app/services/examination-type.service';
import { ExaminationService } from 'src/app/services/examination.service';
import { TimeValidator } from 'src/app/validators/time.validator';
import { AvailableExamination } from 'src/app/models/availableExamination';

@Component({
  selector: 'app-add-examination',
  templateUrl: './add-examination.component.html',
  styleUrls: ['./add-examination.component.css']
})
export class AddExaminationComponent implements OnInit {

  addAvailableExaminationForm : FormGroup;
  dateTimeTypeForm: FormGroup;
  examinationTypes: ExaminationType[] = [];
  dermatologists : Dermatologist[] = [];
  minDate = new Date();
  timeError = false;

  constructor(private toastr : ToastrService, 
              private dermatologistService : DermatologistService,
              private examinationTypeService : ExaminationTypeService,
              private examinationService : ExaminationService,
              private formBuilder : FormBuilder,
              public dialogRef : MatDialogRef<AddExaminationComponent> ) { }

  ngOnInit(): void {

    this.minDate.setDate(this.minDate.getDate() + 1);

    this.dateTimeTypeForm = this.formBuilder.group({
      date: new FormControl(null, [Validators.required]),
      timeFrom: new FormControl(null, [Validators.required]),
      timeTo: new FormControl(null, [Validators.required]),
      examinationType: new FormControl(null, [Validators.required]),
    }, {
      validator: TimeValidator('timeFrom', 'timeTo')
    });

    this.addAvailableExaminationForm = new FormGroup({
      dermatologist : new FormControl(null, [Validators.required])
    });

    this.getExaminationTypes();
    
  }

  getExaminationTypes() {
    this.examinationTypeService.getExaminationTypesForAdmin().subscribe(
      (types) => {
      this.examinationTypes = types;
    })
  }

  next(): void {
    if (!(this.dateTimeTypeForm.value.timeFrom || this.dateTimeTypeForm.value.timeTo)) {
      this.timeError = true;
    }

    if (this.dateTimeTypeForm.value.timeFrom >= this.dateTimeTypeForm.value.timeTo) {
      this.timeError = true;
    }

    this.getDermatologists();
  }

  getDermatologists(): void {

    const examinationType = this.dateTimeTypeForm.value.examinationType;
    if (this.dateTimeTypeForm.value.date && this.dateTimeTypeForm.value.timeFrom && this.dateTimeTypeForm.value.timeTo) {
      const date = formatDate(this.dateTimeTypeForm.value.date, 'yyyy-MM-dd', 'en-US')
      const startDateTime = date + ' ' + this.dateTimeTypeForm.value.timeFrom;
      const endDateTime = date + ' ' + this.dateTimeTypeForm.value.timeTo;

      this.dermatologistService.getAllAvailableDermatologists(startDateTime, endDateTime).subscribe(
        (derms: Dermatologist[]) => {
          this.dermatologists = derms;
      })
    } else {
      this.dermatologists = [];
    }

  }


  create(): void {
    if (this.addAvailableExaminationForm.invalid || this.dateTimeTypeForm.invalid) {
      this.toastr.error('Unesite ispravne podatke.', 'Kreiranje slobodnog termina');
      return;
    }
    const date = formatDate(this.dateTimeTypeForm.value.date, 'yyyy-MM-dd', 'en-US')
    const startDateTime = date + ' ' + this.dateTimeTypeForm.value.timeFrom;
    const endDateTime = date + ' ' + this.dateTimeTypeForm.value.timeTo;

    const availableExamination = new AvailableExamination( startDateTime,
                                                            endDateTime, 
                                                            this.dateTimeTypeForm.value.examinationType,
                                                            this.addAvailableExaminationForm.value.dermatologist);

    this.examinationService.createAvailableExamination(availableExamination).subscribe(
      (responseData) => {
        this.addAvailableExaminationForm.reset();
        this.dateTimeTypeForm.reset();
        this.dialogRef.close();
        this.toastr.success('Uspešno Ste kreirali slobodan termin.', 'Kreiranje slobodnog termina');
        this.examinationService.successCreatedExamination.next(availableExamination);
      },
      () => {
        this.toastr.error('greška pri kreiranju slobodnog termina.', 'Kreiranje slobodnog termina');
      }
    );
  }

}
