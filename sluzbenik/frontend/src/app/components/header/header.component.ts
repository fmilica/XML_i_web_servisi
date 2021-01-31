import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass'],
})
export class HeaderComponent implements OnInit {
  constructor(
    private router: Router
    ) {}

  activeLink = '';
  role = 'ROLE_GRADJANIN';

  ngOnInit(): void {
    // preuzimanje trenutne rute
    this.router.events.subscribe((val) => {
      if ( val instanceof NavigationEnd) {
        const routePaths = this.router.url.split('/');
        this.activeLink = routePaths[routePaths.length - 1];
      }
    })
  }

  onClick(path: string): void {
    this.activeLink = path;
    this.router.navigate([path]);
  }
}
