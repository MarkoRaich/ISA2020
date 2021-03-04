import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { VacationDerm } from 'src/app/models/vacationDerm';
import { VacationPharm } from 'src/app/models/vacationPharm';
import { VacationDermService } from 'src/app/services/vacationDerm.service';
import { VacationPharmService } from 'src/app/services/vacationPharm.service';
import { environment } from 'src/environments/environment';
import { DenialReasonComponent } from '../denial-reason/denial-reason.component';

@Component({
  selector: 'app-list-vacations',
  templateUrl: './list-vacations.component.html',
  styleUrls: ['./list-vacations.component.css']
})
export class ListVacationsComponent implements OnInit {

  vacationRequestPharmDataSource: MatTableDataSource<VacationPharm>;
  displayedColumnsPharm: string[] = [ 'pharmacist', 'dateFrom', 'dateTo', 'status', 'approve', 'deny' ];
  changerequestPharmSuccess: Subscription;

  vacationRequestDermDataSource: MatTableDataSource<VacationDerm>;
  displayedColumnsDerm: string[] = [ 'dermatologist', 'dateFrom', 'dateTo', 'status', 'approve', 'deny' ];
  changerequestDermSuccess: Subscription;

  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;


  @ViewChild('sortPharm', { static: true }) sortPharm: MatSort;
  @ViewChild('paginatorPharm', { static: true }) paginatorPharm: MatPaginator;

  @ViewChild('sortDerm', { static: true }) sortDerm: MatSort;
  @ViewChild('paginatorDerm', { static: true }) paginatorDerm: MatPaginator;

  constructor(public dialog: MatDialog,
              private vacationPharmService: VacationPharmService,
              private vacationDermService: VacationDermService,
              public toastr: ToastrService) { }

  ngOnInit() {

    this.getAllPharmacistRequestsForPharmacy();

    this.changerequestPharmSuccess = this.vacationPharmService.changeSuccessEmitter.subscribe(
      () => {
        this.getAllPharmacistRequestsForPharmacy();
      }
    );

    this.getAllDermatologistRequestsForPharmacy();

    this.changerequestDermSuccess = this.vacationDermService.changeSuccessEmitter.subscribe(
      () => {
        this.getAllDermatologistRequestsForPharmacy();
      }
    );

  }

  isDisabled(request: any){
    if(request.status != 'AWAITING'){
      return true;
    } else {
      return false;
    }
  }
  
  getAllPharmacistRequestsForPharmacy() {
   this.vacationPharmService.getAllRequestsInPharmacy().subscribe(
     (data: VacationPharm[]) => {
       this.vacationRequestPharmDataSource = new MatTableDataSource(data);
       this.vacationRequestPharmDataSource.sort = this.sortPharm;
       this.vacationRequestPharmDataSource.paginator = this.paginatorPharm;
     })
  }
  getAllDermatologistRequestsForPharmacy(){
    this.vacationDermService.getAllRequestsInPharmacy().subscribe(
      (data: VacationDerm[]) => {
        this.vacationRequestDermDataSource = new MatTableDataSource(data);
        this.vacationRequestDermDataSource.sort = this.sortDerm;
        this.vacationRequestDermDataSource.paginator = this.paginatorDerm;
      })
  }

  ApproveRequestPharm(request: VacationPharm ){
    this.vacationPharmService.approveRequest(request.id).subscribe(
      () => {
        this.getAllPharmacistRequestsForPharmacy();
        this.toastr.success('Uspešno Ste povrdili godišnji odmor.', 'Godišnji odmor');
      },
      () => {
        this.toastr.error('Došlo je do greške.', 'Godišnji odmor');
      }
    );
  }
  

  DenyRequestPharm(request: VacationPharm ){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: request,
        worker: "pharmacist"
    };
    this.dialog.open(DenialReasonComponent, dialogConfig);
  }

  ApproveRequestDerm(request: VacationDerm ){
    this.vacationDermService.approveRequest(request.id).subscribe(
      () => {
        this.getAllDermatologistRequestsForPharmacy();
        this.toastr.success('Uspešno Ste povrdili godišnji odmor.', 'Godišnji odmor');
      },
      () => {
        this.toastr.error('Došlo je do greške.', 'Godišnji odmor');
      }
    );
  }

  DenyRequestDerm(request: VacationDerm ){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: request,
        worker: "dermatologist"
    };
    this.dialog.open(DenialReasonComponent, dialogConfig);
  }
}
