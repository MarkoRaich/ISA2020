import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { AvailableExamination } from 'src/app/models/availableExamination';
import { ExaminationService } from 'src/app/services/examination.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-examination-in-pharmacy',
  templateUrl: './examination-in-pharmacy.component.html',
  styleUrls: ['./examination-in-pharmacy.component.css']
})
export class ExaminationInPharmacyComponent implements OnInit {

  pharmId: string;


  examinationsDataSource: MatTableDataSource<AvailableExamination>;
  displayedColumns: string[] = [ 'id', 'dermatologist', 'date', 'time', 'price'];
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  
  constructor(private fb: FormBuilder,
              private examinationService: ExaminationService,
              public toastr: ToastrService,
              private dialogRef: MatDialogRef<ExaminationInPharmacyComponent>,
              @Inject(MAT_DIALOG_DATA) data) {

      this.pharmId = data.id;
  }


  ngOnInit(): void {
    this.examinationService.getAvailableExaminationsInPharmacy(this.pharmId).subscribe(
      (data: AvailableExamination[]) => {
        this.examinationsDataSource = new MatTableDataSource(data);
        this.examinationsDataSource.sort = this.sort;
        this.examinationsDataSource.paginator = this.paginator;
      })

  }

  search(){}
}
