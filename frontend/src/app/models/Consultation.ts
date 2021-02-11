export class Consultation {
  consultationId: number;
  consultationName: String;
  pharmacyName: String
  price: number;
  pharmacyRating: number;
  startDateTime: String;
  endDateTime: String;
  pharmacyAddress: String;
  status: String;

    constructor(consultationId: number, consultationName: String, pharmacyName: String,
            price: number, pharmacyRating: number, startDateTime: String, endDateTime: String,
            pharmacyAddress: String, status: String) {
         this.consultationId = consultationId;
         this.consultationName = consultationName;
         this.pharmacyName = pharmacyName;
         this.price = price;
         this.pharmacyRating = pharmacyRating;
         this.startDateTime = startDateTime;
         this.endDateTime = endDateTime;
         this.pharmacyAddress = pharmacyAddress;
        this.status = status;
    }
}
