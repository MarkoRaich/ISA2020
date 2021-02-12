import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {ExaminationDerm} from '../../models/ExaminationDerm';
import {environment} from '../../../environments/environment';
import {Subscription} from 'rxjs';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {FormBuilder} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {ExaminationDermService} from '../../services/examination-derm.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-list-examinations-done',
  templateUrl: './list-examinations-done.component.html',
  styleUrls: ['./list-examinations-done.component.css']
})
export class ListExaminationsDoneComponent implements OnInit {

  examinationsDataSource: MatTableDataSource<ExaminationDerm>;

  displayedColumns: string[] = [ 'name', 'date', 'time', 'price', 'status', 'oceni'];
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  cancelExaminationSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(private formBuilder: FormBuilder,
              public dialog: MatDialog,
              private examinationDermService: ExaminationDermService,
              public toastr: ToastrService) { }

  examinations: ExaminationDerm[] = [];

  public examinationId: number;
  public examinationName: string;
  public dermatologistName: string;
  public price: number;
  public startDateTime: string;
  public endDateTime: string;
  public dermatologistRating: number;
  public status: string;

  public grade: number;

  ngOnInit() {

    this.getAllExaminationsBooked();

    this.cancelExaminationSuccess =  this.examinationDermService.successCancelExamination.subscribe(
      () => {
        this.getAllExaminationsBooked();
      }
    );
  }


  getAllExaminationsBooked() {
    this.examinationDermService.getAllExaminationsDone().subscribe(
      (data: ExaminationDerm[]) => {
        this.examinations = data;
        this.examinationsDataSource = new MatTableDataSource(data);
        this.examinationsDataSource.sort = this.sort;
        this.examinationsDataSource.paginator = this.paginator;
      });
  }

  public setGrade(examinationId: number) {
    //console.log(status);
    this.examinationDermService.setGrade(examinationId, this.grade).subscribe();
  }



}
