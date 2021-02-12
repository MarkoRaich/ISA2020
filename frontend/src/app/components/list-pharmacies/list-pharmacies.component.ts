import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {Dermatologist} from '../../models/dermatologist';
import {environment} from '../../../environments/environment';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {ToastrService} from 'ngx-toastr';
import {DermatologistService} from '../../services/dermatologist.service';
import {FormBuilder} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Pharmacy} from '../../models/pharmacy';
import {PharmacyService} from '../../services/pharmacy.service';

@Component({
  selector: 'app-list-pharmacies',
  templateUrl: './list-pharmacies.component.html',
  styleUrls: ['./list-pharmacies.component.css']
})
export class ListPharmaciesComponent implements OnInit {

  pharmId: string;

  dermatologistsDataSource: MatTableDataSource<Pharmacy>;
  displayedColumns: string[] = ['pharmacyName', 'pharmacyAddress', 'pharmacyRating'];
  searchString: string;
  searchName: string = '';
  searchAddress: string = '';
  itemsPerPage = environment.itemsPerPage;

  pharmaties: Pharmacy[] = [];

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  constructor(public toastr: ToastrService,
              public pharmacyService: PharmacyService,
              private fb: FormBuilder) {

    //this.pharmId = data.id;
  }

  ngOnInit(): void {
    this.pharmacyService.getAllPharmaties().subscribe(
      (data: Pharmacy[]) => {
        this.pharmaties = data;
        this.dermatologistsDataSource = new MatTableDataSource(data);
        this.dermatologistsDataSource.sort = this.sort;
        this.dermatologistsDataSource.paginator = this.paginator;
      }
    );
  }

  searchByName() {
    this.pharmacyService.searchPharmacyByName(this.searchName).subscribe(
      (data: Pharmacy[]) => {
        this.pharmaties = data;
        this.dermatologistsDataSource = new MatTableDataSource(data);
        this.dermatologistsDataSource.sort = this.sort;
        this.dermatologistsDataSource.paginator = this.paginator;
      });
  }

  searchByAddress() {
    this.pharmacyService.searchPharmacyByAddress(this.searchAddress).subscribe(
      (data: Pharmacy[]) => {
        this.pharmaties = data;
        this.dermatologistsDataSource = new MatTableDataSource(data);
        this.dermatologistsDataSource.sort = this.sort;
        this.dermatologistsDataSource.paginator = this.paginator;
      });
  }

}
