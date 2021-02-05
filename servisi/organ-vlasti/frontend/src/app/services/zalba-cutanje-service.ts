import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { AuthenticationService } from "./authentication.service";

@Injectable({
    providedIn: 'root'
  })
  export class ZalbaCutanjeService {
  
    private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  
    constructor(
      private http: HttpClient,
      private authService: AuthenticationService
    ) { }

    getAllZalbeCutanje(): Observable<any> {
        return this.http.get(environment.apiEndpoint + 'zalba-cutanje', {
          responseType: 'text',
          headers: this.headers
        })
    }

    generisiPDF(zalbaCutanjeId: string) {
        return this.http.get(environment.apiEndpoint + 'zalba-cutanje/generisiPDF/' + zalbaCutanjeId, {
            responseType: 'arraybuffer',
            headers: this.headers
        })
    }

    generisiHTML(zalbaCutanjeId: string) {
        return this.http.get(environment.apiEndpoint + 'zalba-cutanje/generisiHTML/' + zalbaCutanjeId, {
            responseType: 'arraybuffer',
            headers: this.headers
        })
    }

    //Pretrage
    obicnaPretraga(sve: string): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'zalba-cutanje/pretrazi?sadrzaj=' + sve, {
        responseType: 'text',
    headers: this.headers
    })
    }
    generisiRDF(zalbaCutanjeId: string) {
    return this.http.get(environment.apiEndpoint + 'zalba-cutanje/generisiRDF/' + zalbaCutanjeId, {
        responseType: 'arraybuffer',
        headers: this.headers
    })
    }

    naprednaPretraga(xmlDocument: string) {
    return this.http.post(environment.apiEndpoint + 'zalba-cutanje/pretrazi-napredno', xmlDocument, {
        responseType: 'text',
        headers: this.headers
    })
    }

    generisiJSON(zalbaCutanjeId: string) {
    return this.http.get(environment.apiEndpoint + 'zalba-cutanje/generisiJSON/' + zalbaCutanjeId, {
        responseType: 'arraybuffer',
        headers: this.headers
    })
    }
}