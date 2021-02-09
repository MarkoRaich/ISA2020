import { DateTimeInterval } from "./dateTimeInterval";
import { ExaminationType } from "./examinationType";

export class Examination {

      id : number;
      dermFirstName : String;
      dermLastName : String;
      interval : DateTimeInterval;
      type : ExaminationType;

      constructor( id : number, dermFirstName : String, dermLastName : String,
                   interval : DateTimeInterval, type : ExaminationType) {
                      this.id = id;
                      this.dermFirstName = dermFirstName;
                      this.dermLastName = dermLastName;
                      this.interval = interval;
                      this.type = type;
                  }



}