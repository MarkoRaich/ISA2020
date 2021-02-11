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
import {ConsultationService} from '../../services/consulatation.service';
import {Consultation} from '../../models/Consultation';

@Component({
  selector: 'app-list-consultations-booked',
  templateUrl: './list-consultations-booked.component.html',
  styleUrls: ['./list-consultations-booked.component.css']
})
export class ListConsultationsBookedComponent implements OnInit {

  examinationsDataSource: MatTableDataSource<Consultation>;

  displayedColumns: string[] = [ 'name', 'date', 'time', 'pharmacy', 'rating',  'price', 'status', 'otkazi'];
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
    this.consultationService.getAllConsultationsBooked().subscribe(
      (data: Consultation[]) => {
        this.consultations = data;
        this.examinationsDataSource = new MatTableDataSource(data);
        this.examinationsDataSource.sort = this.sort;
        this.examinationsDataSource.paginator = this.paginator;
      });
  }

  public cancelConsultation(id: number): void {
    this.toastr.success('Otkazan je pregled' + id , 'Pregled');
    this.consultationService.cancelConsultation(id).subscribe();
  }
}
