import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserLogin } from '../model/user-login.model';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private http: HttpClient, private router: Router) {}

  private headers = new HttpHeaders({
    'Content-Type': 'application/xml',
  });

  login(userLoginDto: UserLogin): Observable<HttpResponse<void>> {
    let xml =
      '<?xml version="1.0" encoding="UTF-8"?>\
    <kor:Korisnik xmlns:kor="http://korisnik" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://korisnik schema/korisnik/korisnik.xsd ">\
      <kor:korisnicko_ime>osvezenje@email.com</kor:korisnicko_ime>\
      <kor:lozinka>sifra</kor:lozinka>\
      <kor:ime>Bata</kor:ime>\
      <kor:prezime>Kunra</kor:prezime>\
    </kor:Korisnik>';
    return this.http.post<void>('http://localhost:8081/prijava', xml, {
      headers: this.headers,
      observe: 'response',
    });
  }
}
