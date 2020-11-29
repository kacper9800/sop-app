import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {TranslateService} from '@ngx-translate/core';
import {MessageService} from 'primeng/api';
import {ClrLoadingState} from '@clr/angular';
import {AuthService} from '../../_services/auth/auth.service';
import {TokenStorageService} from '../../_services/auth/token-storage.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {

  displayLoginDialog;

  @Output()
  closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  display: boolean;
  rangeDates: Date[];
  other: boolean;
  ranking: number;
  private saved: boolean;


  headerText: string;


  loginForm: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  loginButton: any;
  blockUI: any;
  validateBtnState: any;


  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder,
              private router: Router,
              private translateService: TranslateService,
              private messageService: MessageService) {
    this.prepareForm();
    this.setLanguage();
  }

  public show(): void {
    this.displayLoginDialog = true;
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  private setLanguage(): void {
    this.headerText = this.translateService.instant('login.header');
    this.loginButton = this.translateService.instant('login.loginButton');
  }

  public prepareForm(): void {
    this.loginForm = this.formBuilder.group({
      username: new FormControl({value: null, disabled: false}),
      password: new FormControl({value: null, disabled: false}),
    });
  }


  public onSubmit(): void {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;
    const credentials = [];
    credentials.push(this.loginForm.get('username').value);
    credentials.push(this.loginForm.get('password').value);
    this.authService.login(credentials).subscribe(
      data => {
        console.log('success');
        this.validateBtnState = ClrLoadingState.SUCCESS;
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },

      err => {
        console.log(err);
        if (err.status === 401) {
          this.messageService.add({
            severity: 'error',
            summary: this.translateService.instant('login.loginError'),
            detail: this.translateService.instant('login.loginErrorDetails')
          });
        }
        // this.errorMessage = err.error.message;
        this.validateBtnState = ClrLoadingState.ERROR;
        this.delay(3000);
        this.isLoginFailed = true;
        this.blockUI = false;
      }
    );
  }

  async delay(ms: number) {
    await new Promise(resolve => setTimeout(() => resolve(), ms)).then(() => console.log('fired'));
  }

  public reloadPage(): void {
    this.router.navigate(['app/home'])
    .then(() => {
      window.location.reload();
    });
    this.blockUI = false;
  }

  public onHide(): void {
    if (this.saved) {
      this.closeDialogWithSaveEmitter.emit();
    }
  }
}

//
// public show(id: number) {
//   this.headerText = 'Edycja';
//   this.dictionaryService
//     .getDictionaryValue(id)
//     .subscribe((res: HttpResponse<ITest>) => this.onSuccess(res.body), (res: HttpResponse<any>) => this.onError(res.body));
// }
//
// private onError(error) {
//   this.messageService.add({ severity: 'error', summary: error.error });
// }
//
// private onSuccess(data: IDictionary) {
//   if (data) {
//     this.dictionary = data;
//     if (data.start && data.end) {
//       this.rangeDates = [];
//       this.rangeDates.push(data.start);
//       this.rangeDates.push(data.end);
//     }
//     this.display = true;
//   }
// }
//
// public saveDictionary() {
//   if (this.dictionary.id) {
//     this.dictionaryService
//       .saveDictionaryEntry(this.dictionary)
//       .subscribe((res: number) => this.onSuccessDictionary(res), (res: HttpResponse<number>) => this.onError(res.body));
//   } else {
//     this.dictionaryService
//       .createDictionaryEntry(this.dictionary)
//       .subscribe((res: number) => this.onSuccessDictionary(res), (res: HttpResponse<number>) => this.onError(res.body));
//   }
// }
//
// private onSuccessDictionary(data: any) {
//   if (data) {
//     if (data.uniqueNames === false) {
//       this.messageService.add({ severity: 'error', summary: 'Podana wartość już istnieje!' });
//     } else {
//       this.dictionary.id = data;
//       this.display = false;
//       this.saved = true;
//     }
//   }
// }
//
// mapRangeToModel() {
//   if (this.rangeDates && this.rangeDates.length === 2) {
//     this.dictionary.start = this.rangeDates[0];
//     this.dictionary.end = this.rangeDates[1];
//   }
// }
//
//
// }


