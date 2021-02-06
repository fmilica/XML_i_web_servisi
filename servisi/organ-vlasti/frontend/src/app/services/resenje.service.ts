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

    generisiPDF(resenjeId: string) {
      return this.http.get(environment.apiEndpoint + 'resenje/generisiPDF/' + resenjeId, {
        responseType: 'arraybuffer',
        headers: this.headers
      })
    }
  
    generisiHTML(resenjeId: string) {
      return this.http.get(environment.apiEndpoint + 'resenje/generisiHTML/' + resenjeId, {
        responseType: 'arraybuffer',
        headers: this.headers
      })
    }
  
    //Pretrage
    obicnaPretraga(sve: string): Observable<any> {
      return this.http.get(environment.apiEndpoint + 'resenje/pretrazi?sadrzaj=' + sve, {
        responseType: 'text',
        headers: this.headers
      })
    }
    generisiRDF(resenjeId: string) {
      return this.http.get(environment.apiEndpoint + 'resenje/generisiRDF/' + resenjeId, {
        responseType: 'arraybuffer',
        headers: this.headers
      })
    }
  
    naprednaPretraga(xmlDocument: string) {
      return this.http.post(environment.apiEndpoint + 'resenje/pretrazi-napredno', xmlDocument, {
        responseType: 'text',
        headers: this.headers
      })
    }
  
    generisiJSON(resenjeId: string) {
      return this.http.get(environment.apiEndpoint + 'resenje/generisiJSON/' + resenjeId, {
        responseType: 'arraybuffer',
        headers: this.headers
      })
    }
}