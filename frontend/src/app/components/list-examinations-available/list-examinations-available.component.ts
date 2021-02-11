import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {ExaminationDerm} from '../../models/ExaminationDerm';
import {environment} from '../../../environments/environment';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {FormBuilder} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {ExaminationDermService} from '../../services/examination-derm.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-list-examinations-available',
  templateUrl: './list-examinations-available.component.html',
  styleUrls: ['./list-examinations-available.component.css']
})
export class ListExaminationsAvailableComponent implements OnInit {

  examinationsDataSource: MatTableDataSource<ExaminationDerm>;

  displayedColumns: string[] = [ 'name', 'date', 'time', 'price', 'status', 'zakazi'];
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

    this.getAllExaminationsAvailable();
  }


  getAllExaminationsAvailable() {
    this.examinationDermService.getAllExaminationsAvailable().subscribe(
      (data: ExaminationDerm[]) => {
        this.examinations = data;
        this.examinationsDataSource = new MatTableDataSource(data);
        this.examinationsDataSource.sort = this.sort;
        this.examinationsDataSource.paginator = this.paginator;
      });
  }

  public bookExamination(id: number): void {
    this.toastr.success('Zakazan je pregled' + id , 'Pregled');
    this.examinationDermService.bookExamination(id).subscribe();
  }

}
