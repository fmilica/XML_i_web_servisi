import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
  })
  export class ResenjeService {
  
    private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  
    constructor(
      private http: HttpClient
    ) { }

    getAllResenja(): Observable<any> {
        return this.http.get(environment.apiEndpoint + 'resenje', {
          responseType: 'text',
          headers: this.headers
        })
    }
}