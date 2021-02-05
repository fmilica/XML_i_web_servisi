import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ObavestenjeService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  public novo_obavestenje: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(
    private http: HttpClient,
    private authService: AuthenticationService
  ) { }

  getAllObavestenja(): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'obavestenje', {
      responseType: 'text',
      headers: this.headers
    });
  }
  
  getByZahtevId(zahtevId): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'obavestenje/postoji/' + zahtevId, {
      responseType: 'text',
      headers: this.headers
    });
  }

  getAllGradjaninObavestenja(): Observable<any> {
    let email =  this.authService.getLoggedInUserEmail();
    return this.http.get(environment.apiEndpoint + 'obavestenje/korisnik?userEmail=' + email, {
      responseType: 'text',
      headers: this.headers
    });
  }

  createObavestenje(xmlDocument: string, id: string, email: string) {
    return this.http.post(environment.apiEndpoint + 'obavestenje?zahtevId=' + id +'&userEmail=' + email, xmlDocument, {
      responseType: 'text',
      headers: this.headers
    })
  }

  generisiPDF(obavestenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'obavestenje/generisiPDF/' + obavestenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }

  generisiHTML(obavestenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'obavestenje/generisiHTML/' + obavestenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }

  //Pretrage
  obicnaPretraga(sve: string): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'obavestenje/pretrazi?sadrzaj=' + sve, {
      responseType: 'text',
      headers: this.headers
    })
  }

  generisiRDF(obavestenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'obavestenje/generisiRDF/' + obavestenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }

  naprednaPretraga(xmlDocument: string) {
    return this.http.post(environment.apiEndpoint + 'obavestenje/pretrazi-napredno', xmlDocument, {
      responseType: 'text',
      headers: this.headers
    })
  }

  generisiJSON(obavestenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'obavestenje/generisiJSON/' + obavestenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }
}
