import { DateTime } from 'luxon';

export class DateTimeInterval {
    
    id: number;
    startDateTime: DateTime;
    endDateTime: DateTime;
    
    constructor(startDateTime: DateTime, endDateTime: DateTime, id?: number) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}