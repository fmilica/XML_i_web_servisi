import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { PismoDTO } from "../model/pismo-dto.model";

@Injectable({
    providedIn: 'root',
})
export class EpostaService{
    constructor(private http: HttpClient){}
    private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

    posalji(pismo: string): Observable<any> {
        return this.http.post('http://localhost:8081/poverenik/eposta/send-email', pismo, {
          headers: this.headers,
          responseType: 'text',
        });
      }
}