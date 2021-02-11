import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Pharmacist } from 'src/app/models/pharmacist';
import { Pharmacy } from 'src/app/models/Pharmacy';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-pharmacist-in-pharmacy',
  templateUrl: './pharmacist-in-pharmacy.component.html',
  styleUrls: ['./pharmacist-in-pharmacy.component.css']
})
export class PharmacistInPharmacyComponent implements OnInit {
  

  pharmId: string;

  pharmacistsDataSource: MatTableDataSource<Pharmacist>;
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'phoneNumber', 'workhours', 'rating'];
  searchString: string;
  searchFirstName: string = '';
  searchLastName: string = '';
  itemsPerPage = environment.itemsPerPage;

  
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
      private fb: FormBuilder,
      public pharmacistService: PharmacistService,
      public toastr: ToastrService,
      private dialogRef: MatDialogRef<PharmacistInPharmacyComponent>,
      @Inject(MAT_DIALOG_DATA) data) {

      this.pharmId = data.id;
  }

  ngOnInit() {
    this.pharmacistService.getPharmacistsInPharmacy(this.pharmId).subscribe(
      (data : Pharmacist[]) => {
        this.pharmacistsDataSource = new MatTableDataSource(data);
        this.pharmacistsDataSource.sort = this.sort;
        this.pharmacistsDataSource.paginator = this.paginator;
      }
    )
  }

  search(){
    this.pharmacistService.searchPharmacistsInPharmacy(this.searchFirstName, this.searchLastName, this.pharmId).subscribe(
      (data) => {
      this.pharmacistsDataSource = new MatTableDataSource(data);
      this.pharmacistsDataSource.sort = this.sort;
      this.pharmacistsDataSource.paginator = this.paginator;
    })
  }

}