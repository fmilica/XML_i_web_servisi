import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { AuthenticationService } from "./authentication.service";

@Injectable({
    providedIn: 'root'
})
export class IzvestajService {

    private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(
    private http: HttpClient,
    private authService: AuthenticationService
  ) { }

  getAllIzvestaji(): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'izvestaj', {
      responseType: 'text',
      headers: this.headers
    })
  }

  getIzvestaj(id: string): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'izvestaj/' + id, {
      responseType: 'text',
      headers: this.headers
    })
  }

  obicnaPretraga(datum: string): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'izvestaj/pretrazi?sadrzaj=' + datum, {
      responseType: 'text',
      headers: this.headers
    })
  }
}
