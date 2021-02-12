import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {Consultation} from '../../models/Consultation';
import {environment} from '../../../environments/environment';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {FormBuilder} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {ConsultationService} from '../../services/consulatation.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-list-consultations-not-done-canceled',
  templateUrl: './list-consultations-not-done-canceled.component.html',
  styleUrls: ['./list-consultations-not-done-canceled.component.css']
})
export class ListConsultationsNotDoneCanceledComponent implements OnInit {

  examinationsDataSource: MatTableDataSource<Consultation>;

  displayedColumns: string[] = [ 'name', 'pharmacy', 'rating',  'price', 'status'];
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(private formBuilder: FormBuilder,
              public dialog: MatDialog,
              private consultationService: ConsultationService,
              public toastr: ToastrService) { }

  consultations: Consultation[] = [];

  public consultationId: number;
  public consultationName: String;
  public pharmacyName: String
  public price: number;
  public pharmacyRating: number;
  public startDateTime: String;
  public endDateTime: String;
  public pharmacyAddress: String;
  public status: String;

  ngOnInit() {

    this.getAllConsultationsBooked();
  }


  getAllConsultationsBooked() {
    this.consultationService.getAllConsultationsDoneCanceled().subscribe(
      (data: Consultation[]) => {
        this.consultations = data;
        this.examinationsDataSource = new MatTableDataSource(data);
        this.examinationsDataSource.sort = this.sort;
        this.examinationsDataSource.paginator = this.paginator;
      });
  }
}
