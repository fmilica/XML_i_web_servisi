import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ZalbaOdlukaService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(
    private http: HttpClient,
    private authService: AuthenticationService
  ) { }

  getAllZalbeOdluka(): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'zalba-odbijanje', {
      responseType: 'text',
      headers: this.headers
    })
  }

  getAllGradjaninZalbeOdluka(): Observable<any> {
    let email = this.authService.getLoggedInUserEmail();
    return this.http.get(environment.apiEndpoint + 'zalba-odbijanje/korisnik?userEmail=' + email, {
      responseType: 'text',
      headers: this.headers
    })
  }

  createZalbaOdluka(xmlDocument: string, zahtevId: string) {
    let email = this.authService.getLoggedInUserEmail();
    return this.http.post(environment.apiEndpoint + 'zalba-odbijanje?zahtevId=' + zahtevId + '&userEmail=' + email, xmlDocument, {
      responseType: 'text',
      headers: this.headers
    })
  }
}
