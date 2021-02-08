import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-drug-in-pharmacy',
  templateUrl: './add-drug-in-pharmacy.component.html',
  styleUrls: ['./add-drug-in-pharmacy.component.css']
})
export class AddDrugInPharmacyComponent implements OnInit {

  
  addDrugForm: FormGroup;

  constructor( private router: Router,
               private toastr: ToastrService,) { }

  ngOnInit(): void {
    this.addDrugForm = new FormGroup({
      
    })
  }
  add(){}
}
