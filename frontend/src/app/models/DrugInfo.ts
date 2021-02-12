export class DrugInfo {
  id: number;
  name: String;
  code: String;
  manufacturer: String;
  composition: String;
  notes: String;
  form: String;
  type: String;
  prescription: String;

  constructor(  id: number, name: String, code: String, manufacturer: String, composition: String, notes: String, form: String, type: String, prescription: String) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.manufacturer = manufacturer;
    this.composition = composition;
    this.notes = notes;
    this.form = form;
    this.type = type;
    this.prescription = prescription;
  }

}
