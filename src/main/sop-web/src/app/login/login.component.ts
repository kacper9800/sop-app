import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder) {
  }

  loginForm: FormGroup

  ngOnInit() {

    this.loginForm = this.fb.group({

      userType: new FormControl({value: null, disabled: false}, Validators.required),
      userName: new FormControl({value: null, disabled: false}, Validators.required),
      password: new FormControl({value: null, disabled: false}, Validators.required),
      rememberMe: new FormControl({value: null, disabled: false}, Validators.required),
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
}
