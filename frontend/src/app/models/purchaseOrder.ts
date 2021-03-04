import { Offer } from "./offer";
import { OrderItem } from "./orderItem";
import { Pharmacy } from "./Pharmacy";

export class PurchaseOrder {

    id: number;
    status: String;
    pharmacy : Pharmacy;
    orderitems: OrderItem[];
    deadline: string;
    offers: Offer[];

    constructor( status: String, orderitems: OrderItem[], deadline: string, offers: Offer[], pharmacy?: Pharmacy, id?: number){
       
        this.status=status;
        this.pharmacy=pharmacy;
        this.orderitems=orderitems;
        this.offers = offers;
        this.deadline=deadline;
        this.id=id;
    }
}