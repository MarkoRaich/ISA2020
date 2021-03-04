import { Supplier } from "./supplier";

export class Offer {
    id: number;
    offer: number;
    supplierDTO: Supplier;
    deliveryDeadline: String;
    status: String;
    
    constructor(){}
}