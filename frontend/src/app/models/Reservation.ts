export class Reservation {
  id: number;
  pharmacyName: String;
  drugName: String;
  drugCode: String;
  generatedKey: String;
  quantity: number;
  status: String;

  constructor(id: number, pharmacyName: String, drugName: String, drugCode: String, generatedKey: String, quantity: number, status: String) {
    this.id = id;
    this.pharmacyName = pharmacyName;
    this.drugName = drugName;
    this.drugCode = drugCode;
    this.generatedKey = generatedKey;
    this.quantity = quantity;
    this.status = status;
  }
}
