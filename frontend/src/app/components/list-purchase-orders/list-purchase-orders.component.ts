import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { PurchaseOrder } from 'src/app/models/purchaseOrder';
import { PurchaseOrderService } from 'src/app/services/purchaseOrderService';
import { environment } from 'src/environments/environment';
import { ListOffersComponent } from '../list-offers/list-offers.component';
import { ListOrderItemsComponent } from '../list-order-items/list-order-items.component';

@Component({
  selector: 'app-list-purchase-orders',
  templateUrl: './list-purchase-orders.component.html',
  styleUrls: ['./list-purchase-orders.component.css']
})
export class ListPurchaseOrdersComponent implements OnInit {

  purchaseOrdersDataSource : MatTableDataSource<PurchaseOrder>;
  displayedColumns: string[] = [ 'deadline', 'status', 'drugList', 'offers', 'delete'];
  itemsPerPage = environment.itemsPerPage;
  successCreatedPurchaseorder: Subscription;
  succesChangedPurchaseOrder: Subscription;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(public toastr: ToastrService,
              public dialog: MatDialog,
              public route: Router,
              private purchaseOrderService: PurchaseOrderService) { 
  }

  ngOnInit(): void {

      this.getPurchaseOrdersForAdmin();

    // this.purchaseOrdersDataSource.filterPredicate = 
      //(data: PurchaseOrder, filter: string) => data.status.indexOf(filter) != -1;

      this.purchaseOrdersDataSource.filterPredicate = this.getFilterPredicate();


      this.successCreatedPurchaseorder = this.purchaseOrderService.createSuccessEmitter.subscribe(
          () => {
            this.getPurchaseOrdersForAdmin();
          }
        );

      this.succesChangedPurchaseOrder = this.purchaseOrderService.changeOrderStatusEmitter.subscribe(
          () => {
            this.getPurchaseOrdersForAdmin();
          }
        );
  
  }


  getFilterPredicate() {
    return (data: PurchaseOrder, filter: string) =>  {

      const columnStatus = data.status;

      return columnStatus.toLowerCase().includes(filter);
};
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

  openCreatingPage() {
    this.route.navigate(['pharmacy-admin/add-purchase-order']);
  }
  
  deleteOrder(order : PurchaseOrder){

    this.purchaseOrderService.deleteOrder(order.id).subscribe(
      () => {
        this.getPurchaseOrdersForAdmin();
        this.toastr.success('Uspešno Ste obrisali narudžbenicu', 'Brisanje narudžbenice')
      },
      () => {
        this.toastr.error('Postoje ponude za narudžbenicu i ne može se obrisati', 'Brisanje narudžbenice');
      }
    );
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
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
        id: purchaseOrder
    };
    this.dialog.open(ListOffersComponent, dialogConfig);
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.purchaseOrdersDataSource.filter = filterValue;
  }

}
