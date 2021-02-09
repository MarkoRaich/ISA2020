import { DateTime } from 'luxon';

export class DateTimeInterval {
    
    id: number;
    startDateTime: DateTime;
    endDateTime: DateTime;
    
    constructor(id: number, startDateTime: DateTime, endDateTime: DateTime) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}