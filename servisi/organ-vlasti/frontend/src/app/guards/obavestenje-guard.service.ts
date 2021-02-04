import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { ObavestenjeService } from "../services/obavestenje.service";

@Injectable({
    providedIn: 'root'
})
export class ObavestenjeGuard implements CanActivate {

    constructor(
        private router: Router,
        private toastr: ToastrService,
        private obavestenjeService: ObavestenjeService
        ) { }
    
    canActivate(): boolean {
        if(this.obavestenjeService.novo_obavestenje.value === false) {
            this.router.navigate(['pristigli-zahtevi']);
            this.toastr.error('401 Неовлашћен приступ');
            return false;
        }

        return true;
    }

}