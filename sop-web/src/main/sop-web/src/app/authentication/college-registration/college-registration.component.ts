import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {College, ICollege} from '../../_model/college.model';
import {CollegeService} from "../../_services/structure/college.service";
import {AuthService} from "../../_services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-college-registration',
  templateUrl: './college-registration.component.html',
  styleUrls: ['./college-registration.component.css']
})
export class CollegeRegistrationComponent implements OnInit {
  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public displayCollegeRegistrationDialog = false;
  public collegeRegistrationForm: FormGroup;
  public colleges: College[];
  private blockUI: boolean;
  private collegeToRegister: College;
  private isSuccessful: boolean;
  private isSignUpFailed: boolean;
  private displayRegistrationDialog: boolean;
  private displayAlert: boolean;
  private errorMessage: any;

  constructor(private formBuilder: FormBuilder,
              private collegeService: CollegeService,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.collegeRegistrationForm = this.formBuilder.group({
      collegeId: new FormControl({value: null, disabled: false}, Validators.required),
      mail: new FormControl({value: null, disabled: false}, Validators.required),
      password: new FormControl({value: null, disabled: false}, Validators.required)
    });

    this.loadAvailableColleges();
  }

  public show(): void {
    this.displayCollegeRegistrationDialog = true;
  }

  public onSubmit(): void {
    this.blockUI = true;
    this.collectUserData();
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
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );

  }

  private loadAvailableColleges(): void {
    this.collegeService.getAllAvailableColleges().subscribe(
      (res: ICollege[]) => this.onSuccessLoadAvailableColleges(res),
      () => this.onErrorLoadAvailableColleges()
    );
  }

  private onSuccessLoadAvailableColleges(res: ICollege[]): void {

  }

  private onErrorLoadAvailableColleges(): void {

  }

  private collectUserData(): void {

  }
}
