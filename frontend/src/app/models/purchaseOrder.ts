import { OrderItem } from "./orderItem";
import { Pharmacy } from "./Pharmacy";

export class PurchaseOrder {

    id: number;
    status: String;
    pharmacy : Pharmacy;
    orderitems: OrderItem[];
    deadline: String;

    constructor( status: String, orderitems: OrderItem[], deadline: String, pharmacy?: Pharmacy, id?: number){
       
        this.status=status;
        this.pharmacy=pharmacy;
        this.orderitems=orderitems;
        this.deadline=deadline;
        this.id=id;
    }
}