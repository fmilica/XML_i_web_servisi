import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
    ) {}

  activeLink = '';
  role = '';
  subscription!: Subscription;

  ngOnInit(): void {
    // preuzimanje trenutne rute
    this.router.events.subscribe((val) => {
      if ( val instanceof NavigationEnd) {
        const routePaths = this.router.url.split('/');
        this.activeLink = routePaths[routePaths.length - 1];
      }
    });
    // subscribe
    this.subscription = this.authenticationService.role
    .subscribe(role => {
      this.role = role;
    });
  }

  onClick(path: string): void {
    this.activeLink = path;
    this.router.navigate([path]);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
