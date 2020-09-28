import {
  Component,
  ComponentFactoryResolver,
  Input,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../_services/auth/auth.service';
import {TokenStorageService} from '../_services/auth/token-storage.service';
import {User} from '../security/user';
import {CollegeRegistrationComponent} from '../authentication/college-registration/college-registration.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  @Input()
  public userDetails: User;

  @ViewChild('collegeRegistrationForm', {read: ViewContainerRef, static: false}) entry: ViewContainerRef;
  public componentRef: any;

  public loginForm: FormGroup;
  public isLoggedIn = false;
  public isLoginFailed = false;
  public errorMessage = '';
  public roles: string[] = [];


  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder,
              private resolver: ComponentFactoryResolver) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      console.log(this.roles);

    }
    this.prepareForm();
  }

  public onSubmit(): void {
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

  public reloadPage(): void {
    window.location.reload();
  }


  private prepareForm(): void {
    this.loginForm = this.formBuilder.group({
      username: new FormControl({
        value: null,
        disabled: false
      }, Validators.compose([Validators.required, Validators.minLength(6)])),
      password: new FormControl({value: null, disabled: false}, Validators.required)
    });
  }

  public showRegisterCollegeForm(): void {
    if (!this.isLoggedIn) {
      this.entry.clear();
      const factory = this.resolver.resolveComponentFactory(CollegeRegistrationComponent);
      this.componentRef = this.entry.createComponent(factory);
      this.componentRef.instance.show();
      this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(data => {
        this.refresh();
      });
    }
  }
  public refresh(): void {
    window.location.reload();
  }
}
