import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../_services/auth/auth.service';
import {Router} from '@angular/router';
import {Dictionary} from '../../_model/dictionary.model';
import {DictionariesService} from '../../_services/dictionaries.service';
import {TranslateService} from '@ngx-translate/core';
import {UserRegistration} from '../../_model/user-registration.model';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [MessageService]
})
export class RegistrationComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public sexTypes: Dictionary[] = [];
  public academicDegrees: Dictionary[] = [];
  public registrationForm: FormGroup;
  public displayRegistrationDialog = false;
  public isSuccessful = false;
  public isSignUpFailed = false;
  public errorMessage = '';
  public blockUI: any;

  private userToRegister: UserRegistration;
  private displayAlert: boolean;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private translateService: TranslateService,
              private dictionaryService: DictionariesService,
              private messageService: MessageService) {
  }

  public show(): void {
    this.displayRegistrationDialog = true;
  }

  public ngOnInit(): void {
    this.createForm();
    this.loadSexTypes();
    this.loadAcademicDegrees();
  }

  public createForm() {
    this.registrationForm = this.formBuilder.group({
      token: new FormControl({value: null, disabled: false}, Validators.required),
      firstName: new FormControl({value: null, disabled: false}, Validators.required),
      lastName: new FormControl({value: null, disabled: false}, Validators.required),
      birthDate: new FormControl({value: null, disabled: false}, Validators.required),
      email: new FormControl({value: null, disabled: false}, Validators.required),
      sex: new FormControl({value: null, disabled: false}, Validators.required),
      password: new FormControl({value: null, disabled: false}, Validators.required),
      repeatedPassword: new FormControl({value: null, disabled: false}, Validators.required),
      academicDegree: new FormControl({value: null, disabled: false}),
      phone: new FormControl({value: null, disabled: false}),
    });
  }

  private loadSexTypes() {
    this.dictionaryService.getSexTypes().subscribe(
      (res) => this.onSuccessLoadSexTypes(res),
      (error) => this.onErrorLoadSexTypes(error)
    );
  }

  private onSuccessLoadSexTypes(res) {
    this.sexTypes = [];
    this.sexTypes.push({value: null, label: 'chooseSexType'});
    res.forEach(sexType => this.sexTypes.push({label: sexType.value, value: sexType.value}));
  }

  private onErrorLoadSexTypes(error: any) {
    this.sexTypes = [];
  }

  private loadAcademicDegrees() {
    this.dictionaryService.getAcademicDegrees().subscribe(
      (res) => this.onSuccessLoadAcademicDegrees(res),
      (error) => this.onErrorLoadAcademicDegrees(error)
    );
  }

  private onSuccessLoadAcademicDegrees(res) {
    this.academicDegrees = [];
    this.academicDegrees.push({value: null, label: 'chooseAcademicDegree'});
    res.forEach(academicDegree => this.academicDegrees.push({
      label: academicDegree.value,
      value: academicDegree.value
    }));
  }

  private onErrorLoadAcademicDegrees(error: any) {
    this.academicDegrees = [];
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
        this.blockUI = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Błąd!',
          detail: 'Błędny kod aktywacyjny',
          key: 'registrationToast'
        });
      }
    );
  }

  private collectUserData(): void {
    this.userToRegister = new UserRegistration();
    this.userToRegister.token = this.registrationForm.get('token').value;
    this.userToRegister.firstName = this.registrationForm.get('firstName').value;
    this.userToRegister.lastName = this.registrationForm.get('lastName').value;
    this.userToRegister.birthDate = this.registrationForm.get('birthDate').value;
    this.userToRegister.email = this.registrationForm.get('email').value;
    this.userToRegister.sex = this.registrationForm.get('sex').value;
    this.userToRegister.password = this.registrationForm.get('password').value;
    // this.userToRegister.role = ['ROLE_USER', 'ROLE_STUDENT'];
  }

  public isPasswordCorrect(): boolean {
    const password = this.registrationForm.get('password').value;
    const repeatedPassword = this.registrationForm.get('repeatedPassword').value;
    if (password == null || repeatedPassword == null) {
      return false;
    }
    if (password === repeatedPassword) {
      return true;
    }
  }
}
