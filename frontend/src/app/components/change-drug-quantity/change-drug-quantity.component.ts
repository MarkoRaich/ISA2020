import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-change-drug-quantity',
  templateUrl: './change-drug-quantity.component.html',
  styleUrls: ['./change-drug-quantity.component.css']
})
export class ChangeDrugQuantityComponent implements OnInit {

  


  constructor( private router: Router,
              private toastr: ToastrService) { }

  ngOnInit(): void {
  }

}
