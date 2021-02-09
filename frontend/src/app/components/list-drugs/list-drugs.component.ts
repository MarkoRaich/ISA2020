import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { DrugWithQuantity } from 'src/app/models/drugWithQuantity';
import { DrugService } from 'src/app/services/drug.service';
import { environment } from 'src/environments/environment';
import { AddDrugInPharmacyComponent } from '../add-drug-in-pharmacy/add-drug-in-pharmacy.component';

@Component({
  selector: 'app-list-drugs',
  templateUrl: './list-drugs.component.html',
  styleUrls: ['./list-drugs.component.css']
})
export class ListDrugsComponent implements OnInit {

  drugsDataSource: MatTableDataSource<DrugWithQuantity>;
  displayedColumns: string[] = [ 'name', 'code', 'quantity', 'delete', 'change'];
  searchString: string;
  searchName: string = '';
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  addDrugSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(public dialog: MatDialog, private drugService: DrugService, public toastr: ToastrService) { }

  ngOnInit() {

    this.getDrugsWithQuantityForPharmacy();

    this.addDrugSuccess =  this.drugService.addSuccessEmitter.subscribe(
      () => {
        this.getDrugsWithQuantityForPharmacy();
      }
    )
  }

  
  getDrugsWithQuantityForPharmacy() {
   this.drugService.getAllDrugsWithQuantityInPharmacy().subscribe(
     (data) => {
       this.drugsDataSource = new MatTableDataSource(data);
       this.drugsDataSource.sort = this.sort;
       this.drugsDataSource.paginator = this.paginator;
     })
  }

  openCreatingDialog(){
     this.dialog.open(AddDrugInPharmacyComponent);
  }

  search(){
    this.drugService.searchDrugsFromPharmacy(this.searchName).subscribe(
      (data) => {
        this.drugsDataSource = new MatTableDataSource(data);
        this.drugsDataSource.sort = this.sort;
        this.drugsDataSource.paginator = this.paginator;
      }
    )
  }

  deleteDrugQ(drug : DrugWithQuantity ) {

    this.drugService.deleteDrugFromPharmacy(drug.id).subscribe(
      () => {
        this.getDrugsWithQuantityForPharmacy();
        this.toastr.success('Uspešno Ste obrisali lek iz apoteke.', 'Brisanje leka iz apoteke');
      },
      () => {
        this.toastr.error('Lek je rezervisan i ne može se obrisati.', 'Brisanje leka iz apoteke');
      }
    );
  }

  changeDrugQ(drug : DrugWithQuantity){
    
  }


 


}
