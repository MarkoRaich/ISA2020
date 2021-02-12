export class DrugReservation {
  drugId: number;
  pharmacyId: number;
  pharmacyName: String;
  drugName: String;
  drugCode: String;


  constructor(drugId: number, pharmacyId: number, pharmacyName: String, drugName: String, drugCode: String) {
    this.drugId = drugId;
    this.pharmacyId = pharmacyId;
    this.pharmacyName = pharmacyName;
    this.drugName = drugName;
    this.drugCode = drugCode;
  }

}
