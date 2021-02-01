import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(
    private http: HttpClient
  ) { }

  getAllZahtevi(): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'zahtev', {
      responseType: 'text',
      headers: this.headers
    });
  }
}
