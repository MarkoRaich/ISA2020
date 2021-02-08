import { Pharmacy } from "./Pharmacy";

export class DrugWithQuantity {
    
    id: number;
    name: string;
    code: string;
    quantity: number;
    pharmacy: Pharmacy

    constructor( name: string, code: string, quantity: number, pharmacy?: Pharmacy, id?: number ){
        this.name=name;
        this.code=code;
        this.quantity=quantity;
        this.pharmacy=pharmacy;
        this.id = id;
    }
}