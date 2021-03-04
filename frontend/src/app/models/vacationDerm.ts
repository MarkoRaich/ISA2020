import { DateTimeInterval } from "./dateTimeInterval";
import { Dermatologist } from "./dermatologist";

export class VacationDerm {
    id:number;
    interval:DateTimeInterval;
    status: string;
    dermatologist: Dermatologist;

    constructor(id: number, interval: DateTimeInterval, status: string, dermatologist: Dermatologist){
        this.id=id;
        this.interval=interval;
        this.status=status;
        this.dermatologist=dermatologist;
    }
}