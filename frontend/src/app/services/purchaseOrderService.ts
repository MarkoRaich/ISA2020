import { HttpClient, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Subject } from "rxjs";
import { environment } from "src/environments/environment";
import { Offer } from "../models/offer";
import { OrderItem } from "../models/orderItem";
import { PurchaseOrder } from "../models/purchaseOrder";

@Injectable({
    providedIn: 'root'
})
export class PurchaseOrderService {
 
 
    url = environment.baseUrl + environment.purchaseOrder;

    purchaseOrdersForAdmin:  BehaviorSubject<PurchaseOrder[]> = new BehaviorSubject<PurchaseOrder[]>([]);
    createSuccessEmitter = new Subject<PurchaseOrder>();
    changeOrderStatusEmitter = new Subject<PurchaseOrder>();

    createItemSuccesEmitter = new Subject<OrderItem>();
 
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

     deleteOrder(id: number): any {
        return this.http.delete(this.url + '/' + id); 
      }
 
     acceptOfferForOrder(orderId: string, offerId: string) {

        let params = new HttpParams();
        params = params.append('orderId', orderId);
        params = params.append('offerId', offerId);
        
        return this.http.get(this.url + "/accept-offer", { params: params });
      }
}