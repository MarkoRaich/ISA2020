import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { DrugWithPrice } from 'src/app/models/drugWithPrice';
import { ExaminationType } from 'src/app/models/examinationType';
import { DrugService } from 'src/app/services/drug.service';
import { ExaminationTypeService } from 'src/app/services/examination-type.service';
import { environment } from 'src/environments/environment';
import { AddPricelistComponent } from '../add-pricelist/add-pricelist.component';
import { ChangeDrugPriceComponent } from '../change-drug-price/change-drug-price.component';

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.css']
})
export class PriceListComponent implements OnInit {

  examinationTypesDataSource: MatTableDataSource<ExaminationType>;  
  displayedColumnsType: string[] = [ 'name', 'description', 'price'];
  @ViewChild('TableOnePaginator', {static: true}) tableOnePaginator: MatPaginator;
  @ViewChild('TableOneSort', {static: true}) tableOneSort: MatSort;


  
  drugPriceDataSource: MatTableDataSource<DrugWithPrice>;
  displayedColumnsDrug: string[] = [ 'drug', 'interval', 'price', 'change'];
  @ViewChild('TableTwoPaginator', {static: true}) tableTwoPaginator: MatPaginator;
  @ViewChild('TableTwoSort', {static: true}) tableTwoSort: MatSort;
  
  successCreatedPricelist: Subscription;

  itemsPerPage = environment.itemsPerPage;
  todaysDate: number;

  constructor(public dialog: MatDialog,
              private examinationTypeService: ExaminationTypeService,
              private drugService: DrugService,
              public toastr: ToastrService) { }
  
  ngOnInit() {
    
    this.todaysDate = Date.now();

    this.getExaminationTypesForPharmacy();

    this.getDrugPricesForPharmacy();

    this.successCreatedPricelist = this.drugService.succesCreatedPricelist.subscribe(
      () =>{
        this.getDrugPricesForPharmacy();
      }
    )
  
  }

  
  getExaminationTypesForPharmacy() {
   this.examinationTypeService.getExaminationTypesForAdmin().subscribe(
     (data: ExaminationType[]) => {
       this.examinationTypesDataSource = new MatTableDataSource(data);
       this.examinationTypesDataSource.sort = this.tableOneSort;
       this.examinationTypesDataSource.paginator = this.tableOnePaginator;
     })
  }

  getDrugPricesForPharmacy(){
    this.drugService.getAllDrugPricesForPharmacy().subscribe(
      (data: DrugWithPrice[]) => {
        this.drugPriceDataSource = new MatTableDataSource(data);
        this.drugPriceDataSource.sort = this.tableTwoSort;
        this.drugPriceDataSource.paginator = this.tableTwoPaginator;
      }
    )
  }

  openCreatingDialog(){
    this.dialog.open(AddPricelistComponent);
  }

  
  isDisabled(drug: DrugWithPrice){

    var partsEnd = drug.endDate.split(' ');
    var endDate = Date.parse(partsEnd[0]);
  
    if(this.todaysDate > endDate){
      return true;
    } else {
      return false;
    }
  }

  changePrice(drug: DrugWithPrice){
    this.dialog.open(ChangeDrugPriceComponent, {
      data: {
        drug: drug      }
    });
  }


}
