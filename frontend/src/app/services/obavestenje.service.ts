import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ObavestenjeService {
  constructor(private http: HttpClient) {}

  getObavestenje(id: string): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'obavestenje/' + id, {
      responseType: 'text',
    });
  }
}
