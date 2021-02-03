import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'Портал повереник';

  constructor(
    private authService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.authService.autoLogin()
  }

}
