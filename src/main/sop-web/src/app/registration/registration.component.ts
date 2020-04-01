import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../_services/auth.service';
import { User } from '../security/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  userToRegister: User;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService) {
  }

  ngOnInit() {

    this.registrationForm = this.formBuilder.group({

      token: new FormControl({value: null, disabled: false}),
      username: new FormControl({value: null, disabled: false}, Validators.required),
      firstName: new FormControl({value: null, disabled: false}, Validators.required),
      lastName: new FormControl({value: null, disabled: false}, Validators.required),
      password: new FormControl({value: null, disabled: false}, Validators.required),
      repeatedPassword: new FormControl({value: null, disabled: false}, Validators.required),
      birthDate: new FormControl({value: null, disabled: false}),
      // firstName: new FormControl({value: null, disabled: false}, Validators.required),
      // lastName: new FormControl({value: null, disabled: false}, Validators.required),
      //
      //
      // phone: new FormControl({value: null, disabled: false}, Validators.required),
      // email: new FormControl({
      //   value: null,
      //   disabled: false
      // }, Validators.compose([Validators.required, Validators.email])),
      // birthDate: new FormControl({value: null, disabled: false}, Validators.required),
      // genderId: new FormControl({value: null, disabled: false}, Validators.required),
    });
  }

  onSubmit() {
    this.collectUserData();
    this.authService.register(this.userToRegister).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  private collectUserData() {
    this.userToRegister = new User();
    this.userToRegister.firstName = this.registrationForm.get('firstName').value;
    this.userToRegister.lastName = this.registrationForm.get('lastName').value;
    this.userToRegister.password = this.registrationForm.get('password').value;
    this.userToRegister.email = this.registrationForm.get('username').value;
    this.userToRegister.username = this.registrationForm.get('username').value;
    this.userToRegister.role = ['ROLE_USER','ROLE_ADMIN']
  }
}
