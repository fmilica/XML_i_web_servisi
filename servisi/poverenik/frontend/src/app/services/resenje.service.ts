import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { environment } from "src/environments/environment";
import { ZahtevDto } from "../model/zahtev-dto.model";
import { ZalbaDto } from "../model/zalba-dto.model";

@Injectable({
    providedIn: 'root'
  })
  export class ResenjeService {
  

    zahtevDto: ZahtevDto = {
        id: "1",
        datumPodnosenja: "2020-05-08"
    }

    zalbaDto: ZalbaDto = {
        id: "1",
        datumPodnosenja: "2020-06-09",
        datumProsledjivanja: "2020-06-15"
    }

    public odabraniZahtev: BehaviorSubject<ZahtevDto> = new BehaviorSubject<ZahtevDto>(this.zahtevDto);
    public odabranaZalba: BehaviorSubject<ZalbaDto> = new BehaviorSubject<ZalbaDto>(this.zalbaDto);
  
    private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  
    constructor(
      private http: HttpClient
    ) { }

    createResenje(xmlDocument: string, zahtevId: string, zalbaId: string, email: String) {
        return this.http.post(environment.apiEndpoint + 'resenje?zahtevId=' + zahtevId + '&zalbaId=' + zalbaId + '&userEmail=' + email, xmlDocument, {
          responseType: 'text',
          headers: this.headers
        })
    }
}