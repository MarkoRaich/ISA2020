export class PharmacyAdmin {
    id: number;
    email: String;
    firstName: String;
    lastName: String;
    phoneNumber: String;

    constructor(email: String, firstName: String, lastName: String, phoneNumber: String, id: number) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }
}