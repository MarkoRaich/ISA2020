import { DateTimeInterval } from "./dateTimeInterval";

export class Promotion {

    id: number;
    content: String;
    startDateTime: string;
    endDateTime: string;
    pharmacyId: number;

    constructor(content: String, startDateTime: string,endDateTime: string, pharmId?: number, id?: number) {
        this.content=content;
        this.startDateTime=startDateTime;
        this.endDateTime=endDateTime;
        this.pharmacyId=pharmId;
        this.id=id;
    }
}