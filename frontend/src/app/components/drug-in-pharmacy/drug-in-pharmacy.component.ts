import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { DrugWithQuantity } from 'src/app/models/drugWithQuantity';
import { DrugService } from 'src/app/services/drug.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-drug-in-pharmacy',
  templateUrl: './drug-in-pharmacy.component.html',
  styleUrls: ['./drug-in-pharmacy.component.css']
})
export class DrugInPharmacyComponent implements OnInit {

  pharmId: string


  drugsDataSource: MatTableDataSource<DrugWithQuantity>;
  displayedColumns: string[] = [ 'name', 'code', 'reserve', 'check'];
  searchString: string;
  searchName: string = '';
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(
    private fb: FormBuilder,
    private drugService: DrugService, 
    public toastr: ToastrService,
    private dialogRef: MatDialogRef<DrugInPharmacyComponent>,
    @Inject(MAT_DIALOG_DATA) data) {

    this.pharmId = data.id;
}

  ngOnInit(): void {

    this.drugService.getDrugsWithQuantityInPharmacy(this.pharmId).subscribe(
      (data: DrugWithQuantity[]) => {
        this.drugsDataSource = new MatTableDataSource(data);
        this.drugsDataSource.sort = this.sort;
        this.drugsDataSource.paginator = this.paginator;
      })
  }


  reserve(drug: DrugWithQuantity){
    this.toastr.success("Rezervisali ste lek","Rezervacija leka");
  }

  chechAvailability(drug: DrugWithQuantity){
    this.toastr.success("Provera u toku","Provera dostupnosti leka");
  }
}
