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

  getZahtevById(zahtevId: string): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'zahtev/preuzmi/' + zahtevId, {
      responseType: 'text',
      headers: this.headers
    });
  }
}
