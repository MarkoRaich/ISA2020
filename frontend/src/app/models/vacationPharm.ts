import { DateTimeInterval } from "./dateTimeInterval";
import { Pharmacist } from "./pharmacist";

export class VacationPharm {
    id:number;
    interval:DateTimeInterval;
    status: string;
    pharmacist: Pharmacist;

    constructor(id: number, interval: DateTimeInterval, status: string, pharmacist: Pharmacist){
        this.id=id;
        this.interval=interval;
        this.status=status;
        this.pharmacist=pharmacist;
    }
}