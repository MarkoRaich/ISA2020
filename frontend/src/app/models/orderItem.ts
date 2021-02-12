import { DrugInfo } from "./DrugInfo";

export class OrderItem {
    id: number;
    quantity: number;
    drug: DrugInfo;


    constructor(quantity: number, drug: DrugInfo, id?: number){
        
        this.quantity=quantity;
        this.drug=drug;
        this.id=id;
    }
}