import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ObavestenjeService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(
    private http: HttpClient
  ) { }

  getAllObavestenja(): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'obavestenje', {
      responseType: 'text',
      headers: this.headers
    });
  }
}
