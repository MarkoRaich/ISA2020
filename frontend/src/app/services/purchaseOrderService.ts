import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { PurchaseOrder } from "../models/purchaseOrder";

@Injectable({
    providedIn: 'root'
})
export class PurchaseOrderService {
   
 
    url = environment.baseUrl + environment.purchaseOrder;

    purchaseOrdersForAdmin:  BehaviorSubject<PurchaseOrder[]> = new BehaviorSubject<PurchaseOrder[]>([]);
    createSuccessEmitter = new Subject<PurchaseOrder>();
 
    constructor(private http: HttpClient, private router: Router) {}

    
     getPurchaseordersForAdmin() {

        this.http.get(this.url + "/all").subscribe(
            (data : PurchaseOrder[]) => {
                this.purchaseOrdersForAdmin.next(data);
            },
            (error: HttpErrorResponse) => {}
          );
        return this.purchaseOrdersForAdmin.asObservable();

    }
 
    createPurchaseOrder(purchaseOrder: PurchaseOrder) {
        return this.http.post(this.url, purchaseOrder);
     }
 
}