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

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  login(userLoginDto: UserLogin): Observable<HttpResponse<void>> {
    return this.http.post<void>('https://localhost:8081/login', userLoginDto, {
      headers: this.headers,
      observe: 'response',
    });
  }
}
