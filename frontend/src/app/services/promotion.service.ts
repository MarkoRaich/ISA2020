import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "src/environments/environment";
import { Promotion } from "../models/promotion";

@Injectable({
    providedIn: 'root'
})
export class PromotionService {
 
    url = environment.baseUrl + environment.promotion;
 
    constructor(private http: HttpClient, private router: Router) {}
    
 
    createPromotion(promotion: Promotion) {
        return this.http.post(this.url, promotion);
     }
 
}