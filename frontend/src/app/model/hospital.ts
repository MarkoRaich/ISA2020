export class Hospital {
    id: number;
    name: string;
    city: string;
    address: string;
    apiKey: string;



    constructor($name: string, $city: string, $address: string, $apiKey?: string, $id?: number){
        this.name =$name;
        this.city = $city;
        this.address = $address;
        this.apiKey = $apiKey;
        this.id = $id;
    }
}