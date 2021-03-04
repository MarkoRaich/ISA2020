import { formatDate } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { DateTimeInterval } from 'src/app/models/dateTimeInterval';
import { DrugInfo } from 'src/app/models/DrugInfo';
import { DrugWithPrice } from 'src/app/models/drugWithPrice';
import { DrugWithQuantity } from 'src/app/models/drugWithQuantity';
import { DrugService } from 'src/app/services/drug.service';
import { TimeValidator } from 'src/app/validators/time.validator';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-add-pricelist',
  templateUrl: './add-pricelist.component.html',
  styleUrls: ['./add-pricelist.component.css']
})
export class AddPricelistComponent implements OnInit {

  drugsWithPrice: DrugWithPrice[];
  drugsWithQuantity: DrugWithQuantity[];

  drugsDataSource: MatTableDataSource<DrugWithPrice>;

  dateForm: FormGroup;
  minDate = new Date();

  displayedColumns: string[] = ['name', 'interval', 'price'];
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  addDrugSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(private toastr : ToastrService, 
              private formBuilder : FormBuilder,
              private drugService: DrugService,
              public dialogRef : MatDialogRef<AddPricelistComponent> ) { }


  ngOnInit(): void {
    
    this.minDate.setDate(this.minDate.getDate() + 1);

    this.drugsWithPrice = [];
    this.drugsWithQuantity = [];

    this.dateForm = this.formBuilder.group({
      dateFrom: new FormControl(null, [Validators.required]),
      dateTo: new FormControl(null, [Validators.required]),
      
    }, {
      validator: TimeValidator('dateFrom', 'dateTo')
    });
  }

  next(){
    this.getDrugsForPharmacy();
  }

  getDrugsForPharmacy(){
    this.drugService.getAllDrugsWithQuantityInPharmacy().subscribe(
      (data: DrugWithQuantity[]) => {

        this.drugsWithQuantity = data;

        const dateFrom = formatDate(this.dateForm.value.dateFrom, 'yyyy-MM-dd', 'en-US');
        const dateTo = formatDate(this.dateForm.value.dateTo, 'yyyy-MM-dd', 'en-US');
    
        const startDateTime = dateFrom + ' ' + "00:01";
        const endDateTime = dateTo + ' ' + "00:01";

        this.drugsWithPrice = [];

        for (let index = 0; index < this.drugsWithQuantity.length; index++) {
            
          var drug =new DrugWithPrice(0,
                                      new DrugInfo(this.drugsWithQuantity[index].id, this.drugsWithQuantity[index].name, this.drugsWithQuantity[index].code),
                                      this.drugsWithQuantity[index].pharmacy,
                                      startDateTime,
                                      endDateTime
          )

          this.drugsWithPrice.push(drug);
          
        }

        this.drugsDataSource = new MatTableDataSource(this.drugsWithPrice);
        this.drugsDataSource.sort = this.sort;
        this.drugsDataSource.paginator = this.paginator;
      })
   }

   create(){

      if(this.dateForm.invalid){
        this.toastr.error('Unesite ispravno datume', 'Kreiranje cenovnika');
        return;
      }
      for (let index = 0; index < this.drugsWithPrice.length; index++) {
          if(this.drugsWithPrice[index].price ==0){
            this.toastr.error('Uneisite ispravnu cenu za sve lekove', 'Kreiranje cenovnika');
            return;
          }
      }


      this.drugService.createPriceList(this.drugsWithPrice).subscribe(
        (responseData) => {
          this.dateForm.reset();
          this.dialogRef.close();
          this.toastr.success('Uspešno Ste kreirali cenovnik', 'Kreiranje cenovnika');
          this.drugService.succesCreatedPricelist.next();
        },
        () => {
          this.toastr.error('Postoji konflikt u datumima!','Kreiranje cenovnika');
        }
      )


   }
}
