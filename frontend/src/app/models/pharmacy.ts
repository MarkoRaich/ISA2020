export class Pharmacy {

    id: number;
    name: string;
    address: string;
    description: string;
    rating: number;

    constructor(name: string, address: string, description: string, id?: number, clinicRating?: number) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.id = id;
        this.rating = clinicRating;
    }


}
