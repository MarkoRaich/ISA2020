export class Patient {
  id: number;
  username: String;
  firstName: String;
  lastName: String;
  phoneNumber: String;
  address: String;

  constructor(id: number, username: String, firstName: String, lastName: String, phoneNumber: String, address: String) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;

  }
}
