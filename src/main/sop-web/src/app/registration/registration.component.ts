import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private fb: FormBuilder) {
  }

  registrationForm: FormGroup

  ngOnInit() {

    this.registrationForm = this.fb.group({

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

}
