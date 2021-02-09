export class ExaminationType {
    
    id: number;
    name: String;
    description: String;
    price: number;
    
    constructor(name: String, description: String, price: number, id?: number) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }
}