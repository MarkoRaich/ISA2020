import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { DrugInfo } from 'src/app/models/DrugInfo';
import { OrderItem } from 'src/app/models/orderItem';
import { PurchaseOrder } from 'src/app/models/purchaseOrder';
import { DrugService } from 'src/app/services/drug.service';
import { PurchaseOrderService } from 'src/app/services/purchaseOrderService';

@Component({
  selector: 'app-add-purchase-order',
  templateUrl: './add-purchase-order.component.html',
  styleUrls: ['./add-purchase-order.component.css']
})
export class AddPurchaseOrderComponent implements OnInit {

  dateTimeTypeForm: FormGroup;
  addItemsForm: FormGroup;
  minDate = new Date();
  timeError = false;

  purchaseorder : PurchaseOrder;

  drugs : DrugInfo[] = [];

  constructor(private toastr : ToastrService,
              private drugService: DrugService,
              private purchaseOrderService: PurchaseOrderService,
              private formBuilder : FormBuilder,
              public dialogRef : MatDialogRef<AddPurchaseOrderComponent> ) { }

  ngOnInit(): void {

    this.minDate.setDate(this.minDate.getDate() + 1);

    this.dateTimeTypeForm = this.formBuilder.group({
      date: new FormControl(null, [Validators.required]),
    });

    this.addItemsForm = new FormGroup({
      drug: new FormControl(null, [Validators.required]),
      quantity: new FormControl(null, [Validators.required, Validators.min(1)])
    });

    this.getAllDrugs();
  }


  getAllDrugs() {
    this.drugService.getAllDrugs().subscribe(
    (data: DrugInfo[]) => {
      this.drugs = data;
    });
  }
  
  next(){
    const date = formatDate(this.dateTimeTypeForm.value.date, 'yyyy-MM-dd', 'en-US')
    const endDateTime = date + ' ' + "00:00";
    this.purchaseorder = new PurchaseOrder('AWAITING', [], endDateTime);
  }
  addOrderItem(){
    this.purchaseorder.orderitems.push(new OrderItem(this.addItemsForm.value.quantity, this.addItemsForm.value.drug));
    this.toastr.success("Dodali ste " + this.addItemsForm.value.drug["name"] + " u narudžbenicu", 'Narudžbenica' );
    this.addItemsForm.reset();
  }
  createPurchaseOrder(){
    this.purchaseorder.orderitems.push(new OrderItem(this.addItemsForm.value.quantity, this.addItemsForm.value.drug));
    this.toastr.success("Dodali ste " + this.addItemsForm.value.drug["name"] + " u narudžbenicu", 'Narudžbenica' );
    
    this.purchaseOrderService.createPurchaseOrder(this.purchaseorder).subscribe(
      (data) => {
        this.addItemsForm.reset();
        this.dialogRef.close();
        this.toastr.success('Uspešno Ste kreirali narudzbenicu.', 'Kreiranje narudzbenice');
        this.purchaseOrderService.createSuccessEmitter.next(this.purchaseorder);
      },
      () => {
        this.toastr.error("Greška pri kreiranju narudžbenice","Kreiranje narudžbenice");
      }
    )
    
    
  }
}
