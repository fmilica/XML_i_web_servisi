import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { ResenjeDto } from "../model/resenje-dto.model";
import { ZahtevDto } from "../model/zahtev-dto.model";
import { ZalbaDto } from "../model/zalba-dto.model";

@Injectable({
    providedIn: 'root'
  })
  export class ResenjeService {
  

    zahtevDto: ZahtevDto = {
        id: "1",
        datumPodnosenja: "2020-05-08",
        userEmail: "pera@pera.com"
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
}