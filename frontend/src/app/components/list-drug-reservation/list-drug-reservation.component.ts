import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {DrugInfo} from '../../models/DrugInfo';
import {environment} from '../../../environments/environment';
import {Subscription} from 'rxjs';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {MatDialog} from '@angular/material/dialog';
import {DrugService} from '../../services/drug.service';
import {ToastrService} from 'ngx-toastr';
import {DrugReservation} from '../../models/DrugReservation';
import {AddDrugInPharmacyComponent} from '../add-drug-in-pharmacy/add-drug-in-pharmacy.component';
import {DrugReservationComponent} from '../drug-reservation/drug-reservation.component';
import {DrugWithQuantity} from '../../models/drugWithQuantity';
import {ChangeDrugQuantityComponent} from '../change-drug-quantity/change-drug-quantity.component';

@Component({
  selector: 'app-list-drug-reservation',
  templateUrl: './list-drug-reservation.component.html',
  styleUrls: ['./list-drug-reservation.component.css']
})
export class ListDrugReservationComponent implements OnInit {

  drugsDataSource: MatTableDataSource<DrugReservation>;
  displayedColumns: string[] = ['pharmacyName', 'drugName', 'drugCode'];
  searchString: string;
  searchName: string = '';
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  addAlergieSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  drugs: DrugReservation[] = [];


  constructor(public dialog: MatDialog, private drugService: DrugService, public toastr: ToastrService) { }

  //public drugs: Object;
  public date: string;
  public endTime: string;
  public quantity: number;

  ngOnInit(): void {
    this.getAllDrugQuantities();

    this.addAlergieSuccess =  this.drugService.successAddAlergie.subscribe(
      () => {
        this.getAllDrugQuantities();
      }
    )
  }


  getAllDrugQuantities() {
    this.drugService.getAllDrugQuantities().subscribe(
      (data: DrugReservation[]) => {
        this.drugs = data;
        this.drugsDataSource = new MatTableDataSource(data);
        this.drugsDataSource.sort = this.sort;
        this.drugsDataSource.paginator = this.paginator;
      });
  }

  public addAlergie(drugId: number): void {
    this.toastr.success('Dodata je alergija na lek ' + drugId , 'Alergija');
    this.drugService.addAlergie(drugId).subscribe();
  }

  public makeReservation(drugId: number, pharmacyId: number): void {
    this.toastr.success('Dodata je rezervacija leka ' + drugId , 'Rezervacija leka');
    this.drugService.makeReservationWithQuantity(drugId, pharmacyId, this.quantity, this.date + '..' + this.endTime).subscribe();
  }

  openDrugReservationDialog(drugReservation: DrugReservation){
    this.dialog.open(DrugReservationComponent);
  }


}
