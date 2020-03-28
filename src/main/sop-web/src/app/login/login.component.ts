import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  loginForm: FormGroup;
  isLoggedIn: Boolean = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit() {
    this.authService.login(this.loginForm).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

  // constructor(private fb: FormBuilder,
  //             private authService: AuthService,
  //            private tokenStorage: TokenStorageService) {
  // this.loginForm = this.fb.group({
  //   userType: new FormControl({value: null, disabled: false}, Validators.required),
  //   userName: new FormControl({value: null, disabled: false}, Validators.required),
  //   password: new FormControl({value: null, disabled: false}, Validators.required),
  //   rememberMe: new FormControl({value: null, disabled: false}, Validators.required),
  //   // firstName: new FormControl({value: null, disabled: false}, Validators.required),
  //   // lastName: new FormControl({value: null, disabled: false}, Validators.required),
  //   //
  //   //
  //   // phone: new FormControl({value: null, disabled: false}, Validators.required),
  //   // email: new FormControl({
  //   //   value: null,
  //   //   disabled: false
  //   // }, Validators.compose([Validators.required, Validators.email])),
  //   // birthDate: new FormControl({value: null, disabled: false}, Validators.required),
  //   // genderId: new FormControl({value: null, disabled: false}, Validators.required),
  // });
  // }
}
