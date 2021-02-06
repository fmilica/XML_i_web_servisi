import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root',
  })
  export class AuthenticationService {
  
    constructor(
      private http: HttpClient,
      private router: Router
    ) { 
      this.jwtService = new JwtHelperService();
    }

    public role: BehaviorSubject<string> = new BehaviorSubject<string>('');

    private jwtService: JwtHelperService;

    private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
    
    register(userRegisterXml: string): Observable<any> {
      return this.http.post(environment.apiEndpoint + 'korisnik/registracija', userRegisterXml, {
        responseType: 'text',
        headers: this.headers,
        observe: 'response'
      });
    }
    login(userLoginXml: string): Observable<any> {
      return this.http.post(environment.apiEndpoint + 'korisnik/prijava', userLoginXml, {
        responseType: 'text',
        headers: this.headers,
        observe: 'response',
      });
    }

    
  autoLogin(): boolean {
    const user = this.getLoggedInUser();
    // provera postojanja tokena
    if (!user) {
      return true;
    }
    // provera validnosti tokena
    if (user.exp * 1000 < Date.now()) {
      // token vise nije validan
      localStorage.removeItem('jwtToken');
      localStorage.removeItem('expiresIn');
      this.router.navigate(['login-register/login']);
      return false;
    }
    // token je validan, prosledimo rolu kao sledecu vrednost observable
    const role = this.getLoggedInUserAuthority();
    this.role.next(role);
    // pokrenemo refresh
    return true;
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
  
    // sadrzaj jwt tokena moze biti bilo sta
  getLoggedInUser(): any {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
      return '';
    }
    const info = this.jwtService.decodeToken(token);
    return info;
  }

  getLoggedInUserAuthority(): string {
    const info = this.getLoggedInUser();
    if (info) {
      return info.uloga;
    }
    else {
      return '';
    }
  }

  getLoggedInUserEmail(): string {
    const info = this.getLoggedInUser();
    if (info) {
      return info.sub;
    }
    else {
      return '';
    }
  }

  logout() {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('expiresIn');
    this.router.navigate(['prijava-registracija/prijava']);
    this.role.next('');
  }
}