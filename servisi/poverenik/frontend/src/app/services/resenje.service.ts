import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { ResenjeDto } from "../model/resenje-dto.model";
import { ZahtevDto } from "../model/zahtev-dto.model";
import { ZalbaDto } from "../model/zalba-dto.model";
import { AuthenticationService } from "./authentication.service";

@Injectable({
    providedIn: 'root'
})
export class ResenjeService {

  public novo_resenje: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  public odabraniZahtev: BehaviorSubject<ZahtevDto> = new BehaviorSubject<ZahtevDto>(null);
  public odabranaZalba: BehaviorSubject<ZalbaDto> = new BehaviorSubject<ZalbaDto>(null);

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(
    private http: HttpClient,
    private authService: AuthenticationService
  ) { }

  createResenje(resenjeDto: ResenjeDto) {
      return this.http.post(environment.apiEndpoint + 'resenje?'+
      'zahtevId='+resenjeDto.zahtevId+'&zalbaId='+resenjeDto.zalbaId+
      '&userEmail='+resenjeDto.userEmail, resenjeDto.sadrzaj, {
        responseType: 'text',
        headers: this.headers
      })
  }

  getAllResenja(): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'resenje', {
      responseType: 'text',
      headers: this.headers
    })
  }

  getGradjaninResenja(): Observable<any> {
    let email = this.authService.getLoggedInUserEmail();
    return this.http.get(environment.apiEndpoint + 'resenje/korisnik?userEmail=' + email, {
      responseType: 'text',
      headers: this.headers
    })
  }

  generisiPDF(resenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'resenje/generisiPDF/' + resenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }

  generisiHTML(resenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'resenje/generisiHTML/' + resenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }

  //Pretrage
  obicnaPretraga(sve: string): Observable<any> {
    return this.http.get(environment.apiEndpoint + 'resenje/pretrazi?sadrzaj=' + sve, {
      responseType: 'text',
      headers: this.headers
    })
  }
  generisiRDF(resenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'resenje/generisiRDF/' + resenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }

  naprednaPretraga(xmlDocument: string) {
    return this.http.post(environment.apiEndpoint + 'resenje/pretrazi-napredno', xmlDocument, {
      responseType: 'text',
      headers: this.headers
    })
  }

  generisiJSON(resenjeId: string) {
    return this.http.get(environment.apiEndpoint + 'resenje/generisiJSON/' + resenjeId, {
      responseType: 'arraybuffer',
      headers: this.headers
    })
  }
}