import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {PharmacyAdminService} from '../../services/pharmacy-admin.service';
import {UserService} from '../../services/user.service';
import {error} from '@angular/compiler/src/util';
import {Patient} from 'src/app/models/Patient';
import {PatientService} from '../../services/patient.service';
import {PharmacyAdmin} from '../../models/PharmacyAdmin';
import {PatientInfo} from '../../models/PatientInfo';

@Component({
  selector: 'app-edit-patient-profile',
  templateUrl: './edit-patient-profile.component.html',
  styleUrls: ['./edit-patient-profile.component.css']
})
export class EditPatientProfileComponent implements OnInit {

  PatientForm : FormGroup;
  loggedPatient : PatientInfo = new PatientInfo(-1,"","","","","", "", "", -1, -1);

  constructor(private formBuilder : FormBuilder,
              private toastr: ToastrService,
              private patientService : PatientService,
              private userService : UserService ) { }

  ngOnInit(): void {
    this.PatientForm = this.formBuilder.group({
      username: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      //password: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      firstName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      lastName: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      phoneNumber: new FormControl(null, [Validators.required, Validators.minLength(9), Validators.maxLength(10), Validators.pattern("[0-9]+")]),
      address: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      points: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
      penalties: new FormControl(null, [Validators.required, Validators.maxLength(30)]),

    });

    this.patientService.get(this.userService.getLoggedInUser().id).subscribe(
      (responseData: PatientInfo) => {
        this.loggedPatient = responseData;
        this.PatientForm.patchValue(
          {
            'username': this.loggedPatient.username,
            'firstName': this.loggedPatient.firstName,
            'lastName': this.loggedPatient.lastName,
            'phoneNumber': this.loggedPatient.phoneNumber,
            'address': this.loggedPatient.address,
            'points' : this.loggedPatient.points,
            'penalties' : this.loggedPatient.penalties
          }
        );
      },
      () => {
        this.userService.logout();
      }
    );
  }

  saveChanges() {
    if (this.PatientForm.invalid) {
      this.toastr.error("Unesite ispravne podatke.", 'Moj profil');
      return;
    }

    const patient = new Patient(this.loggedPatient.id,
      this.loggedPatient.username,
      this.PatientForm.value.firstName,
      this.PatientForm.value.lastName,
      this.PatientForm.value.phoneNumber,
      this.PatientForm.value.address
    );

    this.patientService.put(patient).subscribe(
      () => {
        this.toastr.success("Uspesno Ste promenili Vase podatke.", 'Moj profil');
      },
      () => {
        this.toastr.error("Greska, pokusajte ponovo.", 'Moj profil');
      }
    );
  }

}
