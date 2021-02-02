import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class RoleGuard implements CanActivate {

    constructor(private router: Router,
                private toastr: ToastrService) {}

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const expectedRoles: string = route.data.expectedRoles;
        const token = localStorage.getItem('jwtToken');
        const jwt: JwtHelperService = new JwtHelperService();

        if (!token) {
            this.router.navigate(['prijava-registracija/prijava']);
            this.toastr.error('401 Неовлашћен приступ');
            return false;
        }

        const info = jwt.decodeToken(token);
        const roles: string[] = expectedRoles.split('|', 2);

        if (roles.indexOf(info.uloga) === -1) {
            if (info.uloga === 'ROLE_GRADJANIN') {
                this.router.navigate(['/zahtevi']);
            } else {
                this.router.navigate(['/pristigli-zahtevi']);
            }
            this.toastr.error('401 Неовлашћен приступ');
            return false;
        }
        return true;
    }

}
