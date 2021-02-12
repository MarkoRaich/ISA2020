import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { OrderItem } from 'src/app/models/orderItem';
import { PurchaseOrder } from 'src/app/models/purchaseOrder';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-list-order-items',
  templateUrl: './list-order-items.component.html',
  styleUrls: ['./list-order-items.component.css']
})
export class ListOrderItemsComponent implements OnInit {

  purchaseOrder : PurchaseOrder;

  orderListDataSource: MatTableDataSource<OrderItem>;
  displayedColumns: string[] = ['drug', 'quantity'];
  itemsPerPage = environment.itemsPerPage;

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  constructor(public toastr: ToastrService,
              private dialogRef: MatDialogRef<ListOrderItemsComponent>,
              @Inject(MAT_DIALOG_DATA) data) {

      this.purchaseOrder = data.id;
  }

  ngOnInit(): void {
    this.orderListDataSource=new MatTableDataSource(this.purchaseOrder.orderitems);
  }

}
