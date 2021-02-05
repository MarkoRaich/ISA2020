import { Time } from "@angular/common";

export class Pharmacist {
    id: number;
    email: String;
    firstName: String;
    lastName: String;
    phoneNumber: String;
    workHoursFrom: Time;
    workHoursTo: Time;

    constructor(email : String, firstName : String, lastName: String, 
        phoneNumber: String, workHoursFrom : Time, workHoursTo: Time, id: number){
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.workHoursFrom = workHoursFrom;
            this.workHoursTo = workHoursTo;
            this.id = id;
        }
}