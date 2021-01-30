import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserLoginDto } from '../model/user-login-dto.model';
import * as JsonToXML from 'js2xmlparser';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  hide = true;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    // check if form is valid
    if (!this.loginForm.valid) {
      return;
    }

    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;

    const userLoginDto: UserLoginDto = {
      email: email,
      password: password
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let userLoginXml: string = JsonToXML.parse("KorisnikLoginDto", userLoginDto, options);

    console.log(userLoginXml)

    this.authService.login(userLoginXml)
      .subscribe(
        (response) => {
          this.toastr.success('Logged in successfully!');
          // postavljanje u local storage
          this.authService.setLoggedInUser(response);
          // reset forme
          this.loginForm.reset();
          // preusmerenje
          //this.router.navigate(['homepage']);
          //this.authService.role.next(this.authService.getLoggedInUserAuthority());
        },
        (error) => {
          if (error.status === 401) {
            this.toastr.error('Incorrect email or password.');
          } else {
            this.toastr.error('503 Server Unavailable');
          }
          this.loginForm.reset();
        }
      );
  }

  getEmailErrorMessage(): string {
    if (this.loginForm.controls.email.touched) {
      if ( this.loginForm.controls.email.hasError('required')) {
        return 'Required field';
      }

      return  this.loginForm.controls.email.hasError('email') ? 'Not a valid email' : '';
    }
    return '';
  }

  getRequiredFieldErrorMessage(fieldName: string): string {
    if (this.loginForm.controls[fieldName].touched) {
      return  this.loginForm.controls[fieldName].hasError('required') ? 'Required field' : '';
    }

    return '';
  }

}
