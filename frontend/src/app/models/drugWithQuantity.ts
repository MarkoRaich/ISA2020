import { Pharmacy } from "./Pharmacy";

export class DrugWithQuantity {
    
    id: number;
    name: string;
    code: string;
    quantity: number;
    pharmacy: Pharmacy

    constructor( name: string, code: string, quantity: number, id?: number, pharmacy?: Pharmacy  ){
        this.name=name;
        this.code=code;
        this.quantity=quantity;
        this.id = id;
        this.pharmacy=pharmacy;
    }
}