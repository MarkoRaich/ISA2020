import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {AvailableExamination} from '../../models/availableExamination';
import {environment} from '../../../environments/environment';
import {Subscription} from 'rxjs';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {MatDialog} from '@angular/material/dialog';
import {ExaminationService} from '../../services/examination.service';
import {ToastrService} from 'ngx-toastr';
import {AddExaminationComponent} from '../add-examination/add-examination.component';
import {ExaminationDerm} from '../../models/ExaminationDerm';
import {ExaminationDermService} from '../../services/examination-derm.service';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-list-examinations-booked',
  templateUrl: './list-examinations-booked.component.html',
  styleUrls: ['./list-examinations-booked.component.css']
})
export class ListExaminationsBookedComponent implements OnInit {

  examinationsDataSource: MatTableDataSource<ExaminationDerm>;

  displayedColumns: string[] = [ 'name', 'date', 'time', 'price', 'status', 'otkazi'];
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;

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

  ngOnInit() {

    this.getAllExaminationsBooked();
  }


  getAllExaminationsBooked() {
    this.examinationDermService.getAllExaminationsBooked().subscribe(
      (data: ExaminationDerm[]) => {
        this.examinations = data;
        this.examinationsDataSource = new MatTableDataSource(data);
        this.examinationsDataSource.sort = this.sort;
        this.examinationsDataSource.paginator = this.paginator;
      });
  }

  public cancelExamination(id: number): void {
    this.toastr.success('Otkazan je pregled' + id , 'Pregled');
    this.examinationDermService.cancelExamination(id).subscribe();
  }



}
