import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Dermatologist } from 'src/app/models/dermatologist';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-dermatologist-in-pharmacy',
  templateUrl: './dermatologist-in-pharmacy.component.html',
  styleUrls: ['./dermatologist-in-pharmacy.component.css']
})
export class DermatologistInPharmacyComponent implements OnInit {

  pharmId: string;

  dermatologistsDataSource: MatTableDataSource<Dermatologist>;
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'phoneNumber', 'workhours', 'rating'];
  searchString: string;
  searchFirstName: string = '';
  searchLastName: string = '';
  itemsPerPage = environment.itemsPerPage;

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(public toastr: ToastrService,
              public dermatologistService: DermatologistService,
              private fb: FormBuilder,
              private dialogRef: MatDialogRef<DermatologistInPharmacyComponent>,
              @Inject(MAT_DIALOG_DATA) data) {

      this.pharmId = data.id;
  }

  ngOnInit(): void {
    this.dermatologistService.getDermatologistsInPharmacy(this.pharmId).subscribe(
      (data : Dermatologist[]) => {
        this.dermatologistsDataSource = new MatTableDataSource(data);
        this.dermatologistsDataSource.sort = this.sort;
        this.dermatologistsDataSource.paginator = this.paginator;
      }
    )
  }

  search(){
    this.dermatologistService.searchDermatologistsInPharmacy(this.searchFirstName, this.searchLastName, this.pharmId).subscribe(
      (data) => {
      this.dermatologistsDataSource = new MatTableDataSource(data);
      this.dermatologistsDataSource.sort = this.sort;
      this.dermatologistsDataSource.paginator = this.paginator;
    })
  }


}
