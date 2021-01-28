import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { UserLoginDto } from "../model/user-login-dto.model";
import { userLoginXml } from "../model/user-login-xml.model";

@Injectable({
    providedIn: 'root',
  })
  export class AuthenticationService {
  
    constructor(
      private http: HttpClient,
      private router: Router
    ) { }

    private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
    
    /*register(authUserDto: AuthenticatedUser): Observable<AuthenticatedUser> {
      return this.http.post<AuthenticatedUser>(environment.apiEndpoint + 'register', authUserDto, {
        headers: this.headers,
      });
    }
  */
    login(userLoginXml: userLoginXml): Observable<any> {
      return this.http.post(environment.apiEndpoint + 'korisnik/prijava', userLoginXml, {
        responseType: 'text',
        headers: this.headers,
        observe: 'response',
      });
    }

    setLoggedInUser(response: any): void {
        // ekstrakcija tokena
        const jwtTokenBearer = response.headers.get('Authorization');
        const jwtToken = jwtTokenBearer.split(' ')[1];
        const expiresIn = response.headers.get('Expires-In');
        // postavljanje tokena
        localStorage.setItem('jwtToken', jwtToken);
        localStorage.setItem('expiresIn', expiresIn);
     }
  }