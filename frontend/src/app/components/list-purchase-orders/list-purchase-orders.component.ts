import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { PurchaseOrder } from 'src/app/models/purchaseOrder';
import { PurchaseOrderService } from 'src/app/services/purchaseOrderService';
import { environment } from 'src/environments/environment';
import { AddPurchaseOrderComponent } from '../add-purchase-order/add-purchase-order.component';
import { ListOrderItemsComponent } from '../list-order-items/list-order-items.component';

@Component({
  selector: 'app-list-purchase-orders',
  templateUrl: './list-purchase-orders.component.html',
  styleUrls: ['./list-purchase-orders.component.css']
})
export class ListPurchaseOrdersComponent implements OnInit {

  purchaseOrdersDataSource: MatTableDataSource<PurchaseOrder>;
  displayedColumns: string[] = ['id', 'endDate', 'status', 'drugList', 'offers'];
  itemsPerPage = environment.itemsPerPage;
  successCreatedPurchaseorder: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(public toastr: ToastrService,
              public dialog: MatDialog,
              private purchaseOrderService: PurchaseOrderService) { }

  ngOnInit(): void {

      this.getPurchaseOrdersForAdmin();

      this.successCreatedPurchaseorder = this.purchaseOrderService.createSuccessEmitter.subscribe(
        () => {
          this.getPurchaseOrdersForAdmin();
        }
      )
  }


  getPurchaseOrdersForAdmin(){

    this.purchaseOrderService.getPurchaseordersForAdmin().subscribe(
      (data: PurchaseOrder[]) => {
        this.purchaseOrdersDataSource = new MatTableDataSource(data);
        this.purchaseOrdersDataSource.sort = this.sort;
        this.purchaseOrdersDataSource.paginator = this.paginator;
      }
    )
  
  }

  openCreatingDialog() {
    this.dialog.open(AddPurchaseOrderComponent);
  }


  viewDrugList(purchaseOrder: PurchaseOrder){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: purchaseOrder
    };
    this.dialog.open(ListOrderItemsComponent, dialogConfig);
  }

  viewOffersForPurchaseOrder(purchaseOrder: PurchaseOrder){

  }

}
