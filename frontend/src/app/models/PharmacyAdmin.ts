export class PharmacyAdmin {
    id: number;
    email: String;
    firstName: String;
    lastName: String;
    phoneNumber: String;

    constructor(id: number, email: String, firstName: String, lastName: String, phoneNumber: String, ) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        
    }
}