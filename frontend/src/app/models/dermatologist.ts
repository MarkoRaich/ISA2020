import { Time } from "@angular/common";

export class Dermatologist {
    id: number;
    email: String;
    firstName: String;
    lastName: String;
    phoneNumber: String;
    workHoursFrom: Time;
    workHoursTo: Time;
    rating: number;

    constructor(email : String, firstName : String, lastName: String, 
                phoneNumber: String, workHoursFrom : Time, workHoursTo: Time, id?: number, rating?: number){
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.workHoursFrom = workHoursFrom;
            this.workHoursTo = workHoursTo;
            this.id = id;
            this.rating = rating;
        }
}