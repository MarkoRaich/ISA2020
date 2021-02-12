export class Complaint {
  subject: String;
  message: String;
  doctorName: String;
  examination: String;

  constructor(subject: String, message: String, doctorName: String, examination: String) {
    this.subject = subject;
    this.message = message;
    this.doctorName = doctorName;
    this.examination = examination;
  }
}
