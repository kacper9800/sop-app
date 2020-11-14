import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TranslateService} from '@ngx-translate/core';
import {CollegeService} from '../../../_services/organization-structure/college.service';
import {ClrLoadingState} from '@clr/angular';
import {College, ICollege} from '../../../_model/organization-structure/college.model';
import {HttpResponse} from '@angular/common/http';
import {TokenService} from '../../../_helpers/token.service';
import {ActivationKeyService} from '../../../_services/activation-key.service';
import {ActivationKey} from '../../../_model/activation-key.model';

@Component({
  selector: 'app-add-edit-dialog-colleges',
  templateUrl: './add-edit-dialog-colleges.component.html',
  styleUrls: ['./add-edit-dialog-colleges.component.css']
})
export class AddEditDialogCollegesComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public college: College;
  public availableColleges: College[];
  public blockUI: boolean;
  public displayDialog: any;
  public collegeForm: FormGroup;
  private activationKeyToSave: ActivationKey;

  public dialogTitle: string;
  public validateBtnState: any;

  constructor(private collegeService: CollegeService,
              private formBuilder: FormBuilder,
              private tokenService: TokenService,
              private translateService: TranslateService,
              private activationKeyService: ActivationKeyService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadAvailableColleges();
    this.prepareForm(this.college);
  }

  private loadAvailableColleges() {
    this.collegeService.getAllAvailableColleges().subscribe(
      (res: HttpResponse<ICollege[]>) => this.onSuccessLoadAvailableColleges(res),
      (error => this.onErrorLoadAvailableColleges(error))
    );
  }

  private onSuccessLoadAvailableColleges(body) {
    this.availableColleges = [];
    body.forEach(college =>
      this.availableColleges.push(college)
    );
    this.blockUI = false;
  }

  private onErrorLoadAvailableColleges(error: any) {
    this.blockUI = false;
  }

  public showNewCollegeDialog() {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('colleges.dialog.titleNew');
    this.displayDialog = true;
    this.prepareForm(null);
  }

  public showEditCollegeDialog(id: number) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('colleges.dialog.titleEdit');
    this.collegeService.getCollegeForId(id).subscribe(
      (res) => this.onSuccessLoadCollege(res),
      (res) => this.onErrorLoadCollege(res)
    );
  }

  private onSuccessLoadCollege(res) {
    this.college = res;
    this.displayDialog = true;
    this.blockUI = false;
    this.prepareForm(res);
  }

  private onErrorLoadCollege(res: any) {
    console.log(res);
    this.blockUI = false;
  }

  private prepareForm(college: College): void {
    console.log(college);
    this.collegeForm = this.formBuilder.group({
      collegeId: new FormControl({value: college ? college.id : null, disabled: false}),
      collegeName: new FormControl({value: college ? college.name : null, disabled: false}, Validators.required),
      voivodeshipId: new FormControl(),
      voivodeshipName: new FormControl(),
      districtId: new FormControl(),
      districtName: new FormControl(),
      communityId: new FormControl(),
      communityName: new FormControl(),
      // city: new FormControl({value: college ? college.cityName : null, disabled: false}, Validators.required),
      active: new FormControl({value: college ? college.active : null, disabled: false}),
      deleted: new FormControl({value: college ? college.removed : null, disabled: false})
    });
  }

  public onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;
    this.collectFormData();
    this.collegeService.activateCollege(this.activationKeyToSave).subscribe(
      (res: number) => this.onSuccessActivateCollege(res),
      (error) => this.onErrorActivateCollege(error)
    );
  }

  private collectFormData() {
    this.activationKeyToSave = new ActivationKey();
    this.activationKeyToSave.collegeId = this.collegeForm.get('collegeId').value;
    this.activationKeyToSave.value = this.collegeForm.get('token').value;
    this.activationKeyToSave.numberOfUses = 1;
    this.activationKeyToSave.active = true;
  }

  private onSuccessActivateCollege(res) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.SUCCESS;
    this.displayDialog = false;
  }

  private onErrorActivateCollege(error: any) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.ERROR;
    this.displayDialog = false;
  }
}
