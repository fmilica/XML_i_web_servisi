import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass'],
})
export class HeaderComponent implements OnInit {
  constructor(private router: Router) {}

  activeLink = '';
  role = 'ROLE_POVERENIK';

  ngOnInit(): void {}

  onClick(path: string): void {
    this.activeLink = path;
    this.router.navigate([path]);
  }
}
