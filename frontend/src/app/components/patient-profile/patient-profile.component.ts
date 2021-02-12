import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {ReservationService} from '../../services/reservation.service';
import {PatientInfo} from '../../models/PatientInfo';
import {MatTableDataSource} from '@angular/material/table';
import {Reservation} from '../../models/Reservation';
import {Patient} from '../../models/Patient';
import {PatientService} from '../../services/patient.service';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent implements OnInit {

  PatientForm : FormGroup;
  //loggedPatient : PatientInfo = new PatientInfo(-1,"","","","","","", "", -1, -1);

  constructor(private formBuilder : FormBuilder,
              private toastr: ToastrService,
              private patientService : PatientService,
              private userService : UserService ) { }

  ngOnInit(): void {

    }


}
