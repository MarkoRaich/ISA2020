import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {DrugWithQuantity} from '../../models/drugWithQuantity';
import {environment} from '../../../environments/environment';
import {Subscription} from 'rxjs';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {MatDialog} from '@angular/material/dialog';
import {DrugService} from '../../services/drug.service';
import {ToastrService} from 'ngx-toastr';
import {DrugInfo} from '../../models/DrugInfo';
import {AddDrugInPharmacyComponent} from '../add-drug-in-pharmacy/add-drug-in-pharmacy.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-all-drugs',
  templateUrl: './list-all-drugs.component.html',
  styleUrls: ['./list-all-drugs.component.css']
})
export class ListAllDrugsComponent implements OnInit {


  drugsDataSource: MatTableDataSource<DrugInfo>;
  displayedColumns: string[] = [ 'name', 'code', 'manufacturer', 'form', 'type', 'dodaj', 'oceni'];
  searchString: string;
  searchName: string = '';
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  addAlergieSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  drugs: DrugInfo[] = [];

  grade: number;


  constructor(public dialog: MatDialog, private drugService: DrugService, public toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.getAllDrugs();

    this.addAlergieSuccess =  this.drugService.successAddAlergie.subscribe(
      () => {
        this.getAllDrugs();
      }
    )
  }


  getAllDrugs() {
    this.drugService.getAllDrugs().subscribe(
      (data: DrugInfo[]) => {
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

  public makeReservation(): void {
    this.router.navigate(['/patient/make-drug-reservation']);
  }

  public setGrade(drugId: number) {
    this.drugService.setGrade(drugId, this.grade).subscribe();
  }

}
