import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'process';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from './authentication.service';

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
    return this.http.get(environment.apiEndpoint + 'neki url', {
      responseType: 'text',
      headers: this.headers
    })
  }

  getAllGradjaninZalbeCutanje(): Observable<any> {
    let email = this.authService.getLoggedInUserEmail();
    return this.http.get(environment.apiEndpoint + 'neki url' + email, {
      responseType: 'text',
      headers: this.headers
    })
  }

  createZalbaCutanje(xmlDocument: string) {
    let email = this.authService.getLoggedInUserEmail();
    return this.http.post(environment.apiEndpoint + 'neki url' + email, xmlDocument, {
      responseType: 'text',
      headers: this.headers
    })
  }
}
