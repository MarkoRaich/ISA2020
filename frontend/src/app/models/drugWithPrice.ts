import { DateTimeInterval } from "./dateTimeInterval";
import { DrugInfo } from "./DrugInfo";
import { Pharmacy } from "./Pharmacy";

export class DrugWithPrice {
    id: number;
    price: number;
    startDate: string;
    endDate: string;
    drug: DrugInfo;
    pharmacy: Pharmacy;

    constructor(price: number, drug: DrugInfo, pharmacy: Pharmacy, startDate: string, endDate: string, id?: number) {
        this.id=id;
        this.price=price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.drug=drug;
        this.pharmacy=pharmacy;
    }

}