import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ZahtevDto } from '../model/zahtev-dto.model';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {

  public odabraniZahtev: BehaviorSubject<ZahtevDto> = new BehaviorSubject<ZahtevDto>(null);

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(
    private http: HttpClient,
    private authService: AuthenticationService
  ) { }

  getAllZahtevi(): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'zahtev', {
      responseType: 'text',
      headers: this.headers
    });
  }

  getAllGradjaninZahtevi(): Observable<any> {
    let email =  this.authService.getLoggedInUserEmail();
    return this.http.get(environment.apiEndpoint + 'zahtev/korisnik?userEmail=' + email, {
      responseType: 'text',
      headers: this.headers
    });
  }

  createZahtev(xmlDocument: string) {
    let email =  this.authService.getLoggedInUserEmail();
    return this.http.post(environment.apiEndpoint + 'zahtev?userEmail=' + email, xmlDocument, {
      responseType: 'text',
      headers: this.headers
    })
  }

  resiZahtev(idZahteva: string) {
    return this.http.put(environment.apiEndpoint + 'zahtev/' + idZahteva,{
      responseType: 'text',
      headers: this.headers
    })
  }

}
