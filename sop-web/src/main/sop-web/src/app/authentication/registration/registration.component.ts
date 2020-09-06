import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../security/user';
import {AuthService} from '../../_services/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public registrationForm: FormGroup;
  public displayRegistrationDialog = false;
  public isSuccessful = false;
  public isSignUpFailed = false;
  public errorMessage = '';
  public blockUI: any;

  private userToRegister: User;
  private displayAlert: boolean;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }

  public show(): void {
    this.displayRegistrationDialog = true;
  }

  public ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      token: new FormControl({value: null, disabled: false}),
      username: new FormControl({value: null, disabled: false}, Validators.required),
      firstName: new FormControl({value: null, disabled: false}, Validators.required),
      lastName: new FormControl({value: null, disabled: false}, Validators.required),
      password: new FormControl({value: null, disabled: false}, Validators.required),
      repeatedPassword: new FormControl({value: null, disabled: false}, Validators.required),
      birthDate: new FormControl({value: null, disabled: false}),
      sex: new FormControl({value: null, disabled: false}, Validators.required),
      academicDegree: new FormControl({value: null, disabled: false}, Validators.required),
      phone: new FormControl({value: null, disabled: false}, Validators.required),
      // email: new FormControl({
      //   value: null,
      //   disabled: false
      // }, Validators.compose([Validators.required, Validators.email])),
    });
  }

  public onSubmit(): void {
    this.blockUI = true;
    this.collectUserData();
    this.authService.register(this.userToRegister).subscribe(
      data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.blockUI = false;
        this.displayRegistrationDialog = false;
        this.router.navigate(['registered-successfully']);

      },
      err => {
        this.displayAlert = true;
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  private collectUserData(): void {
    this.userToRegister = new User();
    this.userToRegister.firstName = this.registrationForm.get('firstName').value;
    this.userToRegister.lastName = this.registrationForm.get('lastName').value;
    this.userToRegister.password = this.registrationForm.get('password').value;
    this.userToRegister.email = this.registrationForm.get('username').value;
    this.userToRegister.username = this.registrationForm.get('username').value;
    this.userToRegister.role = ['ROLE_USER', 'ROLE_ADMIN'];
  }

  public passwordMatchValidator(g: FormGroup) {
    return this.registrationForm.get('password').value === g.get('repeatedPassword').value ? null : {'mismatch': true};
  }
}
