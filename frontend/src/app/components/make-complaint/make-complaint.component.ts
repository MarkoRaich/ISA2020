import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {PatientInfo} from '../../models/PatientInfo';
import {ToastrService} from 'ngx-toastr';
import {PatientService} from '../../services/patient.service';
import {UserService} from '../../services/user.service';
import {Patient} from '../../models/Patient';
import {Complaint} from '../../models/Complaint';
import {ComplaintService} from '../../services/complaint.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-make-complaint',
  templateUrl: './make-complaint.component.html',
  styleUrls: ['./make-complaint.component.css']
})
export class MakeComplaintComponent implements OnInit {

  ComplaintForm : FormGroup;
  loggedPatient : Complaint = new Complaint("","","","");

  constructor(private formBuilder : FormBuilder,
              private toastr: ToastrService,
              private complaintService : ComplaintService,
              public router: Router) { }

  ngOnInit(): void {
    this.ComplaintForm = this.formBuilder.group({
      subject: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      message: new FormControl(null, [Validators.required, Validators.maxLength(250)]),
      doctorName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      examination: new FormControl(null, [Validators.required, Validators.maxLength(30)]),});
  }

  saveChanges() {
    if (this.ComplaintForm.invalid) {
      this.toastr.error("Unesite ispravne podatke.", 'Moj profil');
      return;
    }

    const complaint = new Complaint(
      this.ComplaintForm.value.subject,
      this.ComplaintForm.value.message,
      this.ComplaintForm.value.doctorName,
      this.ComplaintForm.value.examination
    );

    console.log(complaint);

    this.complaintService.put(complaint).subscribe(
      () => {
        this.toastr.success("Uspesno Ste napravili zalbu.", 'Zalba');
        this.router.navigate(['/patient/complaints']);
      },
      () => {
        this.toastr.error("Greska, pokusajte ponovo.", 'Zalba');
      }
    );
  }

}
