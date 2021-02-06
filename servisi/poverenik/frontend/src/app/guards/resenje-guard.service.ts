import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { ResenjeService } from "../services/resenje.service";

@Injectable({
    providedIn: 'root'
})
export class ObavestenjeGuard implements CanActivate {

    constructor(
        private router: Router,
        private toastr: ToastrService,
        private resenjeService: ResenjeService
        ) { }
    
    canActivate(): boolean {
        if(this.resenjeService.novo_resenje.value === false) {
            this.router.navigate(['resenja']);
            this.toastr.error('401 Неовлашћен приступ');
            return false;
        }

        return true;
    }

}