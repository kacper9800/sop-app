import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../_services/auth/auth.service';
import {Router} from '@angular/router';
import {MessageService} from 'primeng';
import {DropdownItem} from '../../_model/dropdown-item.model';
import {CollegeService} from '../../_services/organization-structure/college.service';
import {ICollege} from '../../_model/organization-structure/college.model';
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-college-registration',
  templateUrl: './college-registration.component.html',
  styleUrls: ['./college-registration.component.css'],
  providers: [MessageService]
})
export class CollegeRegistrationComponent implements OnInit {
  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public displayCollegeRegistrationDialog = false;
  public collegeRegistrationForm: FormGroup;
  public colleges: DropdownItem[] = [];
  private blockUI: boolean;
  private collegeToRegister: any;
  private isSuccessful: boolean;
  private isSignUpFailed: boolean;
  private displayRegistrationDialog: boolean;
  private displayAlert: boolean;
  private errorMessage: any;

  constructor(private formBuilder: FormBuilder,
              private collegeService: CollegeService,
              private authService: AuthService,
              private router: Router,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.collegeRegistrationForm = this.formBuilder.group({
      token: new FormControl({value: null, disabled: false}, Validators.compose([Validators.minLength(10), Validators.required])),
      collegeId: new FormControl({value: null, disabled: false}, Validators.required),
      email: new FormControl({value: null, disabled: false}, Validators.required),
      password: new FormControl({value: null, disabled: false}, Validators.required)
    });
    this.loadAvailableColleges();
  }

  public show(): void {
    this.displayCollegeRegistrationDialog = true;
  }


  private loadAvailableColleges(): void {
    this.collegeService.getAllAvailableColleges().subscribe(
      (res: HttpResponse<ICollege[]>) => this.onSuccessLoadAvailableColleges(res.body),
      () => this.onErrorLoadAvailableColleges()
    );
  }

  private onSuccessLoadAvailableColleges(res: ICollege[]): void {
    res.forEach(college => {
      const collegeView = {label: null, value: null};
      collegeView.label = college.name;
      collegeView.value = college.id;
      this.colleges.push(collegeView);
    });
  }

  private onErrorLoadAvailableColleges(): void {
  console.log('error');
  }

  public onSubmit(): void {
    this.blockUI = true;
    this.collectCollegeData();
    this.authService.registerCollege(this.collegeToRegister).subscribe(
      data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.blockUI = false;
        this.displayRegistrationDialog = false;
        this.router.navigate(['registered-successfully']);

      },
      err => {
        this.displayAlert = true;
        this.errorMessage = err.error;
        this.isSignUpFailed = true;
        console.log(err.error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error!',
          detail: err.error.messagee
        });
      }
    );
  }

  private collectCollegeData(): void {
    this.collegeToRegister = {};
    this.collegeToRegister.token = this.collegeRegistrationForm.get('token').value;
    this.collegeToRegister.collegeId = this.collegeRegistrationForm.get('collegeId').value;
    this.collegeToRegister.email = this.collegeRegistrationForm.get('email').value;
    this.collegeToRegister.password = this.collegeRegistrationForm.get('password').value;
    console.log(this.collegeToRegister);
  }
}
