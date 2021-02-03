import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';

@Injectable({
    providedIn: 'root'
})
export class LoginGuard implements CanActivate {

    constructor(private router: Router,
                private toastr: ToastrService) {}

    canActivate(): boolean {
        let token = localStorage.getItem('jwtToken')
        if (token) {
            const jwt: JwtHelperService = new JwtHelperService();
            const info = jwt.decodeToken(token);
            if (info.uloga === 'ROLE_GRADJANIN') {
                this.router.navigate(['/zahtevi']);
            } else {
                this.router.navigate(['/pristigli-zahtevi']);
            }
            this.toastr.info('Већ сте пријављени.');
            return false;
        }

        return true;
    }

}
