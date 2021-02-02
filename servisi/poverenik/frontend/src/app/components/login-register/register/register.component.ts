import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import {
  ConfirmPasswordMatcher,
  confirmPasswordValidator,
} from 'src/app/directives/confirm-password.directive';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { onlyContainsLetters } from 'src/app/util/util';
import { UserRegisterDto } from '../../../model/user-register-dto.model';
import * as JsonToXML from 'js2xmlparser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass'],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;

  hide = true;
  namePattern = '[A-ZŠĐČĆŽ][a-zšđčćž]*';
  confirmPasswordMatcher = new ConfirmPasswordMatcher();

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.registerForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', [Validators.required]),
          confirmPassword: new FormControl('', [Validators.required]),
        },
        { validators: confirmPasswordValidator() }
      ),
      name: new FormControl('', [
        Validators.required,
        Validators.pattern(this.namePattern),
      ]),
      surname: new FormControl('', [
        Validators.required,
        Validators.pattern(this.namePattern),
      ]),
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.registerForm.invalid) {
      return;
    }

    let email = this.registerForm.value.email;
    let password = this.registerForm.value.passwordGroup.password;
    let name = this.registerForm.value.name;
    let surname = this.registerForm.value.surname;

    const userRegisterDto: UserRegisterDto = {
      email: email,
      password: password,
      name: name,
      surname: surname,
    };

    const options = {
      declaration: {
        include: false,
      },
    };

    let userRegisterXml: string = JsonToXML.parse(
      'UserRegisterDto',
      userRegisterDto,
      options
    );

    console.log(userRegisterXml);

    this.authenticationService.register(userRegisterXml).subscribe(
      (response) => {
        this.toastr.success('Успешна регистрација! Можете се пријавити.');
        this.registerForm.reset();
        //this.router.navigate(['login-register/login']);
      },
      (error) => {
        if (error.status === 400) {
          this.toastr.error('Грешка при регистрацији. Корисник са унетом електронском поштом већ постоји.');
        } else {
          this.toastr.error('503 Сервер недоступан');
        }
        this.registerForm.reset();
      }
    );
  }

  getEmailErrorMessage(): string {
    if (this.registerForm.controls.email.touched) {
      if (this.registerForm.controls.email.hasError('required')) {
        return 'Обавезно поље';
      }
      return this.registerForm.controls.email.hasError('email')
        ? 'Невалидна форма електронске поште'
        : '';
    }
    return '';
  }

  getRequiredFieldErrorMessage(fieldName: string): string {
    if (this.registerForm.controls.passwordGroup.get(fieldName)?.touched) {
      return this.registerForm.controls.passwordGroup
        .get(fieldName)
        ?.hasError('required')
        ? 'Обавезно поље'
        : '';
    }
    return '';
  }

  getNameErrorMessage(fieldName: string): string {
    if (this.registerForm.controls[fieldName].touched) {
      if (this.registerForm.controls[fieldName].hasError('pattern')) {
        if (onlyContainsLetters(this.registerForm.controls[fieldName].value)) {
          return 'Унос мора почети великим словом';
        } else {
          return 'Унос не сме садржати специјалне карактере или бројеве';
        }
      }
      return this.registerForm.controls[fieldName].hasError('required')
        ? 'Обавезно поље'
        : '';
    }
    return '';
  }

  getPasswordsMatch(): string {
    if (
      this.registerForm.controls.passwordGroup.get('password')?.touched &&
      this.registerForm.controls.passwordGroup.get('confirmPassword')?.touched
    ) {
      return this.registerForm.controls.passwordGroup.hasError(
        'passwordsDontMatch'
      )
        ? 'Лозинке се не подударају'
        : '';
    }
    return '';
  }
}
