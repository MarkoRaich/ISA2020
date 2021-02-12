export class PatientInfo {
  id: number;
  username: String;
  password: String;
  firstName: String;
  lastName: String;
  phoneNumber: String;
  address: String;
  city: String;
  points: number;
  penalties: number;

  constructor(id: number, username: String, password: String, firstName: String, lastName: String, phoneNumber: String, address: String, city: String,
        points: number, penalties: number) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.city = city;
    this.points = points;
    this.penalties = penalties;

  }
}
