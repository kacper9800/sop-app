import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { TranslateService } from '@ngx-translate/core';
import { MessageService } from 'primeng';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {

  @Input()
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
  isLoggedIn: Boolean = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  loginButton: any;
  blockUI: any;


  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder,
              private translateService: TranslateService) {
    this.prepareForm();
    this.setLanguage()
  }

  public show() {
    this.displayLoginDialog = true;
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  private setLanguage() {
    this.headerText = this.translateService.instant('login.header');
    this.loginButton = this.translateService.instant('login.loginButton');
  }

  prepareForm() {
    this.loginForm = this.formBuilder.group({
      username: new FormControl({value: null, disabled: false}),
      password: new FormControl({value: null, disabled: false}),
    });
  }

  onSubmit() {
   let credentials = [];
   credentials.push(this.loginForm.get('username').value);
   credentials.push(this.loginForm.get('password').value);
   console.log(credentials);
    this.authService.login(credentials).subscribe(
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

  onHide() {
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


