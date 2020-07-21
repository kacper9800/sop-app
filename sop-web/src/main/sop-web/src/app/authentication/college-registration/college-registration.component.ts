import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {College, ICollege} from '../../_model/college.model';
import {CollegeService} from "../../_services/structure/college.service";

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

  constructor(private formBuilder: FormBuilder,
              private collegeService: CollegeService) {
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
}
