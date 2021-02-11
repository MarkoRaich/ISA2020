export class ExaminationDerm {
  examinationId: number;
  examinationName: String;
  dermatologistName: String;
  price: number;
  startDateTime: String;
  endDateTime: String;
  dermatologistRating: number;
  status: String;

  constructor(examinationId: number, examinationName: String, dermatologistName: String, price: number,
              startDateTime: String, endDateTime: String, dermatologistRating: number, status: String ) {
    this.examinationId = examinationId;
    this.examinationName = examinationName;
    this.dermatologistName = dermatologistName;
    this.price = price;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.dermatologistRating = dermatologistRating;
    this.status = status;
  }


}
