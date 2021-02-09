import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { AvailableExamination } from 'src/app/models/availableExamination';
import { Examination } from 'src/app/models/examination';
import { ExaminationService } from 'src/app/services/examination.service';
import { environment } from 'src/environments/environment';
import { AddExaminationComponent } from '../add-examination/add-examination.component';

@Component({
  selector: 'app-list-examinations',
  templateUrl: './list-examinations.component.html',
  styleUrls: ['./list-examinations.component.css']
})
export class ListExaminationsComponent implements OnInit {

    examinationsDataSource: MatTableDataSource<AvailableExamination>;
    displayedColumns: string[] = [ 'id', 'dermatologist', 'date', 'time', 'price'];
    numberOfItems: number;
    itemsPerPage = environment.itemsPerPage;
    addExaminationSuccess: Subscription;
  
    @ViewChild(MatSort, { static: true }) sort: MatSort;
    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  
    constructor(public dialog: MatDialog, private examinationService: ExaminationService, public toastr: ToastrService) { }
  
    ngOnInit() {
  
      this.getAvailableExaminationsForPharmacy();
  
      this.addExaminationSuccess =  this.examinationService.successCreatedExamination.subscribe(
        () => {
          this.getAvailableExaminationsForPharmacy();
        }
      )
    }
  
    
    getAvailableExaminationsForPharmacy() {
     this.examinationService.getAllAvailableExaminationsInPharmacy().subscribe(
       (data) => {
         this.examinationsDataSource = new MatTableDataSource(data);
         this.examinationsDataSource.sort = this.sort;
         this.examinationsDataSource.paginator = this.paginator;
       })
    }
  
    openCreatingDialog(){
      this.dialog.open(AddExaminationComponent);
    }  
  
  }
  