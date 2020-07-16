import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../_services/auth/auth.service';
import {User} from '../security/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  displayRegistrationDialog: any;

  @Output()
  closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();

  registrationForm: FormGroup;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  userToRegister: User;
  private displayAlert: boolean;
  blockUI: any;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService) {
  }

  show() {
    this.displayRegistrationDialog = true;
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
      sex: new FormControl({value: null, disabled: false}, Validators.required),
      academicDegree: new FormControl({value: null, disabled: false}, Validators.required)
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
    this.blockUI = true;
    this.collectUserData();
    this.authService.register(this.userToRegister).subscribe(
      data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.reloadPage();

      },
      err => {
        this.displayAlert = true;
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
    this.blockUI = false;
  }

  private collectUserData() {
    this.userToRegister = new User();
    this.userToRegister.firstName = this.registrationForm.get('firstName').value;
    this.userToRegister.lastName = this.registrationForm.get('lastName').value;
    this.userToRegister.password = this.registrationForm.get('password').value;
    this.userToRegister.email = this.registrationForm.get('username').value;
    this.userToRegister.username = this.registrationForm.get('username').value;
    this.userToRegister.role = ['ROLE_USER', 'ROLE_ADMIN'];
  }
}
