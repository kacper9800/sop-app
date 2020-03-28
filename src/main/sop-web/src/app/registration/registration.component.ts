import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../_services/auth.service';

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

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService) {
  }

  ngOnInit() {

    this.registrationForm = this.formBuilder.group({

      token: new FormControl({value: null, disabled: false}, Validators.required),
      firstName: new FormControl({value: null, disabled: false}, Validators.required),
      lastName: new FormControl({value: null, disabled: false}, Validators.required),
      password: new FormControl({value: null, disabled: false}, Validators.required),
      email: new FormControl({value: null, disabled: false}, Validators.required),
      repeatedPassword: new FormControl({value: null, disabled: false}, Validators.required),
      birthDate: new FormControl({value: null, disabled: false}, Validators.required),
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
    this.authService.register(this.registrationForm).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
