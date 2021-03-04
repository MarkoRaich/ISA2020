import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { off } from 'process';
import { Offer } from 'src/app/models/offer';
import { PurchaseOrder } from 'src/app/models/purchaseOrder';
import { PurchaseOrderService } from 'src/app/services/purchaseOrderService';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-list-offers',
  templateUrl: './list-offers.component.html',
  styleUrls: ['./list-offers.component.css']
})
export class ListOffersComponent implements OnInit {

   purchaseOrder : PurchaseOrder;

   offersDataSource: MatTableDataSource<Offer>;
   displayedColumns: string[] = ['supplier', 'offer', 'deliveryDeadline', 'status', 'accept'];
   itemsPerPage = environment.itemsPerPage;

   currentDate: Date;
   isDisabled: boolean;

   @ViewChild(MatSort, { static: false }) sort: MatSort;
   @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
 
   constructor(public toastr: ToastrService,
               private dialogRef: MatDialogRef<ListOffersComponent>,
               private purchaseOrderService: PurchaseOrderService,
               @Inject(MAT_DIALOG_DATA) data) {
 
       this.purchaseOrder = data.id;

       this.isDisabled = this.purchaseOrder.status == 'FINISHED';
   }
 
   ngOnInit(): void {
     this.offersDataSource=new MatTableDataSource(this.purchaseOrder.offers);
   }

   acceptOffer(offer: Offer){

    this.currentDate = new Date();

    if(this.currentDate.getTime() < Date.parse(this.purchaseOrder.deadline) ) {
        this.toastr.error("Krajnji rok nije prošao i ne možete birati ponudu","Odabir ponude");
        return;
    }

    this.purchaseOrderService.acceptOfferForOrder(this.purchaseOrder.id.toString(), offer.id.toString()).subscribe(
      (responseData) => {
        this.dialogRef.close();
        this.toastr.success('Uspešno Ste odabrali ponudu.', 'Odabir ponude');
        this.purchaseOrderService.changeOrderStatusEmitter.next(this.purchaseOrder);
      },
      () => {
        this.toastr.error('Greška pri odabiru ponude.', 'Odabir ponude');
      }
    );
   }

}
