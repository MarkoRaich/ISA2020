import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DrugInfo } from 'src/app/models/DrugInfo';
import { OrderItem } from 'src/app/models/orderItem';
import { PurchaseOrder } from 'src/app/models/purchaseOrder';
import { DrugService } from 'src/app/services/drug.service';
import { PurchaseOrderService } from 'src/app/services/purchaseOrderService';

@Component({
  selector: 'app-add-order-item',
  templateUrl: './add-order-item.component.html',
  styleUrls: ['./add-order-item.component.css']
})
export class AddOrderItemComponent implements OnInit {

  addItemForm: FormGroup;
  
  order: PurchaseOrder;
  orderItem: OrderItem;
  drugs: DrugInfo[] = [];

  constructor(private router: Router,
              private toastr: ToastrService,
              private drugService: DrugService,
              private purchaseOrderService : PurchaseOrderService,
              private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<AddOrderItemComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any ) { }

  ngOnInit(): void {

    this.addItemForm = this.formBuilder.group({
      drug: new FormControl(null, [Validators.required]),
      quantity: new FormControl(null, [Validators.required, Validators.min(1)])
    });
  
    this.getDrugs();

    this.order = this.data.order;

  }

  getDrugs(){
    this.drugService.getAllDrugs().subscribe(
      (data: DrugInfo[]) => {
        this.drugs = data;
      });
  }

  add(){
    
    this.orderItem = new OrderItem(this.addItemForm.value.quantity, this.addItemForm.value.drug);
    this.order.orderitems.push(this.orderItem);
    this.dialogRef.close();
    this.purchaseOrderService.createItemSuccesEmitter.next(this.orderItem);                                         
    this.toastr.success('Uspešno Ste kreirali stavku narudzbenice.', 'Kreiranje stavke narudzbenice');                                      
                                        
  }
}
