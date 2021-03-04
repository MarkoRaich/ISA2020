import { formatDate } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { DrugInfo } from 'src/app/models/DrugInfo';
import { OrderItem } from 'src/app/models/orderItem';
import { PurchaseOrder } from 'src/app/models/purchaseOrder';
import { DrugService } from 'src/app/services/drug.service';
import { PurchaseOrderService } from 'src/app/services/purchaseOrderService';
import { environment } from 'src/environments/environment';
import { AddOrderItemComponent } from '../add-order-item/add-order-item.component';

@Component({
  selector: 'app-add-purchase-order',
  templateUrl: './add-purchase-order.component.html',
  styleUrls: ['./add-purchase-order.component.css']
})
export class AddPurchaseOrderComponent implements OnInit {

  createOrderForm: FormGroup;
  minDate = new Date();
  timeError = false;

  purchaseOrder : PurchaseOrder;
  orderItemsDataSource: MatTableDataSource<OrderItem>;
  displayedColumns: string[] = ['drug', 'quantity'];
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  createItemsSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(private toastr : ToastrService,
              private route: Router,
              public dialog: MatDialog,
              private purchaseOrderService: PurchaseOrderService,
              private formBuilder : FormBuilder,
              ) { }

  ngOnInit(): void {

    this.minDate.setDate(this.minDate.getDate() + 2);

    this.purchaseOrder = new PurchaseOrder('AWAITING', [], '', []);
   
    this.createOrderForm = new FormGroup({
      date: new FormControl(null, [Validators.required])
    });
    
    this.orderItemsDataSource = new MatTableDataSource(this.purchaseOrder.orderitems);
    this.orderItemsDataSource.sort = this.sort;
    this.orderItemsDataSource.paginator = this.paginator;

    this.createItemsSuccess = this.purchaseOrderService.createItemSuccesEmitter.subscribe(
      () => {
        this.orderItemsDataSource = new MatTableDataSource(this.purchaseOrder.orderitems);
        this.orderItemsDataSource.sort = this.sort;
        this.orderItemsDataSource.paginator = this.paginator;
      }
    )
  }

  cancelOrder(){
    this.route.navigate(['pharmacy-admin/purchase-orders']);
  }

  openItemDialog(){
    this.dialog.open(AddOrderItemComponent, {
      data: {
        order: this.purchaseOrder
      }
    })
  }


  createPurchaseOrder(){

    if(this.purchaseOrder.orderitems.length == 0){
      this.toastr.error("Vaša narudžbenica nema stavke.", "Kreiranje narudžbenice");
      return;
      
    }
   
    const date = formatDate(this.createOrderForm.value.date, 'yyyy-MM-dd', 'en-US')
    const deadline = date + ' ' + '00:00';

    this.purchaseOrder.deadline = deadline;


    this.purchaseOrderService.createPurchaseOrder(this.purchaseOrder).subscribe(
      (data) => {
        this.createOrderForm.reset();
        this.toastr.success('Uspešno Ste kreirali narudzbenicu.', 'Kreiranje narudzbenice');
        this.purchaseOrderService.createSuccessEmitter.next(this.purchaseOrder);
        this.route.navigate(['pharmacy-admin/purchase-orders']);
      },
      
      () => {
        this.toastr.error("Greška pri kreiranju narudžbenice","Kreiranje narudžbenice");
      }
    )
    
    
  }
}
